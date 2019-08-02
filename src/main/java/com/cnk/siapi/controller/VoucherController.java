package com.cnk.siapi.controller;

import java.io.FileOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cnk.siapi.common.Constants;
import com.cnk.siapi.common.LoadClassesAuto;
import com.cnk.siapi.model.book.BookingDetails;
import com.cnk.siapi.model.storerequst.BookRequestResponse;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Service
public class VoucherController {
	
	@Autowired
	private LoadClassesAuto loadClassesAuto;
	protected final Log logger = LogFactory.getLog(this.getClass());
	
/*	public static void main(String args[])
	{
		 
	}*/
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public void createPdf(String dest, String bookingRef, String userId) {
		
//		SupplierOperations operation = new SupplierOperations();
//		MongoDBOperation mongoOperation = new MongoDBOperation();
    	logger.info("Voucher Destination " + dest);
		try {
			BookingDetails bookingDetails = loadClassesAuto.supplierOperations.getBookingInfo(bookingRef);
			JSONObject bookRSJson = (JSONObject) loadClassesAuto.supplierOperations.getBookingData(bookingRef);
			JSONObject bookRQJson = (JSONObject) loadClassesAuto.supplierOperations.getBookingRequest(bookingRef);
			logger.info(bookingDetails);
			BookRequestResponse bookingRequestResponse = loadClassesAuto.mongoDBOperation.findByBookingId(bookingRef);
			JSONObject repriceRSJson = loadClassesAuto.supplierOperations.getRepriceResponse(bookingRequestResponse.getAccessToken());
			// Get the userCode from the Mongo Database
			logger.info(repriceRSJson);
			String code = (String) loadClassesAuto.supplierOperations.getUserCode(userId);
			
			// Connect to Admin Panel and get the Agent Information
			RestTemplate template = new RestTemplate();
			
			HttpHeaders headers = new HttpHeaders();
			headers.set("usercode", code);
			HttpEntity entity = new HttpEntity(headers);
			System.out.println(loadClassesAuto.applicationProperties.getProperty(Constants.GETUSERDETAILSBYCODE));
			ResponseEntity<String> agentEntity = template
			//								.getForEntity(loadClassesAuto.mongoDBOperation.getURL(Constants.SEARCHCITY_BYCODE_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT) ) + regionCode, String.class);
					.exchange(loadClassesAuto.applicationProperties.getProperty(Constants.GETUSERDETAILSBYCODE),HttpMethod.GET, entity, String.class);
			JSONObject agentDetails = new JSONObject(agentEntity.getBody().toString());

			Font font = new Font(FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
			Font midfont = new Font(FontFamily.HELVETICA, 10);
			Font midboldfont = new Font(FontFamily.TIMES_ROMAN, 10, 1);
			Font smallboldfont = new Font(FontFamily.TIMES_ROMAN, 8, 1);
			Font servicefont = new Font(FontFamily.TIMES_ROMAN, 10, 1);
			Font serviceline = new Font(FontFamily.COURIER, 8);
			Font emeHdrFont = new Font(FontFamily.COURIER, 12, 1);
			emeHdrFont.setColor(51, 102, 153);
			servicefont.setColor(36, 49, 81);
			Document document = new Document();
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
			document.open();
			PdfPTable table = new PdfPTable(2);
			table.setTotalWidth(new float[] { 350, 175 });
			table.setLockedWidth(true);
			// PdfContentByte cb = writer.getDirectContent();
			// first row
			PdfPCell cell;
			URL logoPath = this.getClass().getClassLoader().getResource("logo-cnk.png");
			Image cnkLogo = Image.getInstance(logoPath);
			cell = new PdfPCell(cnkLogo, true);
			cell.setPaddingLeft(35);
			cell.setPaddingBottom(5);
			// cell.setPaddingTop(5);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(65);
			cell.setRowspan(2);
			table.addCell(cell);
			//
			cell = new PdfPCell(new Phrase("Service Voucher", midfont));
			// cell.setFixedHeight(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingRight(15);
			cell.setBorder(Rectangle.NO_BORDER);
			table.addCell(cell);

			DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
			String tourDetails = "Date of Issue:" + dateFormat.format(new Date());
			cell = new PdfPCell(new Phrase(tourDetails, midfont));
			cell.setFixedHeight(50);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setPaddingRight(15);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			table.addCell(cell);

			// second row
			cell = new PdfPCell(new Phrase("Booking Ref: " + bookingRef, smallboldfont));
			cell.setFixedHeight(25);
			cell.setColspan(1);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.TOP);
//			cell.setBorderWidth(1);
			table.addCell(cell);

			// third row
			String supplierBookingId = bookingDetails.getSupplierBookingId();
			cell = new PdfPCell(new Phrase("Supplier Book Ref: " + supplierBookingId, smallboldfont));
			cell.setColspan(1);
			cell.setFixedHeight(25);
//			cell.setBorderWidth(1);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingRight(5);
			cell.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT | Rectangle.TOP);
			table.addCell(cell);
			// Blank Data Fourth Row
			cell = new PdfPCell(new Phrase());
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Body Start Fifth Row
			JSONObject obj = new JSONObject(bookRQJson.toString());

			
			String firstName = obj.getJSONObject("bookRQBody").getString("contactFirstName");
			String nameTitle = obj.getJSONObject("bookRQBody").getString("contactTitle");
			String lastName = obj.getJSONObject("bookRQBody").getString("contactSurname");
			
			cell = new PdfPCell(new Phrase(nameTitle + " "+ firstName + " " + lastName, midboldfont));
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Blank Data 6th Row
			cell = new PdfPCell(new Phrase());
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// 7th Row
			cell = new PdfPCell(new Phrase("Please provide following services as listed below: ", font));
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Blank Data 8th Row
			cell = new PdfPCell(new Phrase());
			cell.setColspan(2);
			cell.setRowspan(2);
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);

			// Service Table
			PdfPTable serviceTable = new PdfPTable(4);
			serviceTable.setTotalWidth(520);
			serviceTable.setSpacingBefore(10);
			serviceTable.setLockedWidth(true);
			PdfPCell serviceCell;

			serviceCell = new PdfPCell(new Phrase("Sightseeing Service", smallboldfont));
//			serviceCell.setUseVariableBorders(true);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceTable.addCell(serviceCell);

			serviceCell = new PdfPCell(new Phrase("City", smallboldfont));
//			serviceCell.setUseVariableBorders(true);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceTable.addCell(serviceCell);

			serviceCell = new PdfPCell(new Phrase("Date", smallboldfont));
//			serviceCell.setUseVariableBorders(true);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceTable.addCell(serviceCell);

			serviceCell = new PdfPCell(new Phrase("No. of Passengers", smallboldfont));
//			serviceCell.setUseVariableBorders(true);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceTable.addCell(serviceCell);

			serviceTable.completeRow();

			JSONObject bookObj = new JSONObject(bookRQJson.toString());
		
			String tourName = bookObj.getJSONObject("bookRQBody").getString("activityName");
			
			JSONArray supplierIdArry = bookObj.getJSONObject("requestHeader").getJSONArray("supplierDao");
			JSONObject supplierObj = (JSONObject) supplierIdArry.get(0);
			String tourSupplier = supplierObj.getString("supplierId");
			String productCode = bookObj.getJSONObject("bookRQBody").getString("supplierProductCode");
			String time = bookObj.getJSONObject("bookRQBody").getString("startTime");
			serviceCell = new PdfPCell(new Phrase(tourName + "\nTourSupplier : " + tourSupplier
					+ "\nSupplier Tour No. : " + productCode + "\nTime : " + time, serviceline));
			serviceCell.setColspan(1);
			serviceCell.setUseVariableBorders(true);
			serviceTable.addCell(serviceCell);

			//Get City Name for City Code
			/*String cityCode = bookObj.getJSONObject("SightSeeingInterfaceRQ").getJSONObject("RequestBody").getJSONObject("OTA_TourActivityBookRQWrapper").
					getJSONObject("OTA_TourActivityBookRQ").getJSONObject("BookingInfo").getJSONObject("Location").getJSONObject("Region").getString("RegionCode");
			*/
			String cityUrl = loadClassesAuto.mongoDBOperation.getURL(Constants.GETCITYNAME_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT));
			String cityCode =  bookObj.getJSONObject("bookRQBody").getString("regionCode");
			ResponseEntity<String> responseEntity = template
//					.getForEntity(loadClassesAuto.mongoDBOperation.getURL(Constants.SEARCHCITY_BYCODE_URL, loadClassesAuto.applicationProperties.getProperty(Constants.ENVIRONMENT) ) + regionCode, String.class);
					.getForEntity(cityUrl + "CityCode/" + cityCode + "/SupplierCode/" + tourSupplier, String.class);
			
			
			String response = responseEntity.getBody();
			JSONArray supplierCodeArray = new JSONArray(response);
			
			JSONObject cityArray = supplierCodeArray.getJSONObject(0);
			
			String city = cityArray.getString("cityName");
			serviceCell = new PdfPCell(new Phrase(city, serviceline));
			serviceCell.setColspan(1);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceCell.setVerticalAlignment(Element.ALIGN_CENTER);
			serviceCell.setColspan(Element.ALIGN_LEFT);
//			serviceCell.setUseVariableBorders(true);
			serviceTable.addCell(serviceCell);
			
			/*String date = bookObj.getJSONObject("SightSeeingInterfaceRQ").getJSONObject("RequestBody").getJSONObject("OTA_TourActivityBookRQWrapper").
					getJSONObject("OTA_TourActivityBookRQ").getJSONObject("BookingInfo").getJSONObject("Schedule").getString("Start");
			*/
			String date = bookObj.getJSONObject("bookRQBody").getString("scheduledStartDate");
			serviceCell = new PdfPCell(new Phrase(date, serviceline));
			serviceCell.setColspan(1);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceCell.setVerticalAlignment(Element.ALIGN_CENTER);
//			serviceCell.setUseVariableBorders(true);
			serviceTable.addCell(serviceCell);
			Integer paxSize;
			try
			{
				JSONArray paxCount = bookObj.getJSONObject("bookRQBody").getJSONArray("participantInfo");
				paxSize = paxCount.length();
			}
			catch(JSONException ex)
			{
				ex.printStackTrace();
				paxSize = 1;
			}
			
			serviceCell = new PdfPCell(new Phrase(paxSize.toString(), serviceline));
			serviceCell.setColspan(1);
			serviceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			serviceCell.setVerticalAlignment(Element.ALIGN_CENTER);
//			serviceCell.setUseVariableBorders(true);
			serviceTable.addCell(serviceCell);
			serviceTable.completeRow();

			// Booking Summary Table
			PdfPTable pickUpTable = new PdfPTable(4);
			pickUpTable.setSpacingBefore(20);
			pickUpTable.setTotalWidth(520);
			pickUpTable.setLockedWidth(true);

			PdfPCell pickUpCell = new PdfPCell(new Phrase("Pick up Details", smallboldfont));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			pickUpCell = new PdfPCell(new Phrase("Duration", smallboldfont));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			pickUpCell = new PdfPCell(new Phrase("Commentary Language", smallboldfont));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			pickUpCell = new PdfPCell(new Phrase("Type of Tour", smallboldfont));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			// 2nd Row

			JSONObject bookRSObj = new JSONObject(bookRSJson.toString());
			boolean isPickupDropAvailable = bookRSObj.getJSONObject("SightSeeingInterfaceRS").getJSONObject("ResponseBody").getJSONObject("OTA_TourActivityBookRSWrapper").
					getJSONObject("OTA_TourActivityBookRS").getJSONObject("ReservationDetails").getJSONObject("PickupDropoff").getBoolean("available");
			String departureTime = "";
			String departurePoint = "";
			String departureAddress = "";
			
			if(isPickupDropAvailable == true)
			{
				departureTime = bookRSObj.getJSONObject("SightSeeingInterfaceRS").getJSONObject("ResponseBody").getJSONObject("OTA_TourActivityBookRSWrapper").
						getJSONObject("OTA_TourActivityBookRS").getJSONObject("ReservationDetails").getJSONObject("PickupDropoff").getString("DateTime");
				
				String[] deptTime = departureTime.split("T");
				departureTime = deptTime[deptTime.length - 1];
				
				departurePoint = bookRSObj.getJSONObject("SightSeeingInterfaceRS").getJSONObject("ResponseBody").getJSONObject("OTA_TourActivityBookRSWrapper").
						getJSONObject("OTA_TourActivityBookRS").getJSONObject("ReservationDetails").getJSONObject("PickupDropoff").getString("LocationName");
				
				departureAddress = bookRSObj.getJSONObject("SightSeeingInterfaceRS").getJSONObject("ResponseBody").getJSONObject("OTA_TourActivityBookRSWrapper").
						getJSONObject("OTA_TourActivityBookRS").getJSONObject("ReservationDetails").getJSONObject("Location").getJSONObject("Region").getString("RegionName");
				
			}
			pickUpCell = new PdfPCell(new Phrase("Departure Time : " + departureTime + "\nDeparture Point : "
					+ departurePoint + "\nDeparture Address : " + departureAddress, serviceline));
			pickUpCell.setColspan(1);
			pickUpTable.addCell(pickUpCell);

			String duration = "";
			pickUpCell = new PdfPCell(new Phrase(duration, serviceline));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpCell.setVerticalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			String tourLanguage = "";
			pickUpCell = new PdfPCell(new Phrase(tourLanguage, serviceline));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpCell.setVerticalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);

			String tourType = "";
			pickUpCell = new PdfPCell(new Phrase(tourType, serviceline));
			pickUpCell.setColspan(1);
			pickUpCell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pickUpCell.setVerticalAlignment(Element.ALIGN_CENTER);
			pickUpTable.addCell(pickUpCell);
			String supplierName = tourSupplier;
			if(tourSupplier.equals("GTA"))
			{
				supplierName = "Gullivers Travel Associates";
			}
			Chunk bookedChunk = new Chunk("Booked & Paid by : " + supplierName, smallboldfont);
			Paragraph bookedPara = new Paragraph(bookedChunk);
			bookedPara.setAlignment(Element.ALIGN_LEFT);
			bookedPara.setSpacingBefore(2);
			bookedPara.setSpacingAfter(4);
			
			
			
			String bookAgent = agentDetails.getJSONObject("companydetails").getString("companyName");
			Chunk agentChunk = new Chunk("Booked Through : " + bookAgent, smallboldfont);
			Paragraph agentPara = new Paragraph(agentChunk);
			agentPara.setSpacingAfter(4);

			Chunk emergencyChunk = new Chunk("Emergency Contact Details : ", smallboldfont);
			Paragraph emergencyPara = new Paragraph(emergencyChunk);

			JSONArray companyDetailsArry = agentDetails.getJSONArray("companyaddress");
			JSONObject companyDetails = (JSONObject) companyDetailsArry.get(0);
			String contactName = companyDetails.getString("preferredContact");
			String contactAddress = bookAgent + "\n" + companyDetails.getString("addressLine1") + " ," + companyDetails.getString("addressLine2") + " ," +
					 companyDetails.getString("city");
		
			Chunk contactNameChunk = new Chunk("Contact Person : " + contactName, serviceline);
			Paragraph contactPara = new Paragraph(contactNameChunk);

			Chunk contactAddChunk = new Chunk(contactAddress, serviceline);
			Paragraph addressPara = new Paragraph(contactAddChunk);

			Paragraph infoPara = new Paragraph(new Chunk(
					"In the event of a problem, it is essential and most practical to try to resolve it locally with the provider of the service (as noted above). We recommend you inform the service provider immediately, or contact us if that fails.",
					serviceline));
			infoPara.setSpacingBefore(4.0f);

			Paragraph blankPara2 = new Paragraph("\n \n");
			Paragraph blankPara = new Paragraph("\n");

			Paragraph tourDescriptionPara = new Paragraph(new Chunk("Tour Description : ", smallboldfont));

			String tourDescription;
			try {
				JSONArray repriceTourArry = repriceRSJson.getJSONObject("responseBody").getJSONObject("otaTourActivityAvailRSWrapper").
						getJSONObject("otaTourActivityAvailRS").getJSONArray("tourActivityInfo");
				JSONObject repricTourObj = (JSONObject) repriceTourArry.get(0);
				 tourDescription = repricTourObj.getJSONObject("description").getString("shortDescription");
			} catch(Exception e ) {
//			} catch (org.json.JSONException e) {
				// TODO Auto-generated catch block
				logger.info("Tour Description not found");
				tourDescription = "";
			}
			Paragraph tourDescDataPara = new Paragraph(new Chunk(tourDescription, serviceline));

			Paragraph inclusionPara = new Paragraph(new Chunk("Inclusions : ", smallboldfont));

			String inclusions = "";
			/*Paragraph inclusionDataPara = new Paragraph(new Chunk(inclusions, serviceline));*/

			Paragraph exclusionsPara = new Paragraph("Exclusions : ", smallboldfont);

			String exclusions = "";
			/*Paragraph exclusionDataPara = new Paragraph(new Chunk(exclusions, serviceline));
			Paragraph thingsPara = new Paragraph(new Chunk("Things you need to know : ", smallboldfont));*/

			JSONArray participantInfo =  bookObj.getJSONObject("bookRQBody").getJSONArray("participantInfo");	
			
			PdfPTable participantTable = new PdfPTable(3);
			participantTable.setTotalWidth(520);
			participantTable.setSpacingBefore(10);
			participantTable.setLockedWidth(true);
			
			PdfPCell participantName;
			
			participantName = new PdfPCell(new Phrase("Participant Name", smallboldfont));
			participantName.setColspan(1);
			participantName.setHorizontalAlignment(Element.ALIGN_CENTER);
			participantTable.addCell(participantName);
			
			participantName = new PdfPCell(new Phrase("Age", smallboldfont));
			participantName.setColspan(1);
			participantName.setHorizontalAlignment(Element.ALIGN_CENTER);
			participantTable.addCell(participantName);
			
			participantName = new PdfPCell(new Phrase("Category", smallboldfont));
			participantName.setColspan(1);
			participantName.setHorizontalAlignment(Element.ALIGN_CENTER);
			participantTable.addCell(participantName);
			
			Paragraph participant = new Paragraph(new Chunk("Participant Information: ", smallboldfont));
			Paragraph participantDetails = new Paragraph();
			
			for(int i=0;i<participantInfo.length();i++) {
				JSONObject json= participantInfo.getJSONObject(i);
				String title = json.getString("participantTitle");
				String fname = json.getString("participantGivenName");
				String mname = json.getString("participantMiddleName");
				String lname = json.getString("participantSurname");
				String age = Integer.toString(json.getInt("participantAge"));
				String category = json.getString("participantQualifierInfo");
				
				
				participantName = new PdfPCell(new Phrase(title + " " + fname
						+ " " + lname + " " + mname, smallboldfont));
				participantName.setUseVariableBorders(true);
				participantName.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				participantTable.addCell(participantName);
				
				participantName = new PdfPCell(new Phrase(age, smallboldfont));
				participantName.setUseVariableBorders(true);
				participantName.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				participantTable.addCell(participantName);
				
				participantName = new PdfPCell(new Phrase(category, smallboldfont));
				participantName.setUseVariableBorders(true);
				participantName.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT );
				participantTable.addCell(participantName);
				
			}
			
			Paragraph notesPara = new Paragraph(new Chunk("Notes : ", smallboldfont));
			Paragraph extraCollectPara = new Paragraph(
					new Chunk("All extras to be collected directly from guest.", serviceline));
			Paragraph voucherTransPara = new Paragraph(new Chunk(
					"This Voucher is not transferable. It is system generated hence does not require any signature.",
					serviceline));
			Paragraph contactNotePara = new Paragraph(
					new Chunk("You must call 1-212-642-0915 at least 24 hours in advance to reconfirm your tour.", serviceline));

			Paragraph finalCompanyPara = new Paragraph("Company Name : " + bookAgent, smallboldfont);
			finalCompanyPara.setAlignment(Element.ALIGN_CENTER);
			finalCompanyPara.setSpacingBefore(10);

			Paragraph companyAddressPara = new Paragraph("Address : " + contactAddress , smallboldfont);
			companyAddressPara.setAlignment(Element.ALIGN_CENTER);
			
			Paragraph companyPhonePara = new Paragraph("Phone : " + agentDetails.getJSONObject("companydetails").getString("companyPhone1"), smallboldfont);
			companyPhonePara.setAlignment(Element.ALIGN_CENTER);
			
			document.add(table);
			document.add(serviceTable);
			document.add(pickUpTable);
			document.add(bookedPara);
			document.add(agentPara);
			document.add(emergencyPara);
			document.add(contactPara);
			document.add(addressPara);
			//document.add(blankPara);
			document.add(infoPara);
			document.add(blankPara);
			document.add(tourDescriptionPara);
			document.add(tourDescDataPara);
			document.add(blankPara);
			document.add(inclusionPara);
			//document.add(inclusionDataPara);
			document.add(blankPara);
			document.add(exclusionsPara);
			//document.add(exclusionDataPara);
			//document.add(blankPara);
			//document.add(thingsPara);
			document.add(blankPara);
			document.add(participant);
			document.add(participantTable);
			document.add(participantDetails);
			document.add(blankPara);
			document.add(notesPara);
			document.add(extraCollectPara);
			document.add(voucherTransPara);
			if(tourSupplier.equals("GTA"))
			{
				document.add(contactNotePara);
			}
			document.add(blankPara);
			document.add(finalCompanyPara);
			document.add(companyAddressPara);
			document.add(companyPhonePara);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
}
