package com.cibil.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author rohit.khaire
 *
 */
public class LogoutBean extends BaseBean {

	private String user;
	private String logoutStatus;

	public String getLogoutStatus() {
		return logoutStatus;
	}

	public void setLogoutStatus(String logoutStatus) {
		this.logoutStatus = logoutStatus;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


}
