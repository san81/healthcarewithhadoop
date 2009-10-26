package com.pramati.healthcare.core.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.pramati.healthcare.model.Table;

/**
 * Make sure the HBase instance is up and running and that the application can
 * connect to it, before running the test case. In case of failure you might
 * like to check for configurations.
 * 
 * @author nitin
 * 
 */
public class DataAccessManagerImplTest {
	private static DataAccessManager manager = null;
	private static String tableName = "myTable";
	private static Table table = new Table();

	@BeforeClass
	public static void init() {
		manager = DataAccessManagerImpl.getInstance();
		if (null == manager) {
			fail("manager not available");
		}
		table.setName(tableName);
		Set<String> coloumnFamilies = new HashSet<String>();
		coloumnFamilies.add("myColumnFamily");
		coloumnFamilies.add("fileStoreColumnFamily");
		table.setColoumnFamilies(coloumnFamilies);
		assertTrue("table not created", manager.create(table));
	}

	@Test
	public void testUpdate() {
		assertTrue("update failed", manager
				.update(tableName,
						"myLittleRow, myColumnFamily, someQualifier, Some Value"
								.trim()));
	}

	@Test
	public void testUpdateBinaryData() {
		assertTrue("update failed", manager
				.update(tableName,
						"myLittleRow, myColumnFamily, someQualifier, Some Value"
								.trim()));
	}


	@Test
	public void testRead() {
		String tableName = "myTable";
		assertEquals("Some Value", manager.read(tableName,
				"myLittleRow,myColumnFamily:someQualifier".trim()));
		manager.read(tableName,
				"fileStoreRow,fileStoreColumnFamily:fileStoreQualifier".trim());
	}
	
	@Test
	public void testPutAFile() {
		assertTrue("file placing failed", manager
				.putAFile(tableName, "/home/santosh/logfiles/extracted/access.log",
						"myRowWithAFile, columnFamilyWithFile, fileStoreQualifier, filecontent".trim()));
	}
	
	@Test
	public void testReadAll() {
		String tableName = "myTable";
		manager.readAll(tableName);
	}

	@AfterClass
	public static void clean() throws Exception {
		assertTrue("Table not deleted", manager.delete(table));
	}
}
