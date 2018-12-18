package io.damo.sms.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * 验证码信息
 * 
 * @author ives
 * @date 2018-03-07 14:14:47
 */
@TableName("tb_sms_captcha")
public class SmsCaptchaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 国际区号(86,852)
	 */
	private String areaCode;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 状态(0-可用 1-失效)
	 */
	private String status;
	/**
	 * 创建时间
	 */
	private Date lasttime;
	/**
	 * 短信类别(0-注册,1-忘记密码,2-重置交易密码)
	 */
	private String type;

	/**
	 * 设置：主键
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：国际区号(86,852)
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * 获取：国际区号(86,852)
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：验证码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：验证码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：状态(0-可用 1-失效)
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：状态(0-可用 1-失效)
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * 设置：创建时间
	 */
	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getLasttime() {
		return lasttime;
	}
	/**
	 * 设置：短信类别(0-注册,1-忘记密码,2-重置交易密码)
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 获取：短信类别(0-注册,1-忘记密码,2-重置交易密码)
	 */
	public String getType() {
		return type;
	}

	public SmsCaptchaEntity() {
		super();
	}

	public SmsCaptchaEntity(String mobile, String code) {
		this.mobile = mobile;
		this.code = code;
	}

	public SmsCaptchaEntity(String id, String mobile, String code) {
		this.id = id;
		this.mobile = mobile;
		this.code = code;
	}
}
