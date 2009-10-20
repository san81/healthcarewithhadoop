package com.pramati.healthcare.core.dao.utilities;

import java.net.URL;

import org.apache.commons.digester.Digester;
import org.apache.commons.digester.RuleSet;
import org.xml.sax.InputSource;

/**
 * <p>
 * Factory class to create a CompositeConfiguration from a .xml file using
 * Digester. By default it can handle the Configurations from commons-
 * configuration. If you need to add your own, then you can pass in your own
 * digester rules to use. It is also namespace aware, by providing a
 * digesterRuleNamespaceURI.
 * </p>
 * 
 * @author nitin
 * 
 */
public class ConfigurationParser {
	/**
	 * <p>
	 * The <code>Digester</code> to be used for parsing.
	 * </p>
	 */
	private Digester digester = null;

	/**
	 * <p>
	 * The <code>RuleSet</code> to be used for configuring our Digester parsing
	 * rules.
	 * </p>
	 */
	private RuleSet ruleSet = null;

	/**
	 * <p>
	 * Should Digester use the context class loader?
	 */
	private boolean useContextClassLoader = true;

	/**
	 * <p>
	 * Return the <code>RuleSet</code> to be used for configuring our
	 * <code>Digester</code> parsing rules, creating one if necessary.
	 * </p>
	 * 
	 * @return The RuleSet for configuring a Digester instance.
	 */
	public RuleSet getRuleSet() {
		if (ruleSet == null) {
			ruleSet = new ConfigRuleSet();
		}
		return (ruleSet);
	}

	/**
	 * <p>
	 * Set the <code>RuleSet</code> to be used for configuring our
	 * <code>Digester</code> parsing rules.
	 * </p>
	 * 
	 * @param ruleSet
	 *            The new RuleSet to use
	 */
	public void setRuleSet(RuleSet ruleSet) {
		this.digester = null;
		this.ruleSet = ruleSet;
	}

	/**
	 * <p>
	 * Return the "use context class loader" flag. If set to <code>true</code>,
	 * Digester will attempt to instantiate new command and chain instances from
	 * the context class loader.
	 * </p>
	 * 
	 * @return <code>true</code> if Digester should use the context class
	 *         loader.
	 */
	public boolean getUseContextClassLoader() {
		return (this.useContextClassLoader);
	}

	/**
	 * <p>
	 * Set the "use context class loader" flag.
	 * </p>
	 * 
	 * @param useContextClassLoader
	 *            The new flag value
	 */
	public void setUseContextClassLoader(boolean useContextClassLoader) {
		this.useContextClassLoader = useContextClassLoader;
	}

	public void parse(URL fileUrl) throws Exception {
		Digester digester = getDigester();
		digester.clear();
		digester.push(Tables.class);
		InputSource source = new InputSource(fileUrl.toExternalForm());
		source.setByteStream(fileUrl.openStream());
		System.out.println(digester.parse(source));
	}

	private Digester getDigester() {
		if (digester == null) {
			digester = new Digester();
			RuleSet ruleSet = getRuleSet();
			digester.setNamespaceAware(ruleSet.getNamespaceURI() != null);
			digester.setUseContextClassLoader(getUseContextClassLoader());
			digester.setValidating(false);
			digester.addRuleSet(ruleSet);
		}
		return (digester);
	}
}
