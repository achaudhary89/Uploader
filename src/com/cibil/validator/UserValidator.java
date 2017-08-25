/**
 * 
 */
package com.cibil.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.cibil.bean.UserBean;

/**
 * @author arunbal.srinivasan
 *
 */
@Component
public class UserValidator extends BaseValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return UserBean.class.isAssignableFrom(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		UserBean userBean = (UserBean) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empName",
				"userBean.empName.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"userBean.userName.empty");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "systemRole","userBean.systemRole.empty");
		 */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empType",
				"userBean.empType.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status",
				"userBean.status.empty");
		/*
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "mobileNumber","userBean.mobileNumber.empty");
		 * ValidationUtils.rejectIfEmptyOrWhitespace(errors,
		 * "emailId","userBean.emailId.empty"); if
		 * (!StringUtils.isEmpty(userBean.getSystemRole())) { try {
		 * Integer.parseInt(userBean.getSystemRole()); } catch (Exception e) {
		 * errors.rejectValue("systemRole", "userBean.systemRole.invalid"); } }
		 */

		if (errors.getErrorCount() > 0) {
			userBean.setValFailed(true);
		}

		if (!userBean.isValFailed()) {
			if (!StringUtils.isEmpty(userBean.getMobileNumber())) {
				if (!validateMobNum(userBean.getMobileNumber())) {
					errors.rejectValue("mobileNumber",
							"userBean.mobileNumber.invalid");
					userBean.setPerformSpecialCharVal(true);
				}
			}
			if (!StringUtils.isEmpty(userBean.getEmailId())) {
				if (!checkValidEmail(userBean.getEmailId())) {
					errors.rejectValue("emailId", "userBean.emailId.invalid");
					userBean.setPerformSpecialCharVal(true);
				}
			}
		}

		if (errors.getErrorCount() > 0 && userBean.getFlowName() != null) {
			userBean.setFlowName(null);
		}

		if (!userBean.isValFailed() && !userBean.isPerformSpecialCharVal()) {
			if (isSpecialChar(userBean.getEmpName())) {
				userBean.setSpecialCharValFailed(true);
			} else if (isSpecialChar(userBean.getUserName())) {
				userBean.setSpecialCharValFailed(true);
			} else if (isSpecialChar(userBean.getEmpType())) {
				userBean.setSpecialCharValFailed(true);
			}
		}

		if (userBean.isValFailed() || userBean.isPerformSpecialCharVal()
				|| userBean.isSpecialCharValFailed()) {
			errors.reject(null);
		}
		/*
		 * if ("NONE".equals(userBean.getSystemRole())) {
		 * errors.rejectValue("systemRole", "roleAccessBean.sysRole.invalid"); }
		 */
	}

}
