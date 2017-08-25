package com.cibil.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.cibil.bean.BaseBean;
import com.cibil.bean.DataStore;
 
public interface IExcelDAO {
	
	public boolean insertData(BaseBean store, String typeData) throws Exception;
	
	public boolean insertRejectedData(BaseBean store) throws Exception;
	
	public List<DataStore>  getDuplicates() throws Exception;
	
	public List<DataStore>  getRejectedRecords() throws Exception;
	
	public List<DataStore>  getSearchResults(BaseBean store) throws Exception;
	
	public void updateRejectedRecords(BaseBean store)  throws Exception;
	
	public void deleteFromTransactions(BaseBean store, HttpServletRequest request)  throws Exception;
	
	public List<DataStore> selectFromTransactions(BaseBean store)  throws Exception;
	
	public Map insertIntoTransactionTable() throws Exception;
	
	public void clearStaging() throws Exception;
	
	public void clearSearchStaging() throws Exception;
	

}
