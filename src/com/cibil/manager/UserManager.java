package com.cibil.manager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.BaseBean;
import com.cibil.bean.UserBean;
import com.cibil.dao.UserDAO;
import com.cibil.util.CIBILException;

/**
 * @author arunbal.srinivasan
 *
 */
public class UserManager implements IUserManager {

	public static UserManager getInstance() {

		return new UserManager();
	}

	@Override
	public List getUserList(HttpServletRequest request) throws CIBILException {
		UserDAO userDAO = UserDAO.getInstance();
		List list = userDAO.getUserList(request);
		return list;
	}

	@Override
	public boolean insertData(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		UserDAO userDAO = UserDAO.getInstance();
		UserBean userBean = (UserBean) baseBean;
		return userDAO.insertData(request,userBean);
	}

	@Override
	public boolean updateData(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		UserDAO userDAO = UserDAO.getInstance();
		UserBean userBean = (UserBean) baseBean;
		return userDAO.updateData(request,userBean);
	}

	@Override
	public boolean deleteData(HttpServletRequest request,BaseBean baseBean) {
		UserDAO userDAO = UserDAO.getInstance();
		UserBean userBean = (UserBean) baseBean;
		return userDAO.deleteData(request,userBean);
	}

	@Override
	public boolean getUser(HttpServletRequest request,BaseBean baseBean) throws CIBILException {
		UserDAO userDAO = UserDAO.getInstance();
		UserBean userBean = (UserBean) baseBean;
		return userDAO.getUser(request,userBean);
	}

	@Override
	public boolean isroleAvailable(HttpServletRequest request,int roleID) throws CIBILException {
		UserDAO userDao = UserDAO.getInstance();
		return userDao.isroleAvailable(request,roleID);
	}

	@Override
	public boolean isUserNameAvailable(HttpServletRequest request,String name) throws CIBILException {
		UserDAO userDao = UserDAO.getInstance();
		return userDao.isUserNameAvailable(request,name);
	}

}
