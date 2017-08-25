package com.cibil.manager;

public class AuthManager implements IAuthManager {
	
	public static AuthManager getInstance(){
		
		return new AuthManager();
	}

}
