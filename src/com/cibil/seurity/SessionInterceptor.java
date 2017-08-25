package com.cibil.seurity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;

/**
 * @author arunbal.srinivasan
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
	private Log log = LogFactory.getLog(SessionInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle
	 * (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		log.info(CommonUtil.getlogDetail(request) +" Intercepting: " + request.getRequestURI());
		if (!request.getRequestURI().endsWith("displayForm.htm") && !request.getRequestURI().endsWith("loginSubmit.htm") && !request.getRequestURI().endsWith("logout.htm")) {
			if (request.getSession().getAttribute(CommonConstants.sessionUserName) == null) {
				log.error(CommonUtil.getlogDetail(request)+ " Session Invalid");
				throw new CIBILException(request,"Session Invalid");
			} else if (!SessionTracking.isValidUser(request.getSession().getAttribute(CommonConstants.sessionUserName).toString(), request)){
				log.error(CommonUtil.getlogDetail(request)+ " User logged from other machine");
				throw new CIBILException(request,"User logged from other machine");
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
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		log.info(CommonUtil.getlogDetail(request) + "Intercepting: " + request.getRequestURI());
		if (request.getSession().getAttribute(CommonConstants.sessionUserName) != null) {
			request.getSession().setAttribute(CommonConstants.operationDate,CommonUtil.getOperationDate());	
		}
		if (!request.getRequestURI().endsWith("displayForm.htm") && !request.getRequestURI().endsWith("loginSubmit.htm") && !request.getRequestURI().endsWith("logout.htm")) {
			if (request.getSession().getAttribute(CommonConstants.sessionUserName) == null) {
				log.error(CommonUtil.getlogDetail(request)+ " Session Invalid");
				throw new CIBILException(request,"Session Invalid");
			} else if (!SessionTracking.isValidUser(request.getSession().getAttribute(CommonConstants.sessionUserName).toString(), request)){
				log.error(CommonUtil.getlogDetail(request)+ " User logged from other machine");
				throw new CIBILException(request,"User logged from other machine");
			}
		}
	}
}
