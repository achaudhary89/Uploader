package com.cibil.util;

/**
 * @author arunbal.srinivasan
 *
 */
public interface CommonConstants {

	public static String searchDateFormat="yyyy-MM-dd";
 	public static String sessionUserName = "userName";
 	public static String operationDate = "operationDate";
	public static String successLoginMsg = "Login.Success";
	public static String failureLoginMsg = "Login.Fail";
	public static String LoginLDAPAuthFail = "message.LoginLDAPAuthFail";
	public static String LoginDBAuthFail = "message.LoginDBAuthFail";
	public static String InactiveUser = "message.InactiveUser";
	public static String UserAlreadyExists = "user.already.exists";
	public static String UserAdded = "user.added";
	public static String UserNotExisted = "user.notexisted";
	public static String RoleAlreadyExists = "role.already.exists";
	public static String UserUpdated = "user.updated";
	public static String RoleAdded = "role.added";
	public static String RoleUpdated = "role.updated";
	public static String RoleWiseAddORUpdated = "rolewise.add.updated";
	public static String validationMessage		=	"error.excel.format";
	public static String headervalidationMessage	=	"error.excel.invalid.header";
	public static String columnNumbervalidationMessage	=	"error.excel.invalid.columns";
	public static String validationApprove		=	"error.excel.approve";
	public static String fileNotSelected		=	"error.excel.fileSelect";
	public static String fileExceedSize			=	"error.excel.fileSize";
	public static String successApprove			=	"success.excel.recordInsert";
	public static String cancelOperation        =	"cancel.excel.operation";
	public static String deleteSearch			=	"delete.excel.operation";
	public static String recordNotToDelete      =	"recordNotTodel.excel.operation";
	public static String searchExport       	=	"search.excel.export";
	public static String searchResult       	=	"search.condition.result";
	public static String fileEmptyError			=	"error.excel.fileEmpty";
	public static String noValidRecords			=	"error.excel.invalid";
	public static String dataInsertion			=	"error.excel.data";


	//Ajax Queries
	public static String loginQuery = "SELECT * FROM tb_users_details where user_name=?";
	public static String getRoleNameQuery = "SELECT * FROM tb_roles_details where NAME=?";

	// public static String insertRoleDetail =
	// "INSERT INTO tb_roles_details (NAME,DESCRIPTION,STATUS,UPDATED_BY,UPDATED_DT) VALUES (?,?,?,?,?)";
	public static String insertRoleDetail = "{call PRC_INSERT_ROLES_DETAILS(?,?,?,?)}";

	// public static String getAllRoleQuery =
	// "SELECT lp.code_description , rd.* FROM tb_roles_details rd, tb_lookup lp where rd.status=lp.code_id and lp.codetype=101";
	public static String getAllRoleQuery = "{call PRC_GET_ROLES_DETAILS()}";

	// public static String getAllRoleNameQuery =
	// "SELECT ID, NAME FROM tb_roles_details order by NAME";

	// public static String getRoleIDQuery =
	// "SELECT * FROM tb_roles_details where id=?";
	public static String getRoleIDQuery = "{call PRC_GET_ROLES_DETAILS_IDWISE(?)}";

	// public static String updateRoleDetail =
	// "UPDATE tb_roles_details set NAME=?, DESCRIPTION=?, STATUS=?, UPDATED_BY=?, UPDATED_DT=? where id=?";
	public static String updateRoleDetail = "{call PRC_UPDATE_ROLES_DETAILS(?,?,?,?,?)}";

	// public static String insertUserDetail =
	// "INSERT INTO tb_users_details (USER_NAME,EMPLOYEE_NAME,ROLE_ID,STATUS_ID,EMAIL_ID,EMPLOYEE_TYPE,MOBILE_NO,UPDATED_BY,UPDATED_DT) VALUES (?,?,?,?,?,?,?,?,?)";
	public static String insertUserDetail = "{call PRC_INSERT_USERS_DETAILS(?,?,?,?,?,?,?,?)}";

	//public static String getAllUserQuery = "SELECT lp.code_description , rd.name, ud.* FROM tb_users_details ud, tb_roles_details rd, tb_lookup lp where ud.role_id=rd.id and ud.status_id=lp.code_id and lp.codetype=102";
	public static String getAllUserQuery = "{call PRC_GET_USERS_DETAILS()}";
			
	//public static String getSpecificUserQuery = "SELECT * FROM tb_users_details where id=?";
	public static String getSpecificUserQuery ="{call PRC_GET_USERS_DETAILS_IDWISE(?)}";
	
	//public static String updateUserDetail = "UPDATE tb_users_details set USER_NAME=?, EMPLOYEE_NAME=?, ROLE_ID=?, STATUS_ID=?, EMAIL_ID=?,EMPLOYEE_TYPE=?,MOBILE_NO=?,UPDATED_BY=?,UPDATED_DT=? where id=?";
	public static String updateUserDetail = "{call PRC_UPDATE_USERS_DETAILS(?,?,?,?,?,?,?,?,?)}";
	
	// Added by Rohit for RoleRights
	// public static String QueryForRoleWiseAccess = "select tb_users_details.USER_NAME, tb_users_details.status_id,  tb_role_rights.role_id,tb_role_rights.PARENT_FEATURE_ID,tb_parent_features.name as parent_name,tb_role_rights.write_rights,tb_child_features.ID,tb_child_features.NAME as child_name,tb_roles_details.STATUS from tb_roles_details  inner join tb_role_rights on tb_roles_details.id = tb_role_rights.role_id left outer join tb_child_features on tb_role_rights.PARENT_FEATURE_ID = tb_child_features.PARENT_FEATURE_ID left outer join tb_parent_features on tb_role_rights.PARENT_FEATURE_ID = tb_parent_features.id right outer join tb_users_details on tb_roles_details.id = tb_users_details.ROLE_ID where  tb_users_details.USER_NAME = ?";
	public static String QueryForRoleWiseAccess ="{call PRC_GET_ROLEWISE_ALL_DETAILS(?)}";
	//public static String getAllParentMenu = "SELECT * FROM tb_parent_features";
	public static String getAllParentMenu = "{call PRC_GET_PARENT_FEATURES_DETAILS()}";

//	public static String insertRoleWiseAccess = "INSERT INTO tb_role_rights (ROLE_ID,PARENT_FEATURE_ID,WRITE_RIGHTS,UPDATED_BY,UPDATED_DT) VALUES (?,?,?,?,?)";
	public static String deleteRoleWiseAccess = "DELETE from tb_role_rights where role_id=?";
//
	
	public static String insertRoleWiseAccess = "{call PRC_INSERT_ROLE_RIGHTS(?,?,?)}";
	
//	public static String getRoleWiseAccess = "select * from tb_role_rights where role_id=? and WRITE_RIGHTS=1";
	public static String getRoleWiseAccess = "{call PRC_GET_ROLEWISE_ACCESS_DETAILS(?)}";

//	public static String updateNoOfBadAttempts = "update tb_users_details set no_of_bad_logins = no_of_bad_logins+1 where user_name=?";
	public static String updateNoOfBadAttempts = "{call PRC_UPDATE_USER_BADATTEMPT(?)}";
	
//	public static String updateLastLoginTime = "update tb_users_details set lastlogin_dt = ? where user_name=?";
	public static String updateLastLoginTime = "{call PRC_UPDATE_USER_LASTLOGINDT(?)}";

//	public static String roleStatusDescription = "select * from tb_lookup where codetype=101";
//	public static String userStatusDescription = "select * from tb_lookup where codetype=102";
//	public static String recordsPerPage = "select * from tb_lookup where codetype=104";
	
	public static String getStatusDescription="{call PRC_GET_STATUS_DESCRIPTION(?)}";
	
	public static String excelInsertSP 			= "{call PRC_INSERT_TSTAGING(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static String excelInsertSPSearch 	= "{call PRC_INSERT_TSTAGING_SEARCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	
	public static String excelInsertRejectedSP  = "{call PRC_INSERT_REJECTED(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
	public static String getAllDuplicateRecords = "{call PRC_FIND_DUPLICATE()}";
	public static String getAllDuplicateRecordsSearch = "{call PRC_FIND_DUPLICATE_ADVANCED_SEARCH()}";
	
	
	public static String getAllRejectedRecords = "{call PRC_GET_REJECTED_RECORDS()}";
	public static String updateRejectedRecords = "{call PRC_REJECTROWS_TSTAGING(?)}";
	public static String deleteSelectedRecords = "{call PRC_DELETE_TRANSACTIONS(?)}";
	public static String exportSelectedRecords = "{call PRC_GET_TRANSACTIONS(?)}";
	public static String deleteDuplicateAndRejectedRecords = "{call PRC_DELETE_TSTAGING(?,?)}";
	public static String insertMainTable = "{call PRC_TRANSACTION_UPLOAD(?,?)}";
	public static String truncatestaging = "{call PRC_TRUNCATE_TSTAGING()}";
	public static String trucateSearchStaging = "{call PRC_TRUNCATE_SEARCH_TSTAGING()}";
	
	
	public static String searchTransactions			=	"{call PRC_TRANSACTION_SEARCH(?,?,?,?,?,?,?,?,?,?,?)}";
	public static String globalProperties = "/global_mapping.properties";
	public static String localProperties = "/excel_mapping.properties";
	public static String oldProperties = "/old_excel_mapping.properties";
	public static String newProperties = "/new_excel_mapping.properties";
	public static int	oldFileColumns	=	14;
	public static int	newFileCOlumns	=	19;
	

	public static long fileSize = 15*1024*1024;

}
