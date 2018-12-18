package io.damo.user.vo;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 用户信息
 * 
 * @author ives
 * @date 2018-04-27 11:33:58
 */
public class QrCodeVo implements Serializable {
	private static final long serialVersionUID = 1L;


	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String phone;

	/**
	 * 钱包地址
	 */
	@ApiModelProperty("钱包地址")
	private String valletUrl;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getValletUrl() {
		return valletUrl;
	}

	public void setValletUrl(String valletUrl) {
		this.valletUrl = valletUrl;
	}
}
