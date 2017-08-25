package com.cibil.service;

import com.cibil.manager.AuditManager;
import com.cibil.manager.AuthManager;
import com.cibil.manager.IAuditManager;
import com.cibil.manager.IAuthManager;
import com.cibil.manager.IMenuManager;
import com.cibil.manager.IRoleManager;
import com.cibil.manager.IUserManager;
import com.cibil.manager.MenuManager;
import com.cibil.manager.RoleManager;
import com.cibil.manager.UserManager;

/**
 * @author arunbal.srinivasan
 *
 */
public class ServiceFactory implements IServiceFactory {

	public static ServiceFactory getInstance() {

		return new ServiceFactory();
	}

	@Override
	public IUserManager getUserManager() {

		return UserManager.getInstance();
	}

	@Override
	public IAuthManager getAuthManager() {

		return AuthManager.getInstance();
	}

	@Override
	public IAuditManager getAuditManager() {

		return AuditManager.getInstance();
	}

	@Override
	public IRoleManager getRoleManager() {

		return RoleManager.getInstance();
	}

	@Override
	public IMenuManager getMenuManager() {
		// TODO Auto-generated method stub
		return MenuManager.getInstance();
	}

}
