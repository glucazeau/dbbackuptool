package com.guillaume.dbbackuptool.bo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseTable {

	private String name;
	
	private List<TableField> fields;

	public DatabaseTable(String name) {
		this.name = name;
		this.fields = new ArrayList<TableField>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<TableField> getFields() {
		return this.fields;
	}
	
	public void setFields(List<TableField> fields) {
		this.fields = fields;
	}
}
