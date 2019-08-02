package com.cnk.siapi.productstatic.response;

public class BookingPolicies
{
    private String infoText;

    private String infoType;

    public String getInfoText ()
    {
        return infoText;
    }

    public void setInfoText (String infoText)
    {
        this.infoText = infoText;
    }

    public String getInfoType ()
    {
        return infoType;
    }

    public void setInfoType (String infoType)
    {
        this.infoType = infoType;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [infoText = "+infoText+", infoType = "+infoType+"]";
    }
}