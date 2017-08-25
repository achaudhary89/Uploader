package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.BaseBean;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public interface IUserManager {
	public List getUserList(HttpServletRequest request) throws CIBILException;

	public boolean insertData(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean updateData(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean deleteData(HttpServletRequest request, BaseBean usereBean);

	public boolean getUser(HttpServletRequest request, BaseBean BaseBean)
			throws CIBILException;

	public boolean isroleAvailable(HttpServletRequest request, int parseInt)
			throws CIBILException;

	public boolean isUserNameAvailable(HttpServletRequest request, String name)
			throws CIBILException;
}
