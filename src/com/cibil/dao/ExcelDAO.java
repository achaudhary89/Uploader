/*package com.cibil.dao;

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
import com.cibil.manager.AuditManager;
import com.cibil.service.AppContext;
import com.cibil.util.CommonConstants;
import com.cibil.util.CommonUtil;
import com.cibil.util.ExcelHelper;
 
*//**
 * 
 * @author alok.chaudhary
 * @Date 01/10/2014
 *//*

public class ExcelDAO implements IExcelDAO{
	
	private DataSource dataSource;
	private Log log = LogFactory.getLog(ExcelDAO.class);
	private ExcelDAO() {
	}

	private static ExcelDAO instance;
	private SimpleJdbcCall jdbcCall;
	
	
	public static ExcelDAO getInstance(){
		
		if (instance == null) {
			instance = (ExcelDAO) AppContext.getInstance().getBean("excelDAOTemplate");
			return instance;
		}
		return instance;
	}
	
	*//**
	 * @param dataSource
	 *//*
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	*//**
	 * @param DataStore
	 * @return
	 * @throws Exception
	 *//*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean insertData(BaseBean baseBean) throws Exception {
		log.info("Method :insertData() Start ");
		CallableStatement callableStatement = null;	
		Connection conn = null;
		boolean result = false;
		try {
			DataStore store		=	 (DataStore)baseBean;
			conn 				=	 dataSource.getConnection();
			callableStatement 	=	 conn.prepareCall(CommonConstants.excelInsertSP);
			log.info("inserting serial number::"+store.getSrNo());
			callableStatement.setString(1, store.getSrNo());
			log.info("inserting OLM Date::"+store.getOlmDate());
			callableStatement.setDate(2, CommonUtil.convertDate(store.getOlmDate()));
			log.info("inserting memeber me::"+store.getMemberMe());
			callableStatement.setString(3, store.getMemberMe());
			log.info("inserting consumer me ::"+store.getConsumerMe());
			callableStatement.setString(4, store.getConsumerMe());
			log.info("inserting acounr number::"+store.getAccountNumber());
			callableStatement.setString(5, store.getAccountNumber());
			log.info("inserting account to supress::"+store.getAccountToSupress());
			callableStatement.setString(6, store.getAccountToSupress());
			log.info("inserting status::"+store.getStatus());
			callableStatement.setString(7, store.getStatus());
			log.info("inserting comments::"+store.getComments());
			callableStatement.setString(8, store.getComments());
			log.info("inserting ownershiop indicator::"+store.getOwnershipIndicator());
			callableStatement.setString(9, store.getOwnershipIndicator());
			log.info("inserting account type"+store.getAccountType());
			callableStatement.setString(10, store.getAccountType());
			log.info("inserting current balane"+store.getCurrentBalance());
			callableStatement.setString(11, store.getCurrentBalance());
			log.info("inserting amount overdue::"+store.getAmountOverdue());
			callableStatement.setString(12, store.getAmountOverdue());
			log.info("inserting date closed::"+store.getDateClosed());
			callableStatement.setDate(13, CommonUtil.convertDate(store.getDateClosed()));
			log.info("inserting date of last payment::"+store.getDateOfLastPayment());
			callableStatement.setDate(14, CommonUtil.convertDate(store.getDateOfLastPayment()));
			log.info("inserting date reported::"+store.getDateReported());
			callableStatement.setDate(15, CommonUtil.convertDate(store.getDateReported()));
			log.info("inserting sanctioned amount::"+store.getSactionedAmount());
			callableStatement.setString(16, store.getSactionedAmount());
			log.info("inserting ndpd for last month::"+store.getNdpdForLatestMonth());
			callableStatement.setString(17, store.getNdpdForLatestMonth());
			log.info("inserting account status::"+store.getAccountStatus());
			callableStatement.setString(18, store.getAccountStatus());
			log.info("inserting others::"+store.getOthers());
			callableStatement.setString(19, store.getOthers());
			log.info("inserting response::"+store.getResponse());
			callableStatement.setString(20, store.getResponse());
			log.info("inserting date processed::"+store.getDateProcessed());
			callableStatement.setDate(21, CommonUtil.convertDate(store.getDateProcessed()));
			log.info("inserting method::"+store.getMethod());
			callableStatement.setString(22, store.getMethod());
			log.info("inserting member code::"+store.getMemberCode());
			callableStatement.setString(23, store.getMemberCode());
			log.info("inserting request  by::"+store.getRequestBy());
			callableStatement.setString(24, store.getRequestBy());
			log.info("inserting date requirement::"+store.getDateRequirement());
			callableStatement.setDate(25, CommonUtil.convertDate(store.getDateRequirement()));
			log.info("inserting time::"+store.getTime());
			callableStatement.setString(26, store.getTime());
			log.info("inserting communication status::"+store.getCommunicationStatus());
			callableStatement.setString(27, store.getCommunicationStatus());
			log.info("inserting request details::"+store.getRequestDetails());
			callableStatement.setString(28, store.getRequestDetails());
			log.info("inserting file name ::"+store.getFileName());
			callableStatement.setString(29, store.getFileName());
			callableStatement.setString(30, store.getLoggedinUserName());
			
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
			log.info("inserting serial number::"+store.getSrNo());
			callableStatement.setString(1, store.getSrNo());
			log.info("inserting OLM Date::"+store.getOlmDate());
			if(store.getOlmDate() instanceof Date){
				callableStatement.setString(2, ExcelHelper.convertDateToString((Date)store.getOlmDate()));
			}else{
				callableStatement.setString(2, (String)store.getOlmDate());
			}
			log.info("inserting memeber me::"+store.getMemberMe());
			callableStatement.setString(3, store.getMemberMe());
			log.info("inserting consumer me ::"+store.getConsumerMe());
			callableStatement.setString(4, store.getConsumerMe());
			log.info("inserting acounr number::"+store.getAccountNumber());
			callableStatement.setString(5, store.getAccountNumber());
			log.info("inserting account to supress::"+store.getAccountToSupress());
			callableStatement.setString(6, store.getAccountToSupress());
			log.info("inserting status::"+store.getStatus());
			callableStatement.setString(7, store.getStatus());
			log.info("inserting comments::"+store.getComments());
			callableStatement.setString(8, store.getComments());
			log.info("inserting ownershiop indicator::"+store.getOwnershipIndicator());
			callableStatement.setString(9, store.getOwnershipIndicator());
			log.info("inserting account type"+store.getAccountType());
			callableStatement.setString(10, store.getAccountType());
			log.info("inserting current balane"+store.getCurrentBalance());
			callableStatement.setString(11, store.getCurrentBalance());
			log.info("inserting amount overdue::"+store.getAmountOverdue());
			callableStatement.setString(12, store.getAmountOverdue());
			log.info("inserting date closed::"+store.getDateClosed());
			callableStatement.setDate(13, CommonUtil.convertDate(store.getDateClosed()));
			log.info("inserting date of last payment::"+store.getDateOfLastPayment());
			callableStatement.setDate(14, CommonUtil.convertDate(store.getDateOfLastPayment()));
			log.info("inserting date reported::"+store.getDateReported());
			callableStatement.setDate(15, CommonUtil.convertDate(store.getDateReported()));
			if(store.getDateClosed() instanceof Date){
				callableStatement.setString(13, ExcelHelper.convertDateToString((Date)store.getDateClosed()));
			}else{
				callableStatement.setString(13, (String)store.getDateClosed());
			}if(store.getDateOfLastPayment() instanceof Date){
				callableStatement.setString(14, ExcelHelper.convertDateToString((Date)store.getDateOfLastPayment()));
			}else{
				callableStatement.setString(14, (String)store.getDateOfLastPayment());
			}if(store.getDateReported() instanceof Date){
				callableStatement.setString(15, ExcelHelper.convertDateToString((Date)store.getDateReported()));
			}else{
				callableStatement.setString(15, (String)store.getDateReported());
			}
			log.info("inserting sanctioned amount::"+store.getSactionedAmount());
			callableStatement.setString(16, store.getSactionedAmount());
			log.info("inserting ndpd for last month::"+store.getNdpdForLatestMonth());
			callableStatement.setString(17, store.getNdpdForLatestMonth());
			log.info("inserting account status::"+store.getAccountStatus());
			callableStatement.setString(18, store.getAccountStatus());
			log.info("inserting others::"+store.getOthers());
			callableStatement.setString(19, store.getOthers());
			log.info("inserting response::"+store.getResponse());
			callableStatement.setString(20, store.getResponse());
			log.info("inserting date processed::"+store.getDateProcessed());
			//callableStatement.setDate(21, CommonUtil.convertDate(store.getDateProcessed()));
			if(store.getDateProcessed() instanceof Date){
				callableStatement.setString(21, ExcelHelper.convertDateToString((Date)store.getDateProcessed()));
			}else{
				callableStatement.setString(21, (String)store.getDateProcessed());
			}
			log.info("inserting method::"+store.getMethod());
			callableStatement.setString(22, store.getMethod());
			log.info("inserting member code::"+store.getMemberCode());
			callableStatement.setString(23, store.getMemberCode());
			log.info("inserting request  by::"+store.getRequestBy());
			callableStatement.setString(24, store.getRequestBy());
			log.info("inserting date requirement::"+store.getDateRequirement());
			//callableStatement.setDate(24, CommonUtil.convertDate(store.getDateRequirement()));
			if(store.getDateRequirement() instanceof Date){
				callableStatement.setString(25, ExcelHelper.convertDateToString((Date)store.getDateRequirement()));
			}else{
				callableStatement.setString(25, (String)store.getDateRequirement());
			}
			log.info("inserting time::"+store.getTime());
			callableStatement.setString(26, store.getTime());
			log.info("inserting communication status::"+store.getCommunicationStatus());
			callableStatement.setString(27, store.getCommunicationStatus());
			log.info("inserting request details::"+store.getRequestDetails());
			callableStatement.setString(28, store.getRequestDetails());
			log.info("inserting file name ::"+store.getFileName());
			callableStatement.setString(29, store.getFileName());
			callableStatement.setString(30, store.getLoggedinUserName());
			//callableStatement.setTimestamp(30, CommonUtil.getCurrentDateTime());
			
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
		storeBean.setSrNo(rs.getString("SR_NO"));
		storeBean.setOlmDate(rs.getString("DATE"));
		storeBean.setMemberMe(rs.getString("MEMBER_ME"));
		storeBean.setConsumerMe(rs.getString("CONSUMER_ME"));
		storeBean.setAccountNumber(rs.getString("ACCOUNT_NUMBER"));
		storeBean.setAccountToSupress(rs.getString("ACCOUNT_TO_BE_SUPPRESSED"));
		storeBean.setStatus(rs.getString("STATUS"));
		storeBean.setComments(rs.getString("COMMENTS"));
		storeBean.setOwnershipIndicator(rs.getString("OWNERSHIP_INDICATOR"));
		storeBean.setAccountType(rs.getString("ACCOUNT_TYPE"));
		storeBean.setCurrentBalance(rs.getString("CURRENT_BALANCE"));
		storeBean.setAmountOverdue(rs.getString("AMOUNT_OVERDUE"));
		storeBean.setDateClosed(rs.getString("DATE_CLOSED"));
		storeBean.setDateOfLastPayment(rs.getString("DATE_OF_LAST_PAYMENT"));
		storeBean.setDateReported(rs.getString("DATE_REPORTED"));
		storeBean.setSactionedAmount(rs.getString("SACTIONED_AMOUNT"));
		storeBean.setNdpdForLatestMonth(rs.getString("NDPD_FOR_LATEST_MONTH"));
		storeBean.setAccountStatus(rs.getString("ACCOUNT_STATUS"));
		storeBean.setOthers(rs.getString("OTHERS"));
		storeBean.setResponse(rs.getString("RESPONSE"));
		storeBean.setDateProcessed(rs.getString("DATE_PROCESSED"));
		storeBean.setMethod(rs.getString("METHOD"));
		storeBean.setMemberCode(rs.getString("MEMBER_CODE"));
		storeBean.setRequestBy(rs.getString("REQUEST_BY"));
		storeBean.setDateRequirement(rs.getString("DATE_REQUIREMENT"));
		storeBean.setTime(rs.getString("TIME"));
		storeBean.setCommunicationStatus(rs.getString("COMMUNICATION_STATUS"));
		storeBean.setRequestDetails(rs.getString("REQUEST_DETAILS"));
		storeBean.setFileName(rs.getString("FILE_NAME"));
		storeBean.setFlag(rs.getInt("FLAG"));
		
		
		return storeBean;
		
	}
	
	
	
	 * (non-Javadoc)
	 * @see com.cibil.dao.IExcelDAO#getSearchResults(com.cibil.bean.BaseBean)
	 
	@Override
	public List<DataStore> getSearchResults(BaseBean bean) {
		log.info("Method :getSearchResults() Start");
		DataStore	searchParamBean				=	(DataStore)bean;
		CallableStatement 	callableStatement5 	= 	null;
		Connection 			conn 				= 	null;
		List<DataStore>   searchBeans			=	null;
		String 				sqlQuery			= 	CommonConstants.searchTransactions;
		String 				memberMe			=	searchParamBean.getMemberMe();
		String				consumerMe			=	searchParamBean.getConsumerMe();
		String				accountNumber		=	searchParamBean.getAccountNumber();
		String 				others				=	searchParamBean.getOthers();
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
		
		if(StringUtils.isNotBlank(memberMe)){
			callableStatement5.setString(2, memberMe.trim());
		}else{
			callableStatement5.setNull(2, Types.NULL);
		}
		if(StringUtils.isNotBlank(consumerMe)){
			callableStatement5.setString(3, consumerMe.trim());
		}else{
			callableStatement5.setNull(3, Types.NULL);
		}
		if(StringUtils.isNotBlank(others)){
			callableStatement5.setString(4, others.trim());
		}else{
			callableStatement5.setNull(4, Types.NULL);
		}
		if(StringUtils.isNotBlank(accountNumber)){
			callableStatement5.setString(5,accountNumber.trim());
		}else{
			callableStatement5.setNull(5, Types.NULL);
		}
		if(StringUtils.isNotBlank(fileName)){
		callableStatement5.setString(6, fileName.trim());
		}else{
			callableStatement5.setNull(6, Types.NULL);
		}
		if(StringUtils.isNotBlank((searchParamBean.getFromDate()))){
			Date fromDate	=	CommonUtil.formatDateString(searchParamBean.getFromDate());
			callableStatement5.setDate(7, new java.sql.Date(fromDate.getTime()));
		}else{
			callableStatement5.setNull(7, Types.NULL);
		}
		if(StringUtils.isNotBlank((searchParamBean.getToDate()))){
			Date toDate		=	CommonUtil.formatDateString(searchParamBean.getToDate());
			callableStatement5.setDate(8,  new java.sql.Date(toDate.getTime()));
		}else{
			callableStatement5.setNull(8, Types.NULL);
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
		
		log.info("Mthod :insertIntoTransactionTable() started");
		String    deleteRecordsSP				=	CommonConstants.truncatestaging;
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
			
			log.info("Mthod :insertIntoTransactionTable() successfully executed");
			
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
*/