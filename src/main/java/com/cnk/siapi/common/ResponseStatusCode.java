package com.cnk.siapi.common;

public enum ResponseStatusCode
{
	Confirmed("confirmed"),
	OnRequest("onrequest"),
	Rejected("rejected"),
	Waiting("waiting"),
	Cancelled("cancelled"),
	Reserved("reserved"),
	BookingID("BookingID"),
	InvoiceCode("InvoiceCode"),
	FileNumber("FileNumber");
	
	
	private String value;

	ResponseStatusCode(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}