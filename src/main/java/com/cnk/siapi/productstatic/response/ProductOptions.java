package com.cnk.siapi.productstatic.response;


public class ProductOptions
{
    private String systemActivityOptionCode;

    private String languageCode;

    private String dealText;

    private String language;

    private String activityType;

    private ClassificationAttrributes[] classificationAttrributes;

    private String options;

    private String optionCode;

    public String getSystemActivityOptionCode ()
    {
        return systemActivityOptionCode;
    }

    public void setSystemActivityOptionCode (String systemActivityOptionCode)
    {
        this.systemActivityOptionCode = systemActivityOptionCode;
    }

    public String getLanguageCode ()
    {
        return languageCode;
    }

    public void setLanguageCode (String languageCode)
    {
        this.languageCode = languageCode;
    }

    public String getDealText ()
    {
        return dealText;
    }

    public void setDealText (String dealText)
    {
        this.dealText = dealText;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getActivityType ()
    {
        return activityType;
    }

    public void setActivityType (String activityType)
    {
        this.activityType = activityType;
    }

    public ClassificationAttrributes[] getClassificationAttrributes ()
    {
        return classificationAttrributes;
    }

    public void setClassificationAttrributes (ClassificationAttrributes[] classificationAttrributes)
    {
        this.classificationAttrributes = classificationAttrributes;
    }

    public String getOptions ()
    {
        return options;
    }

    public void setOptions (String options)
    {
        this.options = options;
    }

    public String getOptionCode ()
    {
        return optionCode;
    }

    public void setOptionCode (String optionCode)
    {
        this.optionCode = optionCode;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [systemActivityOptionCode = "+systemActivityOptionCode+", languageCode = "+languageCode+", dealText = "+dealText+", language = "+language+", activityType = "+activityType+", classificationAttrributes = "+classificationAttrributes+", options = "+options+", optionCode = "+optionCode+"]";
    }
}