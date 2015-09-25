package com.guillaume.dbbackuptool.service;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseTable;

public abstract class AbstractDatabaseBackupService {

	private static final Logger logger = Logger.getLogger(AbstractDatabaseBackupService.class);
	
	protected AbstractDatabaseServerService dbServerService;

	protected Database database;

	public AbstractDatabaseBackupService(AbstractDatabaseServerService dbServerService, Database database) {
		super();
		this.dbServerService = dbServerService;
		this.database = database;
	}

	public Database retrieveDatabaseStructure() throws SQLException {
		try {
			List<DatabaseTable> tables = dbServerService.listTables(this.database);
			for (Iterator<DatabaseTable> iterator = tables.iterator(); iterator.hasNext();) {
				DatabaseTable databaseTable = iterator.next();

				databaseTable.setFields(dbServerService.listColumns(database, databaseTable));
				this.database.getTables().add(databaseTable);
			}
		} catch (SQLException e) {
			logger.error("Error retrieving tables from " + database.getName() + " database");
			logger.debug(e);
		}
		return this.database;
	}

}
