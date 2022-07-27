package com.URPlus.InputValidator.impl;


import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidator;

public class VariableNameValidator implements InputValidator<String> {

	@Override
	public boolean isValid(String value) {
		//a legal variable name should start with a a-z&A-z and do not use punctuation symbol. Variable length should be within 20.
		return value.matches("^[a-zA-Z]{1}[_a-zA-Z0-9]{0,19}$");
	}

	@Override
	public String getMessage(String value) {
		return "Illegal variable name";
	}
}
