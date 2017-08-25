package com.cibil.bean;

/**
 * @author alok.chaudhary
 *
 */
public class BaseBean {
	private String flowName;
	private String loggedinUserName;
	private String message;
	private boolean valFailed;
	private boolean specialCharValFailed;
	private boolean performSpecialCharVal;

	public String getFlowName() {
		return flowName;
	}

	public String getLoggedinUserName() {
		return loggedinUserName;
	}

	public String getMessage() {
		return message;
	}

	public boolean isPerformSpecialCharVal() {
		return performSpecialCharVal;
	}

	public boolean isSpecialCharValFailed() {
		return specialCharValFailed;
	}

	public boolean isValFailed() {
		return valFailed;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public void setLoggedinUserName(String loggedinUserName) {
		this.loggedinUserName = loggedinUserName;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPerformSpecialCharVal(boolean performSpecialCharVal) {
		this.performSpecialCharVal = performSpecialCharVal;
	}

	public void setSpecialCharValFailed(boolean specialCharValFailed) {
		this.specialCharValFailed = specialCharValFailed;
	}

	public void setValFailed(boolean valFailed) {
		this.valFailed = valFailed;
	}
}
