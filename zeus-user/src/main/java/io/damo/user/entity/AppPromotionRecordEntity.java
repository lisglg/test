package io.damo.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * app版本升级配置表
 * 
 * @author ives
 * @date 2019-02-20 16:03:32
 */
@TableName("app_promotion_record")
public class AppPromotionRecordEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主健ID
	 */
	@TableId
	private Integer id;
	/**
	 * 是否强制更新(0.不强制  1.强制)
	 */
	private Integer forceUpdate;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 类型（1.andriod、2.ios）
	 */
	private Integer type;
	/**
	 * 安装包路径
	 */
	private String url;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 版本名
	 */
	private String name;

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
	 * 设置：是否强制更新(0.不强制  1.强制)
	 */
	public void setForceUpdate(Integer forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
	/**
	 * 获取：是否强制更新(0.不强制  1.强制)
	 */
	public Integer getForceUpdate() {
		return forceUpdate;
	}
	/**
	 * 设置：版本号
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	/**
	 * 获取：版本号
	 */
	public String getVersion() {
		return version;
	}
	/**
	 * 设置：类型（1.andriod、2.ios）
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：类型（1.andriod、2.ios）
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：安装包路径
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：安装包路径
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：版本名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：版本名
	 */
	public String getName() {
		return name;
	}
}
