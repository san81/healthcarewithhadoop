package com.pramati.healthcare.core.tools;

import java.net.MalformedURLException;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class TableConfigurationParserTest extends TestCase {
	@Test
	public void testParse() throws MalformedURLException, Exception {
		List<Table> tables = TableConfigurationParser.parse("conf/tables.xml");
		assertTrue("empty list", CollectionUtils.isNotEmpty(tables));
		assertEquals(2, tables.size());
		assertEquals("person", tables.get(0).getName());
	}
}
