/**
 * 
 */
package com.cibil.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.servlet.ServletContext;
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
import com.cibil.bean.UserBean;
import com.cibil.dao.UserDAO;
import com.cibil.manager.AuditManager;
import com.cibil.manager.IAuditManager;
import com.cibil.manager.IMenuManager;
import com.cibil.manager.IRoleManager;
import com.cibil.manager.IUserManager;
import com.cibil.service.ServiceFactory;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ReadPropertiesFile;
import com.cibil.util.ResultPageConstants;
import com.cibil.validator.UserValidator;

/**
 * @author arunbal.srinivasan
 *
 */
@Controller
public class UserMasterContoller extends BaseController {

	@Autowired
	UserValidator userValidator;

	public Log log = LogFactory.getLog(UserMasterContoller.class);

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws CIBILException {
		String result = null;
		try{
			log.info(CommonUtil.getlogDetail(request) +"performAction Method Start");
			UserBean userBean = (UserBean) baseBean;
			userBean.setLoggedinUserName((String) request.getSession().getAttribute(CommonConstants.sessionUserName));
			if (!StringUtils.isEmpty(userBean.getFlowName())) {
				ServiceFactory factory = ServiceFactory.getInstance();
				IUserManager userManager = factory.getUserManager();
				switch (userBean.getFlowName()) {
				case "Save":
					if (userManager.insertData(request, userBean)) {
						IAuditManager auditManager = AuditManager.getInstance();
						auditManager.addAudit(request.getSession().getAttribute("userName").toString(),
								UserAccessConstants.Adminstration, "Add User","User Name: " + userBean.getUserName() + " Added");
						result = ResultPageConstants.viewUser;
						userBean.setMessage(CommonConstants.UserAdded);
					}
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
		log.info(CommonUtil.getlogDetail(request) +"performAction Method End");
		return result;
	}

	@RequestMapping("userNamechange.htm")
	public @ResponseBody String getName(@RequestParam String name,
			HttpServletRequest request) throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"getName Method Start " + name);
			ServiceFactory factory = ServiceFactory.getInstance();
			IUserManager userManager = factory.getUserManager();
			log.info(CommonUtil.getlogDetail(request) +"getName Method End");
			if (userManager.isUserNameAvailable(request, name)) {
				return CommonUtil.geti18nValue(request, CommonConstants.UserAlreadyExists);
			}
			if (!checkUserExistInLdap(name, request)) {
				return CommonUtil.geti18nValue(request, CommonConstants.UserNotExisted);
			}
			return null;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("viewUser.htm")
	protected String viewSubmit(@ModelAttribute("UserBean") UserBean userBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method Start");
			viewForm(model, request);
			IAuditManager auditManager = AuditManager.getInstance();
			auditManager.addAudit(request.getSession().getAttribute("userName")
					.toString(), UserAccessConstants.Adminstration, "User Maintainence", "Users Details Viewed");
			log.info(CommonUtil.getlogDetail(request) +"viewSubmit Method End");
			return ResultPageConstants.viewUser;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	private void viewForm(ModelMap model, HttpServletRequest request) throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method Start");
			ServiceFactory factory = ServiceFactory.getInstance();
			IUserManager roleManager = factory.getUserManager();
			List list = roleManager.getUserList(request);
			IMenuManager iMenuManager=factory.getMenuManager();
			String pageSize=iMenuManager.getRecordsPerPage(request);
			request.setAttribute("pageSize", pageSize);
			model.addAttribute(list);
			log.info(CommonUtil.getlogDetail(request) +"viewForm Method End");
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("userSubmit.htm")
	protected String onSubmit(@ModelAttribute("UserBean") UserBean userBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try {	
			log.info(CommonUtil.getlogDetail(request) + "onSubmit() Method Start");
			userValidator.validate(userBean, result);
			String _result = ResultPageConstants.landUserMaster;
			if (!result.hasErrors()) {
				_result = performAction(request, userBean);
				viewForm(model, request);
			} else {
				getRoleList(request, model);
				getUserStatusList(request, model);
			}
			log.info(CommonUtil.getlogDetail(request) + "onSubmit() Method End");
			return _result;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@Override
	@RequestMapping("userMaster.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			model.addAttribute("UserBean", new UserBean());
			getRoleList(request, model);
			getUserStatusList(request, model);
			return ResultPageConstants.landUserMaster;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	public void getUserStatusList(HttpServletRequest request,ModelMap model) throws CIBILException {
		try{
			UserDAO userDAO = UserDAO.getInstance();
			List userStatusList = userDAO.getUserStatusList(request);
			model.addAttribute("userStatusList", userStatusList);
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	public void getRoleList(HttpServletRequest request, ModelMap model) throws CIBILException {
		try{	
			ServiceFactory factory = ServiceFactory.getInstance();
			IRoleManager roleManager = factory.getRoleManager();
			List roleList = roleManager.getRoleNameList(request);
			model.addAttribute("systemRoleList", roleList);
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("editUser.htm")
	protected String editSubmit(@ModelAttribute("UserBean") UserBean userBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) +"editSubmit Method Start");
			ServiceFactory factory = ServiceFactory.getInstance();
			IUserManager userManager = factory.getUserManager();
			userManager.getUser(request, userBean);
			IAuditManager auditManager = AuditManager.getInstance();
			auditManager.addAudit(request.getSession().getAttribute("userName")
					.toString(), UserAccessConstants.Adminstration, "User Maintainence", "UserName:" + userBean.getUserName() + " Details Viewed");
			getRoleList(request,model);
			getUserStatusList(request, model);
			log.info(CommonUtil.getlogDetail(request) +"editSubmit Method End");
			return ResultPageConstants.userEdit;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@RequestMapping("updateUser.htm")
	protected String onUpdate(@ModelAttribute("UserBean") UserBean userBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		try{
			log.info(CommonUtil.getlogDetail(request) + "onUpdate() Method Start");
			userValidator.validate(userBean, result);
			userBean.setLoggedinUserName((String) request.getSession()
					.getAttribute(CommonConstants.sessionUserName));
			String _result = ResultPageConstants.userEdit;
			if (!result.hasErrors()) {
				ServiceFactory factory = ServiceFactory.getInstance();
				IUserManager userManager = factory.getUserManager();
				userManager.updateData(request, userBean);
				IAuditManager auditManager = AuditManager.getInstance();
				auditManager.addAudit(request.getSession().getAttribute("userName")
						.toString(), UserAccessConstants.Adminstration, "User Maintainence", "User Name:" + userBean.getUserName() + " Details updated");
				_result = ResultPageConstants.viewUser;
				userBean.setMessage(CommonConstants.UserUpdated);
				viewForm(model, request);
			} else {
				getRoleList(request, model);
				getUserStatusList(request, model);
			}
			log.info(CommonUtil.getlogDetail(request) + "onUpdate() Method End");
			return _result;
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	protected boolean checkUserExistInLdap(String userName, HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) +"checkUserExistInLdap() Method Start"+ userName);
		boolean userExist = false;
		try {
			ServletContext sc = null;
			String Key = null;
			String domainComponentValue = null;
			String domainExtensionComponentValue = null;
			String objectClassVal = null;
			String domainNameVal = null;
			String matchStringVal = null;
			String strPath = null;
			sc = request.getSession().getServletContext();
			strPath = sc.getRealPath("/");
			ReadPropertiesFile fileObj = new ReadPropertiesFile();
			Properties properties = fileObj.readFile(strPath + "/userSearch.properties");

			Enumeration<Object> enuKeys = properties.keys();
			while (enuKeys.hasMoreElements()) {
				Key = (String) enuKeys.nextElement();
				if (Key.equalsIgnoreCase(UserAccessConstants.DOMAIN_KEY)) {
					domainComponentValue = properties.getProperty(Key);
				}
				if (Key.equalsIgnoreCase(UserAccessConstants.DOMAIN_EXTENSION_KEY)) {
					domainExtensionComponentValue = properties.getProperty(Key);
				}
				if (Key.equalsIgnoreCase(UserAccessConstants.OBJECT_CLASS_KEY)) {
					objectClassVal = properties.getProperty(Key);
				}
				if (Key.equalsIgnoreCase(UserAccessConstants.DOMAIN_NAME_KEY)) {
					domainNameVal = properties.getProperty(Key);
				}
				if (Key.equalsIgnoreCase(UserAccessConstants.MATCH_STRING_KEY)) {
					matchStringVal = properties.getProperty(Key);
				}
			}

			String searchBase = "DC=" + domainComponentValue.trim() + ",DC=" + domainExtensionComponentValue.trim() + "";
			StringBuilder searchFilter = new StringBuilder("(&");
			searchFilter.append("(objectClass=" + objectClassVal.trim() + ")");
			searchFilter.append("(userPrincipalName=" + userName.trim()+domainNameVal.trim() + ")");
			searchFilter.append(")");
			String returnAttrs[] = { "memberOf" };
			SearchControls sCtrl = new SearchControls();
			sCtrl.setSearchScope(SearchControls.SUBTREE_SCOPE);
			sCtrl.setReturningAttributes(returnAttrs);
			NamingEnumeration<SearchResult> answer = null;
			answer = ((DirContext) request.getSession().getAttribute("ctxObj")).search(searchBase, searchFilter.toString(), sCtrl);

			String match = matchStringVal.trim();
			while (answer.hasMoreElements()) {
				SearchResult sr = null;
				sr = answer.next();
				String memberOfAttrValue = sr.getAttributes().get("memberOf").toString();
				log.info("memberOfAttrValue for criteria : :" + memberOfAttrValue);
				if (memberOfAttrValue.contains(match)) {
					userExist = true;
					log.info("user exists in LDAP "+userName);
					break;
				}
			}
		} catch (Exception e) {
			log.error(request,e);
		}
		log.info(CommonUtil.getlogDetail(request) +"checkUserExistInLdap() Method End");
		return userExist;
	}

}
