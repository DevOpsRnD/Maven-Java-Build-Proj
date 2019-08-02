package com.cnk.siapi.supplier;

import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "updatedbookingdata")
public class UpdateBooking
{
    private String userID;

    private String status;

    private String suppBookRef;

    public String getUserID ()
    {
        return userID;
    }

    public void setUserID (String userID)
    {
        this.userID = userID;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getSuppBookRef ()
    {
        return suppBookRef;
    }

    public void setSuppBookRef (String suppBookRef)
    {
        this.suppBookRef = suppBookRef;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [userID = "+userID+", status = "+status+", suppBookRef = "+suppBookRef+"]";
    }
}
