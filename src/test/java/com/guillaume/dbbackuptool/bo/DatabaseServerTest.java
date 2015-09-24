package com.guillaume.dbbackuptool.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class DatabaseServerTest {

	private static DatabaseServer databaseServer;
	
	private static List<Database> databases;
	
	@BeforeClass
	public static void setUp() {
		databases = new ArrayList<Database>();
		
		databases.add(new Database("database1", new ArrayList<DatabaseTable>()));
		databases.add(new Database("database2", new ArrayList<DatabaseTable>()));
		databases.add(new Database("database3", new ArrayList<DatabaseTable>()));
		
		databaseServer = new DatabaseServer(DatabaseVendor.MYSQL, "root", "", "localhost", "3306", databases);
	}
	
	@Test
	public void testGetVendor() {
		Assert.assertEquals(DatabaseVendor.MYSQL, databaseServer.getVendor());
	}
	
	@Test
	public void testGetHostname() {
		Assert.assertEquals("localhost", databaseServer.getHostname());
	}
	
	@Test
	public void testGetPort() {
		Assert.assertEquals("3306", databaseServer.getPort());
	}
	
	@Test
	public void testGetDatabases() {
		Assert.assertEquals(3, databaseServer.getDatabases().size());
		for (Iterator<Database> iterator = databases.iterator(); iterator.hasNext();) {
			Database database = iterator.next();
			Assert.assertNotNull(database);
		}
	}
	
	@Test
	public void testGetDatabase() {
		Assert.assertEquals("database1", databaseServer.getDatabase(0).getName());
		Assert.assertEquals("database2", databaseServer.getDatabase(1).getName());
		Assert.assertEquals("database3", databaseServer.getDatabase(2).getName());
	}
}
