/**
 * 
 */
package com.cibil.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author arunbal.srinivasan
 *
 */
public class CIBILException extends Exception {
	public Log log = LogFactory.getLog(CIBILException.class);

	public CIBILException() {
		super();
	}

	public CIBILException(HttpServletRequest request, String str) {
		super(str);
		log.error(CommonUtil.getlogDetail(request) + " " + str);
	}

	public CIBILException(HttpServletRequest request, Exception e) {
		super(e);
		log.error(CommonUtil.getlogDetail(request), e);
	}

}
