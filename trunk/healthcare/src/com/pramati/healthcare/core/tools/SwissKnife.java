package com.pramati.healthcare.core.tools;

import java.util.List;

import com.pramati.healthcare.core.dao.DataAccessManager;
import com.pramati.healthcare.core.dao.DataAccessManagerImpl;

public class SwissKnife {
	public static void init() {
		List<Table> tables = TableConfigurationParser.parse("conf/tables.xml");
		DataAccessManager manager = DataAccessManagerImpl.getInstance();
		for (Table table : tables) {
			manager.create(table);
		}
	}

	public static void main(String[] args) {
		init();
	}
}
