package com.pramati.healthcare.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataAccessManagerImplTest {
	private static DataAccessManager manager = null;
	private static String[] columnFamilies = { "myColumnFamily" };
	private static String tableName = "myTable";
	
	@BeforeClass
	public static void init() {
		manager = DataAccessManagerImpl.getInstance();
		if (null == manager) {
			fail("manager not available");
		}
		assertTrue("table not created", manager.create(tableName, Arrays
				.asList(columnFamilies)));
	}

	@Test
	public void testUpdate() {
		assertTrue("update failed", manager.update(tableName,
				"myLittleRow, myColumnFamily, someQualifier, Some Value".trim()));
	}

	@Test
	public void testRead() {
		String tableName = "myTable";
		assertEquals("Some Value", manager.read(tableName,
				"myLittleRow,myColumnFamily:someQualifier".trim()));
	}

	@AfterClass
	public static void clean() throws Exception {
		assertTrue("Table not deleted", manager.delete(tableName));
	}
}
