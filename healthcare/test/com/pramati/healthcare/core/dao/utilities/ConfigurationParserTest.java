package com.pramati.healthcare.core.dao.utilities;

import java.net.MalformedURLException;
import java.net.URL;

import junit.framework.TestCase;

import org.junit.Test;

public class ConfigurationParserTest extends TestCase {
	@Test
	public void testParse() throws MalformedURLException, Exception {
		ConfigurationParser parser = new ConfigurationParser();
		parser.parse(new URL("file:conf/tables.xml"));
	}
}
