
package com.cnk.siapi.staticactivity.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activityCode",
    "supplierCompanyCode",
    "supplierProductCode",
    "interestType",
    "category",
    "type",
    "subType",
    "name",
    "description",
    "departurePoint",
    "returnDetails",
    "daysOfTheWeek",
    "physicalIntensity",
    "suitableFor",
    "overview",
    "recommended",
    "countryName",
    "countryCode",
    "cityName",
    "cityCode",
    "starRating",
    "numberOfPassengers",
    "numberOfReviews",
    "numberOfLikes",
    "numberOfViews",
    "activityMedia",
    "productOptions",
    "simliarProducts",
    "prices",
    "specials",
    "supplierCityDepartureCodes"
})
public class Activity {

    @JsonProperty("activityCode")
    private Integer activityCode;
    @JsonProperty("supplierCompanyCode")
    private String supplierCompanyCode;
    @JsonProperty("supplierProductCode")
    private String supplierProductCode;
    @JsonProperty("category")
    private String category;
    @JsonProperty("type")
    private String type;
    @JsonProperty("subType")
    private String subType;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("departurePoint")
    private String departurePoint;
    @JsonProperty("returnDetails")
    private Object returnDetails;
    @JsonProperty("daysOfTheWeek")
    private List<DaysOfTheWeek> daysOfTheWeek = null;
    @JsonProperty("physicalIntensity")
    private String physicalIntensity;
    @JsonProperty("suitableFor")
    private List<String> suitableFor = null;
    @JsonProperty("overview")
    private Object overview;
    @JsonProperty("recommended")
    private String recommended;
    @JsonProperty("countryName")
    private String countryName;
    @JsonProperty("countryCode")
    private String countryCode;
    @JsonProperty("cityName")
    private String cityName;
    @JsonProperty("cityCode")
    private String cityCode;
    @JsonProperty("starRating")
    private String starRating;
    @JsonProperty("numberOfPassengers")
    private String numberOfPassengers;
    @JsonProperty("numberOfReviews")
    private Object numberOfReviews;
    @JsonProperty("numberOfLikes")
    private Object numberOfLikes;
    @JsonProperty("numberOfViews")
    private Object numberOfViews;
    @JsonProperty("interestType")
    private Object interestType;
    @JsonProperty("activityMedia")
    private List<ActivityMedium> activityMedia = null;
    @JsonProperty("productOptions")
    private List<ProductOption> productOptions = null;
    @JsonProperty("simliarProducts")
    private List<SimliarProduct> simliarProducts = null;
    @JsonProperty("prices")
    private List<Prices> prices = null;
    @JsonProperty("specials")
    private List<Object> specials = null;
    @JsonProperty("supplierCityDepartureCodes")
    private List<SupplierCityDepartureCodes> supplierCityDepartureCodes = null;

    @JsonProperty("activityCode")
    public Integer getActivityCode() {
        return activityCode;
    }

    @JsonProperty("activityCode")
    public void setActivityCode(Integer activityCode) {
        this.activityCode = activityCode;
    }

    @JsonProperty("supplierCompanyCode")
    public String getSupplierCompanyCode() {
        return supplierCompanyCode;
    }

    @JsonProperty("supplierCompanyCode")
    public void setSupplierCompanyCode(String supplierCompanyCode) {
        this.supplierCompanyCode = supplierCompanyCode;
    }

    @JsonProperty("supplierProductCode")
    public String getSupplierProductCode() {
        return supplierProductCode;
    }

    @JsonProperty("supplierProductCode")
    public void setSupplierProductCode(String supplierProductCode) {
        this.supplierProductCode = supplierProductCode;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("interestType")
    public Object getInterestType() {
		return interestType;
	}
    @JsonProperty("interestType")
	public void setInterestType(Object interestType) {
		this.interestType = interestType;
	}

	@JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("subType")
    public String getSubType() {
        return subType;
    }

    @JsonProperty("subType")
    public void setSubType(String subType) {
        this.subType = subType;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("departurePoint")
    public String getDeparturePoint() {
        return departurePoint;
    }

    @JsonProperty("departurePoint")
    public void setDeparturePoint(String departurePoint) {
        this.departurePoint = departurePoint;
    }

    @JsonProperty("returnDetails")
    public Object getReturnDetails() {
        return returnDetails;
    }

    @JsonProperty("returnDetails")
    public void setReturnDetails(Object returnDetails) {
        this.returnDetails = returnDetails;
    }

    @JsonProperty("daysOfTheWeek")
    public List<DaysOfTheWeek> getDaysOfTheWeek() {
        return daysOfTheWeek;
    }

    @JsonProperty("daysOfTheWeek")
    public void setDaysOfTheWeek(List<DaysOfTheWeek> daysOfTheWeek) {
        this.daysOfTheWeek = daysOfTheWeek;
    }

    @JsonProperty("physicalIntensity")
    public String getPhysicalIntensity() {
        return physicalIntensity;
    }

    @JsonProperty("physicalIntensity")
    public void setPhysicalIntensity(String physicalIntensity) {
        this.physicalIntensity = physicalIntensity;
    }

    @JsonProperty("suitableFor")
    public List<String> getSuitableFor() {
        return suitableFor;
    }

    @JsonProperty("suitableFor")
    public void setSuitableFor(List<String> suitableFor) {
        this.suitableFor = suitableFor;
    }

    @JsonProperty("overview")
    public Object getOverview() {
        return overview;
    }

    @JsonProperty("overview")
    public void setOverview(Object overview) {
        this.overview = overview;
    }

    @JsonProperty("recommended")
    public String getRecommended() {
        return recommended;
    }

    @JsonProperty("recommended")
    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    @JsonProperty("countryName")
    public String getCountryName() {
        return countryName;
    }

    @JsonProperty("countryName")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @JsonProperty("countryCode")
    public String getCountryCode() {
        return countryCode;
    }

    @JsonProperty("countryCode")
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

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

    @JsonProperty("starRating")
    public String getStarRating() {
        return starRating;
    }

    @JsonProperty("starRating")
    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    @JsonProperty("numberOfPassengers")
    public String getNumberOfPassengers() {
        return numberOfPassengers;
    }

    @JsonProperty("numberOfPassengers")
    public void setNumberOfPassengers(String numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    @JsonProperty("numberOfReviews")
    public Object getNumberOfReviews() {
        return numberOfReviews;
    }

    @JsonProperty("numberOfReviews")
    public void setNumberOfReviews(Object numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @JsonProperty("numberOfLikes")
    public Object getNumberOfLikes() {
        return numberOfLikes;
    }

    @JsonProperty("numberOfLikes")
    public void setNumberOfLikes(Object numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    @JsonProperty("numberOfViews")
    public Object getNumberOfViews() {
        return numberOfViews;
    }

    @JsonProperty("numberOfViews")
    public void setNumberOfViews(Object numberOfViews) {
        this.numberOfViews = numberOfViews;
    }

    @JsonProperty("activityMedia")
    public List<ActivityMedium> getActivityMedia() {
        return activityMedia;
    }

    @JsonProperty("activityMedia")
    public void setActivityMedia(List<ActivityMedium> activityMedia) {
        this.activityMedia = activityMedia;
    }

    @JsonProperty("productOptions")
    public List<ProductOption> getProductOptions() {
        return productOptions;
    }

    @JsonProperty("productOptions")
    public void setProductOptions(List<ProductOption> productOptions) {
        this.productOptions = productOptions;
    }

    @JsonProperty("simliarProducts")
    public List<SimliarProduct> getSimliarProducts() {
        return simliarProducts;
    }

    @JsonProperty("simliarProducts")
    public void setSimliarProducts(List<SimliarProduct> simliarProducts) {
        this.simliarProducts = simliarProducts;
    }

    @JsonProperty("prices")
    public List<Prices> getPrices() {
        return prices;
    }

    @JsonProperty("prices")
    public void setPrices(List<Prices> prices) {
        this.prices = prices;
    }

    @JsonProperty("specials")
    public List<Object> getSpecials() {
        return specials;
    }

    @JsonProperty("specials")
    public void setSpecials(List<Object> specials) {
        this.specials = specials;
    }
    
    @JsonProperty("supplierCityDepartureCodes")
    public List<SupplierCityDepartureCodes> getSupplierCityDepartureCodes() {
		return supplierCityDepartureCodes;
	}

    @JsonProperty("supplierCityDepartureCodes")
	public void setSupplierCityDepartureCodes(List<SupplierCityDepartureCodes> supplierCityDepartureCodes) {
		this.supplierCityDepartureCodes = supplierCityDepartureCodes;
	}
}
