package com.pramati.healthcare.core.dao;

import java.util.List;

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
	 * @param tableName
	 * @param coloumnFamilies
	 * @return <code>true</code> if table created successfully,
	 *         <code>false</code> otherwise.
	 * @throws DAOException
	 */
	public boolean create(String tableName, List<String> coloumnFamilies)
			throws DAOException;

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
	boolean update(String tableName, String searchString) throws DAOException;

	/**
	 * Deletes the record.
	 * 
	 * @param tableName
	 * @return
	 * @throws DAOException
	 */
	public boolean delete(String tableName) throws DAOException;

}
