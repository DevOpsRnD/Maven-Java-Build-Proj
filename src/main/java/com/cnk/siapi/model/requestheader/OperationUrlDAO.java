package com.cnk.siapi.model.requestheader;

/*+----------------------------------------------------------------------
||
||  Class OperationUrlDao
||
||         Author:  Durgesh Songire
||
||        Purpose:  Class having the information about the Operation part 
||					of the Request Header
||
||  Inherits From:  None
||                  
||
||     Interfaces:  None                   
||
|+-----------------------------------------------------------------------
||
||      Constants:  operation, data
|+-----------------------------------------------------------------------
||
||   Constructors:  OperationUrlDao(String operation, String data)
||					OperationUrlDao
||
||  Class Methods:  getOperation, setOperation, getData, setData,
||				
|+------------------------------------------------------------------------
|| 
||	Creation Date: 24/10/2017
++-----------------------------------------------------------------------*/

import org.eclipse.persistence.oxm.annotations.XmlPath;

public class OperationUrlDAO 
{
	@XmlPath("@operation")
	private String operation;
	@XmlPath("text()")
	private String data;	
	
	public OperationUrlDAO() {
		
	}
	public OperationUrlDAO(String operation, String data) {
		super();
		this.operation = operation;
		this.data = data;
	}
	
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	
}
