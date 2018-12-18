package io.damo.sms.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.math.BigDecimal;
import java.io.Serializable;

/**
 * 国际区号信息
 * 
 * @author ives
 * @date 2018-03-07 14:14:47
 */
@TableName("tb_national_code")
public class NationalCodeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 国际名称
	 */
	private String nationalName;
	/**
	 * 中文名称
	 */
	private String chineseName;
	/**
	 * 国际简称
	 */
	private String abbre;
	/**
	 * 国际编码
	 */
	private String code;
	/**
	 * 洲际
	 */
	private String land;
	/**
	 * 单价
	 */
	private BigDecimal price;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：国际名称
	 */
	public void setNationalName(String nationalName) {
		this.nationalName = nationalName;
	}
	/**
	 * 获取：国际名称
	 */
	public String getNationalName() {
		return nationalName;
	}
	/**
	 * 设置：中文名称
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	/**
	 * 获取：中文名称
	 */
	public String getChineseName() {
		return chineseName;
	}
	/**
	 * 设置：国际简称
	 */
	public void setAbbre(String abbre) {
		this.abbre = abbre;
	}
	/**
	 * 获取：国际简称
	 */
	public String getAbbre() {
		return abbre;
	}
	/**
	 * 设置：国际编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：国际编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：洲际
	 */
	public void setLand(String land) {
		this.land = land;
	}
	/**
	 * 获取：洲际
	 */
	public String getLand() {
		return land;
	}
	/**
	 * 设置：单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
}
