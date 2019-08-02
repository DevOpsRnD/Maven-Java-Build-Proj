package com.cnk.siapi.productstatic.response;

public class SupplierCityDepartureCodes
{
    private String hotelCode;

    private String hotelName;

    private String cityName;

    private String cityCode;

    private String departureCode;

    private String departureName;

    public String getHotelCode ()
    {
        return hotelCode;
    }

    public void setHotelCode (String hotelCode)
    {
        this.hotelCode = hotelCode;
    }

    public String getHotelName ()
    {
        return hotelName;
    }

    public void setHotelName (String hotelName)
    {
        this.hotelName = hotelName;
    }

    public String getCityName ()
    {
        return cityName;
    }

    public void setCityName (String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityCode ()
    {
        return cityCode;
    }

    public void setCityCode (String cityCode)
    {
        this.cityCode = cityCode;
    }

    public String getDepartureCode ()
    {
        return departureCode;
    }

    public void setDepartureCode (String departureCode)
    {
        this.departureCode = departureCode;
    }

    public String getDepartureName ()
    {
        return departureName;
    }

    public void setDepartureName (String departureName)
    {
        this.departureName = departureName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hotelCode = "+hotelCode+", hotelName = "+hotelName+", cityName = "+cityName+", cityCode = "+cityCode+", departureCode = "+departureCode+", departureName = "+departureName+"]";
    }
}