package com.guillaume.dbbackuptool.bo;

import java.util.List;

public class DatabaseServer {
	
	private DatabaseVendor vendor;
	
	private String username;
	
	private String password;
	
	private String hostname;
	
	private String port;
	
	private List<Database> databases;
	
	public DatabaseServer(DatabaseVendor vendor, String username, String password, String hostname, String port) {
		this.vendor = vendor;
		this.username = username;
		this.password = password;
		this.hostname = hostname;
		this.port = port;
	}	
	
	public DatabaseServer(DatabaseVendor vendor, String username, String password, String hostname, String port, List<Database> databases) {
		this(vendor, username, password, hostname, port);
		this.databases = databases;
	}
	
	public DatabaseVendor getVendor() {
		return vendor;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getHostname() {
		return hostname;
	}

	public String getPort() {
		return port;
	}

	public List<Database> getDatabases() {
		return databases;
	}
	
	public Database getDatabase(int index) {
		return this.databases.get(index);
	}
	
	
}
