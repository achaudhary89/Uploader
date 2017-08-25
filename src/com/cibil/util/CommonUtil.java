package com.cibil.util;

import java.net.InetAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

/**
 * @author arunbal.srinivasan
 *
 */
public class CommonUtil {

	public static java.sql.Date getCurrentDate() {
		java.util.Date utilDate = Calendar.getInstance().getTime();
		return new java.sql.Date(utilDate.getTime());
	}

	public static java.sql.Timestamp getCurrentDateTime() {
		java.util.Date utilDate = Calendar.getInstance().getTime();
		return new java.sql.Timestamp(utilDate.getTime());
	}
	
	public static java.sql.Date convertDate(Object dateObject){
		String formattedDateString	=	null;
		Date   outputDate			=	null;
	//	Calendar cal 		= 	Calendar.getInstance();
		if(!(dateObject instanceof Date) || dateObject == null){
			return null;
		}
		
		SimpleDateFormat formatter 	=	 new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 formattedDateString			=	 formatter.format((Date)dateObject);
			 outputDate = formatter.parse(formattedDateString);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		return new java.sql.Date(outputDate.getTime());
	}
	
	
	public static Date formatDateString(String dateObject){
		Date   outputDate			=	null;
		/*String formattedDateString	=	null;
		
		Calendar cal 				= 	Calendar.getInstance();*/
		SimpleDateFormat formatter 	= 	new SimpleDateFormat(CommonConstants.searchDateFormat);
		if(StringUtils.isBlank(dateObject)){
			
			return null;
		}
		 try {
			 outputDate = formatter.parse(dateObject);
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			
		}
		/*if(dateObject == null){
			return null;
		}*/
		/*cal.setTime(outputDate);
		formattedDateString = cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH) + "-"+ (cal.get(Calendar.DATE));*/
		
		//return formattedDateString;
		 return outputDate;
	}
	

	/**
	 * @param request
	 * @param key
	 * @return
	 */
	public static String geti18nValue(HttpServletRequest request, String key) {
		ServletContext sc = request.getSession().getServletContext();
		String strPath = sc.getRealPath("/");
		ReadPropertiesFile fileObj = new ReadPropertiesFile();
		Properties properties = fileObj.readFile(strPath+ "/messages.properties");
		return (String) properties.get(key);
	}

	public static String getlogDetail(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer();
		if (request != null) {
			buffer.append(" Logged in UserName : ");
			buffer.append(request.getSession().getAttribute(CommonConstants.sessionUserName));
		}
		try {
			buffer.append(" IP Address ").append(InetAddress.getLocalHost().getHostAddress()).append(" ");
		} catch (Exception e) {
		}
		return buffer.toString();
	}
	
	public static String changeDateString(Object DateObject){
		String output				=		null;
		String Date					=		null;
		Date   outputDate					=		null;
		if(DateObject == null){
			return Date;
		}
		SimpleDateFormat formatter 	= 	new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter1	=   new SimpleDateFormat("dd-MM-yyyy");
		if(DateObject instanceof String){
			Date		=	(String)DateObject;
		}
		if("0000-00-00".equals(Date)){
			return "NA";
		}
		
		 try {
			 outputDate = formatter.parse(Date);
			 output		=	 formatter1.format(outputDate);
			
		} catch (ParseException e) {
			
			return Date;
			
		}
		return  output;
	}

	public static String getOperationDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yy");
		return dateFormat.format(calendar.getTime());
	}

}
