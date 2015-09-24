package com.guillaume.dbbackuptool.bo;

import java.util.List;

public class Database {

	String name;
	
	List<DatabaseTable> tables;
	
	public Database(String name) {
		this.name = name;
	}
	
	public Database(String name, List<DatabaseTable> tables) {
		this.name = name;
		this.tables = tables;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<DatabaseTable> getTables() {
		return this.tables;
	}
	
	public DatabaseTable getTable(int index) {
		return tables.get(index);
	}
	
}
