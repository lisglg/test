package io.damo.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户基本信息表
 * 
 * @author ives
 * @date 2019-02-20 16:46:38
 */
@TableName("user_basic_info")
public class UserBasicInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主健ID
	 */
	@TableId(type= IdType.INPUT)
	private String id;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 手机区号
	 */
	private String code;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 推荐人ID
	 */
	private String recommenderId;
	/**
	 * 间接推荐人ID
	 */
	private String indirectId;
	/**
	 * 支付密码
	 */
	private String payPassword;
	/**
	 * 用户状态(0:启用,1:禁用)
	 */
	private Integer state;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 登录错误次数
	 */
	private Integer errorNumber;
	/**
	 * 切换账号密钥
	 */
	private String accountKey;
	/**
	 * 是否查看最新公告(0.未提示  1.已提示)
	 */
	private Integer noticeMark;
	/**
	 * 禁用原因
	 */
	private String disableReason;
	/**
	 * 禁用时间
	 */
	private Date disableTime;
	/**
	 * 启用理由
	 */
	private String enableReason;
	/**
	 * 启用时间
	 */
	private Date enableTime;

	/**
	 * 设置：主健ID
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：主健ID
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：手机区号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：手机区号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：推荐人ID
	 */
	public void setRecommenderId(String recommenderId) {
		this.recommenderId = recommenderId;
	}
	/**
	 * 获取：推荐人ID
	 */
	public String getRecommenderId() {
		return recommenderId;
	}
	/**
	 * 设置：间接推荐人ID
	 */
	public void setIndirectId(String indirectId) {
		this.indirectId = indirectId;
	}
	/**
	 * 获取：间接推荐人ID
	 */
	public String getIndirectId() {
		return indirectId;
	}
	/**
	 * 设置：支付密码
	 */
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	/**
	 * 获取：支付密码
	 */
	public String getPayPassword() {
		return payPassword;
	}
	/**
	 * 设置：用户状态(0:启用,1:禁用)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：用户状态(0:启用,1:禁用)
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegisterTime() {
		return registerTime;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：登录错误次数
	 */
	public void setErrorNumber(Integer errorNumber) {
		this.errorNumber = errorNumber;
	}
	/**
	 * 获取：登录错误次数
	 */
	public Integer getErrorNumber() {
		return errorNumber;
	}
	/**
	 * 设置：切换账号密钥
	 */
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
	/**
	 * 获取：切换账号密钥
	 */
	public String getAccountKey() {
		return accountKey;
	}
	/**
	 * 设置：是否查看最新公告(0.未提示  1.已提示)
	 */
	public void setNoticeMark(Integer noticeMark) {
		this.noticeMark = noticeMark;
	}
	/**
	 * 获取：是否查看最新公告(0.未提示  1.已提示)
	 */
	public Integer getNoticeMark() {
		return noticeMark;
	}
	/**
	 * 设置：禁用原因
	 */
	public void setDisableReason(String disableReason) {
		this.disableReason = disableReason;
	}
	/**
	 * 获取：禁用原因
	 */
	public String getDisableReason() {
		return disableReason;
	}
	/**
	 * 设置：禁用时间
	 */
	public void setDisableTime(Date disableTime) {
		this.disableTime = disableTime;
	}
	/**
	 * 获取：禁用时间
	 */
	public Date getDisableTime() {
		return disableTime;
	}
	/**
	 * 设置：启用理由
	 */
	public void setEnableReason(String enableReason) {
		this.enableReason = enableReason;
	}
	/**
	 * 获取：启用理由
	 */
	public String getEnableReason() {
		return enableReason;
	}
	/**
	 * 设置：启用时间
	 */
	public void setEnableTime(Date enableTime) {
		this.enableTime = enableTime;
	}
	/**
	 * 获取：启用时间
	 */
	public Date getEnableTime() {
		return enableTime;
	}
}
