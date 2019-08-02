package com.cnk.siapi.productstatic.response;

public class TourGuideLanguages
{
    private String languageID;

    private String language;

    public String getLanguageID ()
    {
        return languageID;
    }

    public void setLanguageID (String languageID)
    {
        this.languageID = languageID;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [languageID = "+languageID+", language = "+language+"]";
    }
}