package com.cibil.bean;

/**
 * @author arunbal.srinivasan
 *
 */
public class MenuItemBean {
	private String serialNo;
	private String screenName;
	private String accessRights;
	private String menuID;
	private boolean isPreSelected;
	
	public String getAccessRights() {
		return accessRights;
	}

	public boolean getIsPreSelected() {
		return isPreSelected;
	}

	public String getMenuID() {
		return menuID;
	}

	public String getScreenName() {
		return screenName;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setAccessRights(String accessRights) {
		this.accessRights = accessRights;
	}

	public void setIsPreSelected(boolean isPreSelected) {
		this.isPreSelected = isPreSelected;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
}
