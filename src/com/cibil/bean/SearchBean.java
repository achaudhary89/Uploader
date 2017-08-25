package com.cibil.bean;

/**
 * @author arunbal.srinivasan
 *
 */
public class SearchBean extends BaseBean {
	private String andCondition;
	private String orCondition;
	private String memberName;
	private String consumerName;
	private String acctNumber;
	private String fromDate;
	private String fileName;
	private String toDate;
	private String others;
	
	
	public String getAcctNumber() {
		return acctNumber;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getMemberName() {
		return memberName;
	}

	public String getOthers() {
		return others;
	}

	public String getToDate() {
		return toDate;
	}

	public void setAcctNumber(String acctNumber) {
		this.acctNumber = acctNumber;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getAndCondition() {
		return andCondition;
	}

	public void setAndCondition(String andCondition) {
		this.andCondition = andCondition;
	}

	public String getOrCondition() {
		return orCondition;
	}

	public void setOrCondition(String orCondition) {
		this.orCondition = orCondition;
	}
	
	

}
