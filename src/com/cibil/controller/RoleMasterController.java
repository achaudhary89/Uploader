/**
 * 
 */
package com.cibil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.BaseBean;
import com.cibil.bean.RoleBean;
import com.cibil.dao.RoleDAO;
import com.cibil.manager.AuditManager;
import com.cibil.manager.IAuditManager;
import com.cibil.manager.IMenuManager;
import com.cibil.manager.IRoleManager;
import com.cibil.service.ServiceFactory;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ResultPageConstants;
import com.cibil.validator.RoleValidator;

/**
 * @author arunbal.srinivasan
 *
 */
@Controller
public class RoleMasterController extends BaseController {

	@Autowired
	RoleValidator roleValidator;
	public Log log = LogFactory.getLog(RoleMasterController.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.cibil.controller.BaseController#performAction(javax.servlet.http.
	 * HttpServletRequest, com.cibil.bean.BaseBean)
	 */
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"performAction Method Start");
			RoleBean roleBean = (RoleBean) baseBean;
			roleBean.setLoggedinUserName((String) request.getSession()
					.getAttribute(CommonConstants.sessionUserName));
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			String result = null;
	
			if (!StringUtils.isEmpty(roleBean.getFlowName())) {
				String flowName = roleBean.getFlowName().replace(",", "");
				switch (flowName) {
				case "Save":
					if (roleManager.insertData(request, roleBean)){
						IAuditManager auditManager=AuditManager.getInstance();
						auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Add Role", "Role Added Name:"+ roleBean.getRoleName());
						result = ResultPageConstants.viewRole;
						roleBean.setMessage(CommonConstants.RoleAdded);
					}
					break;
				}
			}
			if (roleBean.getFlowName() != null) {
				roleBean.setFlowName(null);
			}
			log.info(CommonUtil.getlogDetail(request) +"performAction Method End");
			return result;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("roleNamechange.htm")
	public @ResponseBody String getName(@RequestParam String name,HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"getName Method Start "+ name);
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			log.info(CommonUtil.getlogDetail(request) +"getName Method End");
			if(roleManager.isNameAvailable(request, name)){
				return CommonUtil.geti18nValue(request, CommonConstants.RoleAlreadyExists);
			}
			return null;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}


	@RequestMapping("viewRole.htm")
	protected String viewSubmit(@ModelAttribute("RoleBean") RoleBean roleBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method Start");
			viewForm(model,request);
			IAuditManager auditManager=AuditManager.getInstance();
			auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Role Maintainence", "Roles Details Viewed");
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method End");
			return ResultPageConstants.viewRole;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	public void viewForm(ModelMap model, HttpServletRequest request) throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method Start");
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			List list = roleManager.getRoleList(request);
			IMenuManager iMenuManager=factory.getMenuManager();
			String pageSize=iMenuManager.getRecordsPerPage(request);
			request.setAttribute("pageSize", pageSize);
			model.addAttribute(list);
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method End");
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("editRole.htm")
	protected String editSubmit(@ModelAttribute("RoleBean") RoleBean roleBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"editSubmit Method Start");
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			roleManager.getRole(request,roleBean);
			IAuditManager auditManager=AuditManager.getInstance();
			auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Role Maintainence", "Role Name:"+roleBean.getRoleName()+" Details Viewed");
			getRoleStatusList(request,model);
			log.info(CommonUtil.getlogDetail(request) +"editSubmit Method End");
			return ResultPageConstants.editRole;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("roleSubmit.htm")
	protected String onSubmit(@ModelAttribute("RoleBean") RoleBean roleBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) + "onSubmit() Method Start");
			roleValidator.validate(roleBean, result);
			String _result = ResultPageConstants.landRoleMaster;
			if (!result.hasErrors()) {
				_result = performAction(request, roleBean);
				viewForm(model, request);
			} else {
				getRoleStatusList(request, model);
			}
			log.info(CommonUtil.getlogDetail(request) + "onSubmit() Method End");
			return _result;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@Override
	@RequestMapping("roleMaster.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			model.addAttribute("RoleBean", new RoleBean());
			ServiceFactory factory = ServiceFactory.getInstance();
			getRoleStatusList(request, model);
			return ResultPageConstants.landRoleMaster;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	public void getRoleStatusList(HttpServletRequest request, ModelMap model) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		List roleStatusList = roleDAO.getRoleStatusList(request);
		model.addAttribute("roleStatusList", roleStatusList);
	}

	@RequestMapping("roleUpdate.htm")
	protected String onUpdate(@ModelAttribute("RoleBean") RoleBean roleBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) + "onUpdate() Method Start");
			roleValidator.validate(roleBean, result);
			roleBean.setLoggedinUserName((String) request.getSession()
					.getAttribute(CommonConstants.sessionUserName));
			String _result = ResultPageConstants.editRole;
			if (!result.hasErrors()) {
				ServiceFactory factory = ServiceFactory.getInstance();
				IRoleManager roleManager = factory.getRoleManager();
				roleManager.updateData(request, roleBean);
				IAuditManager auditManager=AuditManager.getInstance();
				auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Role Maintainence", "Role Name: "+ roleBean.getRoleName() + " Details updated");
				_result = ResultPageConstants.viewRole;
				viewForm(model, request);
				roleBean.setMessage(CommonConstants.RoleUpdated);
			} else {
				getRoleStatusList(request, model);
			}
			log.info(CommonUtil.getlogDetail(request) + "onUpdate() Method End");
			return _result;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

}
