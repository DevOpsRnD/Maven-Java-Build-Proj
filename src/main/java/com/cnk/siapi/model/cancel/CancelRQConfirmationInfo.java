package com.cnk.siapi.model.cancel;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class CancelRQConfirmationInfo {

	
	@XmlPath("@Type")
	private String confirmationType;
	
	@XmlPath("@ID")
	private String confirmationId;
	
	@XmlPath("@Instance")
	private String confirmationInstance;

	public String getConfirmationType() {
		return confirmationType;
	}

	public void setConfirmationType(String confirmationType) {
		this.confirmationType = confirmationType;
	}

	public String getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(String confirmationId) {
		this.confirmationId = confirmationId;
	}

	public String getConfirmationInstance() {
		return confirmationInstance;
	}

	public void setConfirmationInstance(String confirmationInstance) {
		this.confirmationInstance = confirmationInstance;
	}
	
}
