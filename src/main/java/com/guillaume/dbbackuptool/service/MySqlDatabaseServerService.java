package com.guillaume.dbbackuptool.service;

import java.sql.DatabaseMetaData;

import com.guillaume.dbbackuptool.bo.DatabaseServer;
import com.guillaume.dbbackuptool.bo.MySqlTableField;
import com.guillaume.dbbackuptool.bo.TableField;

public class MySqlDatabaseServerService extends AbstractDatabaseServerService {
	
	public MySqlDatabaseServerService(DatabaseServer databaseServer) {
		this.databaseServer = databaseServer;
	}
	
	@Override
	protected TableField getNewField(String name, DatabaseMetaData metaData) {
		return new MySqlTableField(name, metaData);
	}
}
