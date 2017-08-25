package com.cibil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.StatusBean;
import com.cibil.bean.UserBean;
import com.cibil.service.AppContext;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;

/**
 * @author arunbal.srinivasan
 *
 */
public class UserDAO {
	public DataSource dataSource;
	public Log log = LogFactory.getLog(UserDAO.class);

	public UserDAO() {

	}

	public static UserDAO instance;
	public SimpleJdbcCall jdbcCall;

	/**
	 * @return
	 */
	public static UserDAO getInstance() {
		if (instance == null) {
			instance = (UserDAO) AppContext.getInstance().getBean(
					"userJDBCTemplate");
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
	 * @param name
	 * @return
	 * @throws CIBILException 
	 * @ 
	 */
	public String isEmployeeExists(HttpServletRequest request, String name) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :isEmployeeExists() Start " + name);
		String sqlQuery = CommonConstants.loginQuery;
		Connection conn = null;
		boolean result = false;
		String statusId="4";
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
			    statusId=rs.getString("STATUS_ID");	
				result = true;
				if(statusId.equalsIgnoreCase("1"))
				updateLastLoginTime(request,name);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		}  finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :isEmployeeExists() statusId ---: : " + statusId);
		return statusId;
	}

	public void updateLastLoginTime(HttpServletRequest request, String name) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :updateLastLoginTime() Start " + name);
		String sqlQuery = CommonConstants.updateLastLoginTime;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, name);
			ps.execute();
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
		log.info(CommonUtil.getlogDetail(request) + "Method :updateLastLoginTime() End " + name);
	}

	public void updateBadLoginAttempt(HttpServletRequest request, String name) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :updateBadLoginAttempt() Start " + name);
		String sqlQuery = CommonConstants.updateNoOfBadAttempts;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, name);
			ps.execute();
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
		log.info(CommonUtil.getlogDetail(request) + "Method :updateBadLoginAttempt() End " + name);
	}

	/**
	 * @param request 
	 * @param userBean
	 * @return
	 * @throws CIBILException 
	 * @
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertData(HttpServletRequest request, UserBean userBean) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :insertData() Start ");
		String sqlQuery = CommonConstants.insertUserDetail;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, userBean.getUserName());
			ps.setString(2, userBean.getEmpName());
			ps.setInt(3, Integer.parseInt(userBean.getSystemRole()));
			ps.setString(4, userBean.getStatus());
			ps.setString(5, userBean.getEmailId());
			ps.setString(6, userBean.getEmpType());
			ps.setString(7, userBean.getMobileNumber());
			ps.setString(8, userBean.getLoggedinUserName());
			ps.execute();
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
		log.info(CommonUtil.getlogDetail(request) + "Method :insertData() End ");
		return result;
	}

	/**
	 * @param request 
	 * @param userBean
	 * @return
	 * @throws CIBILException 
	 * @
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean updateData(HttpServletRequest request, UserBean userBean) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :updateData() Start ");
		String sqlQuery = CommonConstants.updateUserDetail;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, Integer.parseInt(userBean.getUserID()));
			ps.setString(2, userBean.getUserName());
			ps.setString(3, userBean.getEmpName());
			ps.setInt(4, Integer.parseInt(userBean.getSystemRole()));
			ps.setString(5, userBean.getStatus());
			ps.setString(6, userBean.getEmailId());
			ps.setString(7, userBean.getEmpType());
			ps.setString(8, userBean.getMobileNumber());
			ps.setString(9, userBean.getLoggedinUserName());
			
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
	 * @param request 
	 * @param userBean
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean deleteData(HttpServletRequest request, UserBean userBean) {
		Map inParameters = new HashMap();
		inParameters.put("in_id", userBean.getUserName());
		jdbcCall = new SimpleJdbcCall(dataSource)
				.withProcedureName("deleteUserData");
		try {
			Map<String, Object> out = jdbcCall.execute(inParameters);
			userBean.setEmpName((String) out.get("out_name"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * @param request 
	 * @return
	 * @throws CIBILException 
	 * @ 
	 */
/*	@SuppressWarnings({ "rawtypes" })
	public List getUserList() {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList() Start ");
		String sqlQuery = CommonConstants.getAllUserQuery;
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
		List<UserBean> userBeans = jdbcTemplateObject.query(sqlQuery,
				new UserRowMapper());
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList End ");
		return userBeans;
	}*/

	
	public List getUserList(HttpServletRequest request) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList() Start ");
		String sqlQuery = CommonConstants.getAllUserQuery;
		List<UserBean> userBeans =new ArrayList<UserBean>();
		Connection conn=null;
		try {
			conn = dataSource.getConnection();
			CallableStatement cs = conn.prepareCall(sqlQuery);
			ResultSet rs = cs.executeQuery();
			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setUserID(rs.getString("id"));
				bean.setUserName(rs.getString("user_name"));
				bean.setEmpName(rs.getString("employee_name"));
				bean.setSystemRole(rs.getString("name"));
				bean.setStatus(rs.getString("code_description"));
				bean.setEmpType(rs.getString("employee_type"));
				bean.setEmailId(rs.getString("email_id"));
				userBeans.add(bean);
			}
			cs.close();
		} catch (Exception e) {
			log.error(e);
			throw new CIBILException(request,e);
		}   finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList End ");
		return userBeans;
	}
	/**
	 * @param request 
	 * @param userBean
	 * @return
	 * @throws CIBILException 
	 * @
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean getUser(HttpServletRequest request, UserBean userBean) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUser() Start ");
		String sqlQuery = CommonConstants.getSpecificUserQuery;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setString(1, userBean.getUserID());
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				userBean.setUserName(resultSet.getString("USER_NAME"));
				userBean.setOlduserName(resultSet.getString("USER_NAME"));
				userBean.setEmpName(resultSet.getString("EMPLOYEE_NAME"));
				userBean.setSystemRole(resultSet.getString("ROLE_ID"));
				userBean.setStatus(resultSet.getString("STATUS_ID"));
				userBean.setEmailId(resultSet.getString("EMAIL_ID"));
				userBean.setEmpType(resultSet.getString("EMPLOYEE_TYPE"));
				userBean.setMobileNumber(resultSet.getString("MOBILE_NO"));
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
		log.info(CommonUtil.getlogDetail(request) + "Method :getUser() End ");
		return result;

	}

	public boolean isroleAvailable(HttpServletRequest request, int roleID) throws CIBILException  {
		log.info(CommonUtil.getlogDetail(request) + "Method :isroleAvailable() Start ");
		String sqlQuery = CommonConstants.getRoleIDQuery;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, roleID);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				result = true;
			}
			log.info(CommonUtil.getlogDetail(request) + roleID + " " + result);
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
		log.info(CommonUtil.getlogDetail(request) + "Method :isroleAvailable() End ");
		return result;
	}

	public boolean isUserNameAvailable(HttpServletRequest request, String name)  {
		log.info(CommonUtil.getlogDetail(request) + "Method :isUserNameAvailable() Start " + name);
		String sqlQuery = CommonConstants.loginQuery;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = true;
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			log.error(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :isUserNameAvailable() End " + name);
		return result;
	}

	/*public List getUserStatusList() {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserStatusList() Start ");
		String sqlQuery = CommonConstants.userStatusDescription;
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
		List<StatusBean> userBeans = jdbcTemplateObject.query(sqlQuery,
				new StatusDescRowMapper());
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserStatusList End ");
		return userBeans;
	}*/
	
	public List getUserStatusList(HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserStatusList() Start ");
		String sqlQuery = CommonConstants.getStatusDescription;
		Connection conn = null;
		List<StatusBean> userBeans =new ArrayList<StatusBean>();
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, UserAccessConstants.userStatusLookup);
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
					log.error(CommonUtil.getlogDetail(request),e);
				}
			}
		}
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserStatusList End ");
		return userBeans;
	}
}
