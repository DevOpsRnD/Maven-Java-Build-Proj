package com.cnk.siapi.productstatic.response;

public class Deals
{
    private String dealPrice;

    private String offerTermsAndConditions;

    private String dealText;

    private String dealId;

    private String currency;

    public String getDealPrice ()
    {
        return dealPrice;
    }

    public void setDealPrice (String dealPrice)
    {
        this.dealPrice = dealPrice;
    }

    public String getOfferTermsAndConditions ()
    {
        return offerTermsAndConditions;
    }

    public void setOfferTermsAndConditions (String offerTermsAndConditions)
    {
        this.offerTermsAndConditions = offerTermsAndConditions;
    }

    public String getDealText ()
    {
        return dealText;
    }

    public void setDealText (String dealText)
    {
        this.dealText = dealText;
    }

    public String getDealId ()
    {
        return dealId;
    }

    public void setDealId (String dealId)
    {
        this.dealId = dealId;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dealPrice = "+dealPrice+", offerTermsAndConditions = "+offerTermsAndConditions+", dealText = "+dealText+", dealId = "+dealId+", currency = "+currency+"]";
    }
}
