package com.cibil.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cibil.bean.StatusBean;

public class StatusDescRowMapper implements RowMapper<StatusBean> {

	@Override
	public StatusBean mapRow(ResultSet rs, int paramInt) throws SQLException {
		StatusBean bean = new StatusBean();
		bean.setCodeID(rs.getString("code_id"));
		bean.setCodeDescription(rs.getString("code_description"));
		return bean;
	}

}
