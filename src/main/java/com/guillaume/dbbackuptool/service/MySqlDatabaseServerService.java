package com.guillaume.dbbackuptool.service;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseServer;

public class MySqlDatabaseServerService extends AbstractDatabaseServerService {

	public MySqlDatabaseServerService(DatabaseServer databaseServer) {
		this.databaseServer = databaseServer;
	}
	
	@Override
	public String getConnectionUrl(Database database) {
		StringBuffer connectionUrlBuffer = new StringBuffer("jdbc:mysql://");
		connectionUrlBuffer.append(databaseServer.getHostname());
		connectionUrlBuffer.append(":");
		connectionUrlBuffer.append(databaseServer.getPort());
		connectionUrlBuffer.append("/");
		if (database != null) {
			connectionUrlBuffer.append(database.getName());
		}
		return connectionUrlBuffer.toString();
	}
}
