package com.pramati.healthcare.core.dao.utilities;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;

public class ConfigRuleSet implements RuleSet {

	@Override
	public void addRuleInstances(Digester digester) {
		// digester.addRule("*/", getTablesElement())
	}

	@Override
	public String getNamespaceURI() {
		// TODO Auto-generated method stub
		return null;
	}

}
