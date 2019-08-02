
package com.cnk.siapi.staticactivity.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "totalNumberOfActivities",
    "pageSize",
    "currentPage",
    "totalPage",
    "message",
    "activities"
})
public class StaticActivityRS {

    @JsonProperty("totalNumberOfActivities")
    private Integer totalNumberOfActivities;
    @JsonProperty("pageSize")
    private Integer pageSize;
    @JsonProperty("currentPage")
    private Integer currentPage;
    @JsonProperty("totalPage")
    private Integer totalPage;
    @JsonProperty("message")
    private Object message;
    @JsonProperty("activities")
    private List<Activity> activities = null;

    @JsonProperty("totalNumberOfActivities")
    public Integer getTotalNumberOfActivities() {
        return totalNumberOfActivities;
    }

    @JsonProperty("totalNumberOfActivities")
    public void setTotalNumberOfActivities(Integer totalNumberOfActivities) {
        this.totalNumberOfActivities = totalNumberOfActivities;
    }

    @JsonProperty("pageSize")
    public Integer getPageSize() {
        return pageSize;
    }

    @JsonProperty("pageSize")
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @JsonProperty("currentPage")
    public Integer getCurrentPage() {
        return currentPage;
    }

    @JsonProperty("currentPage")
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    @JsonProperty("totalPage")
    public Integer getTotalPage() {
        return totalPage;
    }

    @JsonProperty("totalPage")
    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    @JsonProperty("message")
    public Object getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(Object message) {
        this.message = message;
    }

    @JsonProperty("activities")
    public List<Activity> getActivities() {
        return activities;
    }

    @JsonProperty("activities")
    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
