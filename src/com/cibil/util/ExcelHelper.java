package com.cibil.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
import com.cibil.builder.ExcelBuilder;
import com.cibil.controller.ExcelController;
import com.cibil.controller.SearchController;

/**
 * 
 * @author alok.chaudhary
 * @Date 01/10/2014
 */


public class ExcelHelper {
	private static Log 							log						=	LogFactory.getLog(ExcelController.class);
	private static boolean	 					reject					=	false;
	private static Map<Integer, String>         headerMap				=	null;
	//public static Properties 					excelProperties 		=	null;
	
	/*static{
		excelProperties	=	SearchController.excelProperties;
		
	}*/
		
	/*
	 * @param Date
	 * 
	 * convert the date object to String format yyyy-mm-dd.
	 */
	public static String convertDateToString(Date inputDate) {
		String formatedDate	=	null;
		if (inputDate != null) {
			try {
				SimpleDateFormat formatter 	=	 new SimpleDateFormat("yyyy-MM-dd");
				   formatedDate		=	formatter.format((Date)inputDate);
			//	log.info("formatedDate String is : " + formatedDate);    
			} catch (Exception e) {
				log.error("Error while converting Date to String " + e.getMessage());    
			}
		}
		return formatedDate;
	}
	
	/**
	 * 
	 * @param colValue
	 * @return formatted Date
	 * 
	 * This funcation will convert the date into yyyy-MM-dd date format
	 */
	
	private static Date formatDateObject(Date colValue) {
		Date   date					=	null;
		String formattedDate		=	convertDateToString(colValue);
		SimpleDateFormat formatter 	= 	new SimpleDateFormat("yyyy-MM-dd");
		try {
			 date 					= 	formatter.parse(formattedDate);
		} catch (ParseException e) {
			
			log.error("Error while formating date object " + e.getMessage());    
		}
		return date;
	}
	
	/**
	 * 
	 * @param colValue
	 * @return
	 * 
	 * parse the column values against the specified date formats and if the date is invalid then updates the rejected flag.
	 */
	
	public static Object convertStringToDate(Object colValue) {//6.6.2014
		Date date 				= null;
		String value			=	String.valueOf(colValue);
		if(value.equalsIgnoreCase("NO")){
			return new Date();
		}
		String[] dateFormats	=	{"dd/MM/yyyy","dd-MM-yyyy","dd-MMM-yyyy","d.M.yyyy"};
		int length				=	dateFormats.length;
		int count				=	0;
		int catchCount			=	0;				
		boolean check			=	true;
		String 	pattern			=	"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]|(?:Jan|Mar|May|Jul|Aug|Oct|Dec)))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2]|(?:Jan|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec))\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)(?:0?2|(?:Feb))\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9]|(?:Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep))|(?:1[0-2]|(?:Oct|Nov|Dec)))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
		Pattern	datePattern		=	Pattern.compile(pattern);
		Matcher	dateMatcher		=	datePattern.matcher(value);
	//	if(StringUtils.isNotBlank(value)){
			/*String yyyyFormat	=	"";
			value				=	value.trim();	
			if(value.contains("-")){
				yyyyFormat	=	value.substring(0, value.indexOf("-"));
			}else if(value.contains("/")){
				yyyyFormat	=	value.substring(0, value.indexOf("/"));
			}else if(value.contains(".")){
				yyyyFormat	=	value.substring(0, value.indexOf("."));
			}
			if(yyyyFormat.length() > 2){
				reject		=	true;
				return value;
			}*/
			
	//	}
		if(dateMatcher.matches()){
			for(String format : dateFormats){
				check					=	true;
				count++;
			SimpleDateFormat formatter 	= new SimpleDateFormat(format);
				 try {
					date = formatter.parse(value);
					
				} catch (ParseException e) {
					catchCount++;
	 				if(count == length){
					reject	=	true;
					}
					check	=	false;
					log.error("Error while parsing date adding to reject list::"+e.getMessage());
					//e.printStackTrace();
					
				}
				 if(check){
					 break;
				 }
			}
			if(catchCount == length){
				
				return value;
			}else
			return date;
			
		}else{
			reject = true;
			return value;
	}
	}
	
	
	public static String convertExptoStringNo(String input) {
		if (input != null && !input.isEmpty()) {
			input=input.trim();
			try{
				BigDecimal bg = new BigDecimal(input);
				input = bg.toPlainString();
			}catch(Exception exception){
				log.error("Error reading number from excel "+ exception.toString());
			}
		}
		return input;
	}
	
	/*public static void setColumnValues(DataStore actionForm,String colHeader, Object colValue,ExcelColumnConstants ExcelColumnConstants)  {
		colHeader		=	colHeader.trim();
		if(colValue == null){
			return;
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.ACCOUNT_NUMBER)) {
			actionForm.setAccountNumber(String.valueOf(colValue));
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.ACCOUNT_STATUS)) {
			actionForm.setAccountStatus(String.valueOf(colValue));
		} 
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.ACCOUNT_SUPPERESSED)) {
			actionForm.setAccountToSupress(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.ACCOUNT_TYPE)) {
			actionForm.setAccountType(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.AMOUNT_OVERDUE)) {
			actionForm.setAmountOverdue(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.COMMENTS)) {
			actionForm.setComments(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.COMMUNICATION_STATUS)) {
			actionForm.setCommunicationStatus(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.CONSUMER_ME)) {
			actionForm.setConsumerMe(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.CURRRENT_BALANCE)) {
			actionForm.setCurrentBalance(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DATE_CLOSED)) {
			if(colValue instanceof Date){
				actionForm.setDateClosed(formatDateObject((Date)colValue));
			}else{
				actionForm.setDateClosed(convertStringToDate(colValue));
			}
		}
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DATE_LAST_PAYMENT)) {
			if(colValue instanceof Date){
				actionForm.setDateOfLastPayment(formatDateObject((Date)colValue));
			}else{
				actionForm.setDateOfLastPayment(convertStringToDate(colValue));
			}
			
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DATE_PROCESSED)) {
			if(colValue instanceof Date){
				actionForm.setDateProcessed(formatDateObject((Date)colValue));
			}else{
				actionForm.setDateProcessed(convertStringToDate(colValue));
			}
			
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DATE_REPORTED)) {
			if(colValue instanceof Date){
				actionForm.setDateReported(formatDateObject((Date)colValue));
			}else{
				actionForm.setDateReported(convertStringToDate(colValue));
			}
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DATE_REQUIREMENT)) {
			if(colValue instanceof Date){
				actionForm.setDateRequirement(formatDateObject((Date)colValue));
			}else{
				actionForm.setDateRequirement(convertStringToDate(colValue));
			}
		}
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.MEMBER_CODE)) {
			actionForm.setMemberCode(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.MEMBER_ME)) {
			actionForm.setMemberMe(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.METHOD)) {
			actionForm.setMethod(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.NDPD_LAST_MONTH)) {
			actionForm.setNdpdForLatestMonth(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.OLM_DATE)) {
			if(colValue instanceof Date){
				actionForm.setOlmDate(formatDateObject((Date)colValue));
			}else{
				actionForm.setOlmDate(convertStringToDate(colValue));
			}
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.OTHERS)) {
			actionForm.setOthers(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.OWNERSHIP_INDICATOR)) {
			actionForm.setOwnershipIndicator(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.REQUEST_BY)) {
			actionForm.setRequestBy(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.REQUEST_DETAILS)) {
			actionForm.setRequestDetails(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.RESPONSE)) {
			actionForm.setResponse(String.valueOf(colValue));
		}  else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.SANCTIONED_AMOUNT)) {
			actionForm.setSactionedAmount(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.SERIAL_NUMBER)) {
			actionForm.setSrNo(String.valueOf(colValue));
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.STATUS)) {
			actionForm.setStatus(String.valueOf(colValue));
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.TIME)) {
				actionForm.setTime(String.valueOf(colValue));
	}
	}*/
	
	public static void setColumnValues(DataStore actionForm,String colHeader, Object colValue,ExcelColumnConstantsCA ExcelColumnConstantsCA)  {
		colHeader		=	colHeader.trim();
		if(colValue == null){
			return;
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.aadharNumber)) {
			actionForm.setAadharNumber(String.valueOf(colValue));
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.addressLine1)) {
			actionForm.setAddressLine1(String.valueOf(colValue));
		} 
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.addressLine2)) {
			actionForm.setAddressLine2(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.addressLine3)) {
			actionForm.setAddressLine3(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.city)) {
			actionForm.setCity(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.companyName1)) {
			actionForm.setCompanyName1(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.companyName2)) {
			actionForm.setCompanyName2(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.DOB)) {
			if(colValue instanceof Date){
				actionForm.setDob(formatDateObject((Date)colValue));
			}else{
				actionForm.setDob(convertStringToDate(colValue));
			}
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.email1)) {
			actionForm.setEmail1(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.email2)) {
			actionForm.setEmail2(String.valueOf(colValue));
		}
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.fathersName)) {
				actionForm.setFathersName(String.valueOf(colValue));
			
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.mobile1)) {
			actionForm.setMobile1(String.valueOf(colValue));
			
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.mobile2)) {
			actionForm.setMobile2(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.Name)) {
			actionForm.setName(String.valueOf(colValue));
		}
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.pan)) {
			actionForm.setPan(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.passportNumber)) {
			actionForm.setPassportNumber(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.pinCode)) {
			actionForm.setPinCode(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.SirName)) {
			actionForm.setSirName(String.valueOf(colValue));
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstantsCA.voterID)) {
			actionForm.setVoterID(String.valueOf(colValue));
		
	}
	}



	public static LinkedList<ArrayList<DataStore>> populateDataStore(List<Map<Integer, String>> l_sheet_data, HttpServletRequest request) {
		
		ArrayList<DataStore>		 dataStore				=	new ArrayList<DataStore>();
		ArrayList<DataStore>		 rejectedStore			=	new ArrayList<DataStore>();
		LinkedList<ArrayList<DataStore>>   storeRecords		=	new LinkedList<ArrayList<DataStore>>();
		Map<Integer, String>         rowMap					=	null;
		String				         headerValue			=	null;
		Object				         columnValue			=	null;
		Properties 					 properties				=	null;
		int 				         key					=	-1;
		ServletContext 				 sc						= 	request.getSession().getServletContext();
		String 						 strPath 				= 	sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 	new ReadPropertiesFile();
	//	excelProperties 									= 	fileObj.readFile(strPath+ "/excel_mapping.properties");
		headerMap											=	l_sheet_data.get(0);
		if(headerMap.size()	==	CommonConstants.oldFileColumns){
			log.info(CommonUtil.getlogDetail(request) +"Loading old file mappings");
			properties		=	ExcelBuilder.loadOldPropertiesFileFormat(request);
			if(!compareCollections(properties)){
				return new LinkedList<>();
			}
			
		}else if(headerMap.size()	==	CommonConstants.newFileCOlumns){
			log.info(CommonUtil.getlogDetail(request) +"Loading new file mappings");
			properties=ExcelBuilder.loadNewPropertiesFileFormat(request);
			if(!compareCollections(properties)){
				return new LinkedList<>();
			}
		}
		 
		ExcelColumnConstantsCA columnConstants=new ExcelColumnConstantsCA(properties);
		for(int i=1 ; i < l_sheet_data.size() ; i++){
			rowMap											=	l_sheet_data.get(i);
			if(CollectionUtils.exists(rowMap.values(), NotNullPredicate.INSTANCE)){
			//if(l_sheet_data.get(i)	!=	null){
				DataStore store					=	new DataStore();
			//	rowMap							=	l_sheet_data.get(i);
				
			Iterator<Integer> itr			=	rowMap.keySet().iterator();
			while(itr.hasNext()){
				 key						=	itr.next();
				headerValue					=	headerMap.get(key);
				columnValue					=	rowMap.get(key);
		//		log.info(CommonUtil.getlogDetail(request) +"Processing header::"+ headerValue+" for value::"+columnValue);
				if(StringUtils.isNotBlank(headerValue))
					setColumnValues(store, headerValue, columnValue,columnConstants);
				}
			if(reject){
				reject	=	false;
				rejectedStore.add(store);
				
			}else{
				dataStore.add(store);
			}
			
			}
		}
			storeRecords.add(dataStore);
			storeRecords.add(rejectedStore);
		return storeRecords;
	}
	
public static LinkedList<ArrayList<DataStore>> populateDataStoreCA(List<Map<Integer, String>> l_sheet_data, HttpServletRequest request) {
		
		ArrayList<DataStore>		 dataStore				=	new ArrayList<DataStore>();
		ArrayList<DataStore>		 rejectedStore			=	new ArrayList<DataStore>();
		LinkedList<ArrayList<DataStore>>   storeRecords		=	new LinkedList<ArrayList<DataStore>>();
		Map<Integer, String>         rowMap					=	null;
		String				         headerValue			=	null;
		Object				         columnValue			=	null;
		Properties 					 properties				=	null;
		int 				         key					=	-1;
		ServletContext 				 sc						= 	request.getSession().getServletContext();
		String 						 strPath 				= 	sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 	new ReadPropertiesFile();
	//	excelProperties 									= 	fileObj.readFile(strPath+ "/excel_mapping.properties");
		headerMap											=	l_sheet_data.get(0);
		if(headerMap.size()	==	CommonConstants.oldFileColumns){
			log.info(CommonUtil.getlogDetail(request) +"Loading old file mappings");
			properties		=	ExcelBuilder.loadOldPropertiesFileFormat(request);
			if(!compareCollections(properties)){
				return new LinkedList<>();
			}
			
		}else if(headerMap.size()	==	CommonConstants.newFileCOlumns){
			log.info(CommonUtil.getlogDetail(request) +"Loading new file mappings");
			properties=ExcelBuilder.loadNewPropertiesFileFormat(request);
			if(!compareCollections(properties)){
				return new LinkedList<>();
			}
		}
		 
		ExcelColumnConstantsCA columnConstants=new ExcelColumnConstantsCA(properties);
		for(int i=1 ; i < l_sheet_data.size() ; i++){
			rowMap											=	l_sheet_data.get(i);
			if(CollectionUtils.exists(rowMap.values(), NotNullPredicate.INSTANCE)){
			//if(l_sheet_data.get(i)	!=	null){
				DataStore store					=	new DataStore();
			//	rowMap							=	l_sheet_data.get(i);
				
			Iterator<Integer> itr			=	rowMap.keySet().iterator();
			while(itr.hasNext()){
				 key						=	itr.next();
				headerValue					=	headerMap.get(key);
				columnValue					=	rowMap.get(key);
			//	log.info(CommonUtil.getlogDetail(request) +"Processing header::"+ headerValue+" for value::"+columnValue);
				if(StringUtils.isNotBlank(headerValue))
					setColumnValues(store, headerValue, columnValue,columnConstants);
				}
			if(reject){
				reject	=	false;
				rejectedStore.add(store);
				
			}else{
				dataStore.add(store);
			}
			
			}
		}
			storeRecords.add(dataStore);
			storeRecords.add(rejectedStore);
		return storeRecords;
	}
	
	

	private static boolean compareCollections(Properties properties) {
		boolean 			value						=		true;
		String 				propValue					=		null;
		String 				headerValue					=		null;
		Collection<String> propSetInsenstive			=		new HashSet<String>();
		Collection<String> headerSetInsenstive			=		new HashSet<String>();
		Collection<String> headerSet					=		new HashSet<String>();
		Collection<Object> propSet						=		properties.values();
		 headerSet										=	 	headerMap.values();
		Iterator<Object>	itr							=	propSet.iterator();
		Iterator<String>	itrHeader							=	headerSet.iterator();
		
		while(itr.hasNext()){
			 propValue							=	(String)itr.next();
			 headerValue						=	itrHeader.next();
			propSetInsenstive.add(propValue.toLowerCase());
			if(StringUtils.isNotBlank(headerValue)){
				headerSetInsenstive.add(headerValue.toLowerCase());
			}
		}
		
		 propSetInsenstive.removeAll(headerSetInsenstive);
		 if(propSetInsenstive.size() > 0){
			 value		=	false;
		 }
		 
		return value;
	}

	public static Map<String, LinkedList<Object>> prepareDataToBuild(LinkedList<DataStore> rejectedRecords, Properties propsToLoad, HttpServletRequest request) {
		int count = 1;
		
		LinkedList<Object> headerList = null;
		Map<String, LinkedList<Object>> dataMap = new LinkedHashMap<String, LinkedList<Object>>();
		headerList = populateHeaders(propsToLoad);
		ExcelColumnConstantsCA columnConstants=new ExcelColumnConstantsCA(propsToLoad);
		dataMap.put("1", headerList);
		Iterator<DataStore> itr = rejectedRecords.iterator();
		while (itr.hasNext()) {
			LinkedList<Object> rowList = new LinkedList<Object>();
			count++;
			DataStore store = itr.next();//rejected bean

			Iterator<Integer> itr1 = headerMap.keySet().iterator();

			while (itr1.hasNext()) {
				int key = itr1.next();
				String headerValue = headerMap.get(key);
				if(StringUtils.isNotBlank(headerValue)){
					Object objectToAdd = setColumnValuesforBuild(store, headerValue,columnConstants);
					if(objectToAdd != null){
						rowList.add(objectToAdd);
					}
				}
			}
			// rowList = prepareObjectArray(store);
			dataMap.put(String.valueOf(count), rowList);
		}
		return dataMap;
	}

	private static LinkedList<Object>  populateHeaders(Properties properties) {
		LinkedList<Object> headerObjects	=	new LinkedList<>();
		int key								=	-1;
		int count							=	0;
			headerMap						=	new HashMap<Integer, String>();
			Enumeration<Object> itr			=	properties.keys();
			while(itr.hasMoreElements()){
				headerMap.put(count, properties.getProperty((String)itr.nextElement()));
				count++;
			}
			
		Iterator<Integer> headerItr	=	headerMap.keySet().iterator();	
		while(headerItr.hasNext()){
			key					=		headerItr.next();
			headerObjects.add(headerMap.get(key));
		}
		return headerObjects;
	}

	
	
	public static Object setColumnValuesforBuild(DataStore actionForm,String colHeader,ExcelColumnConstantsCA ExcelColumnConstants)  {
		colHeader	=	colHeader.trim();
		if (colHeader.equalsIgnoreCase(ExcelColumnConstants.Name)) {
			return actionForm.getName();
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.SirName)) {
			return actionForm.getSirName();
		} 
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.fathersName) ) {
			return actionForm.getFathersName();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.DOB)) {
			return CommonUtil.changeDateString(actionForm.getDob());
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.pan)) {
			return actionForm.getPan();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.voterID)) {
			return actionForm.getVoterID();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.aadharNumber)) {
			return actionForm.getAadharNumber();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.passportNumber)) {
			return actionForm.getPassportNumber();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.addressLine1)) {
			return actionForm.getAddressLine1();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.addressLine2)) {
			return actionForm.getAddressLine2();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.addressLine3)) {
			return actionForm.getAddressLine3();
		}
		else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.pinCode)) {
			return actionForm.getPinCode();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.city)) {
			return actionForm.getCity();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.mobile1)) {
			return actionForm.getMobile1();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.mobile2)) {
			return actionForm.getMobile2();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.email1)) {
			return actionForm.getEmail1();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.email2)) {
			return actionForm.getEmail2();
		}else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.companyName1)) {
			return actionForm.getCompanyName1();
		} else if (colHeader.equalsIgnoreCase(ExcelColumnConstants.companyName2)) {
			return actionForm.getCompanyName2();
		}
		return null;
	}

	
	public static String prepareSPParam(DataStore storedBean) {
		String 	  spParam						=	"";	
		String[]  updatedRecords				=	null;
		updatedRecords							=	storedBean.getSelectedRecordIDCheckBox();
		
	
		if(updatedRecords != null){
		for(String id : updatedRecords){
		log.info("The is for the record is ::"+id.toString());
		if(StringUtils.isNotBlank(id)){
			spParam	= spParam+id+",";
		}
		
		}
		spParam		=			StringUtils.removeEnd(spParam, ",");
		}
		return spParam;
	
}
	

	
}


