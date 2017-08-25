package com.cibil.controller;

/**
 * The controller is used for authentication in LDAP active Directory &
 * against DB user credentials
 * 
 * @author rohit.khaire
 */
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.cibil.bean.DataStore;
import com.cibil.bean.LoginBean;
import com.cibil.dao.UserDAO;
import com.cibil.manager.AuditManager;
import com.cibil.manager.IAuditManager;
import com.cibil.seurity.SessionTracking;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ReadPropertiesFile;
import com.cibil.util.ResultPageConstants;
import com.cibil.validator.LoginValidator;


@Controller
public class LoginController extends BaseController {
	DirContext ctx=null;

	@Autowired
	LoginValidator logInValidator;

	private Log log = LogFactory.getLog(LoginController.class);

	@RequestMapping("loginSubmit.htm")
	protected String onSubmit(@ModelAttribute("LoginBean") LoginBean loginBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
		String _result = ResultPageConstants.landLoginPage;
		try {
			logInValidator.validate(loginBean, result);
			if (!result.hasErrors()) {
				_result = performAction(request, loginBean);
				if (ResultPageConstants.loginSuccess.equals(_result)) {
					DataStore dataStore = new DataStore();
					dataStore.setCondition("2");
					model.addAttribute("DataStore", dataStore);
				}
			}
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
		return _result;
	}

	@Override
	@RequestMapping("displayForm.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws CIBILException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		model.addAttribute("LoginBean", new LoginBean());
		return ResultPageConstants.landLoginPage;
	}

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws CIBILException {
		try {
			boolean autheFlag 					= false;
			ServletContext sc					= null;
			String strPath						= null;
			String strSecAuthenticationValue	= null;
			String strProviderUrlValue			= null;
			String strSecPrincipalDomainValue	= null;
			UserDAO userDAO						= null;
			ReadPropertiesFile fileObj			= null;
			String strSecAuthenticationKey		= null;
			String strProviderUrlKey			= null;
			String strSecPrincipalDomainKey		= null;
			String strStatusId					= null;
			log.info(CommonUtil.getlogDetail(request) + "Method :authenticate() Start ");
			LoginBean loginBean = (LoginBean) baseBean;
			userDAO = UserDAO.getInstance();
			try {
				Hashtable<String, String> env = new Hashtable<String, String>();
				sc = request.getSession().getServletContext();
				strPath=sc.getRealPath("/");
				fileObj=new ReadPropertiesFile();
				// Reading ldap active directory constant values
				Properties properties=fileObj.readFile(strPath+"/login.properties");
				Enumeration<Object> enuKeys = properties.keys();
				if (enuKeys.hasMoreElements()) {
					strSecAuthenticationKey = (String) enuKeys.nextElement();
					strSecAuthenticationValue = properties.getProperty(strSecAuthenticationKey);
					strProviderUrlKey = (String) enuKeys.nextElement();
					strProviderUrlValue = properties.getProperty(strProviderUrlKey);
					strSecPrincipalDomainKey = (String) enuKeys.nextElement();
					strSecPrincipalDomainValue = properties.getProperty(strSecPrincipalDomainKey);
				}
				env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
				env.put(Context.SECURITY_AUTHENTICATION, strSecAuthenticationValue.trim());
				env.put(Context.PROVIDER_URL,  strSecPrincipalDomainValue.trim());
				env.put(Context.SECURITY_PRINCIPAL, loginBean.getUser().trim()+strProviderUrlValue.trim());
				env.put(Context.SECURITY_CREDENTIALS, loginBean.getPassword());
				ctx=new InitialDirContext(env);
				request.getSession().setAttribute("ctxObj",ctx);
				autheFlag = true;
			} catch (Exception e) {
				autheFlag = true;
				log.error("Name "+ loginBean.getUser() +" ; Message "+e.toString());
				loginBean.setLoginStatus(CommonUtil.geti18nValue(request,CommonConstants.LoginLDAPAuthFail));
			}
			if (true == autheFlag) {
				strStatusId=userDAO.isEmployeeExists(request,loginBean.getUser());
				if (strStatusId.equalsIgnoreCase("1")) {
					autheFlag = true;
				} else if (strStatusId.equalsIgnoreCase("2") || strStatusId.equalsIgnoreCase("3")) {
					autheFlag = false;
					loginBean.setLoginStatus(CommonUtil.geti18nValue(request,CommonConstants.InactiveUser));
				} else{
					autheFlag = false;
					loginBean.setLoginStatus(CommonUtil.geti18nValue(request,CommonConstants.LoginDBAuthFail));
				}
			}
			if (true == autheFlag) {
				IAuditManager auditManager=AuditManager.getInstance();
				request.getSession().setAttribute(CommonConstants.sessionUserName,loginBean.getUser());
				auditManager.addAudit(request.getSession().getAttribute("userName").toString(),UserAccessConstants.Login,"Logged in", "User: "+ loginBean.getUser() + " logged in");
				
				loginBean.setLoginStatus(CommonConstants.successLoginMsg);
			} else {
				userDAO.updateBadLoginAttempt(request,loginBean.getUser());
				request.getSession().removeAttribute("userName");
			}
	
			log.info(CommonUtil.getlogDetail(request) + "Method :authenticate() End");
			if (true == autheFlag) {
				SessionTracking.addUser(loginBean.getUser(), request);
			}
			if (autheFlag) {
				return ResultPageConstants.loginSuccess;
			} else {
				return ResultPageConstants.landLoginPage;
			}
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}
	@RequestMapping("home.htm")
	protected String homePage(@ModelAttribute("LoginBean") LoginBean loginBean,
			BindingResult result, ModelMap model, HttpServletRequest request)
			throws CIBILException {
			return ResultPageConstants.loginSuccess;
	}
	
}
