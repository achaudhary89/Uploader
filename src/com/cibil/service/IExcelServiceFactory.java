package com.cibil.service;

import com.cibil.builder.IBuilder;
import com.cibil.dao.IExcelDAO;
import com.cibil.parser.IParser;

public interface IExcelServiceFactory {
	
	public IParser getExcelParser();
	
	public IBuilder getExcelBuilder();
	
	public IExcelDAO getExcelDAO();
	
	public IParser getExcelXLSParser();

}
