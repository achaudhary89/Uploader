package com.cibil.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.ModelMap;

import com.cibil.bean.BaseBean;

/**
 * @author arunbal.srinivasan
 *
 */
public abstract class BaseController {

	/**
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public abstract String returnPage(ModelMap model, HttpServletRequest request)
			throws Exception;

	/**
	 * @param request
	 * @param baseBean
	 * @return
	 * @throws Exception
	 */
	public abstract String performAction(HttpServletRequest request,
			BaseBean baseBean) throws Exception;
	
	
	

}
