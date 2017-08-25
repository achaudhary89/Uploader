package com.cibil.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cibil.bean.MenuItemBean;

public class MenuRowMapper implements RowMapper<MenuItemBean> {

	@Override
	public MenuItemBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuItemBean menuItemBean = new MenuItemBean();
		menuItemBean.setSerialNo(String.valueOf(rowNum+1));
		menuItemBean.setScreenName(rs.getString("NAME"));
		menuItemBean.setMenuID(rs.getString("ID"));
		return menuItemBean;
	}

}
