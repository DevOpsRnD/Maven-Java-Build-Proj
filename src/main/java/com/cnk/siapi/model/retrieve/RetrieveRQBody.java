package com.cnk.siapi.model.retrieve;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class RetrieveRQBody 
{
	@XmlPath("/OTA_ReadRQWrapper/SupplierID/text()")
	private String supplierId;
	
	@XmlPath("/OTA_ReadRQWrapper/Sequence/text()")
	private String sequence  = "1";
	
	@XmlTransient
	private String accessCode;
	
	@XmlPath("/OTA_ReadRQWrapper/OTA_ReadRQ/POS/Source/@ISOCurrency")
	private String ISOCurrency;
	
	@XmlPath("/OTA_ReadRQWrapper/OTA_ReadRQ/UniqueID")
	private List<UniqueIdDetails> uniqueIdDetails;
	
	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSequence() {
		return sequence;
	}

	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getISOCurrency() {
		return ISOCurrency;
	}

	public void setISOCurrency(String iSOCurrency) {
		ISOCurrency = iSOCurrency;
	}

	public List<UniqueIdDetails> getUniqueIdDetails() {
		return uniqueIdDetails;
	}

	public void setUniqueIdDetails(List<UniqueIdDetails> uniqueIdDetails) {
		this.uniqueIdDetails = uniqueIdDetails;
	}

}
