package com.guillaume.dbbackuptool.service;

import com.guillaume.dbbackuptool.bo.DatabaseServer;

public class DatabaseServerServiceFactory {
	
	public AbstractDatabaseServerService getDatabaseServerService(DatabaseServer databaseServer) {
		AbstractDatabaseServerService databaseServerService = null;
		switch (databaseServer.getVendor()) {
		case MYSQL:
			databaseServerService = new MySqlDatabaseServerService(databaseServer);
			break;

		default:
			break;
		}
		return databaseServerService;
	}
}
