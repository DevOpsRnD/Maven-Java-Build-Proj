package com.cnk.siapi.productstatic.response;

public class ClassificationAttrributes
{
    private String value;

    private String type;

    private String group;

    public String getValue ()
    {
        return value;
    }

    public void setValue (String value)
    {
        this.value = value;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getGroup ()
    {
        return group;
    }

    public void setGroup (String group)
    {
        this.group = group;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [value = "+value+", type = "+type+", group = "+group+"]";
    }
}