package io.damo.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by caishun on 2018/3/12.
 */
@ApiModel(value="发送验证码表单")
public class SendManagerFrom {
    @ApiModelProperty(value="国际区号")
    @NotBlank(message="国际区号不能为空")
    private String areaCode;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "状态(0:注册,1:忘记密码,2:重置交易密码,3:绑定邮箱,4:登录手势密码,5:交易手势密码,6:默认)")
    @NotBlank(message="验证码类型")
    private String type;

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
