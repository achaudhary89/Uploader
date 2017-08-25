package com.cibil.builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
import com.cibil.bean.ExcelBean;
import com.cibil.util.CommonConstants;
import com.cibil.util.ExcelHelper;
import com.cibil.util.ReadPropertiesFile;
 
/**
 * 
 * @author alok.chaudhary
 * @Date   01/10/2014
 */

public class ExcelBuilder extends AbstractExcelView implements IBuilder{

	public static Properties excelProperties		=	null;
	public static ExcelBuilder getInstance(){
		
		return new ExcelBuilder();
	}
	private String  flow;
	

	public String getFlow() {
		return flow;
	}

	public void setFlow(String flow) {
		this.flow = flow;
	}

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Properties propsToLoad						=		null;
			ExcelBean   bean						=	(ExcelBean) model.get("buildRecordsToExcel");
			LinkedList<DataStore> recordsToBuild 	= 	bean.getRejectedRecords();
			String format							=	bean.getFormat();
			if("old".equals(format)){
				propsToLoad						=	loadOldPropertiesFileFormat(request);
			}else if("new".equals(format)){
				propsToLoad						=	loadNewPropertiesFileFormat(request);
			}else{
				propsToLoad						=	loadPropertiesFile(flow, request);
			}
			HSSFSheet sheet = workbook.createSheet("Sheet1");
	        Map<String, LinkedList<Object>> excelData	=	ExcelHelper.prepareDataToBuild(recordsToBuild, propsToLoad,request);        
	        Set<String> keyset = excelData.keySet();
	        int rownum = 0;
	        for (String key : keyset)
	        {
	            Row row = sheet.createRow(rownum++);
	            LinkedList<Object> objArr 	=	new LinkedList<>();	
	            objArr 					 	= 	excelData.get(key);
	            int cellnum = 0;
	            Iterator rowIterator		=	objArr.iterator();
	            while(rowIterator.hasNext()){
	            	Object columnValue	=	rowIterator.next();
	            	 Cell cell 			= row.createCell(cellnum++);
	            	 if(columnValue instanceof String){
	            		 cell.setCellValue((String)columnValue);
	            	 } else if(columnValue instanceof Date){
	            		 	String date		=	ExcelHelper.convertDateToString((Date)columnValue);
	            		    cell.setCellValue(date);
	            		 
	            	 } else if(columnValue instanceof Integer){
	            		 cell.setCellValue((Integer)columnValue);
	            	 }
	            }

	        }
			
			if(getFlow().equals("1")){
				response.setHeader("Content-Disposition", "inline;filename=\"ExportedRecords.xls\"");
			}else if(getFlow().equals("2")){
				response.setHeader("Content-Disposition", "inline;filename=\"InvalidRecords.xls\"");
			}else{
				response.setHeader("Content-Disposition", "inline;filename=\"spreadsheet.xls\"");
			}
			response.setContentType("APPLICATION/OCTET-STREAM");
	}

	public static  Properties loadPropertiesFile(String flowName, HttpServletRequest request) {
		
		
		ServletContext 				 sc						= 		request.getSession().getServletContext();
		String 						 strPath 				= 		sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 		new ReadPropertiesFile();
		if("1".equals(flowName)){
			excelProperties 									= 		fileObj.readOrderedFile(strPath+ CommonConstants.globalProperties);
		}else{
			excelProperties 									= 		fileObj.readOrderedFile(strPath+ CommonConstants.localProperties);
		}
		
		
		return excelProperties;
	}
	
	public static  Properties loadOldPropertiesFileFormat(HttpServletRequest request) {
		ServletContext 				 sc						= 		request.getSession().getServletContext();
		String 						 strPath 				= 		sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 		new ReadPropertiesFile();
		
			excelProperties 								= 		fileObj.readOrderedFile(strPath+ CommonConstants.oldProperties);
			return excelProperties;
	}
	
	public static  Properties loadNewPropertiesFileFormat(HttpServletRequest request) {
		ServletContext 				 sc						= 		request.getSession().getServletContext();
		String 						 strPath 				= 		sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 		new ReadPropertiesFile();
		
			excelProperties 									= 		fileObj.readOrderedFile(strPath+ CommonConstants.newProperties);
			return excelProperties;
	}
	@Override
	public void build(ArrayList<DataStore> rejectedRecords) {
		
		//XSSFWorkbook workbook 		= new XSSFWorkbook();
       
		
	}
	





	



	
	
}
