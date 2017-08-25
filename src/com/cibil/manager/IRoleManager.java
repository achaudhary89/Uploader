package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.BaseBean;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public interface IRoleManager {
	public List getRoleList(HttpServletRequest request) throws CIBILException;

	public boolean insertData(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean updateData(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean deleteData(HttpServletRequest request, BaseBean usereBean)
			throws CIBILException;

	public boolean getRole(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean isNameAvailable(HttpServletRequest request, String roleName)
			throws CIBILException;

	public List getRoleNameList(HttpServletRequest request)
			throws CIBILException;
}
