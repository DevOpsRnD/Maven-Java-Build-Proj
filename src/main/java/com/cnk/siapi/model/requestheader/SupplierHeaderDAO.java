package com.cnk.siapi.model.requestheader;

/*+----------------------------------------------------------------------
||
||  Class SupplierHeaderDAO
||
||  Author:  Durgesh Songire
||
||  Purpose:  All the Supplier Information of Supplier Header
||
||  Inherits From:  None
||                  
||  Interfaces:  None                   
||
|+-----------------------------------------------------------------------
||
||  Constants:  supplierId, sequence, Credentials, operdao
||
|+-----------------------------------------------------------------------
||
||  Constructors:  SupplierHeaderDAO(String supplierId, String sequence)
||
||  Class Methods:  getOperdao, setOperdao, getCredentials, 
||					setCredentials, getSupplierId, setSupplierId, 
||					getSequence,setSequence
||				
|+------------------------------------------------------------------------
|| 
||	Creation Date: 24/10/2017
++-----------------------------------------------------------------------*/

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class SupplierHeaderDAO 
{
	public SupplierHeaderDAO() {};
	
	@XmlPath("/SupplierID/text()")
	private String supplierId;
	@XmlPath("/Sequence/text()")
	private String sequence;
	@XmlElementWrapper(name = "Credentials")
	@XmlElement(name="Credential")
	private List<CredentialHeaderDAO> Credentials = new ArrayList<CredentialHeaderDAO>();
	@XmlPath("/Credentials/OperationURLs/OperationURL")
	@XmlElementWrapper(name = "OperationURLs")
	@XmlElement(name="OperationURL")
	private List<OperationUrlDAO> operdao;

	public SupplierHeaderDAO(String supplierId, String sequence) {

		this.supplierId = supplierId;
		this.sequence = sequence;
	}
			
	public List<OperationUrlDAO> getOperdao() {
		return operdao;
	}
	public void setOperdao(List<OperationUrlDAO> operdao) {
		this.operdao = operdao;
	}
	public List<CredentialHeaderDAO> getCredentials() {
		return Credentials;
	}
	public void setCredentials(List<CredentialHeaderDAO> credL) {
		Credentials = credL;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
}
