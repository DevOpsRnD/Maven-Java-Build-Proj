package com.cnk.siapi.productstatic.response;

public class ActivityMedia
{
    private String height;

    private String fullUrl;

    private String description;

    private String sortOrder;

    private String width;

    private String mediaSubType;

    private String thumbUrl;

    private String caption;

    private String mediaType;

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getFullUrl ()
    {
        return fullUrl;
    }

    public void setFullUrl (String fullUrl)
    {
        this.fullUrl = fullUrl;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getSortOrder ()
    {
        return sortOrder;
    }

    public void setSortOrder (String sortOrder)
    {
        this.sortOrder = sortOrder;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getMediaSubType ()
    {
        return mediaSubType;
    }

    public void setMediaSubType (String mediaSubType)
    {
        this.mediaSubType = mediaSubType;
    }

    public String getThumbUrl ()
    {
        return thumbUrl;
    }

    public void setThumbUrl (String thumbUrl)
    {
        this.thumbUrl = thumbUrl;
    }

    public String getCaption ()
    {
        return caption;
    }

    public void setCaption (String caption)
    {
        this.caption = caption;
    }

    public String getMediaType ()
    {
        return mediaType;
    }

    public void setMediaType (String mediaType)
    {
        this.mediaType = mediaType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [height = "+height+", fullUrl = "+fullUrl+", description = "+description+", sortOrder = "+sortOrder+", width = "+width+", mediaSubType = "+mediaSubType+", thumbUrl = "+thumbUrl+", caption = "+caption+", mediaType = "+mediaType+"]";
    }
}
			
			