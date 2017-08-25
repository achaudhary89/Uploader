package com.cibil.bean;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/*
 * @author alok.chaudhary
 * @date   30/09/2014
 */
/**
 * @author alok.chaudhary
 *
 */
public class DataStore1 extends BaseBean {
	
	private int			id;
	private int 		flag;
	private int 		duplicateCount;
	private int			rejectedCount;
	private	int			approvedCount;
	private int			invalidCount;
	private String 		srNo;
	private Object   	olmDate;	
	private String 		memberMe;
	private String 		consumerMe;
	private String 		accountNumber;
	private String 		accountToSupress;
	private String   	status;
	private String 		comments;
	private String 		ownershipIndicator;
	private String 		accountType;
	private String 		currentBalance;
	private String 		amountOverdue;
	private Object		dateClosed;
	private Object		dateOfLastPayment;
	private Object		dateReported;
	private	String		sactionedAmount;
	private String		ndpdForLatestMonth;
	private String		accountStatus;
	private String		others;
	private String		response;
	private Object		dateProcessed;
	private String 		method;
	private String		memberCode;
	private String		requestBy;
	private Object		dateRequirement;
	private String		time;
	private String		communicationStatus;
	private String 		requestDetails;
	private String		fileName;
	private String		LoggedinUserName;
	private String 		validationName;
	private String		orCondition;
	private String		andCondition;
	private String[] 	selectedRecordIDCheckBox;
	private String		fromDate;
	private String		toDate;
	private String      format;
	private boolean		downloadRejected;
	private ArrayList<DataStore1>	rejectedRecords;
	
	private MultipartFile file;
	private String		condition;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getSrNo() {
		return srNo;
	}
	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}
	public Object getOlmDate() {
		return olmDate;
	}
	public void setOlmDate(Object olmDate) {
		this.olmDate = olmDate;
	}
	public String getMemberMe() {
		return memberMe;
	}
	public void setMemberMe(String memberMe) {
		this.memberMe = memberMe;
	}
	public String getConsumerMe() {
		return consumerMe;
	}
	public void setConsumerMe(String consumerMe) {
		this.consumerMe = consumerMe;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setAccountToSupress(String accountToSupress) {
		this.accountToSupress = accountToSupress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getOwnershipIndicator() {
		return ownershipIndicator;
	}
	public void setOwnershipIndicator(String ownershipIndicator) {
		this.ownershipIndicator = ownershipIndicator;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	public String getAmountOverdue() {
		return amountOverdue;
	}
	public void setAmountOverdue(String amountOverdue) {
		this.amountOverdue = amountOverdue;
	}
	public Object getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(Object dateClosed) {
		this.dateClosed = dateClosed;
	}
	public Object getDateOfLastPayment() {
		return dateOfLastPayment;
	}
	public void setDateOfLastPayment(Object dateOfLastPayment) {
		this.dateOfLastPayment = dateOfLastPayment;
	}
	public Object getDateReported() {
		return dateReported;
	}
	public void setDateReported(Object dateReported) {
		this.dateReported = dateReported;
	}
	public String getSactionedAmount() {
		return sactionedAmount;
	}
	public void setSactionedAmount(String sactionedAmount) {
		this.sactionedAmount = sactionedAmount;
	}
	public String getNdpdForLatestMonth() {
		return ndpdForLatestMonth;
	}
	public void setNdpdForLatestMonth(String ndpdForLatestMonth) {
		this.ndpdForLatestMonth = ndpdForLatestMonth;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}

	public Object getDateProcessed() {
		return dateProcessed;
	}

	public void setDateProcessed(Object dateProcessed) {
		this.dateProcessed = dateProcessed;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getRequestBy() {
		return requestBy;
	}
	public void setRequestBy(String requestBy) {
		this.requestBy = requestBy;
	}
	public Object getDateRequirement() {
		return dateRequirement;
	}
	public void setDateRequirement(Object dateRequirement) {
		this.dateRequirement = dateRequirement;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCommunicationStatus() {
		return communicationStatus;
	}
	public void setCommunicationStatus(String communicationStatus) {
		this.communicationStatus = communicationStatus;
	}
	public String getRequestDetails() {
		return requestDetails;
	}
	public void setRequestDetails(String requestDetails) {
		this.requestDetails = requestDetails;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLoggedinUserName() {
		return LoggedinUserName;
	}
	public void setLoggedinUserName(String loggedinUserName) {
		LoggedinUserName = loggedinUserName;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String[] getselectedRecordIDCheckBox() {
		return selectedRecordIDCheckBox;
	}
	public void setselectedRecordIDCheckBox(String[] selectedMenuIDCheckBox) {
		this.selectedRecordIDCheckBox = selectedMenuIDCheckBox;
	}
	public String getValidationName() {
		return validationName;
	}
	public void setValidationName(String validationName) {
		this.validationName = validationName;
	}
	public String getOrCondition() {
		return orCondition;
	}
	public void setOrCondition(String orCondition) {
		this.orCondition = orCondition;
	}
	public String getAndCondition() {
		return andCondition;
	}
	public void setAndCondition(String andCondition) {
		this.andCondition = andCondition;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String[] getSelectedRecordIDCheckBox() {
		return selectedRecordIDCheckBox;
	}
	public void setSelectedRecordIDCheckBox(String[] selectedRecordIDCheckBox) {
		this.selectedRecordIDCheckBox = selectedRecordIDCheckBox;
	}
	public String getAccountToSupress() {
		return accountToSupress;
	}

	public ArrayList<DataStore1> getRejectedRecords() {
		return rejectedRecords;
	}
	public void setRejectedRecords(ArrayList<DataStore1> rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}
	public int getDuplicateCount() {
		return duplicateCount;
	}
	public void setDuplicateCount(int duplicateCount) {
		this.duplicateCount = duplicateCount;
	}
	public int getRejectedCount() {
		return rejectedCount;
	}
	public void setRejectedCount(int rejectedCount) {
		this.rejectedCount = rejectedCount;
	}
	public int getApprovedCount() {
		return approvedCount;
	}
	public void setApprovedCount(int approvedCount) {
		this.approvedCount = approvedCount;
	}
	public int getInvalidCount() {
		return invalidCount;
	}
	public void setInvalidCount(int invalidCount) {
		this.invalidCount = invalidCount;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public boolean isDownloadRejected() {
		return downloadRejected;
	}
	public void setDownloadRejected(boolean downloadRejected) {
		this.downloadRejected = downloadRejected;
	}
	
	
	
	

}
