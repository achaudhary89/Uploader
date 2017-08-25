package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.BaseBean;
import com.cibil.bean.RoleBean;
import com.cibil.dao.RoleDAO;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public class RoleManager implements IRoleManager {

	public static RoleManager getInstance() {

		return new RoleManager();
	}

	@Override
	public List getRoleList(HttpServletRequest request) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		List list = roleDAO.getRoleList(request);
		return list;
	}

	@Override
	public boolean insertData(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		RoleBean roleBean = (RoleBean) baseBean;
		return roleDAO.insertData(request,roleBean);
	}

	@Override
	public boolean updateData(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		RoleBean roleBean = (RoleBean) baseBean;
		return roleDAO.updateData(request,roleBean);
	}

	@Override
	public boolean deleteData(HttpServletRequest request,BaseBean baseBean) {
		RoleBean roleBean = (RoleBean) baseBean;
		return false;
	}

	@Override
	public boolean getRole(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		RoleBean roleBean = (RoleBean) baseBean;
		return roleDAO.getRole(request,roleBean);
	}

	@Override
	public boolean isNameAvailable(HttpServletRequest request,String name) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		return roleDAO.isNameAvailable(request,name);
	}

	@Override
	public List getRoleNameList(HttpServletRequest request) throws CIBILException {
		RoleDAO roleDAO = RoleDAO.getInstance();
		List list = roleDAO.getRoleNameList(request);
		return list;
	}

}
