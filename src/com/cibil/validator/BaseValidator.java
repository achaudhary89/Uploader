/**
 * 
 */
package com.cibil.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * @author arunbal.srinivasan
 *
 */
public abstract class BaseValidator implements Validator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public abstract boolean supports(Class<?> c);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public abstract void validate(Object object, Errors error);

	/**
	 * @param fieldval
	 * @param fieldlabel
	 * @return
	 */
	public boolean checkValidEmail(String fieldval) {
		boolean checkstatus = false;
		Pattern p = Pattern
				.compile("^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");
		Matcher m = p.matcher(fieldval);
		boolean matchFound = m.matches();
		if (matchFound) {
			checkstatus = true;
		}
		return checkstatus;
	}

	/**
	 * @param phoneNum
	 * @return
	 */
	public static boolean validatePhoneNum(String phoneNum) {
		boolean result = false;
		if (phoneNum != null && phoneNum.matches("^[+]{0,1}[0-9 ()-]{3,25}$"))
			result = true;
		return result;
	}

	public static boolean validateMobNum(String phoneNum) {
		boolean result = false;
		try {
			phoneNum = phoneNum.trim();
			Long.parseLong(phoneNum);
			if (phoneNum.contains(".") || phoneNum.contains("-"))
				throw new Exception();
			if (phoneNum.length() < 15) {
				result = true;
			}
		} catch (Exception e) {

		}
		return result;
	}

	/**
	 * @param fieldval
	 * @return
	 */
	public static boolean isSpecialChar(String fieldval) {
		boolean checkstatus = true;
		Pattern regex = Pattern.compile("[^A-Za-z0-9. ]");
		Matcher matcher = regex.matcher(fieldval);
		if (!matcher.find()) {
			checkstatus = false;
		}
		return checkstatus;
	}

	public static void main(String args[]) {
		System.out.println(isSpecialChar("@#@"));
		System.out.println(isSpecialChar("2323"));
		System.out.println(isSpecialChar("SDS.D"));
		System.out.println(isSpecialChar("dsafsd@#"));
		System.out.println(isSpecialChar("DF!@"));
	}

}
