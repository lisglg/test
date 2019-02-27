package io.damo.web.user;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import io.damo.common.annotation.Login;
import io.damo.common.annotation.LoginUser;
import io.damo.common.aspect.OpLogger;
import io.damo.common.message.MessageUtil;
import io.damo.common.response.CommonResponse;
import io.damo.common.utils.StringUtil;
import io.damo.common.utils.U;
import io.damo.common.validator.ValidatorUtils;
import io.damo.sms.service.SmsCaptchaService;
import io.damo.user.entity.BannerManagerEntity;
import io.damo.user.entity.NoticeManagerEntity;
import io.damo.user.entity.UserBasicInfoEntity;
import io.damo.user.form.LoginForm;
import io.damo.user.form.RegisterForm;
import io.damo.user.form.UpdatePasswordForm;
import io.damo.user.form.UpdatePaypasswordForm;
import io.damo.user.service.BannerManagerService;
import io.damo.user.service.NoticeManagerService;
import io.damo.user.service.UserBasicInfoService;
import io.damo.user.service.UserWalletInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 用户基本信息表
 *
 * @author ives
 * @date 2018-06-11 14:46:53
 */
@RestController
@RequestMapping("/api/userBasicInfoController")
@Api(tags = "用户基本信息表")
@Slf4j
public class UserBasicInfoController {

    @Autowired
    private UserBasicInfoService userBasicInfoService;

    @Autowired
    private SmsCaptchaService smsCaptchaService;


    @Autowired
    private BannerManagerService bannerManagerService;

    @Autowired
    private NoticeManagerService noticeManagerService;

    @Autowired
    private UserWalletInfoService userWalletInfoService;

    /**
     * 注册
     *
     * @param form 注册form表单
     * @return
     */
    @PostMapping("register")
    @ApiOperation("注册")
    public CommonResponse<String> register(@RequestBody RegisterForm form) {
        log.info("注册请求参数：{}", form.toString());
        //表单校验
        ValidatorUtils.validateEntity(form);
        CommonResponse<String> commonResponse = validate(form);
        if (StringUtils.checkValNotNull(commonResponse)) {
            return CommonResponse.fail(commonResponse.getMsg());
        }
        //添加用户 插入用户相关信息
        userBasicInfoService.saveUserMessage(form);
        return CommonResponse.success(MessageUtil.getMessage("register.success"));
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
        log.info("登录请求参数：{}", loginForm.toString());
        if(Objects.isNull(loginForm.getPhoneCode())){
            loginForm.setPhoneCode("86");
        }
        //表单校验
        ValidatorUtils.validateEntity(loginForm);
        EntityWrapper<UserBasicInfoEntity> userWrapper = new EntityWrapper<>();
        UserBasicInfoEntity userBasicInfoEntity = userBasicInfoService.selectOne(userWrapper.where("phone={0}", loginForm.getMobile()));
        if (null != userBasicInfoEntity && userBasicInfoEntity.getState()==1) {
            log.error("该账号被禁用！");
            return CommonResponse.fail(MessageUtil.getMessage("Disable.account"));
        }
        //获取token
        Map<String, Object> map = userBasicInfoService.login(loginForm);

        // 分配钱包地址
        try {
            this.userWalletInfoService.setWalletAddress(userBasicInfoEntity.getId());
        } catch (Exception e) {
            log.error("分配钱包地址异常:{}", e.getMessage());
        }
        return CommonResponse.success(MessageUtil.getMessage("login.success"), map);
    }

    @Login
    @PostMapping("userInfo")
    @ApiOperation("当前登录人信息")
    public CommonResponse<UserBasicInfoEntity> userInfo(@ApiIgnore @LoginUser UserBasicInfoEntity userBasicInfoEntity) {
        userBasicInfoEntity.setPassword(null);
        if(!Objects.isNull(userBasicInfoEntity.getPayPassword())){
            // 这里是用来处理是否设置支付密码
            userBasicInfoEntity.setPayPassword("hasset");
        }
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
        ValidatorUtils.validateEntity(form);
        userBasicInfoService.updatePassword(form);
        return CommonResponse.success(MessageUtil.getMessage("update.success"));
    }

    @Login
    @PutMapping("updatePayPassword")
    @ApiOperation("修改支付密码")
    public CommonResponse<String> updatePayPassword(@ApiIgnore @LoginUser UserBasicInfoEntity userEntity, @RequestBody UpdatePasswordForm updatePasswordForm) {
        log.info("设置(修改)支付密码请求参数：用户id：{}", userEntity.getId());
        try {
            //验证短信验证码
            if (!smsCaptchaService.querySmsCaptchaIsPass(updatePasswordForm.getMobile(), updatePasswordForm.getCode())) {
                log.error("短信验证码不存在");
                return CommonResponse.fail(MessageUtil.getMessage("Verification.code.error"));
            }
            //修改密码
            userEntity.setPayPassword(DigestUtils.sha256Hex(updatePasswordForm.getPassword()));
            userBasicInfoService.updateById(userEntity);
        } catch (Exception e) {
            log.info("修改支付密码失败:", e);
            return CommonResponse.fail(MessageUtil.getMessage("update.fail"));
        }
        return CommonResponse.success(MessageUtil.getMessage("update.success"));
    }

    @Login
    @PostMapping("checkPayPassword")
    @ApiOperation("校验支付密码")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "payPassword", value = "支付密码", required = true)})
    public CommonResponse<String> checkPayPassword(@ApiIgnore @LoginUser UserBasicInfoEntity userEntity, @RequestBody UpdatePaypasswordForm updatePaypasswordForm) {
        log.info("校验支付密码请求数据：用户id：{}", userEntity.getId());
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

        //验证昵称2~14位字符不能带特殊符号
        if(!U.checkNickName(form.getUsername())){
            return CommonResponse.fail("昵称只能输入2~14位字符，不能带特殊符号");
        }
        //验证短信验证码
        if (!smsCaptchaService.querySmsCaptchaIsPass(form.getMobile(), form.getCode())) {
            return CommonResponse.fail(MessageUtil.getMessage("Verification.code.error"));
        }
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

        // 去掉公告换行符号
        for(NoticeManagerEntity noticeManagerEntity : noticeManagerEntityList){
            noticeManagerEntity.setNoticeContent(noticeManagerEntity.getNoticeContent().replace("<br>",""));
        }

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

    @GetMapping("/getNoticeByH5")
    @ApiOperation("公告h5展示")
    public CommonResponse<List<NoticeManagerEntity>> getNoticeByH5() {
        EntityWrapper<NoticeManagerEntity> bannerManagerEntityWrapper = new EntityWrapper<>();
        bannerManagerEntityWrapper.where("display={0}", 0).orderBy("create_time",false);
        List<NoticeManagerEntity> noticeManagerEntityList = noticeManagerService.selectList(bannerManagerEntityWrapper);
        return CommonResponse.success(noticeManagerEntityList);
    }
}
