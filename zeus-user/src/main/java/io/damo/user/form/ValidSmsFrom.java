package io.damo.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nangua on 2018/4/14.
 */
@ApiModel(value="验证验证码正确性")
public class ValidSmsFrom {
    @ApiModelProperty(value="验证码")
    @NotBlank(message="验证码不能为空")
    private String validCode;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
