package com.cnk.siapi.model.getpolicy;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class GetPolicyRQParticipant {

	@XmlPath("@Age")
	private String age;

	@XmlPath("@Quantity")
	private String quantity;

	@XmlPath("QualifierInfo/text()")
	private String qualifierInfo;

	public GetPolicyRQParticipant() {
		super();
	}

	public GetPolicyRQParticipant(String age, String quantity, String qualifierInfo) {
		super();
		this.age = age;
		this.quantity = quantity;
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

	public String getQualifierInfo() {
		return qualifierInfo;
	}

	public void setQualifierInfo(String qualifierInfo) {
		this.qualifierInfo = qualifierInfo;
	}

}
