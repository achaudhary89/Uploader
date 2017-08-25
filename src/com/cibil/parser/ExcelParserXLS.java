package com.cibil.parser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alok Chaudhary
 * @Date   16/10/2014
 */

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;

import com.cibil.controller.ExcelController;
 
public class ExcelParserXLS implements IParser{

	private int numColumns = -1;
	private Log log		   =	LogFactory.getLog(ExcelParserXLS.class);
	
	
	public static ExcelParserXLS getInstance(){
		
		return new ExcelParserXLS();
	}

	public Object convertExcelToList(Object p_data_to_parse, Object p_file_properties) {
		byte[] l_data = null;
		Sheet l_sheet = null;
		List<Map> l_sheet_data = null;
		Integer l_sheet_number = -1;

		try {
			l_data = (byte[]) p_data_to_parse;
			if (p_file_properties instanceof String) {
				l_sheet = getExcelSheet(l_data, (String) p_file_properties);
			} else {
				l_sheet_number = (Integer) p_file_properties;

				l_sheet = getExcelSheet(l_data, l_sheet_number);
			}
			// System.out.println("Getting data for sheet : "+l_sheet.getSheetName());
			l_sheet_data = getSheetData(l_sheet);

			//System.out.println("Sheet data is: " + l_sheet_data);
			log.info("Sheet data is: " + l_sheet_data);

			return l_sheet_data;
		} catch (Exception e) {
			System.out.println("Error while parsing excel file ");
			log.error("Error while parsing excel file "+e.getMessage());
			e.printStackTrace();
			return null;
		}

	}

	private Sheet getExcelSheet(byte[] p_data, Integer p_sheet_number)
			throws Exception {
		ByteArrayInputStream l_in_stream = null;
		Workbook l_workbook = null;
		if (p_data == null) {
			return null;
		}
		l_in_stream = new ByteArrayInputStream(p_data);
		l_workbook = (Workbook) WorkbookFactory.create(l_in_stream);

		if (p_sheet_number == null) {
			p_sheet_number = 0;
		}

		//System.out.println("Getting data from default sheet: " + p_sheet_number);
				
		log.info("Getting data from default sheet: " + p_sheet_number);

		return l_workbook.getSheetAt(p_sheet_number);

	}

	public int getNumberOfSheets(byte[] p_data) throws Exception {
		ByteArrayInputStream l_in_stream = null;
		Workbook l_workbook = null;
		if (p_data == null) {
			return -1;
		}
		l_in_stream = new ByteArrayInputStream(p_data);
		l_workbook = (Workbook) WorkbookFactory.create(l_in_stream);
		return l_workbook.getNumberOfSheets();

	}

	public String getSheetName(byte[] p_data, Integer p_sheet_number)
			throws Exception {
		Sheet l_sheet = getExcelSheet(p_data, p_sheet_number);
		return l_sheet.getSheetName();
	}

	private Sheet getExcelSheet(byte[] p_data, String p_sheet_name)
			throws Exception {
		ByteArrayInputStream l_in_stream = null;
		Workbook l_workbook = null;
		if (p_data == null) {
			return null;
		}
		l_in_stream = new ByteArrayInputStream(p_data);
		l_workbook = (Workbook) WorkbookFactory.create(l_in_stream);

		if (p_sheet_name == null) {
			return null;
		}

		//System.out.println("Getting data from default sheet: " + p_sheet_name);
		log.info("Getting data from default sheet: " + p_sheet_name);

		return l_workbook.getSheet(p_sheet_name);

	}

	private List<Map> getSheetData(Sheet p_sheet) {
		int l_num_rows = -1;
		List<Map> l_sheet_data = null;
		if (p_sheet == null) {
			return null;
		}
		l_num_rows = p_sheet.getLastRowNum();
		l_sheet_data = new ArrayList<Map>();
		for (int i = 0; i <= l_num_rows; i++) {
			Row l_row = p_sheet.getRow(i);
			Map<Integer, Object> l_row_data = getRowData(l_row);

			l_sheet_data.add(l_row_data);

		}

		return l_sheet_data;
	}

	private Map<Integer, Object> getRowData(Row p_row) {
		int l_num_cols = -1;
		Map<Integer, Object> l_data = null;

		if (p_row == null) {
			return null;
		}
		l_data = new HashMap<Integer, Object>();
		l_num_cols = p_row.getLastCellNum();

		//System.out.println("Number of columns: " + l_num_cols);
	//	log.info("Number of columns: " + l_num_cols);

		if (l_num_cols > numColumns) {
			numColumns = l_num_cols;
		}

		for (int i = 0; i < l_num_cols; i++) {
			Cell l_cell = p_row.getCell(i);
			Object l_cell_data = getCellData(l_cell);
			if (l_cell != null) {
				l_data.put(i, l_cell_data);
			}
		}

		return l_data;
	}

	private Object getCellData(Cell p_cell) {
		int l_cell_type = -1;

		if (p_cell == null) {
			return null;
		}

		l_cell_type = p_cell.getCellType();

		switch (l_cell_type) {
		case Cell.CELL_TYPE_BLANK:
			return null;

		case Cell.CELL_TYPE_BOOLEAN:
			return String.valueOf(p_cell.getBooleanCellValue());

		case Cell.CELL_TYPE_NUMERIC:
			if (HSSFDateUtil.isCellDateFormatted(p_cell)) {
				return HSSFDateUtil.getJavaDate(p_cell.getNumericCellValue());
			} else if (checkInteger(p_cell.getNumericCellValue())) {
				return String.valueOf(BigDecimal.valueOf((long) p_cell
						.getNumericCellValue()));
			} else {
				return String.valueOf(BigDecimal.valueOf(p_cell
						.getNumericCellValue()));
			}

		case Cell.CELL_TYPE_STRING:
			return String.valueOf(p_cell.getStringCellValue());

		case Cell.CELL_TYPE_ERROR:
			return String.valueOf(p_cell.getErrorCellValue());

		case Cell.CELL_TYPE_FORMULA:
			p_cell.setCellType(Cell.CELL_TYPE_STRING);
			String l_value = p_cell.getStringCellValue();
			// System.out.println("Cell Value is : "+l_value);
			// p_cell.setCellType(Cell.CELL_TYPE_FORMULA);
			return l_value;

		default:
			return null;

		}
	}

	public int getNumberColumns() {
		return numColumns;
	}

	private boolean checkInteger(double p_double) {
		return ((p_double * 10) % 10 == 0);
	}

}
