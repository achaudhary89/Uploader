package com.cibil.mapper;
 
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cibil.bean.DataStore;

/**
 * 
 * @author alok.chaudhary
 * @date	11/10/2014
 */
public class ExcelRowMapper implements RowMapper<DataStore> {

	@Override
	public DataStore mapRow(ResultSet rs, int rowNum) throws SQLException {
		DataStore storeBean 	= new DataStore();
		
		storeBean.setId(rs.getInt("ID"));
		storeBean.setName(rs.getString("NAME"));
		storeBean.setSirName(rs.getString("SIR_NAME"));
		storeBean.setFathersName(rs.getString("FATHERS_NAME"));
		storeBean.setDob(rs.getString("DOB"));
		storeBean.setPan(rs.getString("PAN"));
		storeBean.setVoterID(rs.getString("VOTER_ID"));
		storeBean.setAadharNumber(rs.getString("AADHAR_NUMBER"));
		storeBean.setPassportNumber(rs.getString("PASSPORT_NUMBER"));
		storeBean.setDateOfMarriage(rs.getString("DATE_OF_MARRIAGE"));
		storeBean.setAddressLine1(rs.getString("ADDRESS_LINE1"));
		storeBean.setAddressLine2(rs.getString("ADDRESS_LINE2"));
		storeBean.setAddressLine3(rs.getString("ADDRESS_LINE3"));
		storeBean.setPinCode(rs.getString("PIN_CODE"));
		storeBean.setCity(rs.getString("CITY"));
		storeBean.setState(rs.getString("STATE"));
		storeBean.setCompanyName1(rs.getString("COMPANY_NAME1"));
		storeBean.setCompanyName2(rs.getString("COMPANY_NAME2"));
		storeBean.setCompanyName3(rs.getString("COMPANY_NAME3"));
		storeBean.setCompanyName4(rs.getString("COMPANY_NAME4"));
		storeBean.setCompanyName5(rs.getString("COMPANY_NAME5"));
		storeBean.setMobile1(rs.getString("MOBILE1"));
		storeBean.setMobile2(rs.getString("MOBILE2"));
		storeBean.setMobile3(rs.getString("MOBILE3"));
		storeBean.setMobile4(rs.getString("MOBILE4"));
		storeBean.setMobile5(rs.getString("MOBILE5"));
		storeBean.setLandLine1(rs.getString("LANDLINE1"));
		storeBean.setLandLine2(rs.getString("LANDLINE2"));
		storeBean.setLandLine3(rs.getString("LANDLINE3"));
		storeBean.setLandLine4(rs.getString("LANDLINE4"));
		storeBean.setLandLine5(rs.getString("LANDLINE5"));
		storeBean.setEmail1(rs.getString("EMAIL1"));
		storeBean.setEmail2(rs.getString("EMAIL2"));
		storeBean.setEmail3(rs.getString("EMAIL3"));
		storeBean.setEmail4(rs.getString("EMAIL4"));
		storeBean.setEmail5(rs.getString("EMAIL5"));
		storeBean.setFileName(rs.getString("FILE_NAME"));
		
		return storeBean;
	}

}
