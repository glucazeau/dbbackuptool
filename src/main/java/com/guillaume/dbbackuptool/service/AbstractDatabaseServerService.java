package com.guillaume.dbbackuptool.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseServer;
import com.guillaume.dbbackuptool.bo.DatabaseTable;
import com.guillaume.dbbackuptool.bo.TableField;

public abstract class AbstractDatabaseServerService {

	protected DatabaseServer databaseServer;

	protected Connection connection;

	protected String getConnectionUrl(Database database) {
		StringBuffer connectionUrlBuffer = new StringBuffer("jdbc:");
		connectionUrlBuffer.append(this.databaseServer.getVendor().toString().toLowerCase());
		connectionUrlBuffer.append("://");
		connectionUrlBuffer.append(databaseServer.getHostname());
		connectionUrlBuffer.append(":");
		connectionUrlBuffer.append(databaseServer.getPort());
		connectionUrlBuffer.append("/");
		if (database != null) {
			connectionUrlBuffer.append(database.getName());
		}
		return connectionUrlBuffer.toString();
	}
	
	public Connection getConnection(Database database) throws SQLException {
		if (this.connection == null) {
			openConnection(database);
		}
		return this.connection;
	}

	protected void openConnection(Database database) throws SQLException {
		String connectionUrl = this.getConnectionUrl(database);
		Properties connectionProps = new Properties();
		connectionProps.put("user", databaseServer.getUsername());
		connectionProps.put("password", databaseServer.getPassword());

		this.connection = DriverManager.getConnection(connectionUrl, connectionProps);
	}

	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	private void closeResultSet(ResultSet rs) throws SQLException {
		rs.close();
	}

	private DatabaseMetaData getMetaData(Database database) throws SQLException {
		return this.getConnection(database).getMetaData();
	}

	protected abstract TableField getNewField(String name, DatabaseMetaData metaData);
	
	public List<Database> listDatabases() throws SQLException {
		List<Database> databases = new ArrayList<Database>();
		DatabaseMetaData metaData = this.getMetaData(null);
		ResultSet rs = metaData.getCatalogs();
		while (rs.next()) {
			databases.add(new Database(rs.getString("TABLE_CAT")));
		}
		closeResultSet(rs);
		closeConnection();
		return databases;
	}

	public List<DatabaseTable> listTables(Database database) throws SQLException {
		List<DatabaseTable> tables = new ArrayList<DatabaseTable>();
		DatabaseMetaData metaData = this.getMetaData(database);
		ResultSet rs = metaData.getTables(null, database.getName(), "%", null);
		while (rs.next()) {
			tables.add(new DatabaseTable(rs.getString("TABLE_NAME")));
		}
	    closeResultSet(rs);
	    closeConnection();
		return tables;
	}

	public List<TableField> listColumns(Database database, DatabaseTable table) throws SQLException {
		List<TableField> tableFields = new ArrayList<TableField>();
		DatabaseMetaData metaData = this.getMetaData(database);
		ResultSet rs = metaData.getColumns(null, database.getName(), table.getName(), null);
		while (rs.next()) {
			tableFields.add(getNewField(rs.getString("COLUMN_NAME"), metaData));
		}
	    closeResultSet(rs);
	    closeConnection();
		return tableFields;
	}
}
