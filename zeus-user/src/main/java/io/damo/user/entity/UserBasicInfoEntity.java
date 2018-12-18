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
 * @date 2018-09-06 16:39:11
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
	 * 手机号
	 */
	private String phone;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 推荐人手机号
	 */
	private String recommenderPhone;
	/**
	 * 间接推荐人
	 */
	private String indirectRecommend;
	/**
	 * 二维码
	 */
	private String qrCode;
	/**
	 * 钱包地址
	 */
	private String valletUrl;
	/**
	 * 头像
	 */
	private String headImage;
	/**
	 * 支付密码
	 */
	private String payPassword;
	/**
	 * 用户状态(0.正常  1.禁用)
	 */
	private Integer state;
	/**
	 * 登录错误次数
	 */
	private Integer errorNumber;

	/**
	 * 切换账号秘钥
	 */
	private String accountKey;

	/**
	 * 是否查看最新公告(0.未查看  1.已查看)
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
	 * 启用原因
	 */
	private String enableReason;
	/**
	 * 启用时间
	 */
	private Date enableTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;

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
	 * 设置：推荐人手机号
	 */
	public void setRecommenderPhone(String recommenderPhone) {
		this.recommenderPhone = recommenderPhone;
	}
	/**
	 * 获取：推荐人手机号
	 */
	public String getRecommenderPhone() {
		return recommenderPhone;
	}
	/**
	 * 设置：间接推荐人
	 */
	public void setIndirectRecommend(String indirectRecommend) {
		this.indirectRecommend = indirectRecommend;
	}
	/**
	 * 获取：间接推荐人
	 */
	public String getIndirectRecommend() {
		return indirectRecommend;
	}
	/**
	 * 设置：二维码
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	/**
	 * 获取：二维码
	 */
	public String getQrCode() {
		return qrCode;
	}
	/**
	 * 设置：钱包地址
	 */
	public void setValletUrl(String valletUrl) {
		this.valletUrl = valletUrl;
	}
	/**
	 * 获取：钱包地址
	 */
	public String getValletUrl() {
		return valletUrl;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadImage() {
		return headImage;
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
	 * 设置：用户状态(0.正常  1.禁用)
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：用户状态(0.正常  1.禁用)
	 */
	public Integer getState() {
		return state;
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
	 * 设置：是否查看最新公告(0.未查看  1.已查看)
	 */
	public void setNoticeMark(Integer noticeMark) {
		this.noticeMark = noticeMark;
	}
	/**
	 * 获取：是否查看最新公告(0.未查看  1.已查看)
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
	 * 设置：启用原因
	 */
	public void setEnableReason(String enableReason) {
		this.enableReason = enableReason;
	}
	/**
	 * 获取：启用原因
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

	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}
}
