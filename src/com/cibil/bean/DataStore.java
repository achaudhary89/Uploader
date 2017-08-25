package com.cibil.bean;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

/*
 * @author alok.chaudhary
 */
public class DataStore extends BaseBean {
	
	private int			id;
	private int 		flag;
	private int 		duplicateCount;
	private int			rejectedCount;
	private	int			approvedCount;
	private int			invalidCount;
	private String 		name;
	private String   	sirName;	
	private String 		fathersName;
	private Object 		dob;
	private String 		pan;
	private String 		voterID;
	private String   	aadharNumber;
	private String 		passportNumber;
	private Object 		dateOfMarriage;
	private String 		addressLine1;
	private String 		addressLine2;
	private String 		addressLine3;
	private String      pinCode;
	private String      city;
	private String		state;
	private String 		companyName1;
	private String 		companyName2;
	private String 		companyName3;
	private String 		companyName4;
	private String 		companyName5;
	private String		mobile1;
	private String		mobile2;
	private String		mobile3;
	private String		mobile4;
	private String		mobile5;
	private String 		landLine1;
	private String 		landLine2;
	private String 		landLine3;
	private String 		landLine4;
	private String 		landLine5;
	private String		email1;
	private String		email2;
	private String		email3;
	private String		email4;
	private String		email5;
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
	private ArrayList<DataStore>	rejectedRecords;
	
	private MultipartFile file;
	private String		condition;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSirName() {
		return sirName;
	}
	public void setSirName(String sirName) {
		this.sirName = sirName;
	}
	public String getFathersName() {
		return fathersName;
	}
	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}
	public Object getDob() {
		return dob;
	}
	public void setDob(Object dob) {
		this.dob = dob;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getVoterID() {
		return voterID;
	}
	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}
	public String getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public Object getDateOfMarriage() {
		return dateOfMarriage;
	}
	public void setDateOfMarriage(Object dateOfMarriage) {
		this.dateOfMarriage = dateOfMarriage;
	}
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getAddressLine3() {
		return addressLine3;
	}
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCompanyName1() {
		return companyName1;
	}
	public void setCompanyName1(String companyName1) {
		this.companyName1 = companyName1;
	}
	public String getCompanyName2() {
		return companyName2;
	}
	public void setCompanyName2(String companyName2) {
		this.companyName2 = companyName2;
	}
	public String getCompanyName3() {
		return companyName3;
	}
	public void setCompanyName3(String companyName3) {
		this.companyName3 = companyName3;
	}
	public String getCompanyName4() {
		return companyName4;
	}
	public void setCompanyName4(String companyName4) {
		this.companyName4 = companyName4;
	}
	public String getCompanyName5() {
		return companyName5;
	}
	public void setCompanyName5(String companyName5) {
		this.companyName5 = companyName5;
	}
	public String getMobile1() {
		return mobile1;
	}
	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}
	public String getMobile2() {
		return mobile2;
	}
	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public String getMobile3() {
		return mobile3;
	}
	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}
	public String getMobile4() {
		return mobile4;
	}
	public void setMobile4(String mobile4) {
		this.mobile4 = mobile4;
	}
	public String getMobile5() {
		return mobile5;
	}
	public void setMobile5(String mobile5) {
		this.mobile5 = mobile5;
	}
	public String getLandLine1() {
		return landLine1;
	}
	public void setLandLine1(String landLine1) {
		this.landLine1 = landLine1;
	}
	public String getLandLine2() {
		return landLine2;
	}
	public void setLandLine2(String landLine2) {
		this.landLine2 = landLine2;
	}
	public String getLandLine3() {
		return landLine3;
	}
	public void setLandLine3(String landLine3) {
		this.landLine3 = landLine3;
	}
	public String getLandLine4() {
		return landLine4;
	}
	public void setLandLine4(String landLine4) {
		this.landLine4 = landLine4;
	}
	public String getLandLine5() {
		return landLine5;
	}
	public void setLandLine5(String landLine5) {
		this.landLine5 = landLine5;
	}
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getEmail3() {
		return email3;
	}
	public void setEmail3(String email3) {
		this.email3 = email3;
	}
	public String getEmail4() {
		return email4;
	}
	public void setEmail4(String email4) {
		this.email4 = email4;
	}
	public String getEmail5() {
		return email5;
	}
	public void setEmail5(String email5) {
		this.email5 = email5;
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
	public String[] getSelectedRecordIDCheckBox() {
		return selectedRecordIDCheckBox;
	}
	public void setSelectedRecordIDCheckBox(String[] selectedRecordIDCheckBox) {
		this.selectedRecordIDCheckBox = selectedRecordIDCheckBox;
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
	public ArrayList<DataStore> getRejectedRecords() {
		return rejectedRecords;
	}
	public void setRejectedRecords(ArrayList<DataStore> rejectedRecords) {
		this.rejectedRecords = rejectedRecords;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	

}
