package com.cnk.siapi.model.reprice;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class RepriceRQParticipant {

	@XmlPath("@Age")
	private String age;

	@XmlPath("@Quantity")
	private String quantity;

	@XmlPath("QualifierInfo/@Extension")
	private String extension;

	@XmlPath("QualifierInfo/text()")
	private String qualifierInfo;

	public RepriceRQParticipant() {
		super();
	}

	public RepriceRQParticipant(String age, String quantity, String extension, String qualifierInfo) {
		super();
		this.age = age;
		this.quantity = quantity;
		this.extension = extension;
		this.qualifierInfo = qualifierInfo;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
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
