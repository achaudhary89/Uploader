package com.cibil.seurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.owasp.esapi.ESAPI;
import org.owasp.esapi.EncoderConstants;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cibil.util.CIBILException;
import com.cibil.util.CommonUtil;

/**
 * @author arunbal.srinivasan
 *
 */
public class TokenInterceptor extends HandlerInterceptorAdapter {
	private Log log = LogFactory.getLog(TokenInterceptor.class);

	private String csrfToken;
	final static String CSRF_TOKEN_NAME = "ctoken";

	public String resetCSRFToken() {
		csrfToken = ESAPI.randomizer().getRandomString(24,EncoderConstants.CHAR_ALPHANUMERICS);
		return csrfToken;
	}

	public void verifyCSRFToken(HttpServletRequest request) throws Exception {
		String tokenRequest = request.getParameter(CSRF_TOKEN_NAME) != null ? (String) request.getParameter(CSRF_TOKEN_NAME) : null;
		String sessionToken = request.getSession().getAttribute(CSRF_TOKEN_NAME) != null ? (String) request.getSession().getAttribute(CSRF_TOKEN_NAME) : null;
		if (sessionToken != null && !sessionToken.equals(tokenRequest)) {
			log.error(CommonUtil.getlogDetail(request) +"Token Error -- Invalid HTTP Request found");
			request.getSession().invalidate();
			throw new CIBILException(request,"Token Error -- Invalid HTTP Request found");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		log.info(CommonUtil.getlogDetail(request) + " Request URL " + request.getRequestURI()+ ";Request Token " + request.getParameter(CSRF_TOKEN_NAME) + ";Session Token " + request.getSession().getAttribute(CSRF_TOKEN_NAME) + ";");
		String sessionToken = request.getSession().getAttribute(CSRF_TOKEN_NAME) != null ? (String) request.getSession().getAttribute(CSRF_TOKEN_NAME) : null;
		if (!request.getRequestURI().endsWith("displayForm.htm") && !request.getRequestURI().endsWith("logout.htm")) {
			if (sessionToken != null) {
				if (!request.getRequestURI().endsWith("downloadRejectedRecords.htm") &&!request.getRequestURI().endsWith("exportSearch.htm")&& !request.getRequestURI().endsWith("roleNamechange.htm") && !request.getRequestURI().endsWith("userNamechange.htm")) {
					verifyCSRFToken(request);
				}
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#postHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object,
	 * org.springframework.web.servlet.ModelAndView)
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		if (!request.getRequestURI().endsWith("downloadRejectedRecords.htm") &&!request.getRequestURI().endsWith("exportSearch.htm") && !request.getRequestURI().endsWith("roleNamechange.htm") && !request.getRequestURI().endsWith("userNamechange.htm")) {
			String ctoken = resetCSRFToken();
			request.getSession().setAttribute(CSRF_TOKEN_NAME, ctoken);
			request.setAttribute(CSRF_TOKEN_NAME, ctoken);
		} else if(request.getRequestURI().endsWith("exportSearch.htm")){
			String sessionToken = request.getSession().getAttribute(CSRF_TOKEN_NAME) != null ? (String) request.getSession().getAttribute(CSRF_TOKEN_NAME) : null;
			request.setAttribute(CSRF_TOKEN_NAME, sessionToken);
		}
	}

}
