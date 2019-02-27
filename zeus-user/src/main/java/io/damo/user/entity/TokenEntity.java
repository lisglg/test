package io.damo.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户Token
 * 
 * @author ives
 * @date 2019-02-20 16:03:31
 */
@TableName("tb_token")
public class TokenEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type= IdType.INPUT)
	private String userId;
	/**
	 * token
	 */
	private String token;
	/**
	 * 过期时间
	 */
	private Date expireTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * token类型(1:普通用户token 2:系统用户token)
	 */
	private Long tokenType;

	/**
	 * 设置：
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：token
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * 获取：token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * 设置：过期时间
	 */
	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}
	/**
	 * 获取：过期时间
	 */
	public Date getExpireTime() {
		return expireTime;
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
	 * 设置：token类型(1:普通用户token 2:系统用户token)
	 */
	public void setTokenType(Long tokenType) {
		this.tokenType = tokenType;
	}
	/**
	 * 获取：token类型(1:普通用户token 2:系统用户token)
	 */
	public Long getTokenType() {
		return tokenType;
	}
}
