package com.pramati.healthcare.core.dao;

import java.io.IOException;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

import com.pramati.healthcare.model.Table;

/**
 * Implements {@link DataAccessManager}
 * 
 * @author nitin
 * 
 */
public class DataAccessManagerImpl implements DataAccessManager {

	private static final String INVALID_TABLE_MESSAGE = "table is null or not fit for processing";

	/**
	 * Factory method.
	 * 
	 * @return
	 */
	public static DataAccessManager getInstance() {
		if (null == instance) {
			instance = new DataAccessManagerImpl();
		}
		return instance;
	}

	private HBaseConfiguration conf;
	private HBaseAdmin admin;
	private static DataAccessManager instance = null;

	private static Logger logger = Logger
			.getLogger(DataAccessManagerImpl.class);

	protected DataAccessManagerImpl() {
		this(new HBaseConfiguration());
	}

	protected DataAccessManagerImpl(HBaseConfiguration hBaseConfiguration) {
		try {
			if (null == hBaseConfiguration) {
				throw new IllegalArgumentException("HBaseConfiguration is null");
			}
			conf = hBaseConfiguration;
			admin = new HBaseAdmin(conf);
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public boolean create(Table table) throws DAOException {
		try {
			// check pre-conditions
			if (!isValid(table)) {
				throw new IllegalArgumentException(INVALID_TABLE_MESSAGE);
			}

			String tableName = table.getName();
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			Set<String> coloumnFamilies = table.getColoumnFamilies();
			// add coloumn families to the table.
			for (String familyName : coloumnFamilies) {
				tableDescriptor.addFamily(new HColumnDescriptor(familyName));
			}

			// create table.
			if (!admin.tableExists(tableName)) {
				admin.createTable(tableDescriptor);
				logger.info(table + " created");
				return true;
			}
			logger
					.warn(table
							+ " already exists. Delete the existing table or give a new name to the table.");
			return false;
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	private boolean isValid(Table table) {
		if (null == table) {
			logger.error("input param can not be null.");
			return false;
		}
		if (StringUtils.isEmpty(table.getName())) {
			logger.error("table name not found.");
			return false;
		}
		if (CollectionUtils.isEmpty(table.getColoumnFamilies())) {
			logger
					.error("table without coloumn families are not valid, atleast one coloumn family is required.");
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Table table) throws DAOException {
		try {
			if (!isValid(table)) {
				throw new IllegalArgumentException(INVALID_TABLE_MESSAGE);
			}
			String tableName = table.getName();
			if (admin.tableExists(tableName)) {
				if (admin.isTableEnabled(tableName)) {
					admin.disableTable(tableName);
				}
				admin.deleteTable(tableName);
				logger.info(table + " deleted");
				return true;
			}
			logger.warn(table + "not deleted: " + table.getName()
					+ " does not exits.");
			return false;
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public String read(String tableName, String searchString)
			throws DAOException {
		/*
		 * TODO: replace search string with search criterai API similar to
		 * hibernate.
		 */
		try {
			if (StringUtils.isEmpty(tableName)
					|| StringUtils.isEmpty(searchString)) {
				throw new IllegalArgumentException();
			}
			if (!admin.tableExists(tableName)) {
				throw new DAOException("Table " + tableName
						+ " does not exits.");
			}
			HTable table = new HTable(conf, tableName);
			StringTokenizer tokenizer = new StringTokenizer(searchString, ",");
			Get g = new Get(Bytes.toBytes(tokenizer.nextToken().trim()));
			Result result = table.get(g);
			return Bytes.toString(result.getValue(Bytes.toBytes(tokenizer
					.nextToken().trim())));
		} catch (Exception e) {
			throw new DAOException(e.getCause().toString());
		}
	}

	/**
	 * TODO
	 * 
	 * @throws IOException
	 */
	public void readAll() throws IOException {
		/*
		 * Scan s = new Scan(); s.addColumn(Bytes.toBytes("myColumnFamily"),
		 * Bytes .toBytes("someQualifier")); HTable table = new HTable(conf,
		 * "myTable"); ResultScanner scanner = table.getScanner(s); try { //
		 * Scanners return Result instances. // Now, for the actual iteration.
		 * One way is to use a while loop // like so: for (Result rr =
		 * scanner.next(); rr != null; rr = scanner.next()) { // print out the
		 * row we found and the columns we were looking // for
		 * System.out.println("Found row: " + rr.toString()); }
		 * 
		 * // The other approach is to use a foreach loop. Scanners are //
		 * iterable! // for (Result rr : scanner) { //
		 * System.out.println("Found row: " + rr); // } } finally { // Make sure
		 * you close your scanners when you are done! // Thats why we have it
		 * inside a try/finally clause scanner.close(); }
		 */

	}

	@Override
	public boolean update(String tableName, String searchString)
			throws DAOException {
		/*
		 * TODO: replace search string with better approach.
		 */
		try {
			if (StringUtils.isEmpty(tableName)
					|| StringUtils.isEmpty(searchString)) {
				throw new IllegalArgumentException();
			}
			if (!admin.tableExists(tableName)) {
				throw new DAOException("Table " + tableName
						+ " does not exists.");
			}

			HTable table = new HTable(conf, tableName);
			StringTokenizer tokenizer = new StringTokenizer(searchString, ",");
			Put p = new Put(Bytes.toBytes(tokenizer.nextToken().trim()));
			p.add(Bytes.toBytes(tokenizer.nextToken().trim()), Bytes
					.toBytes(tokenizer.nextToken().trim()), Bytes
					.toBytes(tokenizer.nextToken().trim()));
			table.put(p);
			logger.info("record updated/inserted");
			return true;
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}
}
