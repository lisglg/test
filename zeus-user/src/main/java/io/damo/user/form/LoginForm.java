package io.damo.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 登录表单
 *
 */
@ApiModel(value = "登录表单")
public class LoginForm {

    @ApiModelProperty(value = "手机区号")
    @NotBlank(message="手机区号不能为空")
    private String phoneCode;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message="手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message="密码不能为空")
    private String password;

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
                "mobile='" + mobile + '\'' +
                ", password='" + DigestUtils.sha256Hex(password) + '\'' +
                '}';
    }
}
