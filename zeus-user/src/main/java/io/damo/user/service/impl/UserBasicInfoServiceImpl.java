package io.damo.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.damo.common.exception.RRException;
import io.damo.common.message.MessageUtil;
import io.damo.common.utils.AESUtil;
import io.damo.common.utils.DateUtils;
import io.damo.common.utils.RandomUtil;
import io.damo.common.validator.Assert;
import io.damo.user.dao.UserBasicInfoDao;
import io.damo.user.entity.TokenEntity;
import io.damo.user.entity.UserAccountInfoEntity;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.LoginForm;
import io.damo.user.form.RegisterForm;
import io.damo.user.service.TokenService;
import io.damo.user.service.UserAccountInfoService;
import io.damo.user.service.UserBasicInfoService;
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

@Service("userBasicInfoService")
@EnableTransactionManagement
public class UserBasicInfoServiceImpl extends ServiceImpl<UserBasicInfoDao, UserBasicInfoEntity> implements UserBasicInfoService {

    @Resource
    private UserBasicInfoDao userBasicInfoDao;

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    private UserAccountInfoService userAccountInfoService;

    @Autowired
    private TokenService tokenService;

    private static Logger logger = LoggerFactory.getLogger(UserBasicInfoServiceImpl.class);

    /**
     * 保存用户信息 账户信息  历史记录
     *
     * @param form 注册表单
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public boolean saveUserMessage(RegisterForm form) {
        UserBasicInfoEntity userBasicInfoEntity = new UserBasicInfoEntity();
        //id
        userBasicInfoEntity.setId(RandomUtil.generatorUserId());
        //用户名称
        userBasicInfoEntity.setNickName(form.getUsername());
        //用户手机号
        userBasicInfoEntity.setPhone(form.getMobile());
        //用户状态
        userBasicInfoEntity.setState(0);
        //用户密码
        userBasicInfoEntity.setPassword(DigestUtils.sha256Hex(form.getPassword()));
        //用户支付密码
        userBasicInfoEntity.setPayPassword(DigestUtils.sha256Hex(form.getPayPassword()));
        //推荐人
        userBasicInfoEntity.setRecommenderPhone(form.getRecommend());
        //间接推荐人
        userBasicInfoEntity.setIndirectRecommend(getIndirectRecommend(form.getRecommend()));
        //创建时间
        userBasicInfoEntity.setCreateTime(DateUtils.now());
        //地址
        userBasicInfoEntity.setValletUrl(RandomUtil.getCharAndNumr(30));
        userBasicInfoEntity.setUpdateTime(DateUtils.now());
        userBasicInfoEntity.setCreateTime(DateUtils.now());

        try {
            //添加到用户信息
            baseMapper.insert(userBasicInfoEntity);
            //添加记录到用户账户表和用户明细表
            saveAccountInfo(userBasicInfoEntity);
            //推荐会员注册后记录到用户历史记录表
            // TODO:?
        } catch (Exception e) {
            logger.info("saveUserMessage方法错误", e);
            return false;
        }
        return true;
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
        if(null==user){
            logger.error("账号不存在！");
        }
        Assert.isNull(user, MessageUtil.getMessage("account.does.not.exist"));
        if (!Objects.isNull(user.getErrorNumber())&&user.getErrorNumber() == 5) {
            throw new RRException(MessageUtil.getMessage("Error.Number"));
        }
        //密码错误
        if (!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))) {
            if(!Objects.isNull(user.getErrorNumber())){
                user.setErrorNumber(user.getErrorNumber() + 1);
                userBasicInfoService.updateById(user);
                throw new RRException(MessageUtil.getMessage("phone.password.error"));
            }
        }
        user.setAccountKey(DigestUtils.sha256Hex(form.getMobile()+RandomUtil.ValletUrl()));
        user.setErrorNumber(0);
        userBasicInfoService.updateById(user);
        logger.error("修改accountKey成功！");
        //获取登录token
        TokenEntity tokenEntity = tokenService.createToken(user.getId(), 1L);
        logger.error("获取token成功！");
        //token放到map保存
        Map<String, Object> map = new HashMap<>(4);
        map.put("token", tokenEntity.getToken());
        map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
        map.put("loginAccountKey",AESUtil.aesEncryptString(user.getAccountKey()));
        map.put("phone",form.getMobile());
        return map;
    }


    /**
     * 登录
     *
     * @param mobile 手机号
     * @return
     */
    private UserBasicInfoEntity queryByMobile(String mobile) {
        UserBasicInfoEntity entity = new UserBasicInfoEntity();
        entity.setPhone(mobile);
        return baseMapper.selectOne(entity);
    }

    /**
     * 添加记录到用户账户表
     *
     * @param userEntity
     */
    private void saveAccountInfo(UserBasicInfoEntity userEntity) {
        UserAccountInfoEntity infoEntity = new UserAccountInfoEntity();
        infoEntity.setUserId(userEntity.getId());
        infoEntity.setCreateTime(DateUtils.now());
        //用户账户表
        userAccountInfoService.insert(infoEntity);
    }

    /**
     * 获取推荐人的间接推荐人
     *
     * @param phone 推荐人电话
     * @return 推荐人以及间接推荐人
     */
    private String getIndirectRecommend(String phone) {
        EntityWrapper<UserBasicInfoEntity> entityEntity = new EntityWrapper<>();
        entityEntity.where("phone={0}", phone);
        UserBasicInfoEntity userEntity = userBasicInfoService.selectOne(entityEntity);
        String indirectRecommendPhone;
        //如果推荐人不存在  间接推荐人默认给0
        if (null != userEntity) {
            indirectRecommendPhone = phone + "," + userEntity.getIndirectRecommend();
        } else {
            indirectRecommendPhone = "0";
        }
        return indirectRecommendPhone;
    }
}
