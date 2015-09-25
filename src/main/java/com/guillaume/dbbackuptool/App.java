package com.guillaume.dbbackuptool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseServer;
import com.guillaume.dbbackuptool.bo.DatabaseVendor;
import com.guillaume.dbbackuptool.service.AbstractDatabaseServerService;
import com.guillaume.dbbackuptool.service.DatabaseServerServiceFactory;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = Logger.getLogger(App.class);
	
    public static void main( String[] args )
    {
        // read parameters
    	DatabaseServer databaseServer = new DatabaseServer(DatabaseVendor.MYSQL, "root", "", "localhost", "3306");
    	
    	DatabaseServerServiceFactory serverFactory = new DatabaseServerServiceFactory();
    	AbstractDatabaseServerService serverService = serverFactory.getDatabaseServerService(databaseServer);
    	
    	List<Database> databases = new ArrayList<Database>();
    	try {
    		databases = serverService.listDatabases();
    		for (Iterator<Database> iterator = databases.iterator(); iterator.hasNext();) {
				Database database = iterator.next();
				System.out.println(database.getName());
			}
    	} catch (SQLException e) {
    		logger.error("Error while backuping the database" + e);
    	}
    	
    	
    }
}
