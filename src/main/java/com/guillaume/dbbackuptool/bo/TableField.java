package com.guillaume.dbbackuptool.bo;

import java.sql.DatabaseMetaData;

public abstract class TableField {
	private String name;
	
	private DatabaseMetaData metaData;
	
	public TableField(String name, DatabaseMetaData metaData) {
		this.name = name;
		this.metaData = metaData;
	}
	
	public String getName() {
		return name;
	}
	
	public DatabaseMetaData getMetaData() {
		return this.metaData;
	}
	
	public abstract String getCreateStatement();
}
