package com.cibil.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.cibil.bean.RoleBean;

/**
 * @author arunbal.srinivasan
 *
 */
@Component
public class RoleValidator extends BaseValidator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return RoleBean.class.isAssignableFrom(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object object, Errors errors) {
		RoleBean roleBean = (RoleBean) object;
		if (!StringUtils.isEmpty(roleBean.getFlowName())
				&& roleBean.getFlowName().contains("Save")) {
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName",
					"roleBean.roleName.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "status",
					"roleBean.status.empty");
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
					"roleBean.description.empty");
		}
		if (errors.getErrorCount() > 0 && roleBean.getFlowName() != null) {
			roleBean.setFlowName(null);
		}
		if (errors.getErrorCount() > 0) {
			roleBean.setValFailed(true);
		}

		if (!roleBean.isValFailed()) {
			if (isSpecialChar(roleBean.getRoleName())) {
				roleBean.setSpecialCharValFailed(true);
			} 
		}
		
		if (roleBean.isValFailed() || roleBean.isSpecialCharValFailed()) {
			errors.reject(null);
		}
	}
}
