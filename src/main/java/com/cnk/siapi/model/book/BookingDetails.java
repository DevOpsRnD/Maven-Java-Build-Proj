package com.cnk.siapi.model.book;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cnk.siapi.response.CommonBookRS.ResponseBody.OTATourActivityBookRSWrapper.OTATourActivityBookRS.ReservationDetails.Confirmation;

@Document(collection = "bookingdetails")
public class BookingDetails {

	String bookingId;
	String supplierBookingId;
	String bookingStatus;
	Integer bookingType;
	Date bookingDate;
	String description;
	String supplierId;
	String supplierName;
	String username;
	String accessToken;
	List<Confirmation> otherBookInfo;
	
	public BookingDetails() {
		super();
	}

	public BookingDetails(String bookingId, String supplierBookingId, String bookingStatus, Integer bookingType,
			Date bookingDate, String description, String supplierId, String supplierName, String username,
			String accessToken, List<Confirmation> otherBookInfo) {
		super();
		this.bookingId = bookingId;
		this.supplierBookingId = supplierBookingId;
		this.bookingStatus = bookingStatus;
		this.bookingType = bookingType;
		this.bookingDate = bookingDate;
		this.description = description;
		this.supplierId = supplierId;
		this.supplierName = supplierName;
		this.username = username;
		this.accessToken = accessToken;
		this.otherBookInfo = otherBookInfo;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public String getSupplierBookingId() {
		return supplierBookingId;
	}

	public void setSupplierBookingId(String supplierBookingId) {
		this.supplierBookingId = supplierBookingId;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Integer getBookingType() {
		return bookingType;
	}

	public void setBookingType(Integer bookingType) {
		this.bookingType = bookingType;
	}

	public String getBookingDate() {
		return new SimpleDateFormat("dd-MM-yyyy").format(bookingDate);
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public List<Confirmation> getOtherBookInfo() {
		return otherBookInfo;
	}

	public void setOtherBookInfo(List<Confirmation> oldConfirmationList) {
		this.otherBookInfo = oldConfirmationList;
	}

}
