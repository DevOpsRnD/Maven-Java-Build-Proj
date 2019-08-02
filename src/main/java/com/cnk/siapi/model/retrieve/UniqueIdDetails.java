package com.cnk.siapi.model.retrieve;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class UniqueIdDetails {

	@XmlPath("@ID")
	private String uniqueId;
	
	@XmlPath("@Type")
	private String uniqueIdType;
	
	@XmlPath("@Instance")
	private String instance;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getUniqueIdType() {
		return uniqueIdType;
	}

	public void setUniqueIdType(String uniqueIdType) {
		this.uniqueIdType = uniqueIdType;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	@Override
	public String toString() {
		return "UniqueIdDetails [uniqueId=" + uniqueId + ", uniqueIdType=" + uniqueIdType + ", instance=" + instance
				+ "]";
	}
	
}
