package com.pramati.healthcare.core.tools;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.log4j.Logger;

/**
 * 
 * @author nitin
 * 
 */
public class TableConfigurationParser {
	private static final String COLOUMNFAMILY_NAME = "coloumnfamilies.coloumnfamily.name";
	/*
	 * SET TRUE, TO ENABLE VALIDATING PARSER.
	 */
	private static final boolean VALIDATE = false;
	/*
	 * TABLE NAME
	 */
	private static final String TABLE_NAME = "tables.table.name";

	/**
	 * Parses the table description file.
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public List<Table> parse(String fileName) throws Exception {
		XMLConfiguration configuration = getConfiguration(fileName);
		List<Table> tables = configuration.getList(TABLE_NAME);
		if (CollectionUtils.isEmpty(tables)) {
			throw new IllegalStateException();
		}
		HierarchicalConfiguration table = null;
		for (int size = tables.size(), index = 0; index < size; index++) {
			table = configuration
					.configurationAt("tables.table(" + index + ")");
			getTableDescriptor(table);
		}
		return tables;
	}

	/**
	 * 
	 * @return
	 * @throws ConfigurationException
	 * @throws org.apache.commons.configuration.ConfigurationException
	 */
	private XMLConfiguration getConfiguration(String fileName)
			throws ConfigurationException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new IllegalArgumentException();
		}
		XMLConfiguration configuration = new XMLConfiguration();
		configuration.setFileName(fileName);
		configuration.setValidating(VALIDATE);
		configuration.load();
		return configuration;
	}

	private Table getTableDescriptor(
			HierarchicalConfiguration tableConfiguration) {
		Table table = new Table();
		table.setName(tableConfiguration.getString("name"));
		List listColoumnFamily = tableConfiguration.getList(COLOUMNFAMILY_NAME);
		Iterator<?> iterator = listColoumnFamily.iterator();
		Set<String> setColoumnFamily = new HashSet<String>();
		while (iterator.hasNext()) {
			setColoumnFamily.add((String) iterator.next());
		}
		table.setColoumnFamilies(setColoumnFamily);
		System.out.println(table);
		return table;
	}

	public static void main(String[] args) throws Exception {
		new TableConfigurationParser().parse("tables.xml");
	}
}
