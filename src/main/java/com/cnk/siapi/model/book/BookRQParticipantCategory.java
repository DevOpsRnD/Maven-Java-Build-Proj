package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQParticipantCategory 
{
	@XmlPath("QualifierInfo/text()")
	private String qualifierInfo;
	@XmlPath("Price/@Amount")
	private Double priceAmount;
	
	
	public BookRQParticipantCategory() {
		super();
		// TODO Auto-generated constructor stub
	}


	public BookRQParticipantCategory(String qualifierInfo, Double d) {
		super();
		this.qualifierInfo = qualifierInfo;
		this.priceAmount = d;
	}


	public String getQualifierInfo() {
		return qualifierInfo;
	}


	public void setQualifierInfo(String qualifierInfo) {
		this.qualifierInfo = qualifierInfo;
	}


	public Double getPriceAmount() {
		return priceAmount;
	}


	public void setPriceAmount(Double priceAmount) {
		this.priceAmount = priceAmount;
	}
	
	
	
}
