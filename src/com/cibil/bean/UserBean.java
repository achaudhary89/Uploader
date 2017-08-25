package com.cibil.bean;

/**
 * @author arunbal.srinivasan
 *
 */
public class UserBean extends BaseBean {
	private String empName;
	private String userName;
	private String olduserName;
	private String systemRole;
	private String empType;
	private String status;
	private String mobileNumber;
	private String emailId;

	private String userID;

	public String getEmailId() {
		return emailId;
	}

	public String getEmpName() {
		return empName;
	}

	public String getEmpType() {
		return empType;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String getOlduserName() {
		return olduserName;
	}

	public String getStatus() {
		return status;
	}

	public String getSystemRole() {
		return systemRole;
	}

	public String getUserID() {
		return userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setOlduserName(String olduserName) {
		this.olduserName = olduserName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
