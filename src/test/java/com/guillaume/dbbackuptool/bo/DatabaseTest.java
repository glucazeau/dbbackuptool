package com.guillaume.dbbackuptool.bo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class DatabaseTest {

	@Test
	public void testGetName() {
		Database database = new Database("database1", null);
		Assert.assertEquals("database1", database.getName());
	}
	
	@Test
	public void testGetDatabases() {
		List<DatabaseTable> tables = new ArrayList<DatabaseTable>();
		tables.add(new DatabaseTable("table1"));
		tables.add(new DatabaseTable("table2"));
		
		Database database = new Database("database1", tables);
		Assert.assertEquals(2, database.getTables().size());
	}
}
