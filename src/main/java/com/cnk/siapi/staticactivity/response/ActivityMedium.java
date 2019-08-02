
package com.cnk.siapi.staticactivity.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mediaType",
    "mediaSubType",
    "fullUrl",
    "thumbUrl",
    "sortOrder",
    "description",
    "width",
    "height",
    "caption"
})
public class ActivityMedium {

    @JsonProperty("mediaType")
    private String mediaType;
    @JsonProperty("mediaSubType")
    private String mediaSubType;
    @JsonProperty("fullUrl")
    private String fullUrl;
    @JsonProperty("thumbUrl")
    private String thumbUrl;
    @JsonProperty("sortOrder")
    private String sortOrder;
    @JsonProperty("description")
    private Object description;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("caption")
    private Object caption;

    @JsonProperty("mediaType")
    public String getMediaType() {
        return mediaType;
    }

    @JsonProperty("mediaType")
    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    @JsonProperty("mediaSubType")
    public String getMediaSubType() {
        return mediaSubType;
    }

    @JsonProperty("mediaSubType")
    public void setMediaSubType(String mediaSubType) {
        this.mediaSubType = mediaSubType;
    }

    @JsonProperty("fullUrl")
    public String getFullUrl() {
        return fullUrl;
    }

    @JsonProperty("fullUrl")
    public void setFullUrl(String fullUrl) {
        this.fullUrl = fullUrl;
    }

    @JsonProperty("thumbUrl")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("thumbUrl")
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @JsonProperty("sortOrder")
    public String getSortOrder() {
        return sortOrder;
    }

    @JsonProperty("sortOrder")
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    @JsonProperty("description")
    public Object getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Object description) {
        this.description = description;
    }

    @JsonProperty("width")
    public Integer getWidth() {
        return width;
    }

    @JsonProperty("width")
    public void setWidth(Integer width) {
        this.width = width;
    }

    @JsonProperty("height")
    public Integer getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(Integer height) {
        this.height = height;
    }

    @JsonProperty("caption")
    public Object getCaption() {
        return caption;
    }

    @JsonProperty("caption")
    public void setCaption(Object caption) {
        this.caption = caption;
    }
}
