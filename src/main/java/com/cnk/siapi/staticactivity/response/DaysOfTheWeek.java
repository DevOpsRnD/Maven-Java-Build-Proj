
package com.cnk.siapi.staticactivity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "supplierFrequency",
    "monday",
    "tuesday",
    "wednesday",
    "thursday",
    "friday",
    "saturday",
    "sunday",
    "supplierStartTime",
    "startTime",
    "supplierEndTime",
    "endTime",
    "supplierDuration",
    "duration",
    "supplierSession",
    "session",
    "operatingFromDate",
    "operatingToDate",
    "departureCode",
    "departurePoint"
})
public class DaysOfTheWeek {

    @JsonProperty("supplierFrequency")
    private String supplierFrequency;
    @JsonProperty("monday")
    private Boolean monday;
    @JsonProperty("tuesday")
    private Boolean tuesday;
    @JsonProperty("wednesday")
    private Boolean wednesday;
    @JsonProperty("thursday")
    private Boolean thursday;
    @JsonProperty("friday")
    private Boolean friday;
    @JsonProperty("saturday")
    private Boolean saturday;
    @JsonProperty("sunday")
    private Boolean sunday;
    @JsonProperty("supplierStartTime")
    private String supplierStartTime;
    @JsonProperty("startTime")
    private String startTime;
    @JsonProperty("supplierEndTime")
    private String supplierEndTime;
    @JsonProperty("endTime")
    private Object endTime;
    @JsonProperty("supplierDuration")
    private String supplierDuration;
    @JsonProperty("duration")
    private String duration;
    @JsonProperty("supplierSession")
    private String supplierSession;
    @JsonProperty("session")
    private String session;
    @JsonProperty("operatingFromDate")
    private String operatingFromDate;
    @JsonProperty("operatingToDate")
    private String operatingToDate;
    @JsonProperty("departureCode")
    private Object departureCode;
    @JsonProperty("departurePoint")
    private Object departurePoint;

    @JsonProperty("supplierFrequency")
    public String getSupplierFrequency() {
        return supplierFrequency;
    }

    @JsonProperty("supplierFrequency")
    public void setSupplierFrequency(String supplierFrequency) {
        this.supplierFrequency = supplierFrequency;
    }

    @JsonProperty("monday")
    public Boolean getMonday() {
        return monday;
    }

    @JsonProperty("monday")
    public void setMonday(Boolean monday) {
        this.monday = monday;
    }

    @JsonProperty("tuesday")
    public Boolean getTuesday() {
        return tuesday;
    }

    @JsonProperty("tuesday")
    public void setTuesday(Boolean tuesday) {
        this.tuesday = tuesday;
    }

    @JsonProperty("wednesday")
    public Boolean getWednesday() {
        return wednesday;
    }

    @JsonProperty("wednesday")
    public void setWednesday(Boolean wednesday) {
        this.wednesday = wednesday;
    }

    @JsonProperty("thursday")
    public Boolean getThursday() {
        return thursday;
    }

    @JsonProperty("thursday")
    public void setThursday(Boolean thursday) {
        this.thursday = thursday;
    }

    @JsonProperty("friday")
    public Boolean getFriday() {
        return friday;
    }

    @JsonProperty("friday")
    public void setFriday(Boolean friday) {
        this.friday = friday;
    }

    @JsonProperty("saturday")
    public Boolean getSaturday() {
        return saturday;
    }

    @JsonProperty("saturday")
    public void setSaturday(Boolean saturday) {
        this.saturday = saturday;
    }

    @JsonProperty("sunday")
    public Boolean getSunday() {
        return sunday;
    }

    @JsonProperty("sunday")
    public void setSunday(Boolean sunday) {
        this.sunday = sunday;
    }

    @JsonProperty("supplierStartTime")
    public String getSupplierStartTime() {
        return supplierStartTime;
    }

    @JsonProperty("supplierStartTime")
    public void setSupplierStartTime(String supplierStartTime) {
        this.supplierStartTime = supplierStartTime;
    }

    @JsonProperty("startTime")
    public String getStartTime() {
        return startTime;
    }

    @JsonProperty("startTime")
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("supplierEndTime")
    public String getSupplierEndTime() {
        return supplierEndTime;
    }

    @JsonProperty("supplierEndTime")
    public void setSupplierEndTime(String supplierEndTime) {
        this.supplierEndTime = supplierEndTime;
    }

    @JsonProperty("endTime")
    public Object getEndTime() {
        return endTime;
    }

    @JsonProperty("endTime")
    public void setEndTime(Object endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("supplierDuration")
    public String getSupplierDuration() {
        return supplierDuration;
    }

    @JsonProperty("supplierDuration")
    public void setSupplierDuration(String supplierDuration) {
        this.supplierDuration = supplierDuration;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    @JsonProperty("supplierSession")
    public String getSupplierSession() {
        return supplierSession;
    }

    @JsonProperty("supplierSession")
    public void setSupplierSession(String supplierSession) {
        this.supplierSession = supplierSession;
    }

    @JsonProperty("session")
    public String getSession() {
        return session;
    }

    @JsonProperty("session")
    public void setSession(String session) {
        this.session = session;
    }

    @JsonProperty("operatingFromDate")
    public String getOperatingFromDate() {
        return operatingFromDate;
    }

    @JsonProperty("operatingFromDate")
    public void setOperatingFromDate(String operatingFromDate) {
        this.operatingFromDate = operatingFromDate;
    }

    @JsonProperty("operatingToDate")
    public String getOperatingToDate() {
        return operatingToDate;
    }

    @JsonProperty("operatingToDate")
    public void setOperatingToDate(String operatingToDate) {
        this.operatingToDate = operatingToDate;
    }

    @JsonProperty("departureCode")
    public Object getDepartureCode() {
        return departureCode;
    }

    @JsonProperty("departureCode")
    public void setDepartureCode(Object departureCode) {
        this.departureCode = departureCode;
    }

    @JsonProperty("departurePoint")
    public Object getDeparturePoint() {
        return departurePoint;
    }

    @JsonProperty("departurePoint")
    public void setDeparturePoint(Object departurePoint) {
        this.departurePoint = departurePoint;
    }
}
