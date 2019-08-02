package com.cnk.siapi.model.book;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import com.cnk.siapi.model.requestheader.RequestHeaderDAO;



public class BookRequest 
{
	@XmlPath("/SightSeeingInterfaceRQ/@xmlns")
	private String sightSeeing = "http://www.coxandkings.com/scota";
	@XmlPath("/SightSeeingInterfaceRQ/RequestHeader")
	private RequestHeaderDAO requestHeader;
	@XmlPath("/SightSeeingInterfaceRQ/RequestBody")
	private  BookRQBody bookRQBody;
	
	public String getSightSeeing() {
		return sightSeeing;
	}
	public void setSightSeeing(String sightSeeing) {
		this.sightSeeing = sightSeeing;
	}
	public RequestHeaderDAO getRequestHeader() {
		return requestHeader;
	}
	public void setRequestHeader(RequestHeaderDAO requestHeader) {
		this.requestHeader = requestHeader;
	}
	public BookRQBody getBookRQBody() {
		return bookRQBody;
	}
	public void setBookRQBody(BookRQBody bookRQBody) {
		this.bookRQBody = bookRQBody;
	}
	


}
