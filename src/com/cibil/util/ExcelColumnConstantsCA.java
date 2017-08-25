package com.cibil.util;

import java.util.Properties;

/*
 * @author: alok.chaudhary
 */
public class ExcelColumnConstantsCA {
	public String Name;
	public String SirName;
	public String DOB;
	public String fathersName;
	public String dateOfMarriage;
	public String mobile1;
	public String mobile2;
	public String mobile3;
	public String mobile4;
	public String mobile5;
	public String email1;
	public String email2;
	public String email3;
	public String email4;
	public String email5;
	public String landline1;
	public String landline2;
	public String landline3;
	public String landline4;
	public String landline5;
	public String addressLine1;
	public String addressLine2;
	public String companyName1;
	public String companyName2;
	public String companyName3;
	public String companyName4;
	public String companyName5;
	public String city;
	public String state;
	public String addressLine3;
	public String pinCode;
	public String pan;
	public String aadharNumber;
	public String voterID;
	public String passportNumber;
	
	public ExcelColumnConstantsCA(Properties properties){
		Name	=	properties.getProperty("Name");
		SirName	=	properties.getProperty("SIR_NAME");
		DOB		=	properties.getProperty("DOB");
		fathersName	=	properties.getProperty("FATHERS_NAME");
		mobile1		=	properties.getProperty("MOBILE1");
		mobile2		=	properties.getProperty("MOBILE2");
		email1		=	properties.getProperty("EMAIL1");
		email2		=	properties.getProperty("EMAIL2");
		addressLine1=	properties.getProperty("ADDRESS_LINE1");
		addressLine2=	properties.getProperty("ADDRESS_LINE2");
		addressLine3=	properties.getProperty("ADDRESS_LINE3");
		companyName1=	properties.getProperty("COMPANY_NAME1");
		companyName2=	properties.getProperty("COMPANY_NAME2");
		city		=	properties.getProperty("city");
		pinCode		=	properties.getProperty("PIN_CODE");
		pan			=	properties.getProperty("PAN");
		aadharNumber=	properties.getProperty("AADHAR_NUMBER");
		voterID		=	properties.getProperty("VOTER_ID");
		passportNumber=	properties.getProperty("PASSPORT_NUMBER");
		
		
		
	}
	
	
	
	}
