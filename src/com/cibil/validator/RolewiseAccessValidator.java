package com.cibil.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.cibil.bean.RoleWiseAccessBean;

@Component
public class RolewiseAccessValidator extends BaseValidator {

	@Override
	public boolean supports(Class<?> c) {
		// TODO Auto-generated method stub
		return RoleWiseAccessBean.class.isAssignableFrom(c);
	}

	@Override
	public void validate(Object object, Errors errors) {
		RoleWiseAccessBean roleWiseAccessBean = (RoleWiseAccessBean) object;
		if ("NONE".equals(roleWiseAccessBean.getSystemRole())) {
			errors.rejectValue("systemRole", "roleAccessBean.sysRole.invalid");
		}
		
		if (errors.getErrorCount() > 0) {
			roleWiseAccessBean.setValFailed(true);
		}
		
		/*if (roleWiseAccessBean.getSelectedMenuIDCheckBox() == null
				|| roleWiseAccessBean.getSelectedMenuIDCheckBox().length < 0) {
			errors.rejectValue("selectedMenuIDCheckBox", "select.accessRights");
		}*/
	}

}
