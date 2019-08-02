package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class BookRQRates 
{
	@XmlPath("AreaID/text()")
	private String areaId;
	@XmlPath("Name/text()")
	private String areaName;
	@XmlPath("Cost/text()")
	private String areaCost;
	

	public BookRQRates(String areaId, String areaName, String areaCost) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.areaCost = areaCost;
	}

	public BookRQRates() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
