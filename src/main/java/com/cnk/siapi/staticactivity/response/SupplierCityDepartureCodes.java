
package com.cnk.siapi.staticactivity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "cityName", "cityCode", "hotelName", "hotelCode", "departureName", "" })
public class SupplierCityDepartureCodes {

	@JsonProperty("cityName")
	private String cityName;
	@JsonProperty("cityCode")
	private String cityCode;
	@JsonProperty("hotelName")
	private String hotelName;
	@JsonProperty("hotelCode")
	private String hotelCode;
	@JsonProperty("departureName")
	private String departureName;
	@JsonProperty("departureCode")
	private String departureCode;

	@JsonProperty("cityName")
	public String getCityName() {
		return cityName;
	}

	@JsonProperty("cityName")
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@JsonProperty("cityCode")
	public String getCityCode() {
		return cityCode;
	}

	@JsonProperty("cityCode")
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@JsonProperty("hotelName")
	public String getHotelName() {
		return hotelName;
	}

	@JsonProperty("hotelName")
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	@JsonProperty("hotelCode")
	public String getHotelCode() {
		return hotelCode;
	}

	@JsonProperty("hotelCode")
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}

	@JsonProperty("departureName")
	public String getDepartureName() {
		return departureName;
	}

	@JsonProperty("departureName")
	public void setDepartureName(String departureName) {
		this.departureName = departureName;
	}

	@JsonProperty("departureCode")
	public String getDepartureCode() {
		return departureCode;
	}

	@JsonProperty("departureCode")
	public void setDepartureCode(String departureCode) {
		this.departureCode = departureCode;
	}

}
