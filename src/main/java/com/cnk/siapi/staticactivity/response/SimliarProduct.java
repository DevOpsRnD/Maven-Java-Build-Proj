
package com.cnk.siapi.staticactivity.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "systemActivityCode",
    "systemActivityName",
    "activityType",
    "options",
    "prices"
})
public class SimliarProduct {

    @JsonProperty("systemActivityCode")
    private String systemActivityCode;
    @JsonProperty("systemActivityName")
    private String systemActivityName;
    @JsonProperty("activityType")
    private String activityType;
    @JsonProperty("options")
    private List<Option> options = null;
    @JsonProperty("prices")
    private List<Price> prices = null;

    @JsonProperty("systemActivityCode")
    public String getSystemActivityCode() {
        return systemActivityCode;
    }

    @JsonProperty("systemActivityCode")
    public void setSystemActivityCode(String systemActivityCode) {
        this.systemActivityCode = systemActivityCode;
    }

    @JsonProperty("systemActivityName")
    public String getSystemActivityName() {
        return systemActivityName;
    }

    @JsonProperty("systemActivityName")
    public void setSystemActivityName(String systemActivityName) {
        this.systemActivityName = systemActivityName;
    }

    @JsonProperty("activityType")
    public String getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("options")
    public List<Option> getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @JsonProperty("prices")
    public List<Price> getPrices() {
        return prices;
    }

    @JsonProperty("prices")
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
}
