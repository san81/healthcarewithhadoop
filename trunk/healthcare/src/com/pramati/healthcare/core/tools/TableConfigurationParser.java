package com.pramati.healthcare.core.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;

import com.pramati.healthcare.model.Table;

/**
 * Extracts table configuration information from the configuration file. <br/>
 * TODO: check for configuration file validation.
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
	public static List<Table> parse(String fileName) {
		XMLConfiguration configuration;
		List<Table> tables = new ArrayList<Table>();
		try {
			configuration = getConfiguration(fileName);
			int size = configuration.getList(TABLE_NAME).size();
			if (size <= 0) {
				throw new IllegalStateException();
			}
			HierarchicalConfiguration table = null;
			for (int index = 0; index < size; index++) {
				table = configuration.configurationAt("tables.table(" + index
						+ ")");
				tables.add(getTableDescriptor(table));
			}
		} catch (ConfigurationException e) {
			throw new ToolsException(e.getMessage());
		}
		return tables;
	}

	/**
	 * 
	 * @return
	 * @throws ConfigurationException
	 * @throws org.apache.commons.configuration.ConfigurationException
	 */
	private static XMLConfiguration getConfiguration(String fileName)
			throws ConfigurationException {
		File file = new File(fileName);
		if (!file.exists()) {
			throw new IllegalArgumentException(
					"configuration file does nto exists.");
		}
		// TODO: add and check for validations.
		XMLConfiguration configuration = new XMLConfiguration();
		configuration.setFileName(fileName);
		configuration.setValidating(VALIDATE);
		configuration.load();
		return configuration;
	}

	private static Table getTableDescriptor(
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
		return table;
	}

	public static void main(String[] args) {
		List<Table> tables = parse("conf/tables.xml");
		for (Table table : tables) {
			System.out.println(table);
		}
	}
}
