package io.damo.user.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * app版本升级配置表
 * 
 * @author ives
 * @date 2018-05-05 10:20:44
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
	 * 版本号
	 */
	@ApiModelProperty("版本号")
	private String version;
	/**
	 * 类型（1.andriod、2.ios）
	 */
	@ApiModelProperty("类型（1.andriod、2.ios）")
	private Integer type;
	/**
	 * 安装包路径
	 */
	@ApiModelProperty("安装包路径")
	private String url;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;

	/**
	 * 是否强制更新(0.否、1.是)
	 */
	@ApiModelProperty("是否强制更新(0.否、1.是)")
	private Integer forceUpdate;

	/**
	 * 版本名
	 */
	@ApiModelProperty("版本名")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	public Integer getForceUpdate() {
		return forceUpdate;
	}

	public void setForceUpdate(Integer forceUpdate) {
		this.forceUpdate = forceUpdate;
	}
}
