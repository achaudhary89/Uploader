package com.cibil.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cibil.bean.RoleBean;

public class RoleRowMapper implements RowMapper<RoleBean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet,
	 * int)
	 */
	@Override
	public RoleBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		RoleBean bean = new RoleBean();
		bean.setRoleName(rs.getString("NAME"));
		bean.setStatus(rs.getString("code_description"));
		bean.setDescription(rs.getString("DESCRIPTION"));
		bean.setRoleID(rs.getString("ID"));
		return bean;
	}

}
