package com.pramati.healthcare.core.dao;

import java.io.IOException;
import java.util.List;
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
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

/**
 * Implements {@link DataAccessManager}
 * 
 * @author nitin
 * 
 */
public class DataAccessManagerImpl implements DataAccessManager {

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
	public boolean create(String tableName, List<String> coloumnFamilies)
			throws DAOException {
		try {
			// check pre-conditions
			if (null == tableName && CollectionUtils.isEmpty(coloumnFamilies)) {
				throw new IllegalArgumentException("tableName is null");
			}
			HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
			// add coloumn families to the table.
			for (String familyName : coloumnFamilies) {
				tableDescriptor.addFamily(new HColumnDescriptor(familyName));
			}

			// create table.
			String table = tableDescriptor.getNameAsString();
			if (!admin.tableExists(table)) {
				admin.createTable(tableDescriptor);
				logger.info("Table [" + table + "] created");
				return true;
			}
			logger
					.warn("Table ["
							+ table
							+ "] already exists. Delete the existing table or give a new name to the table.");
			return false;
		} catch (Exception e) {
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public boolean delete(String tableName) throws DAOException {
		try {
			if (null == tableName) {
				throw new IllegalArgumentException("HTableDescriptor is null");
			}
			if (admin.tableExists(tableName)) {
				if (admin.isTableEnabled(tableName)) {
					admin.disableTable(tableName);
				}
				admin.deleteTable(tableName);
				logger.info("Table [" + tableName + "] deleted");
				return true;
			}
			logger.info("Table [" + tableName + "] not deleted");
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

	public void readAll() throws IOException {
		Scan s = new Scan();
		s.addColumn(Bytes.toBytes("myColumnFamily"), Bytes
				.toBytes("someQualifier"));
		HTable table = new HTable(conf, "myTable");
		ResultScanner scanner = table.getScanner(s);
		try {
			// Scanners return Result instances.
			// Now, for the actual iteration. One way is to use a while loop
			// like so:
			for (Result rr = scanner.next(); rr != null; rr = scanner.next()) {
				// print out the row we found and the columns we were looking
				// for
				System.out.println("Found row: " + rr.toString());
			}

			// The other approach is to use a foreach loop. Scanners are
			// iterable!
			// for (Result rr : scanner) {
			// System.out.println("Found row: " + rr);
			// }
		} finally {
			// Make sure you close your scanners when you are done!
			// Thats why we have it inside a try/finally clause
			scanner.close();
		}

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
}
