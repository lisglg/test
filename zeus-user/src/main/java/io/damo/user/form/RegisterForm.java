package io.damo.user.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 注册表单
 */
@ApiModel(value = "注册表单")
public class RegisterForm {
    @ApiModelProperty(value = "手机区号")
    @NotBlank(message = "手机区号不能为空")
    private String phoneCode;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "用户名称")
    @NotBlank(message = "用户名称不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "mobile='" + mobile + '\'' +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
