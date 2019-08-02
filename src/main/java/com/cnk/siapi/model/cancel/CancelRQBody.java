package com.cnk.siapi.model.cancel;

import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class CancelRQBody {

	@XmlPath("/OTA_TourActivityCancelRQWrapper/SupplierID/text()")
	private String supplierId;
	
	@XmlPath("/OTA_TourActivityCancelRQWrapper/Sequence/text()")
	private String sequence = "1";
	
	@XmlTransient
	private String accessCode;
	
	@XmlPath("/OTA_TourActivityCancelRQWrapper/OTA_TourActivityCancelRQ/POS/Source/@ISOCurrency")
	private String ISOCurrency;
	
	@XmlPath("/OTA_TourActivityCancelRQWrapper/OTA_TourActivityCancelRQ/Confirmation")
	private List<CancelRQConfirmationInfo> cancelConfirmationInfo;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getISOCurrency() {
		return ISOCurrency;
	}

	public void setISOCurrency(String iSOCurrency) {
		ISOCurrency = iSOCurrency;
	}

	public List<CancelRQConfirmationInfo> getCancelConfirmationInfo() {
		return cancelConfirmationInfo;
	}

	public void setCancelConfirmationInfo(List<CancelRQConfirmationInfo> cancelConfirmationInfo) {
		this.cancelConfirmationInfo = cancelConfirmationInfo;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	
	

}
