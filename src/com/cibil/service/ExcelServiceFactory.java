package com.cibil.service;

import com.cibil.builder.ExcelBuilder;
import com.cibil.builder.IBuilder;
import com.cibil.dao.ExcelDAOCA;
import com.cibil.dao.IExcelDAO;
import com.cibil.parser.ExcelParser;
import com.cibil.parser.ExcelParserXLS;
import com.cibil.parser.IParser;

/**
 * 
 * @author alok.chaudhary
 * @Date 01/10/2014
 */

public class ExcelServiceFactory implements IExcelServiceFactory {
	
	public static ExcelServiceFactory getInstance(){
		
		return new ExcelServiceFactory();
	}

	@Override
	public IParser getExcelParser() {
		
		return ExcelParser.getInstance();
	}

	@Override
	public IBuilder getExcelBuilder() {
		
		return ExcelBuilder.getInstance();
	}

	@Override
	public IExcelDAO getExcelDAO() {
		
		return ExcelDAOCA.getInstance();
	}
	
	public IParser  getExcelXLSParser(){
		
		return ExcelParserXLS.getInstance();
	}
	
	

}
