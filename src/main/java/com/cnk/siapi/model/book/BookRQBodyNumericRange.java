package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;
public class BookRQBodyNumericRange
{
	@XmlPath("AdditionTypeID/text()")
	private String additionTypeId;
	@XmlPath("AdditionType/text()")
	private String additionType;
	@XmlPath("minValue/text()")
	private String mixValue;
	@XmlPath("maxValue/text()")
	private String maxValue;
	@XmlPath("Value/text()")
	private String value;
	
	public BookRQBodyNumericRange() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookRQBodyNumericRange(String additionTypeId, String additionType, String mixValue, String maxValue,
			String value) {
		super();
		this.additionTypeId = additionTypeId;
		this.additionType = additionType;
		this.mixValue = mixValue;
		this.maxValue = maxValue;
		this.value = value;
	}
	
	public String getAdditionTypeId() {
		return additionTypeId;
	}
	public void setAdditionTypeId(String additionTypeId) {
		this.additionTypeId = additionTypeId;
	}
	public String getAdditionType() {
		return additionType;
	}
	public void setAdditionType(String additionType) {
		this.additionType = additionType;
	}
	public String getMixValue() {
		return mixValue;
	}
	public void setMixValue(String mixValue) {
		this.mixValue = mixValue;
	}
	public String getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}	
}