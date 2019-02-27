package io.damo.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.common.exception.RRException;
import io.damo.common.message.MessageUtil;
import io.damo.common.utils.AESUtil;
import io.damo.common.utils.DateUtils;
import io.damo.common.utils.RandomUtil;
import io.damo.common.validator.Assert;
import io.damo.sms.service.SmsCaptchaService;
import io.damo.user.dao.UserBasicInfoDao;
import io.damo.user.entity.TokenEntity;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.LoginForm;
import io.damo.user.form.RegisterForm;
import io.damo.user.form.UpdatePasswordForm;
import io.damo.user.service.TokenService;
import io.damo.user.service.UserBasicInfoService;
import io.damo.user.service.UserWalletInfoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

@Service("userBasicInfoService")
@EnableTransactionManagement
public class UserBasicInfoServiceImpl extends ServiceImpl<UserBasicInfoDao, UserBasicInfoEntity> implements UserBasicInfoService {

    @Resource
    private UserBasicInfoDao userBasicInfoDao;

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @Autowired
    private UserWalletInfoService userWalletInfoService;

    private static Logger logger = LoggerFactory.getLogger(UserBasicInfoServiceImpl.class);

    /**
     * 保存用户信息 账户信息  历史记录
     *
     * @param form 注册表单
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUserMessage(RegisterForm form) {
        UserBasicInfoEntity userBasicInfoEntity = new UserBasicInfoEntity();
        userBasicInfoEntity.setId(RandomUtil.generatorUserId());
        Assert.assertCase(Pattern.matches(".*(客服|官方|管理员|售前|售后|service|offical|admin|Pre-sale|After-sale).*", form.getUsername()), "昵称存在敏感字符");
        userBasicInfoEntity.setNickName(form.getUsername());
        userBasicInfoEntity.setPhone(form.getMobile());
        userBasicInfoEntity.setCode(form.getPhoneCode().replace("+", ""));
        userBasicInfoEntity.setState(0);
        userBasicInfoEntity.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        userBasicInfoEntity.setRegisterTime(DateUtils.now());
        userBasicInfoEntity.setUpdateTime(DateUtils.now());
        userBasicInfoEntity.setCreateTime(DateUtils.now());
        userBasicInfoEntity.setAccountKey(DigestUtils.sha256Hex(form.getMobile() + RandomUtil.ValletUrl()));

        //添加到用户信息
        baseMapper.insert(userBasicInfoEntity);
        // 分配钱包地址
        this.userWalletInfoService.setWalletAddress(userBasicInfoEntity.getId());
    }

    /**
     * 根据用户ID传用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserBasicInfoEntity selectByUserId(String userId) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.where("id={0}", userId);
        return userBasicInfoService.selectOne(entityWrapper);
    }

    @Override
    public UserBasicInfoEntity selectByPhone(String phone) {
        EntityWrapper<UserBasicInfoEntity> entityEntity = new EntityWrapper<>();
        entityEntity.where("phone={0}", phone);
        UserBasicInfoEntity userEntity = userBasicInfoService.selectOne(entityEntity);
        return userEntity;
    }


    /**
     * 登录
     *
     * @param mobile 手机号
     * @return
     */
    public UserBasicInfoEntity queryByMobile(String mobile) {
        UserBasicInfoEntity entity = new UserBasicInfoEntity();
        entity.setPhone(mobile);
        return baseMapper.selectOne(entity);
    }

    /**
     * 登录验证
     *
     * @param form 登录表单
     * @return
     */
    @Override
    public Map<String, Object> login(LoginForm form) {
        UserBasicInfoEntity user = queryByMobile(form.getMobile());
        Assert.isNull(user, MessageUtil.getMessage("account.does.not.exist"));
        Assert.assertCase(Objects.nonNull(user.getErrorNumber()) && user.getErrorNumber() > 5, MessageUtil.getMessage("Error.Number"));

        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            if (!Objects.isNull(user.getErrorNumber())) {
                user.setErrorNumber(user.getErrorNumber() + 1);
                userBasicInfoService.updateById(user);
                throw new RRException(MessageUtil.getMessage("phone.password.error"));
            }
        }
        user.setAccountKey(DigestUtils.sha256Hex(form.getMobile() + RandomUtil.ValletUrl()));
        user.setErrorNumber(0);
        userBasicInfoService.updateById(user);

        // 登录分配钱包地址
        try {
            this.userWalletInfoService.setWalletAddress(user.getId());
        } catch (Exception e) {
            logger.error("分配钱包地址异常:{}", e.getMessage());
        }

        // 获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getId(), 1L);

        // token放到map保存
        Map<String, Object> map = new HashMap<>(4);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
        map.put("loginAccountKey", AESUtil.aesEncryptString(user.getAccountKey()));
        map.put("phone", form.getMobile());
        return map;
    }

    @Override
    public void updatePassword(UpdatePasswordForm form) {
        // 校验密码强度(8-20位数字和字母组合)
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,20}$";
        Assert.assertCase(!form.getPassword().matches(regex), MessageUtil.getMessage("password.check.regex"));

        // 获取用户信息
        EntityWrapper<UserBasicInfoEntity> userWrapper = new EntityWrapper<>();
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userWrapper.where("phone={0}", form.getMobile()));
        Assert.isNull(Objects.isNull(userBasicInfoEntity), MessageUtil.getMessage("user.does.not.exist"));

        // 错误次数超过10次，不能修改密码
        Assert.assertCase(userBasicInfoEntity.getErrorNumber() > 10, MessageUtil.getMessage("update.Number"));

        if (!smsCaptchaService.querySmsCaptchaIsPass(form.getMobile(), form.getCode())) {
            // 短信校验失败，错误次数加1
            UserBasicInfoEntity user = new UserBasicInfoEntity();
            user.setId(userBasicInfoEntity.getId());
            user.setErrorNumber(userBasicInfoEntity.getErrorNumber() + 1);
            userBasicInfoService.updateById(user);
            Assert.assertCase(true, MessageUtil.getMessage("Verification.code.error"));
        }
        //修改密码
        this.updatePasswordDetail(userBasicInfoEntity, form.getPassword());
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updatePasswordDetail(UserBasicInfoEntity userBasicInfoEntity, String password) {
        //修改密码
        UserBasicInfoEntity updateUserInfo = new UserBasicInfoEntity();
        updateUserInfo.setId(userBasicInfoEntity.getId());
        updateUserInfo.setPassword(DigestUtils.sha256Hex(password));
        updateUserInfo.setAccountKey(DigestUtils.sha256Hex(userBasicInfoEntity.getPhone() + RandomUtil.ValletUrl()));
        updateUserInfo.setErrorNumber(0);
        userBasicInfoService.updateById(updateUserInfo);
        EntityWrapper<TokenEntity> tokenEntityWrapper = new EntityWrapper<>();

        // 删除token
        tokenEntityWrapper.where("user_id={0}", userBasicInfoEntity.getId());
        tokenService.delete(tokenEntityWrapper);
    }

}
