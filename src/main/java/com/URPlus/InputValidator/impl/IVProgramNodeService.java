package com.URPlus.InputValidator.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class IVProgramNodeService implements SwingProgramNodeService<IVProgramNodeContribution, IVProgramNodeView>{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "IV_Node";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		configuration.setChildrenAllowed(false);
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return "InputValidator Demo";
	}

	@Override
	public IVProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Locale locale = systemAPI.getSystemSettings().getLocalization().getLocaleForProgrammingLanguage();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >=5 ? new V5Style() : new V3Style();
		return new IVProgramNodeView(apiProvider, style, locale);
		
	}
	

	@Override
	public IVProgramNodeContribution createNode(ProgramAPIProvider apiProvider, IVProgramNodeView view, DataModel model,
			CreationContext context) {
		// TODO Auto-generated method stub
		return new IVProgramNodeContribution(apiProvider, view, model);
	}

}
