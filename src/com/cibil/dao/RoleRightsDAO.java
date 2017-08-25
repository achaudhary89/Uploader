package com.cibil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.RoleRightsBean;
import com.cibil.service.AppContext;
import com.cibil.util.CommonConstants;

/**
 * This DAO class is used for setting rolewise access to app users
 * @author : rohit.khaire
 *  
 */
public class RoleRightsDAO {
	private DataSource dataSource;
	private Log log = LogFactory.getLog(RoleRightsDAO.class);

	private RoleRightsDAO() {

	}

	private static RoleRightsDAO instance;
	private SimpleJdbcCall jdbcCall;

	/**
	 * @return
	 */
	public static RoleRightsDAO getInstance() {
		if (instance == null) {
			instance = (RoleRightsDAO) AppContext.getInstance().getBean(
					"roleRightsJDBCTemplate");
			return instance;
		}
		return instance;
	}

	/**
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @param name
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public RoleRightsBean getUserRoles(String name) throws Exception {
		String RoleWiseAccessQuery = CommonConstants.QueryForRoleWiseAccess;
		Connection conn = null;
		String strRoleIdStatus = null;
		PreparedStatement psObjForRoleWiseAccess = null;
		ResultSet rsObjRoleWiseAccess = null;
		String strParentFeatureID = null;
		String strgetWritRights = null;
		RoleRightsBean roleRightsBean = new RoleRightsBean();
		try {
			conn = dataSource.getConnection();
			psObjForRoleWiseAccess = conn.prepareStatement(RoleWiseAccessQuery);
			psObjForRoleWiseAccess.setString(1, name);
			rsObjRoleWiseAccess = psObjForRoleWiseAccess.executeQuery();
			if (rsObjRoleWiseAccess != null) {
				while (rsObjRoleWiseAccess.next()) {
					strRoleIdStatus = rsObjRoleWiseAccess.getString("STATUS");
					if (UserAccessConstants.ROLE_STATUS.equalsIgnoreCase(strRoleIdStatus)) {
						strParentFeatureID = rsObjRoleWiseAccess.getString("PARENT_FEATURE_ID");
						strgetWritRights = rsObjRoleWiseAccess.getString("WRITE_RIGHTS");
						
						if (UserAccessConstants.ADMINISTRATOR.equalsIgnoreCase(strParentFeatureID) && UserAccessConstants.WRITE_RIGHTS.equalsIgnoreCase(strgetWritRights)) {
							roleRightsBean.setAdministrator(true);
							roleRightsBean.setPagewiseAccess(true);
							roleRightsBean.setAdminLabel(rsObjRoleWiseAccess.getString("parent_name"));
							if(UserAccessConstants.USER_MAINTENANCE.equalsIgnoreCase(rsObjRoleWiseAccess.getString("ID")))
							roleRightsBean.setUserMaintenanceLabel(rsObjRoleWiseAccess.getString("child_name"));
							if(UserAccessConstants.ROLEWISE_ACCESS_RIGHTS.equalsIgnoreCase(rsObjRoleWiseAccess.getString("ID")))
								roleRightsBean.setRolewiseAccessRightLabel(rsObjRoleWiseAccess.getString("child_name"));
							if(UserAccessConstants.ROLE_MASTER.equalsIgnoreCase(rsObjRoleWiseAccess.getString("ID")))
								roleRightsBean.setRoleMasterLabel(rsObjRoleWiseAccess.getString("child_name"));
						}
						if (UserAccessConstants.DATA_REPOSITORY.equalsIgnoreCase(strParentFeatureID) && UserAccessConstants.WRITE_RIGHTS.equalsIgnoreCase(strgetWritRights)) {
							roleRightsBean.setDataRepository(true);
							roleRightsBean.setPagewiseAccess(true);
							roleRightsBean.setDataRepositoryLabel(rsObjRoleWiseAccess.getString("parent_name"));
							if(rsObjRoleWiseAccess.getString("ID").equalsIgnoreCase(UserAccessConstants.UPLOAD))
							roleRightsBean.setUploadLabel(rsObjRoleWiseAccess.getString("child_name"));
						}
						if (UserAccessConstants.SEARCH.equalsIgnoreCase(strParentFeatureID) && UserAccessConstants.WRITE_RIGHTS.equalsIgnoreCase(strgetWritRights)) {
							roleRightsBean.setSearch(true);
							roleRightsBean.setPagewiseAccess(true);
							roleRightsBean.setSearchLabel(rsObjRoleWiseAccess.getString("parent_name"));
							roleRightsBean.setSingleSearch("Single Search");
							roleRightsBean.setAdvancedSearch("Advanced Search");
						}
					} else {
						roleRightsBean.setRoleStatus(true);
					}
				}
			}
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
					log.error(e.toString());
				}
			}
		}
		return roleRightsBean;
	}
}
