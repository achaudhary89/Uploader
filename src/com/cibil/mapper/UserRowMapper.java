package com.cibil.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cibil.bean.UserBean;

/**
 * @author arunbal.srinivasan
 *
 */
public class UserRowMapper implements RowMapper<UserBean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public UserBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBean bean = new UserBean();
		bean.setUserID(rs.getString("id"));
		bean.setUserName(rs.getString("user_name"));
		bean.setEmpName(rs.getString("employee_name"));
		bean.setSystemRole(rs.getString("name"));
		bean.setStatus(rs.getString("code_description"));
		bean.setEmpType(rs.getString("employee_type"));
		bean.setEmailId(rs.getString("email_id"));
		return bean;
	}

}
