package com.pramati.healthcare.core.tools;

import java.net.MalformedURLException;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;

public class TableConfigurationParserTest extends TestCase {
	@Test
	public void testParse() throws MalformedURLException, Exception {
		TableConfigurationParser parser = new TableConfigurationParser();
		assertTrue("empty list", CollectionUtils.isNotEmpty(parser
				.parse("tables.xml")));
	}
}
