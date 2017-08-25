package com.cibil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.RoleBean;
import com.cibil.bean.StatusBean;
import com.cibil.service.AppContext;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;

/**
 * @author arunbal.srinivasan
 *
 */
public class RoleDAO {
	private DataSource dataSource;
	private Log log = LogFactory.getLog(RoleDAO.class);

	private RoleDAO() {

	}

	private static RoleDAO instance;
	private SimpleJdbcCall jdbcCall;

	/**
	 * @return
	 */
	public static RoleDAO getInstance() {
		if (instance == null) {
			instance = (RoleDAO) AppContext.getInstance().getBean(
					"roleJDBCTemplate");
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
	 * @param request 
	 * @param roleBean
	 * @return
	 * @throws CIBILException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertData(HttpServletRequest request, RoleBean roleBean) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :insertData() Start ");
		String sqlQuery = CommonConstants.insertRoleDetail;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement cs = conn.prepareCall(sqlQuery);
			cs.setString(1, roleBean.getRoleName());
			cs.setString(2, roleBean.getDescription());
			cs.setString(3, roleBean.getStatus());
			cs.setString(4, roleBean.getLoggedinUserName());
			result = cs.execute();
			cs.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :insertData() End ");
		return result;
	}

	/**
	 * @param request 
	 * @param userBean
	 * @return
	 * @throws CIBILException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateData(HttpServletRequest request, RoleBean roleBean) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :updateData() Start ");
		String sqlQuery = CommonConstants.updateRoleDetail;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, roleBean.getRoleName());
			ps.setString(2, roleBean.getDescription());
			ps.setString(3, roleBean.getStatus());
			ps.setString(4, roleBean.getLoggedinUserName());
			ps.setInt(5, Integer.parseInt(roleBean.getRoleID()));
			ps.executeUpdate();
			ps.close();
			result = true;
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :updateData() End ");
		return result;
	}

	/**
	 * @param userBean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean deleteData(RoleBean userBean) {

		return true;
	}

	/**
	 * @param request 
	 * @param userBean
	 * @return
	 * @throws CIBILException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getRole(HttpServletRequest request, RoleBean roleBean) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getRole() Start ");
		String sqlQuery = CommonConstants.getRoleIDQuery;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, roleBean.getRoleID());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				roleBean.setRoleName(resultSet.getString("NAME"));
				roleBean.setOldRoleName(resultSet.getString("NAME"));
				roleBean.setDescription(resultSet.getString("DESCRIPTION"));
				roleBean.setStatus(resultSet.getString("STATUS"));
			}
			ps.close();
			result = true;
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :getRole() End ");
		return result;
	}

	public boolean isNameAvailable(HttpServletRequest request, String name) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :isNameAvailable() Start ");
		String sqlQuery = CommonConstants.getRoleNameQuery;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
			log.info(CommonUtil.getlogDetail(request) + name + " " + result);
			ps.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :isNameAvailable() End ");
		return result;

	}

	/*public List getRoleList() {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList() Start ");
		String sqlQuery = CommonConstants.getAllRoleQuery;
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
		List<RoleBean> userBeans = jdbcTemplateObject.query(sqlQuery,
				new RoleRowMapper());
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList End ");
		return userBeans;
	}*/
	
	public List getRoleList(HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :isNameAvailable() Start ");
		String sqlQuery = CommonConstants.getAllRoleQuery;
		Connection conn = null;
		List<RoleBean> userBeans =new ArrayList<RoleBean>();
		try {
			conn = dataSource.getConnection();
			CallableStatement cs = conn.prepareCall(sqlQuery);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				RoleBean bean = new RoleBean();
				bean.setRoleName(rs.getString("NAME"));
				bean.setStatus(rs.getString("code_description"));
				bean.setDescription(rs.getString("DESCRIPTION"));
				bean.setRoleID(rs.getString("ID"));
				userBeans.add(bean);
			}
			cs.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :isNameAvailable() End ");
		return userBeans;
	}

	public List getRoleNameList(HttpServletRequest request) throws CIBILException {
		return getRoleList(request);
	}

	public List getRoleStatusList(HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getRoleStatusList() Start ");
		String sqlQuery = CommonConstants.getStatusDescription;
		Connection conn = null;
		List<StatusBean> userBeans =new ArrayList<StatusBean>();
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, UserAccessConstants.roleStatusLookup);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StatusBean bean = new StatusBean();
				bean.setCodeID(rs.getString("code_id"));
				bean.setCodeDescription(rs.getString("code_description"));
				userBeans.add(bean);
			}
			ps.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :getRoleStatusList End ");
		return userBeans;
	}

}
