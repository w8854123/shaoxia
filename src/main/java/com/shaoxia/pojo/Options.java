package com.shaoxia.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 配置信息表
 * @author wu885
 *
 */
@Table(name="shaoxia_options")
public class Options {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long optionId;
	//选项名称
	private String optionName;
	//选项值
	private String optionValue;
	//选项是否每次都被自动加载，枚举enum(’yes’,’no’)值，默认为yes
	private String autoload;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(String optionValue) {
		this.optionValue = optionValue;
	}

	public String getAutoload() {
		return autoload;
	}

	public void setAutoload(String autoload) {
		this.autoload = autoload;
	}
	
}
