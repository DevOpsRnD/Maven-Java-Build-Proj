package com.cnk.siapi.model.requestheader;

/*+----------------------------------------------------------------------
||
||  Class RequestHeaderDAO
||
||         Author:  Durgesh Songire
||
||        Purpose:  Class having all the data about Request Header 
||
||  Inherits From:  None
||                  
||     Interfaces:  None                   
||
|+-----------------------------------------------------------------------
||
||      Constants:  userId, sessionId, transactionId
|+-----------------------------------------------------------------------
||
||   Constructors:  None
||
||  Class Methods:  getSupplierDao, setSupplierDao, getTransactionId, 
||					setTransactionId, getUserId, setUserId, getSessionId,
||					setSessionId
||				
|+------------------------------------------------------------------------
|| 
||	Creation Date: 24/10/2017
++-----------------------------------------------------------------------*/

import java.util.List;
import javax.xml.bind.annotation.XmlType;
import org.eclipse.persistence.oxm.annotations.XmlPath;


@XmlType(propOrder= {"userId","sessionId","transactionId","supplierDao"})
public class RequestHeaderDAO 
{
	@XmlPath("/UserID/text()")
	private String userId;
	@XmlPath("/SessionID/text()")
	private String sessionId;
	@XmlPath("/TransactionID/text()")
	private String transactionId;
	@XmlPath("/SupplierCredentialsList/SupplierCredentials/")
	private List<SupplierHeaderDAO> supplierDao;
	
	/**
	 * All Getters and Setters
	 * @return
	 */	
	public List<SupplierHeaderDAO> getSupplierDao() {
		return supplierDao;
	}
	public void setSupplierDao(List<SupplierHeaderDAO> supplierDao) {
		this.supplierDao = supplierDao;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
