package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.RoleWiseAccessBean;
import com.cibil.dao.MenuDAO;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public class MenuManager implements IMenuManager {

	public static MenuManager getInstance() {
		return new MenuManager();
	}

	@Override
	public List getParentMenus(HttpServletRequest request) throws CIBILException {
		MenuDAO menuDAO = MenuDAO.getInstance();
		return menuDAO.getParentMenus(request);
	}

	@Override
	public boolean setRolewiseAccess(HttpServletRequest request,RoleWiseAccessBean roleWiseAccessBean)
			throws CIBILException {
		MenuDAO menuDAO = MenuDAO.getInstance();
	//	menuDAO.clearRoleWiseAccess(roleWiseAccessBean);
		return menuDAO.setRolewiseAccess(request,roleWiseAccessBean);
	}

	@Override
	public List getRolewiseAccess(HttpServletRequest request,RoleWiseAccessBean roleWiseAccessBean)
			throws CIBILException {
		MenuDAO menuDAO = MenuDAO.getInstance();
		return menuDAO.getRolewiseAccess(request,roleWiseAccessBean);
	}

	public String getRecordsPerPage(HttpServletRequest request) throws CIBILException {
		MenuDAO menuDAO = MenuDAO.getInstance();
		return menuDAO.getRecordsPerPage(request);
	}

}
