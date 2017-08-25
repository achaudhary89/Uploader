package com.cibil.bean;

import java.util.LinkedList;

public class ExcelBean {
	private LinkedList<DataStore> rejectedRecords;
	private String format;
	
	public LinkedList<DataStore> getRejectedRecords() {
		return rejectedRecords;
	}
	public void setRejectedRecords(LinkedList<DataStore> rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
