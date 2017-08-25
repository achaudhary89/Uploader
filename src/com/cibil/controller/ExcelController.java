package com.cibil.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
import com.cibil.bean.ExcelBean;
import com.cibil.bean.FileUploadBean;
import com.cibil.dao.IExcelDAO;
import com.cibil.manager.AuditManager;
import com.cibil.parser.IParser;
import com.cibil.service.ExcelServiceFactory;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ExcelHelper;
import com.cibil.util.ResultPageConstants;

/**
 * 
 * @author alok.chaudhary
 *
 */

@Controller
public class ExcelController extends BaseController {
	private Log 					log						=	LogFactory.getLog(ExcelController.class);
	public static AuditManager auditInstance;
	
	@Override
	@RequestMapping("upload.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws CIBILException {
	try {
		model.addAttribute("FileUploadBean", new FileUploadBean());
		model.addAttribute("DataStore", new DataStore());
		return null;
	} catch (Exception e) {
		throw new CIBILException(request, e);
	}
	}
	
	
	@RequestMapping("advancedSearch.htm")
	public String returnAdvancedSearch(ModelMap model, HttpServletRequest request)
			throws CIBILException {
	try {
		model.addAttribute("FileUploadBean", new FileUploadBean());
		model.addAttribute("DataStore", new DataStore());
		return null;
	} catch (Exception e) {
		throw new CIBILException(request, e);
	}
	}
	
	@SuppressWarnings("unused")
	@RequestMapping("excelSubmit.htm")   
	public String onExcelSubmit(@ModelAttribute("DataStore") DataStore dataStoreBean,
				BindingResult result, ModelMap model, HttpServletRequest request)
				throws CIBILException {
		try{
 			String _result =	null;
 			
 			String typeData		= 		request.getParameter("typeData");
			auditInstance		= 		AuditManager.getInstance();
			if (!result.hasErrors()) {
				_result = performAction(request, dataStoreBean);
				if("SEARCH".equalsIgnoreCase(typeData)) {
					viewMatchedForm(model,request);
				} else {
					int dupCount	=	viewForm(model, request);
				}
			}
			
			return ResultPageConstants.landExcelPage;
		}catch (Exception e) {
				throw new CIBILException(request, e);
			}
		}
	
	@RequestMapping("excelApprove.htm")   
	public String onExcelApprove(@ModelAttribute("DataStore") DataStore dataStoreBean,
				BindingResult result, ModelMap model, HttpServletRequest request)
				throws CIBILException {
		 
		try{
			Map				    countMap							=		null;
			int 				duplicateCount;
			int					rejectedCount;
			int 				approvedCount;
			int					invalidCount;
			ExcelServiceFactory factory							 	=		ExcelServiceFactory.getInstance();	
			IExcelDAO			excelDAO							=		factory.getExcelDAO();
			auditInstance											= 		AuditManager.getInstance();
			String[] checked										= 		dataStoreBean.getSelectedRecordIDCheckBox();
			//This code use to handle the server side validation on clicking the approve as of now it is being commented because it is now being handled by client sode validation.
			/*if(checked	==	null){
				log.info(CommonUtil.getlogDetail(request) +"No records are selected for approve returning from here");
				dataStoreBean.setValidationName(CommonConstants.validationApprove);
				return ResultPageConstants.landExcelPage;
				
			}*/
			
			excelDAO.updateRejectedRecords(dataStoreBean);
			countMap = excelDAO.insertIntoTransactionTable();
			duplicateCount				=	(int)countMap.get("duplicate_count");
			rejectedCount				=	(int)countMap.get("rejected_count");
			approvedCount				=	(int)countMap.get("approved_count");
			invalidCount				=	(int)countMap.get("invalid_count");
			dataStoreBean.setDuplicateCount(duplicateCount);
			dataStoreBean.setRejectedCount(rejectedCount);
			dataStoreBean.setApprovedCount(approvedCount);
			dataStoreBean.setInvalidCount(invalidCount);
			log.info(CommonUtil.getlogDetail(request) +"Data process successfully completed");
			model.addAttribute("count", countMap);
			if((boolean)countMap.get("result")==true)
			{	
				model.addAttribute("firstload",true);
				dataStoreBean.setValidationName(CommonConstants.successApprove);
				auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Records rejected by the user::"+rejectedCount);
				auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Duplicate records present in the file are::"+duplicateCount);
				auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Number of invalid records in the file::"+invalidCount);
				auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Number of approved records ::"+approvedCount);
				return ResultPageConstants.landExcelPage;	
			}
			return ResultPageConstants.landExcelPage;
		}catch (Exception e) {
			throw new CIBILException(request, e);
		}
		}
	
	
	@RequestMapping("excelCancel.htm")   
	public String onExcelCancel(@ModelAttribute("DataStore") DataStore dataStoreBean,
				BindingResult result, ModelMap model, HttpServletRequest request)
				throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"Cancel Operation started");
			auditInstance									= 		AuditManager.getInstance();
			ExcelServiceFactory factory						=		ExcelServiceFactory.getInstance();	
			IExcelDAO			excelDAO					=		factory.getExcelDAO();
			excelDAO.clearStaging();
			log.info(CommonUtil.getlogDetail(request) +"Cancel process successfully completed");
			auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Upload operation cancelled by user");
			dataStoreBean.setValidationName(CommonConstants.cancelOperation);
			return ResultPageConstants.landExcelPage;
		}catch (Exception e) {
			throw new CIBILException(request, e);
		}
		}
	
	
	@RequestMapping("downloadRejectedRecords.htm")   
	public ModelAndView downloadRejectedRecordsRecords(@ModelAttribute("DataStore") DataStore dataStoreBean,
				BindingResult result, ModelMap model, HttpServletRequest request)
				throws CIBILException {
			try{
			log.info(CommonUtil.getlogDetail(request) +"Method downloadRejectedRecordsRecords() started::");
			auditInstance									= 		AuditManager.getInstance();
			ExcelServiceFactory factory						=		ExcelServiceFactory.getInstance();	
			IExcelDAO			excelDAO					=		factory.getExcelDAO();
			LinkedList<DataStore> rejectedRecords			=		(LinkedList<DataStore>) excelDAO.getRejectedRecords();
			ExcelBean		bean							=		new ExcelBean();
			bean.setRejectedRecords(rejectedRecords);
			bean.setFormat(dataStoreBean.getFormat());
			//if(rejectedRecords.size()  > 0){
			log.info(CommonUtil.getlogDetail(request) +"Method downloadRejectedRecordsRecords() ended::");
				return new ModelAndView("RejectdRecords", "buildRecordsToExcel", bean);
			//}
				
			}catch (Exception e) {
				throw new CIBILException(request, e);
			}
		}
	
	
	

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)throws CIBILException {
		
	try{	
        File l_file 										=	 	null;
        IParser				excelParser						=		null;
        String 				typeData						=		null;
		String 				fileName						=		null;
		int    				rejectedRecordssize				=		0;
		int 				totalRecords					=		0;
		boolean				result							=		true;
		DataStore 			dataStoreBean					=		(DataStore) baseBean;
		MultipartFile 		multipartFile					=		dataStoreBean.getFile();
		if(multipartFile == null || multipartFile.isEmpty()){
			dataStoreBean.setValidationName(CommonConstants.fileNotSelected);
			return ResultPageConstants.landExcelPage;
			
		}else if(multipartFile.getSize()>CommonConstants.fileSize){
			dataStoreBean.setValidationName(CommonConstants.fileExceedSize);
			return ResultPageConstants.landExcelPage;
		}

		typeData											= 		request.getParameter("typeData");
		System.out.println("Type Data is::"+typeData);
		
		fileName											=		multipartFile.getOriginalFilename();
		
		ExcelServiceFactory factory							=		ExcelServiceFactory.getInstance();
		auditInstance										= 		AuditManager.getInstance();
		 byte[] bytes = multipartFile.getBytes();
         String rootPath = System.getProperty("catalina.home");
         log.info(CommonUtil.getlogDetail(request) +"root path is::"+rootPath);
         File dir = new File(rootPath + File.separator + "tmpFiles");
         if (!dir.exists())
             dir.mkdirs();
		 
		l_file												=		new File(dir.getAbsolutePath()  + File.separator + fileName);
		BufferedOutputStream stream 						= 		new BufferedOutputStream(new FileOutputStream(l_file));
		stream.write(bytes);
        stream.close();
        
		log.info(CommonUtil.getlogDetail(request) +"Server file location is ::"+l_file.getAbsolutePath());
		if(StringUtils.endsWithIgnoreCase(fileName, "xlsx")){
				excelParser								=		factory.getExcelParser();
		}else if(StringUtils.endsWithIgnoreCase(fileName, "xls")){
			
					excelParser							=		factory.getExcelXLSParser();
		}else{
			l_file.delete();
			dataStoreBean.setValidationName(CommonConstants.validationMessage);
			return ResultPageConstants.landExcelPage;
		}
		auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "File name "+fileName +" started to upload");
		FileInputStream l_stream;
		try {
			l_stream = new FileInputStream(l_file);
        byte[] b = new byte[(int) l_file.length()];
        l_stream.read(b);
        List<Map<Integer, String>> l_sheet_data 			= 		(List<Map<Integer, String>>) excelParser.convertExcelToList(b, 0);
        log.info(CommonUtil.getlogDetail(request) +"Parsed Data is : "+l_sheet_data);
        if(l_sheet_data != null){
        	totalRecords	=	l_sheet_data.size();
        	
        	System.out.println("totalRecords --- "+totalRecords);
        	if(l_sheet_data.get(0)	==	null || totalRecords == 1)
        	{
        		l_file.delete();
        		dataStoreBean.setValidationName(CommonConstants.fileEmptyError);
    			return ResultPageConstants.landExcelPage;
        	}
        	if(l_sheet_data.get(0).size() == CommonConstants.oldFileColumns){
        		
        		dataStoreBean.setFormat("old");
        	}else if(l_sheet_data.get(0).size() == CommonConstants.newFileCOlumns){
        		dataStoreBean.setFormat("new");
        		
        	}else if(l_sheet_data.get(0).size()!=CommonConstants.newFileCOlumns && l_sheet_data.get(0).size() != CommonConstants.oldFileColumns){
        		dataStoreBean.setValidationName(CommonConstants.columnNumbervalidationMessage);
    			return ResultPageConstants.landExcelPage;
        	}
        	
        }
        auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Total number of records present in the file"+fileName+" are::"+(totalRecords-1));
        LinkedList<ArrayList<DataStore>>	store			=    	ExcelHelper.populateDataStore(l_sheet_data, request);
        if(store.size() == 0){
        	dataStoreBean.setValidationName(CommonConstants.headervalidationMessage);
			return ResultPageConstants.landExcelPage;
        	
        }
        ArrayList<DataStore> storedRecords					=		store.get(0);
        if(storedRecords.size() == 0){
        	dataStoreBean.setValidationName(CommonConstants.noValidRecords);
        	return ResultPageConstants.landExcelPage;
        	
        }
        log.info(CommonUtil.getlogDetail(request) +"stored records are::"+storedRecords.toString());
        ArrayList<DataStore> rejectedRecords				=		store.get(1);
        if(rejectedRecords !=null){
        	rejectedRecordssize								=		rejectedRecords.size();
        	if(rejectedRecordssize >0){
        		dataStoreBean.setDownloadRejected(true);
        	}else{
        		dataStoreBean.setDownloadRejected(false);
        	}
        }
        IExcelDAO excelDao									=		factory.getExcelDAO();
        log.info(CommonUtil.getlogDetail(request) +"Clearing staging before insertion of new records");
        if("SEARCH".equalsIgnoreCase(typeData)) {
        	excelDao.clearSearchStaging();//added a new table here for multiple search
        } else {
        	excelDao.clearStaging();

            for(DataStore rejectedBeans : rejectedRecords){
            	rejectedBeans.setLoggedinUserName(request.getSession().getAttribute("userName").toString());
            	rejectedBeans.setFileName(fileName);
            	excelDao.insertRejectedData(rejectedBeans);
            }
        }        
       
        
        for(DataStore dataBean : storedRecords){        	
        	dataBean.setFileName(fileName);
        	dataBean.setLoggedinUserName(request.getSession().getAttribute("userName").toString());
        	if("SEARCH".equalsIgnoreCase(typeData)) {
        		result		=	excelDao.insertData(dataBean,CommonConstants.excelInsertSPSearch);   
        	} else {
        		result		=	excelDao.insertData(dataBean,CommonConstants.excelInsertSP);   
        	}
        }

		}catch (FileNotFoundException e) {
			log.error("File not found"+e.getMessage());
    	} catch (IOException e) {
    		log.error("Exception while reading input"+e.getMessage());
		} catch (Exception e) {
			log.error("Error while performing action");
				e.printStackTrace();
			}finally{
				l_file.delete();
				if(!result){
					dataStoreBean.setValidationName(CommonConstants.dataInsertion);
					return ResultPageConstants.landExcelPage;
				}
				
			}
		//auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.DataUpload, "DataUpload", "Records successfully inserted into the staging table::"+(totalRecords-rejectedRecordssize));
		l_file.delete();
	}catch (Exception e) {
		throw new CIBILException(request, e);
	}
	
		return ResultPageConstants.landExcelPage;
	} 
	
	public int viewForm(ModelMap model, HttpServletRequest request) throws CIBILException {
	try{	
		List<DataStore>		dataStoreBeansFlag					=		null;
		int   				duplicateRecordsSize				=		0;
		ExcelServiceFactory factory								=	 	ExcelServiceFactory.getInstance();
		IExcelDAO 		excelDAO								= 		factory.getExcelDAO();
		try {
			dataStoreBeansFlag										=	 	excelDAO.getDuplicates();
		} catch (Exception e) {
			log.error("Error while getting the duplicates::"+e.getMessage());
			e.printStackTrace();
		}	
		log.info("Results from duplicate proc::"+dataStoreBeansFlag);
		if(dataStoreBeansFlag !=null){
			duplicateRecordsSize	=	dataStoreBeansFlag.size();
		}
		model.addAttribute("beansBag",dataStoreBeansFlag);
		return duplicateRecordsSize;
	}catch (Exception e) {
		throw new CIBILException(request, e);
	}
		
	}
	
	public int viewMatchedForm(ModelMap model, HttpServletRequest request) throws CIBILException {
		try{	
			List<DataStore>		dataStoreBeansFlag					=		null;
			int   				duplicateRecordsSize				=		0;
			ExcelServiceFactory factory								=	 	ExcelServiceFactory.getInstance();
			IExcelDAO 		excelDAO								= 		factory.getExcelDAO();
			try {
				dataStoreBeansFlag										=	 	excelDAO.getDuplicates();
			} catch (Exception e) {
				log.error("Error while getting the duplicates::"+e.getMessage());
				e.printStackTrace();
			}	
			log.info("Results from duplicate proc::"+dataStoreBeansFlag);
			if(dataStoreBeansFlag !=null){
				duplicateRecordsSize	=	dataStoreBeansFlag.size();
			}
			model.addAttribute("beansBag",dataStoreBeansFlag);
			return duplicateRecordsSize;
		}catch (Exception e) {
			throw new CIBILException(request, e);
		}
			
		}
	
	
	
	
	


}
