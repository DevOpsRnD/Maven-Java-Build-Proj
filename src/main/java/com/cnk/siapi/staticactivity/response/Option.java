
package com.cnk.siapi.staticactivity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "systemActivityOptionCode",
    "optionCode",
    "dealText",
    "options",
    "activityType",
    "languageCode",
    "language"
})
public class Option {

    @JsonProperty("systemActivityOptionCode")
    private String systemActivityOptionCode;
    @JsonProperty("optionCode")
    private String optionCode;
    @JsonProperty("dealText")
    private Object dealText;
    @JsonProperty("options")
    private String options;
    @JsonProperty("activityType")
    private Object activityType;
    @JsonProperty("languageCode")
    private Object languageCode;
    @JsonProperty("language")
    private Object language;

    @JsonProperty("systemActivityOptionCode")
    public String getSystemActivityOptionCode() {
        return systemActivityOptionCode;
    }

    @JsonProperty("systemActivityOptionCode")
    public void setSystemActivityOptionCode(String systemActivityOptionCode) {
        this.systemActivityOptionCode = systemActivityOptionCode;
    }

    @JsonProperty("optionCode")
    public String getOptionCode() {
        return optionCode;
    }

    @JsonProperty("optionCode")
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    @JsonProperty("dealText")
    public Object getDealText() {
        return dealText;
    }

    @JsonProperty("dealText")
    public void setDealText(Object dealText) {
        this.dealText = dealText;
    }

    @JsonProperty("options")
    public String getOptions() {
        return options;
    }

    @JsonProperty("options")
    public void setOptions(String options) {
        this.options = options;
    }

    @JsonProperty("activityType")
    public Object getActivityType() {
        return activityType;
    }

    @JsonProperty("activityType")
    public void setActivityType(Object activityType) {
        this.activityType = activityType;
    }

    @JsonProperty("languageCode")
    public Object getLanguageCode() {
        return languageCode;
    }

    @JsonProperty("languageCode")
    public void setLanguageCode(Object languageCode) {
        this.languageCode = languageCode;
    }

    @JsonProperty("language")
    public Object getLanguage() {
        return language;
    }

    @JsonProperty("language")
    public void setLanguage(Object language) {
        this.language = language;
    }
}
