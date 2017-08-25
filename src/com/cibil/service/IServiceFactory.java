package com.cibil.service;

import com.cibil.manager.IAuditManager;
import com.cibil.manager.IAuthManager;
import com.cibil.manager.IMenuManager;
import com.cibil.manager.IRoleManager;
import com.cibil.manager.IUserManager;

public interface IServiceFactory {
	
	public IUserManager getUserManager();
	
	public IAuthManager getAuthManager();
	
	public IAuditManager getAuditManager();
	
	public IRoleManager  getRoleManager();
	
	public IMenuManager getMenuManager();
}
