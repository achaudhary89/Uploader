/**
 * 
 */
package com.cibil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.cibil.accesscontrol.UserAccessConstants;
import com.cibil.bean.MenuItemBean;
import com.cibil.bean.RoleWiseAccessBean;
import com.cibil.service.AppContext;
import com.cibil.util.CIBILException;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;

/**
 * @author arunbal.srinivasan
 *
 */
public class MenuDAO {
	private DataSource dataSource;
	private Log log = LogFactory.getLog(MenuDAO.class);

	private MenuDAO() {

	}

	private static MenuDAO instance;
	private SimpleJdbcCall jdbcCall;

	/**
	 * @return
	 */
	public static MenuDAO getInstance() {
		if (instance == null) {
			instance = (MenuDAO) AppContext.getInstance().getBean(
					"menuJDBCTemplate");
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
/*
	public List getParentMenus() {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList() Start ");
		String sqlQuery = CommonConstants.getAllParentMenu;
		JdbcTemplate jdbcTemplateObject = new JdbcTemplate(dataSource);
		List<MenuItemBean> userBeans = jdbcTemplateObject.query(sqlQuery,
				new MenuRowMapper());
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList End ");
		return userBeans;
	}*/
	

	public List getParentMenus(HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList() Start ");
		String sqlQuery = CommonConstants.getAllParentMenu;
		Connection conn = null;
		List<MenuItemBean> userBeans =new ArrayList<MenuItemBean>();
		try {
			conn = dataSource.getConnection();
			CallableStatement cs = conn.prepareCall(sqlQuery);
			ResultSet rs = cs.executeQuery();
			int count=1;
			while (rs.next()) {
				MenuItemBean menuItemBean = new MenuItemBean();
				menuItemBean.setSerialNo(String.valueOf(count));
				menuItemBean.setScreenName(rs.getString("NAME"));
				menuItemBean.setMenuID(rs.getString("ID"));
				userBeans.add(menuItemBean);
				count++;
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
		log.info(CommonUtil.getlogDetail(request) + "Method :getUserList End ");
		return userBeans;
	}

	public boolean setRolewiseAccess(HttpServletRequest request, RoleWiseAccessBean roleWiseAccessBean)
			throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :setRolewiseAccess() Start ");
		String sqlQuery = CommonConstants.insertRoleWiseAccess;
		Connection conn = null;
		boolean result = false;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			String[] selectedMenus = roleWiseAccessBean.getSelectedMenuIDCheckBox();
			StringBuffer stringBuffer=new StringBuffer();
			if (selectedMenus != null) {
				int length=selectedMenus.length;
				int count=0;
				for (String menu : selectedMenus) {
					count++;
					stringBuffer.append(menu);
					if(count!=length){
						stringBuffer.append(",");
					}
				}
			}
			ps.setInt(1, Integer.parseInt(roleWiseAccessBean.getSystemRole()));
			ps.setString(2, stringBuffer.toString());
			ps.setString(3, roleWiseAccessBean.getLoggedinUserName());
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
		log.info(CommonUtil.getlogDetail(request) + "Method :setRolewiseAccess() End ");
		return result;
	}

	/*public void clearRoleWiseAccess(RoleWiseAccessBean roleWiseAccessBean)
			throws CIBILException {
		String sqlQuery = CommonConstants.deleteRoleWiseAccess;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			ps.setInt(1, Integer.parseInt(roleWiseAccessBean.getSystemRole()));
			ps.execute();
			ps.close();
		} catch (Exception e) {
			log.error(e);
			throw new Exception(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e);
				}
			}
		}
	}*/

	public List getRolewiseAccess(HttpServletRequest request, RoleWiseAccessBean roleWiseAccessBean)
			throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getRolewiseAccess() Start ");
		String sqlQuery = CommonConstants.getRoleWiseAccess;
		Connection conn = null;
		boolean result = false;
		List list = new ArrayList();
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, Integer.parseInt(roleWiseAccessBean.getSystemRole()));
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				list.add(resultSet.getString("PARENT_FEATURE_ID"));
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
		log.info(CommonUtil.getlogDetail(request) + "Method :getRolewiseAccess() End ");
		return list;
	}

	public String getRecordsPerPage(HttpServletRequest request) throws CIBILException {
		log.info(CommonUtil.getlogDetail(request) + "Method :getRecordsPerPage() Start ");
		String sqlQuery = CommonConstants.getStatusDescription;
		Connection conn = null;
		String result = null;
		try {
			conn = dataSource.getConnection();
			CallableStatement ps = conn.prepareCall(sqlQuery);
			ps.setInt(1, UserAccessConstants.pageSizeLookup);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getString("CODE_DESCRIPTION");
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
		log.info(CommonUtil.getlogDetail(request) + "Method :getRecordsPerPage() End ");
		return result;
	}

}
