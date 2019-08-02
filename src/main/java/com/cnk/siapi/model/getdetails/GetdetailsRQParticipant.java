package com.cnk.siapi.model.getdetails;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class GetdetailsRQParticipant {

	@XmlPath("@Quantity")
	private int quantity;

	@XmlPath("@Age")
	private int age;

	@XmlPath("QualifierInfo/@Extension")
	private String extension;

	@XmlPath("QualifierInfo/text()")
	private String qualifierInfo;

	public GetdetailsRQParticipant() {
		super();
	}

	public GetdetailsRQParticipant(int quantity, int age, String extension, String qualifierInfo) {
		super();
		this.quantity = quantity;
		this.age = age;
		this.extension = extension;
		this.qualifierInfo = qualifierInfo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getQualifierInfo() {
		return qualifierInfo;
	}

	public void setQualifierInfo(String qualifierInfo) {
		this.qualifierInfo = qualifierInfo;
	}

}
