package com.cibil.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.BaseBean;
import com.cibil.bean.LoginBean;
import com.cibil.manager.AuditManager;
import com.cibil.seurity.SessionTracking;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;

/**
 * @author rohit.khaire
 *
 */
@Controller
public class LogoutController extends BaseController {
	private Log log = LogFactory.getLog(LogoutController.class);
	public static AuditManager auditInstance;

	@RequestMapping("logout.htm")
	public String returnPage(ModelMap model, HttpServletRequest request)
			throws Exception {
		try{
			String strUrl = null;
			/* for Audit Event by Somesh Mungi */
			String userName = (null != request.getSession().getAttribute(
					CommonConstants.sessionUserName)) ? request.getSession().getAttribute(CommonConstants.sessionUserName).toString(): null;
	
			auditInstance = AuditManager.getInstance();
			auditInstance.addAudit(request.getSession().getAttribute("userName") .toString(), UserAccessConstants.Login, "Logout", userName + " Logged Out");
			/* End of Audit Event Code */
			log.info(CommonUtil.getlogDetail(request) + " Logged out successfully");
			request.getSession().invalidate();
			strUrl = request.getRequestURL().toString();
			strUrl = strUrl.replace("logout.htm", "");
			request.setAttribute("logoutMessage", "true");
			//return "redirect:" + strUrl;
			model.addAttribute("LoginBean", new LoginBean());
			SessionTracking.deleteUser(userName);
			return "loginPage";
		} catch (Exception e) {
			throw new CIBILException(request, e);
		}
	}

	@Override
	public String performAction(HttpServletRequest request, BaseBean baseBean)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}