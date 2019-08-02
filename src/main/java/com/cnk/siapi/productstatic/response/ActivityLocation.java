package com.cnk.siapi.productstatic.response;

public class ActivityLocation
{
    private String area;

    private String location;

    private String address;

    private String longitude;

    private String latitude;

    public String getArea ()
    {
        return area;
    }

    public void setArea (String area)
    {
        this.area = area;
    }

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getLongitude ()
    {
        return longitude;
    }

    public void setLongitude (String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude ()
    {
        return latitude;
    }

    public void setLatitude (String latitude)
    {
        this.latitude = latitude;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [area = "+area+", location = "+location+", address = "+address+", longitude = "+longitude+", latitude = "+latitude+"]";
    }
}
		