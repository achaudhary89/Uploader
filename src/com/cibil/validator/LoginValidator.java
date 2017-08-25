package com.cibil.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.cibil.bean.LoginBean;

/**
 * @author arunbal.srinivasan
 *
 */
@Component
public class LoginValidator extends BaseValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return LoginBean.class.isAssignableFrom(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		LoginBean loginBean = (LoginBean) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user","loginBean.user.empty");
		if (errors.getErrorCount() ==0) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password","loginBean.user.empty");
		}
		
	}
}
