package com.cibil.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.cibil.bean.DataStore;
import com.cibil.util.CommonConstants;


@Component
public class SearchValidator extends BaseValidator {

	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return DataStore.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors error) {
		DataStore searchBean = (DataStore) object;
		/*if (StringUtils.isBlank(searchBean.getAccountNumber())
				&& StringUtils.isBlank(searchBean.getConsumerMe())
				&& StringUtils.isBlank(searchBean.getFileName())
				&& StringUtils.isBlank(searchBean.getFromDate())
				&& StringUtils.isBlank(searchBean.getMemberMe())
				&& StringUtils.isBlank(searchBean.getToDate())
				&& StringUtils.isBlank(searchBean.getOthers())) {
			error.rejectValue("message","error.search.mandatory");
		}

		if (error.getErrorCount() == 0) {
			checkAllFieldValidation(searchBean.getAccountNumber(), error);
			checkAllFieldValidation(searchBean.getConsumerMe(), error);
			checkAllFieldValidation(searchBean.getFileName(), error);
			checkAllFieldValidation(searchBean.getMemberMe(), error);
			checkAllFieldValidation(searchBean.getOthers(), error);
			checkDateValidation(searchBean.getToDate(), error);
			checkDateValidation(searchBean.getFromDate(), error);
		}*/
		
		if (error.getErrorCount() == 0) {
			checkCalendarValidation(searchBean.getFromDate(),searchBean.getToDate(), error);
		}
	}

	private void checkCalendarValidation(String fromDate, String toDate,Errors error) {
		/*if(StringUtils.isNotBlank(fromDate) && StringUtils.isBlank(toDate)){		
			error.rejectValue("message","error.search.incorrectDate");
		}else if(StringUtils.isBlank(fromDate) && StringUtils.isNotBlank(toDate)){
			error.rejectValue("message","error.search.incorrectDate");
		}else*/ if(StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)){
			Date fromDt=convertDate(fromDate,error);
			Date toDt=convertDate(toDate,error);
			if(fromDt.after(toDt)){
				error.rejectValue("message","error.search.incorrectCalendar");
			}
		}
	}

	private Date convertDate(String value, Errors error) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.searchDateFormat);
		Date date = null;
		try {
			date = dateFormat.parse(value);
		} catch (ParseException e) {
			error.rejectValue("message", "error.search.incorrectDate");
		}
		return date;
	}

	private void checkDateValidation(Object obj, Errors error) {
		String value=(String)obj;
		if (error.getErrorCount() == 0) {
			if (StringUtils.isNotBlank(value)) {
				try {
					if(value.length()!=10){
						error.rejectValue("message","error.search.incorrectDate");
					}else{
						SimpleDateFormat dateFormat = new SimpleDateFormat(CommonConstants.searchDateFormat);
						dateFormat.parse(value);
					}
				} catch (Exception e) {
					error.rejectValue("message","error.search.incorrectDate");
				}
			}
		}
	}


	public void checkAllFieldValidation(String value, Errors error) {
		if (error.getErrorCount() == 0) {
			if (StringUtils.isNotBlank(value)) {
				if (value.length() < 4) {
					error.rejectValue("message","error.search.minChar");
				}else if (value.length() > 50) {
					error.rejectValue("message","error.search.maxChar");
				} else if (value.contains("%")) {
					error.rejectValue("message","error.search.incorrectPattern");
				}
			}
		}
	}

}
