package io.damo.web.user;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import io.damo.common.annotation.Login;
import io.damo.common.annotation.LoginUser;
import io.damo.common.aspect.OpLogger;
import io.damo.common.exception.RRException;
import io.damo.common.message.MessageUtil;
import io.damo.common.response.CommonResponse;
import io.damo.common.utils.AESUtil;
import io.damo.common.utils.RandomUtil;
import io.damo.common.utils.StringUtil;
import io.damo.common.validator.ValidatorUtils;
import io.damo.sms.entity.SmsCaptchaEntity;
import io.damo.sms.service.SmsCaptchaService;
import io.damo.user.entity.SysNoticeEntity;
import io.damo.user.entity.TokenEntity;
import io.damo.user.entity.UserAccountInfoEntity;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.*;
import io.damo.user.service.SysNoticeService;
import io.damo.user.service.TokenService;
import io.damo.user.service.UserAccountInfoService;
import io.damo.user.service.UserBasicInfoService;
import io.damo.user.vo.QrCodeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用户基本信息表
 *
 * @author ives
 * @date 2018-06-11 14:46:53
 */
@RestController
@RequestMapping("/api/userBasicInfoController")
@Api(tags = "用户基本信息表")
public class UserBasicInfoController {

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @Autowired
    private UserAccountInfoService userAccountInfoService;

    @Autowired
    private SysNoticeService sysNoticeService;

    @Autowired
    private TokenService tokenService;


    private static Logger logger = LoggerFactory.getLogger(UserBasicInfoController.class);

    /**
     * 注册
     *
     * @param form 注册form表单
     * @return
     */
    @PostMapping("register")
    @ApiOperation("注册")
    public CommonResponse<String> register(@RequestBody RegisterForm form) {
        logger.info("注册请求参数：{}", form.toString());
        //表单校验
        ValidatorUtils.validateEntity(form);
        CommonResponse<String> commonResponse = validate(form);
        if (StringUtils.checkValNotNull(commonResponse)) {
            return CommonResponse.fail(commonResponse.getMsg());
        }
        //添加用户 插入用户相关信息
        boolean flag = userBasicInfoService.saveUserMessage(form);
        if (flag) {
            return CommonResponse.success(MessageUtil.getMessage("register.success"));
        }
        return CommonResponse.fail(MessageUtil.getMessage("register.fail"));
    }

    /**
     * 登录
     *
     * @param loginForm 登录表单
     * @return
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public CommonResponse<Object> loginController(@RequestBody LoginForm loginForm) {
        logger.error("登录请求参数：{}", loginForm.toString());
        SysNoticeEntity sysNoticeEntity = sysNoticeService.selectOne(new EntityWrapper<>());
        boolean flag = false;
        if (!Objects.isNull(sysNoticeEntity) && sysNoticeEntity.getStatus() == 0) {
            logger.error("广告过滤");
            if (Objects.isNull(sysNoticeEntity.getPhone())) {
                return CommonResponse.fail(sysNoticeEntity.getContent());
            }
            String phones[] = sysNoticeEntity.getPhone().split(",");
            for (int i = 0; i < phones.length; i++) {
                if (phones[i].equals(loginForm.getMobile())) {
                    flag = true;
                }
            }
            if (!flag) {
                logger.error("广告过滤");
                return CommonResponse.fail(sysNoticeEntity.getContent());
            }
        }
        //表单校验
        ValidatorUtils.validateEntity(loginForm);
        EntityWrapper<UserBasicInfoEntity> userWrapper = new EntityWrapper<>();
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userWrapper.where("phone={0}", loginForm.getMobile()));
        if (null != userBasicInfoEntity && userBasicInfoEntity.getState() == 1) {
            logger.error("该账号被禁用！");
            return CommonResponse.fail(MessageUtil.getMessage("Disable.account"));
        }
        //获取token
        Map<String, Object> map = userBasicInfoService.login(loginForm);
        return CommonResponse.success(MessageUtil.getMessage("login.success"), map);
    }

    @Login
    @PostMapping("userInfo")
    @ApiOperation("当前登录人信息")
    public CommonResponse<UserBasicInfoEntity> userInfo(@ApiIgnore @LoginUser UserBasicInfoEntity userBasicInfoEntity) {
        return CommonResponse.success(userBasicInfoEntity);
    }


    /**
     * 忘记(修改)密码
     *
     * @param form 修改密码表单
     * @return
     */
    @PutMapping("updatePassword")
    @ApiOperation("修改密码")
    public CommonResponse<String> updatePassword(@RequestBody UpdatePasswordForm form) {
        logger.error("忘记(修改)密码请求参数:{}", form.toString());
        if (null == form.getMobile()) {
            logger.error("手机号为空");
            return CommonResponse.fail(MessageUtil.getMessage("Cell.phone.number.can.not.be.empty"));
        }
        //获取要修改密码的用户信息
        EntityWrapper<UserBasicInfoEntity> userWrapper = new EntityWrapper<>();
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userWrapper.where("phone={0}", form.getMobile()));
        if (null == userBasicInfoEntity) {
            logger.error("用户信息为空");
            return CommonResponse.fail(MessageUtil.getMessage("user.does.not.exist"));
        }
        //校验手机号验证码
        EntityWrapper<SmsCaptchaEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("mobile={0}", form.getMobile());
        entityWrapper.and("code={0}", form.getCode());
        SmsCaptchaEntity smsCaptcha = smsCaptchaService.selectOne(entityWrapper);
        if (null == smsCaptcha) {
            logger.error("短信验证码不存在");
            return CommonResponse.fail(MessageUtil.getMessage("Verification.code.error"));
        }
        try {
            //修改密码
            userBasicInfoEntity.setPassword(DigestUtils.sha256Hex(form.getPassword()));
            userBasicInfoEntity.setAccountKey(DigestUtils.sha256Hex(userBasicInfoEntity.getPhone() + RandomUtil.ValletUrl()));
            userBasicInfoEntity.setErrorNumber(0);
            userBasicInfoService.updateById(userBasicInfoEntity);
            logger.error("修改密码成功！开始修改tokne....");
            EntityWrapper<TokenEntity> tokenEntityWrapper = new EntityWrapper<>();
            tokenEntityWrapper.where("user_id={0}", userBasicInfoEntity.getId());
            tokenService.delete(tokenEntityWrapper);
            logger.error("修改密码完成！");
        } catch (Exception e) {
            logger.error("修改密码失败:", e);
            throw new RRException(MessageUtil.getMessage("update.fail"));
        }
        return CommonResponse.success(MessageUtil.getMessage("update.success"));
    }

    @Login
    @PutMapping("updatePayPassword")
    @ApiOperation("修改支付密码")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "password", value = "支付密码", required = true)})
    public CommonResponse<String> updatePayPassword(@ApiIgnore @LoginUser UserBasicInfoEntity userEntity, @RequestBody UpdatePasswordForm updatePasswordForm) {
        logger.info("设置(修改)支付密码请求参数：用户id：{}", userEntity.getId());
        try {
            //修改密码
            userEntity.setPayPassword(DigestUtils.sha256Hex(updatePasswordForm.getPassword()));
            userBasicInfoService.updateById(userEntity);
        } catch (Exception e) {
            logger.info("修改支付密码失败:", e);
            return CommonResponse.fail(MessageUtil.getMessage("update.fail"));
        }
        return CommonResponse.success(MessageUtil.getMessage("update.success"));
    }

    @Login
    @PostMapping("checkPayPassword")
    @ApiOperation("校验支付密码")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "payPassword", value = "支付密码", required = true)})
    public CommonResponse<String> checkPayPassword(@ApiIgnore @LoginUser UserBasicInfoEntity userEntity, @RequestBody UpdatePaypasswordForm updatePaypasswordForm) {
        logger.info("校验支付密码请求数据：用户id：{}", userEntity.getId());
        //充值、提现、兑换判断支付密码是否正确
        String password = org.apache.commons.codec.digest.DigestUtils.sha256Hex(updatePaypasswordForm.getPassword());
        if (!password.equals(userEntity.getPayPassword())) {
            return CommonResponse.fail(MessageUtil.getMessage("pay.password.errormsg"));
        }
        return CommonResponse.success(MessageUtil.getMessage("pay.failed"));
    }


    /**
     * 验证码、昵称唯一、手机唯一性校验、免费注册会员不能成为推荐人
     *
     * @param form
     * @return
     */
    public CommonResponse<String> validate(RegisterForm form) {
        //验证是否失效（30分钟）
        //查询手机验证码
        int count = smsCaptchaService.checkSmsCode(form.getMobile(), form.getCode());
        if (count == 0) {
            return CommonResponse.fail(MessageUtil.getMessage("Verification.code.invalid"));
        }
        //验证短信验证码
        if (!smsCaptchaService.querySmsCaptchaIsPass(form.getMobile(), form.getCode())) {
            return CommonResponse.fail(MessageUtil.getMessage("Verification.code.error"));
        }
        if (form.getMobile().equals(form.getRecommend())) {
            return CommonResponse.fail(MessageUtil.getMessage("The.user.itself.cannot.be.its.own.recommendation"));
        }
        //根据推荐人手机号或者钱包地址查询推荐人信息
        EntityWrapper<UserBasicInfoEntity> userWrapper = new EntityWrapper<>();
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userWrapper.where("phone={0} or vallet_url={0}", form.getRecommend()));
        if (null == userBasicInfoEntity) {
            return CommonResponse.fail(MessageUtil.getMessage("The.referee.does.not.exist"));
        }

        if (userBasicInfoEntity.getState() == 1) {
            return CommonResponse.fail("被禁用的账号不能成为推荐人！");
        }

        form.setRecommend(userBasicInfoEntity.getPhone());

        //验证昵称是否重复
        EntityWrapper<UserBasicInfoEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.where("nick_name={0}", form.getUsername());
        UserBasicInfoEntity entity = userBasicInfoService.selectOne(entityWrapper);
        if (StringUtils.checkValNotNull(entity)) {
            return CommonResponse.fail(MessageUtil.getMessage("User.nickname.repetition"));
        }
        //查询手机号是否存在 验证手机号是否重复
        EntityWrapper<UserBasicInfoEntity> wrapper = new EntityWrapper<>();
        wrapper.where("phone={0}", form.getMobile());
        entity = userBasicInfoService.selectOne(wrapper);
        if (StringUtils.checkValNotNull(entity)) {
            return CommonResponse.fail(MessageUtil.getMessage("The.phone.number.has.been.registered"));
        }
        return null;
    }


    @PostMapping("/getQrCode")
    @ApiOperation(value = "根据二维码获取用户信息")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "qrCode", value = "二维码或手机号", required = true)})
    @OpLogger
    public CommonResponse<QrCodeVo> getQrCode(String qrCode) {
        logger.info("请求参数：qrCode:{}", qrCode);
        UserBasicInfoEntity userBasicInfoEntity = selectByQrCode(qrCode);
        if (userBasicInfoEntity == null) {
            return CommonResponse.fail(MessageUtil.getMessage("no.query.to.record"));
        }
        QrCodeVo qrCodeVo = new QrCodeVo();
        qrCodeVo.setPhone(userBasicInfoEntity.getPhone());
        qrCodeVo.setValletUrl(userBasicInfoEntity.getValletUrl());
        return CommonResponse.success(qrCodeVo);
    }


    @GetMapping("getUserQrCode")
    @ApiOperation("获取当前用户的二维码")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "int", name = "type", value = "类型(0.获取二维码 1.重置二维码)", required = true)})
    @OpLogger
    public CommonResponse getQrCode(@ApiIgnore @LoginUser UserBasicInfoEntity sysUser, Integer type) {
        logger.info("请求参数：type:{}", type);
        UserBasicInfoEntity userBasicInfoEntity = selectByPhone(sysUser.getPhone());
        //生成二维码
        if (type == 1 || Objects.isNull(userBasicInfoEntity.getQrCode())) {
            String qrCode = UUID.randomUUID().toString();
            userBasicInfoEntity.setQrCode(qrCode);
            updateQrCode(userBasicInfoEntity);
            return CommonResponse.success("success", qrCode);
        }
        return CommonResponse.success("success", userBasicInfoEntity.getQrCode());
    }

    /**
     * 根据手机号查询二维码
     *
     * @return
     */
    public UserBasicInfoEntity selectByQrCode(String qrCode) {
        EntityWrapper<UserBasicInfoEntity> userInfoWrapper = new EntityWrapper<>();
        userInfoWrapper.where("qr_code = {0} or phone = {0}", qrCode);
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userInfoWrapper);
        return userBasicInfoEntity;
    }


    /**
     * 根据手机号查询二维码
     *
     * @param phone
     * @return
     */
    public UserBasicInfoEntity selectByPhone(String phone) {
        EntityWrapper<UserBasicInfoEntity> userInfoWrapper = new EntityWrapper<>();
        userInfoWrapper.where("phone = {0}", phone);
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userInfoWrapper);
        return userBasicInfoEntity;
    }

    /**
     * 更新二维码
     *
     * @param userBasicInfoEntity
     * @return
     */
    public boolean updateQrCode(UserBasicInfoEntity userBasicInfoEntity) {
        EntityWrapper<UserBasicInfoEntity> userInfoWrapper = new EntityWrapper<>();
        userInfoWrapper.where("phone = {0}", userBasicInfoEntity.getPhone());
        return userBasicInfoService.update(userBasicInfoEntity, userInfoWrapper);
    }

    /*
    @GetMapping("/showBanner")
    @ApiOperation("banner展示")
    @OpLogger
    public CommonResponse<List<BannerManagerEntity>> showBanner() {
        EntityWrapper<BannerManagerEntity> bannerManagerEntityWrapper = new EntityWrapper<>();
        List<BannerManagerEntity> bannerManagerEntityList = bannerManagerService.selectList(bannerManagerEntityWrapper);
        for (BannerManagerEntity bannerManagerEntity : bannerManagerEntityList) {
            if (!bannerManagerEntity.getSrc().startsWith("http")) {
                if (StringUtil.isNotEmpty(bannerManagerEntity.getSrc())) {
                    bannerManagerEntity.setSrc("http://" + bannerManagerEntity.getSrc());
                } else {
                    bannerManagerEntity.setSrc("");
                }
            }
        }
        return CommonResponse.success(bannerManagerEntityList);
    }

    @Login
    @GetMapping("/getNotice")
    @ApiOperation("公告展示")
    @OpLogger
    public CommonResponse getNotice(@ApiIgnore @LoginUser UserBasicInfoEntity user) {
        EntityWrapper<NoticeManagerEntity> bannerManagerEntityWrapper = new EntityWrapper<>();
        bannerManagerEntityWrapper.where("display={0}", 0).orderBy("create_time",false);
        List<NoticeManagerEntity> noticeManagerEntityList = noticeManagerService.selectList(bannerManagerEntityWrapper);

        Map map = new HashMap();
        // 修改查看公告用户状态
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectByUserId(user.getId());
        map.put("noticeMark",userBasicInfoEntity.getNoticeMark());
        if(userBasicInfoEntity.getNoticeMark() == 0){
            userBasicInfoEntity.setNoticeMark(1);
            userBasicInfoService.updateById(userBasicInfoEntity);
        }
        map.put("noticeList",CollectionUtils.isNotEmpty(noticeManagerEntityList) ? noticeManagerEntityList : "");
        return CommonResponse.success(map);
    }
    */

    @GetMapping("/userInfoByid")
    @ApiOperation("根据id查询用户信息")
    @OpLogger
    public CommonResponse<UserBasicInfoEntity> userInfoByid(@RequestParam("userId") String userId) {
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectById(userId);
        if (null == userBasicInfoEntity) {
            return CommonResponse.fail(MessageUtil.getMessage("no.data"));
        }
        return CommonResponse.success(userBasicInfoEntity);
    }

    /*
    @Login
    @GetMapping("/getInviteFriendQrCode")
    @ApiOperation("获取邀请好友二维码")
    @OpLogger
    public CommonResponse<InviteFriendsBgEntity> getInviteFriendQrCode(@ApiIgnore @LoginUser UserBasicInfoEntity userBasicInfoEntity) {
        // 获取图片
        List<InviteFriendsBgEntity> list = inviteFriendsBgService.selectList(new EntityWrapper());
        if(CollectionUtils.isEmpty(list)){
            return CommonResponse.fail(MessageUtil.getMessage("no.data"));
        }
        List urlList=new ArrayList();
        for(InviteFriendsBgEntity entity:list){
            if(!Objects.isNull(entity.getUrl())){
                urlList.add(entity.getUrl());
            }
        }
        InviteFriendsBgEntity inviteFriendsBgEntity = list.get(0);
        inviteFriendsBgEntity.setQrCode(inviteFriendsBgEntity.getQrCode()+userBasicInfoEntity.getValletUrl()+"+");
        inviteFriendsBgEntity.setUrlList(urlList);
        return CommonResponse.success(inviteFriendsBgEntity);
    }
    */

    @Login
    @PostMapping("switchAccount")
    @ApiOperation("切换账号")
    @OpLogger
    public CommonResponse<Map<String, Object>> switchAccount(@ApiIgnore @LoginUser UserBasicInfoEntity userBasicInfoEntity, @RequestBody SwitchAccountForm switchAccountForm) {
        logger.info("切换账号：{}", switchAccountForm.getAccountKey());
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != userBasicInfoEntity) {
            //查询切换上来的accountKey
            UserBasicInfoEntity userBasicInfoEntity1 = userBasicInfoService.selectOne(new EntityWrapper<UserBasicInfoEntity>().where("account_key={0}", switchAccountForm.getAccountKey()));
            if (null != userBasicInfoEntity1) {
                //设置当前账号accountKey
                userBasicInfoEntity.setAccountKey(DigestUtils.sha256Hex(userBasicInfoEntity.getPhone() + RandomUtil.ValletUrl()));
                //获取登录token
                TokenEntity tokenEntity = tokenService.createToken(userBasicInfoEntity1.getId(), 1L);
                map.put("token", tokenEntity.getToken());
                map.put("expire", tokenEntity.getExpireTime().getTime() - System.currentTimeMillis());
                //返回切换下去账号的accountKey
                map.put("accountKey", AESUtil.aesEncryptString(userBasicInfoEntity.getAccountKey()));
                map.put("accountKeyPhone", userBasicInfoEntity.getPhone());
                userBasicInfoEntity1.setAccountKey(DigestUtils.sha256Hex(userBasicInfoEntity1.getPhone() + RandomUtil.ValletUrl()));
                map.put("loginAccountKey", AESUtil.aesEncryptString(userBasicInfoEntity1.getAccountKey()));
                map.put("loginAccountKeyPhone", userBasicInfoEntity1.getPhone());
                //更新当前账号accountKey
                userBasicInfoService.updateById(userBasicInfoEntity);
                userBasicInfoService.updateById(userBasicInfoEntity1);
            } else {
                throw new RRException("为了账号安全，需要切换上来的账号请重新登录！", 402);
            }
        }
        return CommonResponse.success(map);
    }

}
