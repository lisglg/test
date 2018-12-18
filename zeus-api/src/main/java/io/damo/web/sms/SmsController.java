package io.damo.web.sms;


import io.damo.common.aspect.OpLogger;
import io.damo.common.response.CommonResponse;
import io.damo.common.validator.ValidatorUtils;
import io.damo.sms.service.SmsCaptchaService;
import io.damo.user.form.SendManagerFrom;
import io.damo.user.form.ValidSmsFrom;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by caishun on 2018/3/12.
 */
@RestController
@RequestMapping("/api/sms")
@Api(tags = "发送短信接口")
public class SmsController {

    @Autowired
    private SmsCaptchaService smsCaptchaService;

    @PostMapping("sendMeneger")
    @ApiOperation("发送注册验证码")
    @OpLogger
    public CommonResponse<String> sendMeneger(@RequestBody SendManagerFrom sendManagerFrom) {
        //表单校验
        ValidatorUtils.validateEntity(sendManagerFrom);
        if (null == sendManagerFrom.getAreaCode()) {
            smsCaptchaService.sendSmsCaptcha("0", sendManagerFrom.getMobile(), sendManagerFrom.getType());
        } else {
            smsCaptchaService.sendSmsCaptcha(sendManagerFrom.getAreaCode(), sendManagerFrom.getMobile(), sendManagerFrom.getType());
        }
        return CommonResponse.success("短信验证码发送成功！");
    }

    @PutMapping("validSms")
    @ApiOperation("验证手机验证码")
    @OpLogger
    public CommonResponse<String> validSms( @RequestBody ValidSmsFrom validSmsFrom) {
        //查询手机验证码
        int count = smsCaptchaService.checkSmsCode(validSmsFrom.getMobile(),validSmsFrom.getValidCode());
        return count == 0? CommonResponse.fail("验证码无效，请重新获取"):CommonResponse.success("短信验证码验证成功！");

    }
}
