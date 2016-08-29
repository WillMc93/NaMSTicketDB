/*
 * Class for connecting to database.
 * 
 * 
 */
import java.sql.*;

import java.util.Scanner;


public class Connector {

	static String dbDriver = "org.postgresql.Driver";
	static String dbServer = "jdbc:postgresql://127.0.0.1:5432/";
	static String dbUser;
	static String dbPass;
	static String dbName = "Test";
	
	static Connection connection;

	Connector() {
		Scanner in = new Scanner(System.in);
		
		// Get Username
		System.out.print("Username: ");
		dbUser = in.nextLine();
				
		// Get Password (hidden this way)
		System.out.print("Password: ");
		dbPass = in.nextLine();
		
		// try to open connection to the database
		try {
			Class.forName(Connector.dbDriver);
			
			connection = DriverManager.getConnection(dbServer + dbName, dbUser, dbPass);
		}
		catch (SQLException e) {
			System.out.println("SQLException Occured Connecting to Database.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Exception Occured Connecting to Database: ClassNotFound");
		}
	}
}
