package com.pramati.healthcare.core.dao;

import com.pramati.healthcare.core.tools.Table;

/**
 * Provides persistence to application.
 * 
 * @author nitin
 * 
 */
public interface DataAccessManager {

	/**
	 * Creates the table.
	 * 
	 * @param table
	 * @return <code>true</code> if table created successfully,
	 *         <code>false</code> otherwise.
	 * @throws DAOException
	 */
	public boolean create(Table table)
			throws DAOException;

	/**
	 * Deletes the record.
	 * 
	 * @param table
	 * @return
	 * @throws DAOException
	 */
	public boolean delete(Table table) throws DAOException;

	/**
	 * Retrieves results against the query.
	 * 
	 * @param tableName
	 * @param searchString
	 * @return
	 * @throws DAOException
	 */
	public String read(String tableName, String searchString)
			throws DAOException;

	/**
	 * Updates the persisted data.
	 * 
	 * @param tableName
	 * @param record
	 * @return
	 * @throws DAOException
	 */
	public boolean update(String tableName, String searchString) throws DAOException;

}
