package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQBodyParticipant {
	
	@XmlPath("/Category/@Age")
	private int participantAge;

	@XmlPath("/Category/@ParticipantCategoryID")
	private String participantCategoryId;

	@XmlPath("/Category/@Quantity")
	private int participantQuantity;

	@XmlPath("/Category/QualifierInfo/text()")
	private String participantQualifierInfo;

	@XmlPath("/Category/QualifierInfo/@Extension")
	private String participantExtension;

	@XmlPath("/Category/Contact/PersonName/GivenName/text()")
	private String participantGivenName;

	@XmlPath("/Category/Contact/PersonName/MiddleName/text()")
	private String participantMiddleName;

	@XmlPath("/Category/Contact/PersonName/Surname/text()")
	private String participantSurname;

	@XmlPath("/Category/Contact/PersonName/NameTitle/text()")
	private String participantTitle;

	@XmlPath("/Category/Contact/Telephone/@CountryAccessCode")
	private String participantPhoneCountryCode;

	@XmlPath("/Category/Contact/Telephone/@PhoneNumber")
	private String participantTelephone;

	public BookRQBodyParticipant() {
		super();
	}

	public BookRQBodyParticipant(int participantAge, String participantCategoryId, int participantQuantity,
			String participantQualifierInfo, String participantExtension, String participantGivenName,
			String participantMiddleName, String participantSurname, String participantTitle,
			String participantPhoneCountryCode, String participantTelephone) {
		super();
		this.participantAge = participantAge;
		this.participantCategoryId = participantCategoryId;
		this.participantQuantity = participantQuantity;
		this.participantQualifierInfo = participantQualifierInfo;
		this.participantExtension = participantExtension;
		this.participantGivenName = participantGivenName;
		this.participantMiddleName = participantMiddleName;
		this.participantSurname = participantSurname;
		this.participantTitle = participantTitle;
		this.participantPhoneCountryCode = participantPhoneCountryCode;
		this.participantTelephone = participantTelephone;
	}

	public int getParticipantAge() {
		return participantAge;
	}

	public void setParticipantAge(int participantAge) {
		this.participantAge = participantAge;
	}

	public String getParticipantCategoryId() {
		return participantCategoryId;
	}

	public void setParticipantCategoryId(String participantCategoryId) {
		this.participantCategoryId = participantCategoryId;
	}

	public int getParticipantQuantity() {
		return participantQuantity;
	}

	public void setParticipantQuantity(int participantQuantity) {
		this.participantQuantity = participantQuantity;
	}

	public String getParticipantQualifierInfo() {
		return participantQualifierInfo;
	}

	public void setParticipantQualifierInfo(String participantQualifierInfo) {
		this.participantQualifierInfo = participantQualifierInfo;
	}

	public String getParticipantExtension() {
		return participantExtension;
	}

	public void setParticipantExtension(String participantExtension) {
		this.participantExtension = participantExtension;
	}

	public String getParticipantGivenName() {
		return participantGivenName;
	}

	public void setParticipantGivenName(String participantGivenName) {
		this.participantGivenName = participantGivenName;
	}

	public String getParticipantMiddleName() {
		return participantMiddleName;
	}

	public void setParticipantMiddleName(String participantMiddleName) {
		this.participantMiddleName = participantMiddleName;
	}

	public String getParticipantSurname() {
		return participantSurname;
	}

	public void setParticipantSurname(String participantSurname) {
		this.participantSurname = participantSurname;
	}

	public String getParticipantTitle() {
		return participantTitle;
	}

	public void setParticipantTitle(String participantTitle) {
		this.participantTitle = participantTitle;
	}

	public String getParticipantPhoneCountryCode() {
		return participantPhoneCountryCode;
	}

	public void setParticipantPhoneCountryCode(String participantPhoneCountryCode) {
		this.participantPhoneCountryCode = participantPhoneCountryCode;
	}

	public String getParticipantTelephone() {
		return participantTelephone;
	}

	public void setParticipantTelephone(String participantTelephone) {
		this.participantTelephone = participantTelephone;
	}

}
