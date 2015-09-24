package com.guillaume.dbbackuptool.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseServer;
import com.guillaume.dbbackuptool.bo.DatabaseTable;

public abstract class AbstractDatabaseServerService {

	protected DatabaseServer databaseServer;

	protected Connection connection;

	protected String LIST_DATABASES_QUERY = "SHOW DATABASES";

	protected String LIST_TABLES_QUERY = "SHOW TABLES";
	
	public abstract String getConnectionUrl(Database database);
	
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

	protected ResultSet executeQuery(Database database, String query) throws SQLException {
		this.openConnection(database);
		Statement stmt = null;
		try {
			stmt = this.getConnection(database).createStatement();
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		} catch (SQLException e) {
			throw e;
		}
	}

	public List<Database> listDatabases() throws SQLException {
		List<Database> databases = new ArrayList<Database>();
		try {
			ResultSet rs = this.executeQuery(null, this.LIST_DATABASES_QUERY);
			while (rs.next()) {
				String databaseName = rs.getString("Database");
				Database database = new Database(databaseName);
				databases.add(database);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection();
		}
		return databases;
	}
	
	public List<DatabaseTable> listTables(Database database) throws SQLException {
		List<DatabaseTable> tables = new ArrayList<DatabaseTable>();
		try {
			ResultSet rs = this.executeQuery(database, this.LIST_TABLES_QUERY);
			while (rs.next()) {
				String tableName = rs.getString("Tables_in_" + database.getName());
				DatabaseTable table = new DatabaseTable(tableName);
				tables.add(table);
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeConnection();
		}
		return tables;
	}
}
