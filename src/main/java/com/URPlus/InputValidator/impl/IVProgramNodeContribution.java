package com.URPlus.InputValidator.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidationFactory;
import com.ur.urcap.api.domain.userinteraction.inputvalidation.InputValidator;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputFactory;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardNumberInput;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IVProgramNodeContribution implements ProgramNodeContribution{
	
	private final ProgramAPIProvider apiProvider;
	private final IVProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	private final InputValidationFactory validationFactory;
	private final KeyboardInputFactory keyboardInputFactory;
	
	private static final String POSITIVE_INTEGER_NUMBER_KEY = "positiveintegernumber";
	private static final String VAR_NAME_KEY = "variablename";
	private static final String IPADDRESS_KEY = "ipaddress";
	private static final String GENERAL_STR_KEY = "generalstring";
	private static final Integer POSITIVE_INTEGER_DEFAULT = 12;
	private static final String VARIABLE_NAME_DEFAULT = "variabledefalutname";
	private static final String IPADDRESS_DEFAULT = "192.168.105.102";
	private static final String GENERAL_STR_DEFAULT = "generalstr";
	
	public IVProgramNodeContribution(ProgramAPIProvider apiProvider, IVProgramNodeView view, DataModel model) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.undoRedoManager = apiProvider.getProgramAPI().getUndoRedoManager();
		this.keyboardInputFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getKeyboardInputFactory();
		this.validationFactory = apiProvider.getUserInterfaceAPI().getUserInteraction().getInputValidationFactory();
	}

	public KeyboardTextInput getKeyboardForVariableName() {
		KeyboardTextInput keyboard = keyboardInputFactory.createStringKeyboardInput();
		keyboard.setErrorValidator(new VariableNameValidator());
		keyboard.setInitialValue(model.get(VAR_NAME_KEY, VARIABLE_NAME_DEFAULT));
		return keyboard;
	}
	public KeyboardInputCallback<String> getCallbackForVariableName(){
		return new KeyboardInputCallback<String>() {

			@Override
			public void onOk(String value) {
				// TODO Auto-generated method stub
				model.set(VAR_NAME_KEY, value);
				view.setVariableName(value);
			}
		};
	}
	public KeyboardTextInput getKeyboardForGeneralString(Integer minLength, Integer maxLength) {
		KeyboardTextInput keyboard = keyboardInputFactory.createStringKeyboardInput();
		keyboard.setErrorValidator(validationFactory.createStringLengthValidator(minLength, maxLength));
		keyboard.setInitialValue(model.get(GENERAL_STR_KEY, GENERAL_STR_DEFAULT));
		return keyboard;
	}
	public KeyboardInputCallback<String> getCallbackForGeneralString(){
		return new KeyboardInputCallback<String>() {

			@Override
			public void onOk(String value) {
				// TODO Auto-generated method stub
				view.setGeneralStringText(value);
				model.set(GENERAL_STR_KEY, value);
			}
		};
	}
	
	public KeyboardNumberInput<Integer> getKeyboardForIntegerInput(Integer minValue, Integer maxValue){
		KeyboardNumberInput<Integer> keyboard = keyboardInputFactory.createPositiveIntegerKeypadInput();
		keyboard.setErrorValidator(validationFactory.createIntegerRangeValidator(minValue, maxValue));
		keyboard.setInitialValue(model.get(POSITIVE_INTEGER_NUMBER_KEY, POSITIVE_INTEGER_DEFAULT));
		
		return keyboard;
	}
	public KeyboardInputCallback<Integer> getCallbackForIntegerInput(){
		return new KeyboardInputCallback<Integer>() {

			@Override
			public void onOk(Integer value) {
				// TODO Auto-generated method stub
				view.setPositiveIntegerText(value);
				model.set(POSITIVE_INTEGER_NUMBER_KEY, value);

			}
		};
	}
	
	public KeyboardTextInput getKeyboardForIpAddress() {
		KeyboardTextInput keyboard = keyboardInputFactory.createIPAddressKeyboardInput();
//		keyboard.setErrorValidator(); //ipaddress has built-in validator
		keyboard.setInitialValue(model.get(IPADDRESS_KEY, IPADDRESS_DEFAULT));
		return keyboard;
	}
	public KeyboardInputCallback<String> getCallbackForIpAddress(){
		return new KeyboardInputCallback<String>() {

			@Override
			public void onOk(String value) {
				// TODO Auto-generated method stub
				view.setIpAddressText(value);
				model.set(IPADDRESS_KEY, value);
			}
		};
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		view.setIpAddressText(model.get(IPADDRESS_KEY, IPADDRESS_DEFAULT));
		view.setPositiveIntegerText(model.get(POSITIVE_INTEGER_NUMBER_KEY, POSITIVE_INTEGER_DEFAULT));
		view.setVariableName(model.get(VAR_NAME_KEY, VARIABLE_NAME_DEFAULT));
		view.setGeneralStringText(model.get(GENERAL_STR_KEY, GENERAL_STR_DEFAULT));
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Validator Demo";
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
