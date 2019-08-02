package com.cnk.siapi.model.requestheader;

/*+----------------------------------------------------------------------
||
||  Class CredHeaderDAO
||
||         Author:  Durgesh Songire
||
||        Purpose:  Class having the Information about the credetials of the
||					Request Header
||
||  Inherits From:  None
||                  
||
||     Interfaces:  None                   
||
|+-----------------------------------------------------------------------
||
||      Constants:  name, encrypted, encData, operdao
|+-----------------------------------------------------------------------
||
||   Constructors:  CredHeaderDAO(String name, String encrypted, String encData)
||					CredHeaderDAO
||
||  Class Methods:  getOperdao, setOperdao, getEncData, setEncData, getEncrypted,
||					setEncrypted, getName, setName
|+------------------------------------------------------------------------
|| 
||	Creation Date: 24/10/2017
++-----------------------------------------------------------------------*/

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class CredentialHeaderDAO 
{

	public CredentialHeaderDAO(String name, String encrypted, String encData) {
		this.name = name;
		this.encrypted = encrypted;
		this.encData = encData;
	}
	public CredentialHeaderDAO() 
	{
	}
	
	@XmlPath("@name")
	private String name;
	@XmlPath("@isEncrypted")
	private String encrypted;
	@XmlPath("text()")
	private String encData;
	@XmlElementWrapper(name = "OperationURLs")
	@XmlElement(name="OperationURL")
	private List<OperationUrlDAO> operdao;

	public List<OperationUrlDAO> getOperdao() {
		return operdao;
	}
	public void setOperdao(List<OperationUrlDAO> operdao) {
		this.operdao = operdao;
	}
	public String getEncData() {
		return encData;
	}
	public void setEncData(String encData) {
		this.encData = encData;
	}
	public String getEncrypted() {
		return encrypted;
	}
	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
