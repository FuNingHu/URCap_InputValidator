package com.URPlus.InputValidator.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardInputCallback;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardNumberInput;
import com.ur.urcap.api.domain.userinteraction.keyboard.KeyboardTextInput;

public class IVProgramNodeView implements SwingProgramNodeView<IVProgramNodeContribution>{

	private final Style style;
	private final ViewAPIProvider apiProvider;
	private Locale locale;
	private JTextField variableName = new JTextField();
	private JTextField positiveInteger = new JTextField();
	private JTextField ipAddress = new JTextField();
	private JTextField generalString = new JTextField();
	
	public IVProgramNodeView(ViewAPIProvider apiProvider, Style style, Locale locale) {
		// TODO Auto-generated constructor stub
		this.apiProvider = apiProvider;
		this.style = style;
		this.locale = locale;
	}
	@Override
	public void buildUI(JPanel panel, final ContributionProvider<IVProgramNodeContribution> provider) {
		// TODO Auto-generated method stub
		variableName.setHorizontalAlignment(JTextField.RIGHT);
		positiveInteger.setHorizontalAlignment(JTextField.LEFT);
		ipAddress.setHorizontalAlignment(JTextField.RIGHT);
		generalString.setAlignmentX(JTextField.RIGHT);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(createVerticalSpacing());
		panel.add(createLabelInputField("Verify ipAddress: ", ipAddress, new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				KeyboardTextInput keyboardInput = provider.get().getKeyboardForIpAddress();
				keyboardInput.show(ipAddress, provider.get().getCallbackForIpAddress());
			}
		}));
		panel.add(createSpacing(0, 30));
		panel.add(createLabelInputField("Positive number input[1,99]: ", positiveInteger, new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				KeyboardNumberInput<Integer> keyboardInput = provider.get().getKeyboardForIntegerInput(1, 99);
				keyboardInput.show(positiveInteger, provider.get().getCallbackForIntegerInput());
			}
		}));
		panel.add(createSpacing(0, 30));
		panel.add(createLabelInputField("Verify variable name syntax: ", variableName, new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				KeyboardTextInput keyboardInput = provider.get().getKeyboardForVariableName();
				keyboardInput.show(variableName, provider.get().getCallbackForVariableName());
			}
		}));
		panel.add(createSpacing(0, 30));
		panel.add(createLabelInputField("Only verify string length[2,6]: ", generalString, new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				KeyboardTextInput keyboardInput = provider.get().getKeyboardForGeneralString(2, 6);
				keyboardInput.show(generalString, provider.get().getCallbackForGeneralString());
			}
		}));
	}
	
	public void setVariableName(String value) {
		variableName.setText(value);
	}

	public void setPositiveIntegerText(Integer value) {
		positiveInteger.setText(value.toString());
	}
	public void setIpAddressText(String value) {
		ipAddress.setText(value);
	}
	public void setGeneralStringText(String value) {
		generalString.setText(value);
	}
	
	private Box createLabelInputField(String desc, JTextField inputField, MouseAdapter mouseAdapter) {
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		JLabel jLabel = new JLabel(desc);
//		inputField.setFocusable(false);
		inputField.setPreferredSize(style.getInputfieldSize());
		inputField.setMaximumSize(inputField.getPreferredSize());
		inputField.addMouseListener(mouseAdapter);
		horizontalBox.add(jLabel);
		horizontalBox.add(createHorizontalSpacing());
		horizontalBox.add(inputField);
		return horizontalBox;
	}
	
	private Component createHorizontalSpacing() {
		return Box.createRigidArea(new Dimension(style.getHorizontalSpacing(),0));
	}
	private Component createVerticalSpacing() {
		return Box.createRigidArea(new Dimension(0,style.getHorizontalSpacing()));
	}
	private Component createSpacing(int xValue, int yValue) {
		return Box.createRigidArea(new Dimension(xValue,yValue));
	}
}
