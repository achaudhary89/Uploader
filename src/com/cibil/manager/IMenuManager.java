package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.RoleWiseAccessBean;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public interface IMenuManager {
	public List getParentMenus(HttpServletRequest request)
			throws CIBILException;

	public boolean setRolewiseAccess(HttpServletRequest request,
			RoleWiseAccessBean roleWiseAccessBean) throws CIBILException;

	public List getRolewiseAccess(HttpServletRequest request,
			RoleWiseAccessBean roleWiseAccessBean) throws CIBILException;

	public String getRecordsPerPage(HttpServletRequest request)
			throws CIBILException;
}
