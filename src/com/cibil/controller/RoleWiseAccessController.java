package com.cibil.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.BaseBean;
import com.cibil.bean.MenuItemBean;
import com.cibil.bean.RoleBean;
import com.cibil.bean.RoleWiseAccessBean;
import com.cibil.manager.AuditManager;
import com.cibil.manager.IAuditManager;
import com.cibil.manager.IMenuManager;
import com.cibil.manager.IRoleManager;
import com.cibil.service.ServiceFactory;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ResultPageConstants;
import com.cibil.validator.RolewiseAccessValidator;

/**
 * @author arunbal.srinivasan
 *
 */
@Controller
public class RoleWiseAccessController extends BaseController {
	
	public Log log = LogFactory.getLog(RoleWiseAccessController.class);
	
	@Autowired
	RolewiseAccessValidator roleValidator;

	@Override
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws CIBILException {
		return null;
	}

	@RequestMapping("roleWiseAccess.htm")
	protected String viewSubmit(
			@ModelAttribute("RoleWiseAccessBean") RoleWiseAccessBean roleWiseAccessBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method Start");
			viewForm(roleWiseAccessBean, model, "onloadFlow",request, null);
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method End");
			return ResultPageConstants.landRoleWiseAccess;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	private void viewForm(RoleWiseAccessBean roleWiseAccessBean,
			ModelMap model, String flow, HttpServletRequest request, String action) throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method Start");
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			List roleList = roleManager.getRoleNameList(request);
			model.addAttribute("systemRoleList", roleList);
			if("Save".equalsIgnoreCase(action)){
				String roleName=null;
				IAuditManager auditManager=AuditManager.getInstance();
				for (RoleBean bean : (List<RoleBean>) roleList) {
					if(bean.getRoleID().equals(roleWiseAccessBean.getSystemRole())){
						roleName=bean.getRoleName();
						break;
					}
				}
				auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Rolewise Access Rights", "Role Name "+roleName+" Rights Updated");
			}
			IMenuManager menuManager = factory.getMenuManager();
			List menuList = menuManager.getParentMenus(request);
			if ("changedFlow".equals(flow) && !"NONE".equalsIgnoreCase(roleWiseAccessBean.getSystemRole())) {
				IAuditManager auditManager=AuditManager.getInstance();
				auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Adminstration,"Rolewise Access Rights", "Rolewise Access Rights Viewed");
				List writeAccessMenus = menuManager
						.getRolewiseAccess(request, roleWiseAccessBean);
				for (Object object : menuList) {
					MenuItemBean menuItemBean = (MenuItemBean) object;
					menuItemBean.setIsPreSelected(false);
					if (writeAccessMenus.contains(menuItemBean.getMenuID())) {
						menuItemBean.setIsPreSelected(true);
					}
				}
			}else{
				roleWiseAccessBean.setSystemRole(null);
				for (Object object : menuList) {
					MenuItemBean menuItemBean = (MenuItemBean) object;
					if ("changedFlow".equals(flow) && (roleWiseAccessBean.getSystemRole()==null || "NONE".equalsIgnoreCase(roleWiseAccessBean.getSystemRole()))){
						menuItemBean.setIsPreSelected(false);
					} else if(roleWiseAccessBean.getSelectedMenuIDCheckBox()!=null) {
						for(String menuID: roleWiseAccessBean.getSelectedMenuIDCheckBox()){
							if(menuItemBean.getMenuID().contains(menuID)){
								menuItemBean.setIsPreSelected(true);
							}
						}
					}
				}
			}
			model.addAttribute("menuList", menuList);
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method End");
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("rolewiseAccessSubmit.htm")
	protected String roleWiseAccessSubmit(
			@ModelAttribute("RoleWiseAccessBean") RoleWiseAccessBean roleWiseAccessBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"roleWiseAccessSubmit Method Start");
			roleValidator.validate(roleWiseAccessBean, result);
			if (result.hasErrors()) {
				viewForm(roleWiseAccessBean, model, "onloadFlow", request, null);
				return ResultPageConstants.landRoleWiseAccess;
			}
			ServiceFactory factory = ServiceFactory.getInstance();
			IMenuManager menuManager = factory.getMenuManager();
			roleWiseAccessBean.setLoggedinUserName((String) request.getSession()
					.getAttribute(CommonConstants.sessionUserName));
			menuManager.setRolewiseAccess(request, roleWiseAccessBean);
			viewForm(roleWiseAccessBean, model, "changedFlow", request,"Save");
			/*roleWiseAccessBean.setSystemRole(null);*/
			roleWiseAccessBean.setMessage(CommonConstants.RoleWiseAddORUpdated);
			log.info(CommonUtil.getlogDetail(request) +"roleWiseAccessSubmit Method End");
			return ResultPageConstants.landRoleWiseAccess;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("chngRoleWiseSysRole.htm")
	protected String chngRoleWiseSysRole(
			@ModelAttribute("RoleWiseAccessBean") RoleWiseAccessBean roleWiseAccessBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"chngRoleWiseSysRole Method Start");
			viewForm(roleWiseAccessBean, model, "changedFlow", request, null);
			log.info(CommonUtil.getlogDetail(request) +"chngRoleWiseSysRole Method End");
			return ResultPageConstants.landRoleWiseAccess;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) +"performAction Method Start");
		return null;
	}

}
