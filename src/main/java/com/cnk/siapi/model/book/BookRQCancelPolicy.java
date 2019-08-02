package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQCancelPolicy 
{
	@XmlPath("Unit/text()")
	private String cancellationUnit;
	@XmlPath("FromValue/text()")
	private String cancellationfromValue;
	@XmlPath("ChargeType/text()")
	private String cancellationChargeType;
	@XmlPath("Rate/text()")
	private String rate;
	
	
	public BookRQCancelPolicy(String cancellationUnit, String cancellationfromValue, String cancellationChargeType,
			String rate) {
		super();
		this.cancellationUnit = cancellationUnit;
		this.cancellationfromValue = cancellationfromValue;
		this.cancellationChargeType = cancellationChargeType;
		this.rate = rate;
	}
	public BookRQCancelPolicy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCancellationUnit() {
		return cancellationUnit;
	}
	public void setCancellationUnit(String cancellationUnit) {
		this.cancellationUnit = cancellationUnit;
	}
	public String getCancellationfromValue() {
		return cancellationfromValue;
	}
	public void setCancellationfromValue(String cancellationfromValue) {
		this.cancellationfromValue = cancellationfromValue;
	}
	public String getCancellationChargeType() {
		return cancellationChargeType;
	}
	public void setCancellationChargeType(String cancellationChargeType) {
		this.cancellationChargeType = cancellationChargeType;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}

	
	
	
	
}
