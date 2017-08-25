package com.cibil.util;

import java.util.Properties;

/**
 * 
 * @author alok.chaudhary
 * @Date 10/10/2014
 */

public class ExcelColumnConstants {
	public String SERIAL_NUMBER;
	public String OLM_DATE;
	public String MEMBER_ME;
	public String CONSUMER_ME;
	public String ACCOUNT_NUMBER;
	public String ACCOUNT_SUPPERESSED;
	public String STATUS;
	public String COMMENTS;
	public String OWNERSHIP_INDICATOR;
	public String ACCOUNT_TYPE;
	public String CURRRENT_BALANCE;
	public String AMOUNT_OVERDUE;
	public String DATE_CLOSED;
	public String DATE_LAST_PAYMENT;
	public String DATE_REPORTED;
	public String SANCTIONED_AMOUNT;
	public String NDPD_LAST_MONTH;
	public String ACCOUNT_STATUS;
	public String OTHERS;
	public String RESPONSE;
	public String DATE_PROCESSED;
	public String METHOD;
	public String MEMBER_CODE;
	public String REQUEST_BY;
	public String DATE_REQUIREMENT;
	public String TIME;
	public String COMMUNICATION_STATUS;
	public String REQUEST_DETAILS;
		
	public ExcelColumnConstants(Properties properties){
		 SERIAL_NUMBER			=	properties.getProperty("sr_no");
		 OLM_DATE				=	properties.getProperty("date");
		 MEMBER_ME				=	properties.getProperty("member_me");
		 CONSUMER_ME			=	properties.getProperty("consumer_me");
		 ACCOUNT_NUMBER			=	properties.getProperty("account_number");
		 ACCOUNT_SUPPERESSED	=	properties.getProperty("account_to_be_suppressed");
		 STATUS					=	properties.getProperty("status");
		 COMMENTS				=	properties.getProperty("comments");
		 OWNERSHIP_INDICATOR	=	properties.getProperty("ownership_indicator");
		 ACCOUNT_TYPE			=	properties.getProperty("account_type");
		 CURRRENT_BALANCE		=	properties.getProperty("current_balance");
		 AMOUNT_OVERDUE			=	properties.getProperty("amount_overdue");
		 DATE_CLOSED			=	properties.getProperty("date_closed");
		 DATE_LAST_PAYMENT		=	properties.getProperty("date_of_last_payment");
		 DATE_REPORTED			=	properties.getProperty("date_reported");
		 SANCTIONED_AMOUNT		=	properties.getProperty("sactioned_amount");
		 NDPD_LAST_MONTH		=	properties.getProperty("ndpd_for_latest_month");
		 ACCOUNT_STATUS			=	properties.getProperty("account_status");
		 OTHERS					=	properties.getProperty("others");
		 RESPONSE				=	properties.getProperty("response");
		 DATE_PROCESSED			=	properties.getProperty("date_processed");
		 METHOD					=	properties.getProperty("method");
		 MEMBER_CODE			=	properties.getProperty("member_code");
		 REQUEST_BY				=	properties.getProperty("request_by");
		 DATE_REQUIREMENT		=	properties.getProperty("date_requirement");
		 TIME					=	properties.getProperty("time");
		 COMMUNICATION_STATUS	=	properties.getProperty("communication_status");
		 REQUEST_DETAILS		=	properties.getProperty("request_details");
	}
	
}
