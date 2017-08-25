package com.cibil.bean;

/**
 * @author rohit.khaire
 *
 */
public class RoleRightsBean extends BaseBean {
	private boolean administrator;
	private boolean dataRepository;
	private boolean search;
	private boolean roleStatus;
	private boolean pagewiseAccess;
	private String 	adminLabel;
	private String 	dataRepositoryLabel;
	private String	searchLabel;
	private String 	rolewiseAccessRightLabel;
	private String 	roleMasterLabel;
	private String 	uploadLabel;
	private String 	userMaintenanceLabel;
	private String  advancedSearch;
	private String  singleSearch;
	
	
	public String getUserMaintenanceLabel() {
		return userMaintenanceLabel;
	}
	public void setUserMaintenanceLabel(String userMaintenanceLabel) {
		this.userMaintenanceLabel = userMaintenanceLabel;
	}
	public String getRolewiseAccessRightLabel() {
		return rolewiseAccessRightLabel;
	}
	public void setRolewiseAccessRightLabel(String rolewiseAccessRightLabel) {
		this.rolewiseAccessRightLabel = rolewiseAccessRightLabel;
	}
	public String getRoleMasterLabel() {
		return roleMasterLabel;
	}
	public void setRoleMasterLabel(String roleMasterLabel) {
		this.roleMasterLabel = roleMasterLabel;
	}
	public String getUploadLabel() {
		return uploadLabel;
	}
	public void setUploadLabel(String uploadLabel) {
		this.uploadLabel = uploadLabel;
	}
	
	public String getAdminLabel() {
			return adminLabel;
	}
	public void setAdminLabel(String adminLabel) {
		this.adminLabel = adminLabel;
	}
	public String getDataRepositoryLabel() {
		return dataRepositoryLabel;
	}
	public void setDataRepositoryLabel(String dataRepositoryLabel) {
		this.dataRepositoryLabel = dataRepositoryLabel;
	}
	public String getSearchLabel() {
		return searchLabel;
	}
	public void setSearchLabel(String searchLabel) {
		this.searchLabel = searchLabel;
	}
	public boolean isPagewiseAccess() {
		return pagewiseAccess;
	}
	public void setPagewiseAccess(boolean pagewiseAccess) {
		this.pagewiseAccess = pagewiseAccess;
	}
	public boolean isRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(boolean roleStatus) {
		this.roleStatus = roleStatus;
	}
	public boolean isAdministrator() {
		return administrator;
	}
	public void setAdministrator(boolean administrator) {
		this.administrator = administrator;
	}
	public boolean isDataRepository() {
		return dataRepository;
	}
	public void setDataRepository(boolean dataRepository) {
		this.dataRepository = dataRepository;
	}
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
	public String getAdvancedSearch() {
		return advancedSearch;
	}
	public void setAdvancedSearch(String advancedSearch) {
		this.advancedSearch = advancedSearch;
	}
	
	public String getSingleSearch() {
		return singleSearch;
	}
	public void setSingleSearch(String singleSearch) {
		this.singleSearch = singleSearch;
	}
	
}
