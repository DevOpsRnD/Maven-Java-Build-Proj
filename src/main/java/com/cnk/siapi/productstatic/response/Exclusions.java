package com.cnk.siapi.productstatic.response;

public class Exclusions
{
    private String description;

    private String name;

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [description = "+description+", name = "+name+"]";
    }
}