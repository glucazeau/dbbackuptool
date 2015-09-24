package com.guillaume.dbbackuptool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.guillaume.dbbackuptool.bo.Database;
import com.guillaume.dbbackuptool.bo.DatabaseServer;
import com.guillaume.dbbackuptool.bo.DatabaseTable;
import com.guillaume.dbbackuptool.bo.DatabaseVendor;
import com.guillaume.dbbackuptool.service.AbstractDatabaseServerService;
import com.guillaume.dbbackuptool.service.DatabaseServerServiceFactory;

/**
 * Hello world!
 *
 */
public class App 
{
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
    		System.out.println("Error retrieving databases from the server : ");
    		System.out.println(e.getMessage());
    	}
    	
    	Database db = null;
    	if (databases.size() > 0) {
    		try {
    			db = databases.get(0);
    			List<DatabaseTable> tables = serverService.listTables(db);
    			for (Iterator<DatabaseTable> iterator = tables.iterator(); iterator.hasNext();) {
					DatabaseTable databaseTable = iterator.next();
					System.out.println(databaseTable.getName());
				}
    		} catch (SQLException e) {
        		System.out.println("Error retrieving tables from " + db.getName() + " database :");
        		System.out.println(e.getMessage());
    		}
    	}
    	
    }
}
