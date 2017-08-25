/**
 * 
 */
package com.cibil.bean;

import java.util.List;

/**
 * @author arunbal.srinivasan
 *
 */
public class RoleWiseAccessBean extends BaseBean {
	private String systemRole;
	private List systemRoleList;
	private List<MenuItemBean> screenDetails;
	private String[] selectedMenuIDCheckBox;

	public List<MenuItemBean> getScreenDetails() {
		return screenDetails;
	}

	public String[] getSelectedMenuIDCheckBox() {
		return selectedMenuIDCheckBox;
	}

	public String getSystemRole() {
		return systemRole;
	}

	public List getSystemRoleList() {
		return systemRoleList;
	}

	public void setScreenDetails(List<MenuItemBean> screenDetails) {
		this.screenDetails = screenDetails;
	}

	public void setSelectedMenuIDCheckBox(String[] selectedMenuIDCheckBox) {
		this.selectedMenuIDCheckBox = selectedMenuIDCheckBox;
	}

	public void setSystemRole(String systemRole) {
		this.systemRole = systemRole;
	}

	public void setSystemRoleList(List systemRoleList) {
		this.systemRoleList = systemRoleList;
	}
}
