package com.cibil.bean;

/**
 * @author arunbal.srinivasan
 *
 */
public class RoleBean extends BaseBean {
	private String roleName;
	private String oldRoleName;
	private String status;
	private String description;
	private String roleID;

	public String getDescription() {
		return description;
	}

	public String getOldRoleName() {
		return oldRoleName;
	}

	public String getRoleID() {
		return roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setOldRoleName(String oldRoleName) {
		this.oldRoleName = oldRoleName;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
