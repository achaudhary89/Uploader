package com.cibil.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
import com.cibil.service.AppContext;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ExcelHelper;

public class ExcelDAOCA implements IExcelDAO {
	
	private DataSource dataSource;
	private Log log = LogFactory.getLog(ExcelDAOCA.class);
	private ExcelDAOCA() {
	}

	private static ExcelDAOCA instance;
	private SimpleJdbcCall jdbcCall;
	
	
	public static ExcelDAOCA getInstance(){
		
		if (instance == null) {
			instance = (ExcelDAOCA) AppContext.getInstance().getBean("excelDAOTemplateCA");
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
	 * @param DataStore
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertData(BaseBean baseBean, String procedureName) throws Exception {
		log.info("Method :insertData() Start ");
		CallableStatement callableStatement = null;	
		Connection conn = null;
		boolean result = false;
		try {
			DataStore store		=	 (DataStore)baseBean;
			conn 				=	 dataSource.getConnection();
			
			callableStatement 	=	 conn.prepareCall(procedureName);
			// log.info("inserting name::"+store.getName());
			callableStatement.setString(1, store.getName());
			// log.info("inserting sir name::"+store.getSirName());
			callableStatement.setString(2, store.getSirName());
			// log.info("inserting fathers name::"+store.getFathersName());
			callableStatement.setString(3, store.getFathersName());
			// log.info("inserting dob::"+CommonUtil.convertDate(store.getDob()));
			callableStatement.setDate(4, CommonUtil.convertDate(store.getDob()));
			// log.info("inserting PAN ::"+store.getPan());
			callableStatement.setString(5, store.getPan());
			// log.info("inserting voter id::"+store.getVoterID());
			callableStatement.setString(6, store.getVoterID());
			// log.info("inserting aadhar number::"+store.getAadharNumber());
			callableStatement.setString(7, store.getAadharNumber());
			// log.info("inserting passport number::"+store.getPassportNumber());
			callableStatement.setString(8, store.getPassportNumber());
			// log.info("inserting date of marriage::"+CommonUtil.convertDate(store.getDob()));
			callableStatement.setDate(9, CommonUtil.convertDate(store.getDob()));
			//callableStatement.setDate(8, CommonUtil.convertDate(store.getDateOfMarriage()));
			// log.info("inserting address line1::"+store.getAddressLine1());
			callableStatement.setString(10, store.getAddressLine1());
			// log.info("inserting aaddress line2"+store.getAddressLine2());
			callableStatement.setString(11, store.getAddressLine2());
			// log.info("inserting address line3"+store.getAddressLine3());
			callableStatement.setString(12, store.getAddressLine3());
			// log.info("inserting pin code::"+store.getPinCode());
			callableStatement.setString(13, store.getPinCode());
			// log.info("inserting city::"+store.getCity());
			callableStatement.setString(14, store.getCity());
			// log.info("inserting state::"+store.getState());
			callableStatement.setString(15, store.getState());
			// log.info("inserting company name1::"+store.getCompanyName1());
			callableStatement.setString(16, store.getCompanyName1());
			// log.info("inserting company name2::"+store.getCompanyName2());
			callableStatement.setString(17, store.getCompanyName2());
			// log.info("inserting company name3::"+store.getCompanyName3());
			callableStatement.setString(18, store.getCompanyName3());
			// log.info("inserting company name4::"+store.getCompanyName4());
			callableStatement.setString(19, store.getCompanyName4());
			// log.info("inserting company name5::"+store.getCompanyName5());
			callableStatement.setString(20, store.getCompanyName5());
			// log.info("inserting mobile1::"+store.getMobile1());
			callableStatement.setString(21, store.getMobile1());
			// log.info("inserting mobile2::"+store.getMobile2());
			callableStatement.setString(22, store.getMobile2());
			// log.info("inserting mobile3::"+store.getMobile3());
			callableStatement.setString(23, store.getMobile3());
			// log.info("inserting mobile4::"+store.getMobile4());
			callableStatement.setString(24, store.getMobile4());
			// log.info("inserting mobile5::"+store.getMobile5());
			callableStatement.setString(25, store.getMobile5());
			// log.info("inserting landline1::"+store.getLandLine1());
			callableStatement.setString(26, store.getLandLine1());
			// log.info("inserting landline2::"+store.getLandLine2());
			callableStatement.setString(27, store.getLandLine2());
			// log.info("inserting landline3"+store.getLandLine3());
			callableStatement.setString(28, store.getLandLine3());
			// log.info("inserting landline4::"+store.getLandLine4());
			callableStatement.setString(29, store.getLandLine4());
			// log.info("inserting landline5::"+store.getLandLine5());
			callableStatement.setString(30, store.getLandLine5());
			// log.info("inserting email1::"+store.getEmail1());
			callableStatement.setString(31, store.getEmail1());
			// log.info("inserting email2::"+store.getEmail2());
			callableStatement.setString(32, store.getEmail2());
			// log.info("inserting email3::"+store.getEmail3());
			callableStatement.setString(33, store.getEmail3());
			// log.info("inserting email4::"+store.getEmail4());
			callableStatement.setString(34, store.getEmail4());
			// log.info("inserting email5::"+store.getEmail5());
			callableStatement.setString(35, store.getEmail5());
			// log.info("inserting file name ::"+store.getFileName());
			callableStatement.setString(36, store.getFileName());
			callableStatement.setString(37, store.getLoggedinUserName());
			
			callableStatement.executeUpdate();
			result = true;
		} 
		catch (Exception e) {
			result	=	false;
			log.error(e.getMessage());
			//e.printStackTrace();
			return result;
		}
		finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}
		log.info("Method :insertData() End ");
		return result;
	}
	
	
	public boolean insertRejectedData(BaseBean baseBean) throws Exception {
		log.info("Method :insertRejectedData() Start ");
		CallableStatement callableStatement = null;	
		Connection conn = null;
		boolean result = false;
		try {
			DataStore store		=	 (DataStore)baseBean;
			conn 				=	 dataSource.getConnection();
			callableStatement 	=	 conn.prepareCall(CommonConstants.excelInsertRejectedSP);
			// log.info("inserting name::"+store.getName());
			callableStatement.setString(1, store.getName());
			// log.info("inserting sir name::"+store.getSirName());
			callableStatement.setString(2, store.getSirName());
			// log.info("inserting fathers name::"+store.getFathersName());
			callableStatement.setString(3, store.getFathersName());
			// log.info("inserting dob::"+store.getDob());
			if(store.getDob() instanceof Date){
				callableStatement.setString(4, ExcelHelper.convertDateToString((Date)store.getDob()));
			}else{
				callableStatement.setString(4, (String)store.getDob());
			}
			// log.info("inserting PAN ::"+store.getPan());
			callableStatement.setString(5, store.getPan());
			// log.info("inserting voter id::"+store.getVoterID());
			callableStatement.setString(6, store.getVoterID());
			// log.info("inserting aadhar number::"+store.getAadharNumber());
			callableStatement.setString(7, store.getAadharNumber());
			// log.info("inserting passport number::"+store.getPassportNumber());
			callableStatement.setString(8, store.getPassportNumber());
			// log.info("inserting date of marriage::"+store.getDateOfMarriage());
			if(store.getDateOfMarriage() instanceof Date){
				callableStatement.setString(9, ExcelHelper.convertDateToString((Date)store.getDateOfMarriage()));
			}else{
				callableStatement.setString(9, (String)store.getDateOfMarriage());
			}
			//callableStatement.setDate(8, CommonUtil.convertDate(store.getDateOfMarriage()));
			// log.info("inserting address line1::"+store.getAddressLine1());
			callableStatement.setString(10, store.getAddressLine1());
			// log.info("inserting aaddress line2"+store.getAddressLine2());
			callableStatement.setString(11, store.getAddressLine2());
			// log.info("inserting address line3"+store.getAddressLine3());
			callableStatement.setString(12, store.getAddressLine3());
			// log.info("inserting pin code::"+store.getPinCode());
			callableStatement.setString(13, store.getPinCode());
			// log.info("inserting city::"+store.getCity());
			callableStatement.setString(14, store.getCity());
			// log.info("inserting state::"+store.getState());
			callableStatement.setString(15, store.getState());
			// log.info("inserting company name1::"+store.getCompanyName1());
			callableStatement.setString(16, store.getCompanyName1());
			// log.info("inserting company name2::"+store.getCompanyName2());
			callableStatement.setString(17, store.getCompanyName2());
			// log.info("inserting company name3::"+store.getCompanyName3());
			callableStatement.setString(18, store.getCompanyName3());
			// log.info("inserting company name4::"+store.getCompanyName4());
			callableStatement.setString(19, store.getCompanyName4());
			// log.info("inserting company name5::"+store.getCompanyName5());
			callableStatement.setString(20, store.getCompanyName5());
			// log.info("inserting mobile1::"+store.getMobile1());
			callableStatement.setString(21, store.getMobile1());
			// log.info("inserting mobile2::"+store.getMobile2());
			callableStatement.setString(22, store.getMobile2());
			// log.info("inserting mobile3::"+store.getMobile3());
			callableStatement.setString(23, store.getMobile3());
			// log.info("inserting mobile4::"+store.getMobile4());
			callableStatement.setString(24, store.getMobile4());
			// log.info("inserting mobile5::"+store.getMobile5());
			callableStatement.setString(25, store.getMobile5());
			// log.info("inserting landline1::"+store.getLandLine1());
			callableStatement.setString(26, store.getLandLine1());
			// log.info("inserting landline2::"+store.getLandLine2());
			callableStatement.setString(27, store.getLandLine2());
			// log.info("inserting landline3"+store.getLandLine3());
			callableStatement.setString(28, store.getLandLine3());
			// log.info("inserting landline4::"+store.getLandLine4());
			callableStatement.setString(29, store.getLandLine4());
			// log.info("inserting landline5::"+store.getLandLine5());
			callableStatement.setString(30, store.getLandLine5());
			// log.info("inserting email1::"+store.getEmail1());
			callableStatement.setString(31, store.getEmail1());
			// log.info("inserting email2::"+store.getEmail2());
			callableStatement.setString(32, store.getEmail2());
			// log.info("inserting email3::"+store.getEmail3());
			callableStatement.setString(33, store.getEmail3());
			// log.info("inserting email4::"+store.getEmail4());
			callableStatement.setString(34, store.getEmail4());
			// log.info("inserting email5::"+store.getEmail5());
			callableStatement.setString(35, store.getEmail5());
			// log.info("inserting file name ::"+store.getFileName());
			callableStatement.setString(36, store.getFileName());
			callableStatement.setString(37, store.getLoggedinUserName());
			
			callableStatement.executeUpdate();
			result = true;
		} catch (Exception e) {
			log.error(e.toString());
			throw new Exception(e.toString());
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}
		log.info("Method :insertRejectedData() End ");
		return result;
	}
	

	@Override
	public List<DataStore> getDuplicates() throws Exception {
		log.info("Method :getDuplicates() Start ");
		CallableStatement 	callableStatement1 	= 	null;
		Connection 			conn 				= 	null;
		List<DataStore>     dataStoreBeans		=	null;
		String 				sqlQuery			= 	CommonConstants.getAllDuplicateRecords;
		
		try {
			conn 								=	 dataSource.getConnection();
			callableStatement1 					=	 conn.prepareCall(sqlQuery);
			dataStoreBeans						=	 new ArrayList<DataStore>();
			ResultSet rs						=	callableStatement1.executeQuery();
			
			while(rs.next()){
				DataStore storeBean					=	new DataStore();						
				dataStoreBeans.add(setDataBean(storeBean , rs));
				
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}
		log.info("Method :getDuplicates() End ");
	
		return dataStoreBeans;
		
	}
	
	
	@Override
	public List<DataStore> getRejectedRecords() throws Exception {
		log.info("Method :getRejectedRecords() Start ");
		CallableStatement 	callableStatement1 	= 	null;
		Connection 			conn 				= 	null;
		List<DataStore>     dataStoreBeans		=	null;
		String 				sqlQuery			= 	CommonConstants.getAllRejectedRecords;
		
		try {
			conn 								=	 dataSource.getConnection();
			callableStatement1 					=	 conn.prepareCall(sqlQuery);
			dataStoreBeans						=	 new LinkedList<DataStore>();
			ResultSet rs						=		callableStatement1.executeQuery();
			
			while(rs.next()){
				DataStore storeBean					=	new DataStore();						
				dataStoreBeans.add(setDataBean(storeBean , rs));
				
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}
		log.info("Method :getRejectedRecords() End ");
	
		return dataStoreBeans;
		
	}
	

	private DataStore setDataBean(DataStore storeBean, ResultSet rs) throws SQLException {
							
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
		storeBean.setFlag(rs.getInt("FLAG"));
		
		
		return storeBean;
		
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see com.cibil.dao.IExcelDAO#getSearchResults(com.cibil.bean.BaseBean)
	 */
	@Override
	public List<DataStore> getSearchResults(BaseBean bean) {
		log.info("Method :getSearchResults() Start");
		DataStore	searchParamBean				=	(DataStore)bean;
		CallableStatement 	callableStatement5 	= 	null;
		Connection 			conn 				= 	null;
		List<DataStore>   searchBeans			=	null;
		String 				sqlQuery			= 	CommonConstants.searchTransactions;
		String 				name			=	searchParamBean.getName();
		String				sirName			=	searchParamBean.getSirName();
		String				fathersName		=	searchParamBean.getFathersName();
		String 				mobile1				=	searchParamBean.getMobile1();
		String 				pan					=	searchParamBean.getPan();
		String				voterID				=	searchParamBean.getVoterID();
		String				aadharNumber		=	searchParamBean.getAadharNumber();
		String 				fileName			=	searchParamBean.getFileName();
		
		try {
		conn 									=	 dataSource.getConnection();
		String condition						=	searchParamBean.getCondition() ;
		if(StringUtils.isBlank(condition)){
			condition							=	"1";
		}
		callableStatement5 						=	 conn.prepareCall(sqlQuery);
		
		if(condition.equals("1")){
		callableStatement5.setInt(1, 1);
		}else if(condition.equals("2")){
			callableStatement5.setInt(1, 0);	
		}
		
		if(StringUtils.isNotBlank(name)){
			callableStatement5.setString(2, name.trim());
		}else{
			callableStatement5.setNull(2, Types.NULL);
		}
		if(StringUtils.isNotBlank(sirName)){
			callableStatement5.setString(3, sirName.trim());
		}else{
			callableStatement5.setNull(3, Types.NULL);
		}
		if(StringUtils.isNotBlank(fathersName)){
			callableStatement5.setString(4, fathersName.trim());
		}else{
			callableStatement5.setNull(4, Types.NULL);
		}
		if(StringUtils.isNotBlank(mobile1)){
			callableStatement5.setString(5,mobile1.trim());
		}else{
			callableStatement5.setNull(5, Types.NULL);
		}
		if(StringUtils.isNotBlank(pan)){
			callableStatement5.setString(6,pan.trim());
		}else{
			callableStatement5.setNull(6, Types.NULL);
		}
		if(StringUtils.isNotBlank(voterID)){
			callableStatement5.setString(7,voterID.trim());
		}else{
			callableStatement5.setNull(7, Types.NULL);
		}
		if(StringUtils.isNotBlank(aadharNumber)){
			callableStatement5.setString(8,aadharNumber.trim());
		}else{
			callableStatement5.setNull(8, Types.NULL);
		}
		if(StringUtils.isNotBlank(fileName)){
		callableStatement5.setString(9, fileName.trim());
		}else{
			callableStatement5.setNull(9, Types.NULL);
		}
		if(StringUtils.isNotBlank((searchParamBean.getFromDate()))){
			Date fromDate	=	CommonUtil.formatDateString(searchParamBean.getFromDate());
			callableStatement5.setDate(10, new java.sql.Date(fromDate.getTime()));
		}else{
			callableStatement5.setNull(10, Types.NULL);
		}
		if(StringUtils.isNotBlank((searchParamBean.getToDate()))){
			Date toDate		=	CommonUtil.formatDateString(searchParamBean.getToDate());
			callableStatement5.setDate(11,  new java.sql.Date(toDate.getTime()));
		}else{
			callableStatement5.setNull(11, Types.NULL);
		}
		
			searchBeans							=	 new ArrayList<DataStore>();
			ResultSet rs						=	 callableStatement5.executeQuery();
			
			while(rs.next()){
				DataStore	searchParamBean1			=	new DataStore();
				searchBeans.add(setDataBean(searchParamBean1 , rs));
				
			}
			
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					log.error(e.toString());
				}
			}
		}
		
		log.info("Method :getSearchResults() End ");
		//return searchBeans;
		return searchBeans;
	}

	@Override
	public void updateRejectedRecords(BaseBean store) throws Exception {
		log.info("Mthod :updateRejectedRecords started");
		
		String    spParam						=	"";
		String    spToExecute					=	CommonConstants.updateRejectedRecords;
		
		CallableStatement 	callableStatement2 	= 	null;
		Connection 			conn 				= 	null;
		DataStore storedBean					=	(DataStore)store;
		if(storedBean == null){
			
			return;
		}
		spParam									=	ExcelHelper.prepareSPParam(storedBean);
		
			log.info("paramter for  SP is ::"+spParam);
			try {
				conn 								=	 dataSource.getConnection();
				callableStatement2 					=	 conn.prepareCall(spToExecute);
				callableStatement2.setString(1, spParam);
				callableStatement2.executeUpdate();
			} catch (SQLException e) {
				log.info("Error while executing the Update record SP"+e.getMessage());
				throw new Exception();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						log.error(e.toString());
					}
				}
			}
			
			log.info("Mthod :updateRejectedRecords successfully executed");
		}

	@Override
	public Map insertIntoTransactionTable() throws Exception {
		
		log.info("Mthod :insertIntoTransactionTable() started");
		boolean    result						=	false;
		String    deleteRecordsSP				=	CommonConstants.deleteDuplicateAndRejectedRecords;
		String    insertMainTableSP				=	CommonConstants.insertMainTable;
		int 	  duplicateRecords				=	-1;
		int 	  rejectedRecords				=	-1;
		int       approvedRecords				=	-1;
		int 	  invalidRecords				=	-1;
		Map<String, Object>		  countMap		=	null;
		CallableStatement 	callableStatement3 	= 	null;
		CallableStatement 	callableStatement9 	= 	null;
		Connection 			conn 				= 	null;
		
			try {
				countMap							=	 new HashMap<String,Object>();
				countMap.put("result", result);
				conn 								=	 dataSource.getConnection();
				callableStatement9 					=	 conn.prepareCall(deleteRecordsSP);
				callableStatement9.registerOutParameter(1, java.sql.Types.INTEGER);
				callableStatement9.registerOutParameter(2, java.sql.Types.INTEGER);
				callableStatement9.executeUpdate();
				duplicateRecords 							=	callableStatement9.getInt(1);
				countMap.put("duplicate_count", duplicateRecords);
				log.info("Duplicate records are::"+duplicateRecords);
				rejectedRecords							=	callableStatement9.getInt(2);
				countMap.put("rejected_count", rejectedRecords);
				log.info("rejected records are ::"+rejectedRecords);
				log.info("Delete procedure successfully executed");
				callableStatement3 					=	 conn.prepareCall(insertMainTableSP);
				callableStatement3.registerOutParameter(1, java.sql.Types.INTEGER);
				callableStatement3.registerOutParameter(2, java.sql.Types.INTEGER);
				callableStatement3.executeUpdate();
				approvedRecords							=	callableStatement3.getInt(1);//approved_count
				invalidRecords							=	callableStatement3.getInt(2);
				log.info("approved records are"+approvedRecords);
				countMap.put("approved_count", approvedRecords);
				log.info("invalid records are"+invalidRecords);
				countMap.put("invalid_count", invalidRecords);
				 result						=	true;
				 countMap.put("result", result);
				log.info("The Insertion Sp successfully executed");
			} catch (SQLException e) {
				log.info("Error while executing the Delete record SP"+e.getMessage());
					e.printStackTrace();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						log.error(e.toString());
					}
				}
			}
			
			log.info("Mthod :insertIntoTransactionTable() successfully executed");
			
			return countMap;
	}

	@Override
	public void clearStaging() throws Exception {
		
		log.info("Mthod :clearStaging() started");
		executeProcedure(CommonConstants.truncatestaging);			
		log.info("Mthod :clearStaging() successfully executed");
			
	}
	
	
	@Override
	public void clearSearchStaging() throws Exception {
		
		log.info("Mthod :clearSearchStaging() started");
		executeProcedure(CommonConstants.trucateSearchStaging);
		log.info("Mthod :clearStaging() successfully executed");
		
	}
	
	private void executeProcedure(String procedureToCall) throws Exception {
		String    deleteRecordsSP				=	procedureToCall;
		CallableStatement 	callableStatement4 	= 	null;
		Connection 			conn 				= 	null;
		
			try {
				conn 								=	 dataSource.getConnection();
				callableStatement4 					=	 conn.prepareCall(deleteRecordsSP);
				callableStatement4.executeQuery();
				log.info("Delete procedure successfully executed");
			} catch (SQLException e) {
				log.info("Error while executing the Delete record SP"+e.getMessage());
				throw new Exception();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						log.error(e.toString());
					}
				}
			}
		
	}
	

	@Override
	public void deleteFromTransactions(BaseBean store, HttpServletRequest request) throws Exception {
		log.info("Mthod :deleteFromTransactions started");
		String    spParam						=	"";
		String    spToExecute					=	CommonConstants.deleteSelectedRecords;
		
		CallableStatement 	callableStatement2 	= 	null;
		Connection 			conn 				= 	null;
		DataStore storedBean					=	(DataStore)store;
		
		spParam									=	ExcelHelper.prepareSPParam(storedBean);
		
			log.info("paramter for  SP is ::"+spParam);
			try {
				conn 								=	 dataSource.getConnection();
				callableStatement2 					=	 conn.prepareCall(spToExecute);
				callableStatement2.setString(1, spParam);
				callableStatement2.executeUpdate();
			} catch (SQLException e) {
				log.info("Error while deleting transactions"+e.getMessage());
				e.printStackTrace();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						log.error(e.toString());
					}
				}
			}
			
			log.info("Mthod :deleteFromTransactions successfully executed");
	}

	@Override
	public List<DataStore> selectFromTransactions(BaseBean store) throws Exception {
		log.info("Mthod :selectFromTransactions started");
		String    spParam						=	"";
		List<DataStore>   searchBeans			=	null;
		String    spToExecute					=	CommonConstants.exportSelectedRecords;
		
		CallableStatement 	callableStatement2 	= 	null;
		Connection 			conn 				= 	null;
		DataStore storedBean					=	(DataStore)store;
		
		spParam									=	ExcelHelper.prepareSPParam(storedBean);
		
			log.info("paramter for  SP is ::"+spParam);
			try {
				conn 								=	 dataSource.getConnection();
				callableStatement2 					=	 conn.prepareCall(spToExecute);
				callableStatement2.setString(1, spParam);
				ResultSet rs						=	callableStatement2.executeQuery();
				searchBeans							=	 new LinkedList<DataStore>();
				while(rs.next()){
					DataStore	searchParamBean1			=	new DataStore();
					searchBeans.add(setDataBean(searchParamBean1 , rs));
					
				}
			} catch (SQLException e) {
				log.info("Error while executing the Update record SP"+e.getMessage());
				e.printStackTrace();
			}finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (Exception e) {
						log.error(e.toString());
					}
				}
			}
			
			log.info("Mthod :selectFromTransactions successfully executed");
			return searchBeans;
	}


}
