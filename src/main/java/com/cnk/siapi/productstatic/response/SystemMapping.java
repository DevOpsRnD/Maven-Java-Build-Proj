package com.cnk.siapi.productstatic.response;

public class SystemMapping
{
    private String systemName;

    private String systemID;

    public String getSystemName ()
    {
        return systemName;
    }

    public void setSystemName (String systemName)
    {
        this.systemName = systemName;
    }

    public String getSystemID ()
    {
        return systemID;
    }

    public void setSystemID (String systemID)
    {
        this.systemID = systemID;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [systemName = "+systemName+", systemID = "+systemID+"]";
    }
}