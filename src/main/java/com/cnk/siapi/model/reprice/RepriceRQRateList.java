package com.cnk.siapi.model.reprice;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class RepriceRQRateList {

	@XmlPath("/AreaID/text()")
	private String areadId;

	@XmlPath("/Name/text()")
	private String areaName;

	@XmlPath("/Cost/text()")
	private String areaCost;

	public String getAreadId() {
		return areadId;
	}

	public void setAreadId(String areadId) {
		this.areadId = areadId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaCost() {
		return areaCost;
	}

	public void setAreaCost(String areaCost) {
		this.areaCost = areaCost;
	}
}
