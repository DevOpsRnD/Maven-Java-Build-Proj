
package com.cnk.siapi.staticactivity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "supplierCurrency",
    "price",
    "priceType",
    "priceBasis",
    "priceId",
    "optionCode",
    "priceFor"
})
public class Prices {

    @JsonProperty("supplierCurrency")
    private String supplierCurrency;
    @JsonProperty("price")
    private Integer price;
    @JsonProperty("priceType")
    private String priceType;
    @JsonProperty("priceBasis")
    private Object priceBasis;
    @JsonProperty("priceId")
    private Object priceId;
    @JsonProperty("optionCode")
    private Object optionCode;
    @JsonProperty("priceFor")
    private String priceFor;

    @JsonProperty("supplierCurrency")
    public String getSupplierCurrency() {
        return supplierCurrency;
    }

    @JsonProperty("supplierCurrency")
    public void setSupplierCurrency(String supplierCurrency) {
        this.supplierCurrency = supplierCurrency;
    }

    @JsonProperty("price")
    public Integer getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Integer price) {
        this.price = price;
    }

    @JsonProperty("priceType")
    public String getPriceType() {
        return priceType;
    }

    @JsonProperty("priceType")
    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    @JsonProperty("priceBasis")
    public Object getPriceBasis() {
        return priceBasis;
    }

    @JsonProperty("priceBasis")
    public void setPriceBasis(Object priceBasis) {
        this.priceBasis = priceBasis;
    }

    @JsonProperty("priceId")
    public Object getPriceId() {
        return priceId;
    }

    @JsonProperty("priceId")
    public void setPriceId(Object priceId) {
        this.priceId = priceId;
    }

    @JsonProperty("optionCode")
    public Object getOptionCode() {
        return optionCode;
    }

    @JsonProperty("optionCode")
    public void setOptionCode(Object optionCode) {
        this.optionCode = optionCode;
    }

    @JsonProperty("priceFor")
    public String getPriceFor() {
        return priceFor;
    }

    @JsonProperty("priceFor")
    public void setPriceFor(String priceFor) {
        this.priceFor = priceFor;
    }
}
