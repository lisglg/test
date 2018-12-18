package io.damo.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户账户表
 * 
 * @author ives
 * @date 2018-09-06 16:39:11
 */
@TableName("user_account_info")
public class UserAccountInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主健ID
	 */
	@TableId
	private Integer id;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 注册分
	 */
	private BigDecimal registerPoint;
	/**
	 * 奖金分
	 */
	private BigDecimal bonusPoint;
	/**
	 * 算力分
	 */
	private BigDecimal powerPoint;
	/**
	 * 算力值
	 */
	private BigDecimal powerValue;
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
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主健ID
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户ID
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：注册分
	 */
	public void setRegisterPoint(BigDecimal registerPoint) {
		this.registerPoint = registerPoint;
	}
	/**
	 * 获取：注册分
	 */
	public BigDecimal getRegisterPoint() {
		return registerPoint;
	}
	/**
	 * 设置：奖金分
	 */
	public void setBonusPoint(BigDecimal bonusPoint) {
		this.bonusPoint = bonusPoint;
	}
	/**
	 * 获取：奖金分
	 */
	public BigDecimal getBonusPoint() {
		return bonusPoint;
	}
	/**
	 * 设置：算力分
	 */
	public void setPowerPoint(BigDecimal powerPoint) {
		this.powerPoint = powerPoint;
	}
	/**
	 * 获取：算力分
	 */
	public BigDecimal getPowerPoint() {
		return powerPoint;
	}
	/**
	 * 设置：算力值
	 */
	public void setPowerValue(BigDecimal powerValue) {
		this.powerValue = powerValue;
	}
	/**
	 * 获取：算力值
	 */
	public BigDecimal getPowerValue() {
		return powerValue;
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
}
