package com.cibil.controller;

/**
 * @author alok.chaudhary
 * 
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
import com.cibil.bean.ExcelBean;
import com.cibil.bean.SearchBean;
import com.cibil.dao.IExcelDAO;
import com.cibil.manager.AuditManager;
import com.cibil.service.ExcelServiceFactory;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil; 
import com.cibil.util.LinkedProperties;
import com.cibil.util.ReadPropertiesFile;
import com.cibil.util.ResultPageConstants;
import com.cibil.validator.SearchValidator;

@Controller
public class SearchController extends BaseController {

	@Autowired
	SearchValidator searchValidator;
	private Log 							log 					= 	LogFactory.getLog(SearchController.class);
	//public static Properties 			 	excelProperties			=	new LinkedProperties();
	public List<DataStore> 					beansBag				=	null;
	public static AuditManager				auditInstance;
	@Override
	@RequestMapping("searchMaster.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws Exception {
		// TODO Auto-generated method stub
		DataStore dataStore = new DataStore();
		dataStore.setCondition("2");
		model.addAttribute("DataStore", dataStore);
		return ResultPageConstants.landSearchMaster;
	}

	@RequestMapping("repoSearch.htm")
	protected String onSubmit(
			@ModelAttribute("DataStore") DataStore searchBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws Exception {
		log.info(CommonUtil.getlogDetail(request) +"onSubmit search started::");
		auditInstance										= 		AuditManager.getInstance();
		searchValidator.validate(searchBean, result);
		String _result = ResultPageConstants.loginSuccess;
		if (!result.hasErrors()) {
			_result = performAction(request, searchBean);
			model.addAttribute("beansBag",beansBag);
		}
		
		log.info(CommonUtil.getlogDetail(request) +"onSubmit search completed::");
		return _result;
	}
	
	@RequestMapping("deleteSearch.htm")
	public String deleteFromSearch(@ModelAttribute("DataStore") DataStore searchBean,
			BindingResult result, ModelMap model, HttpServletRequest request)throws Exception {
		
		log.info(CommonUtil.getlogDetail(request) +"Method deleteFromSearch() start:");
		auditInstance									= 		AuditManager.getInstance();
		ExcelServiceFactory factory						=		ExcelServiceFactory.getInstance();	
		IExcelDAO			excelDAO					=		factory.getExcelDAO();
		String[] checked								= 		searchBean.getSelectedRecordIDCheckBox();
		if(checked	==	null){
			searchBean.setValidationName(CommonConstants.recordNotToDelete);
			return ResultPageConstants.loginSuccess;
			
		}
		excelDAO.deleteFromTransactions(searchBean,request);
		int length			=	checked.length;
		auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.SearchData, "Search", "User deleted "+length+" records");
		log.info(CommonUtil.getlogDetail(request) +"Method deleteFromSearch() end:");
		searchBean.setValidationName(CommonConstants.deleteSearch);
		return ResultPageConstants.loginSuccess;
	}
	
	
	@RequestMapping("exportSearch.htm")
	public ModelAndView exportFromSearch(@ModelAttribute("DataStore") DataStore searchBean,
			BindingResult result, ModelMap model, HttpServletRequest request)throws Exception {
		
		log.info(CommonUtil.getlogDetail(request) +"Method exportFromSearch() start:");
		auditInstance									= 		AuditManager.getInstance();
		ServletContext 				 sc						= 		request.getSession().getServletContext();
		String 						 strPath 				= 		sc.getRealPath("/");
		ReadPropertiesFile 			 fileObj 				= 		new ReadPropertiesFile();
//		excelProperties 									= 		fileObj.readOrderedFile(strPath+ CommonConstants.globalProperties);
		ExcelServiceFactory 	factory						=		ExcelServiceFactory.getInstance();	
		IExcelDAO				excelDAO					=		factory.getExcelDAO();
		String[] 				selectedCheckBoxes			=		searchBean.getSelectedRecordIDCheckBox();
		if(selectedCheckBoxes	==	null){
			log.info(CommonUtil.getlogDetail(request) +"No record to export returning validation message");
			searchBean.setValidationName(CommonConstants.searchExport);
			return new ModelAndView("success", "command", searchBean);
		}
		LinkedList<DataStore>	exportRecords  				=		(LinkedList<DataStore>) excelDAO.selectFromTransactions(searchBean);
		if(exportRecords != null){
			int length			=	exportRecords.size();
			auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.SearchData, "Search", "User exported "+length+" records");
		}
		ExcelBean	bean					=		new ExcelBean();
		bean.setRejectedRecords(exportRecords);
		log.info(CommonUtil.getlogDetail(request) +"Method exportFromSearch() end:");
		return new ModelAndView("RecordsToExport", "buildRecordsToExcel", bean);
	}
	

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws Exception {
		
		ExcelServiceFactory factory						=		ExcelServiceFactory.getInstance();	
		IExcelDAO			excelDAO					=		factory.getExcelDAO();
		DataStore	searchParamBean						=		(DataStore)baseBean;
		beansBag										=		excelDAO.getSearchResults(baseBean);
		if(beansBag.size() == 0){
			searchParamBean.setValidationName(CommonConstants.searchResult);
		}
		//auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.SearchData, "Search", "User searched using the following parameters "+"first name:"+searchParamBean.getName()+" consumer me:"+searchParamBean.getConsumerMe()+" other:"+searchParamBean.getOthers()+" Account Number:"+searchParamBean.getAccountNumber()+" From Date:"+searchParamBean.getFromDate()+" To Date:"+searchParamBean.getToDate() +" FileName:"+searchParamBean.getFileName());
		return ResultPageConstants.loginSuccess;
	}
	
	
	

}
