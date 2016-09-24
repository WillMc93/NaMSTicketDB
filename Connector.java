/*
 * Class for connecting to database.
 * 
 * 
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Scanner;

public class Connector {
	private static String dbDriver = "org.postgresql.Driver";
	private static String dbServer = "jdbc:postgresql://127.0.0.1:5432/";
	private static String dbUser;
	private static String dbPass;
	private static String dbName = "Test";
	
	public static Connection connection;

	Connector() {
		Scanner in = new Scanner(System.in);
		
		// Get Username
		System.out.print("Username: ");
		Connector.dbUser = in.nextLine();
				
		// Get Password (hidden this way)
		System.out.print("Password: ");
		Connector.dbPass = in.nextLine();
		
		// try to open connection to the database
		try {
			Class.forName(Connector.dbDriver);
			
			connection = DriverManager.getConnection(Connector.dbServer + Connector.dbName, Connector.dbUser, Connector.dbPass);
		}
		catch (SQLException e) {
			System.out.println("SQLException Occured Connecting to Database.");
			//System.out.println(e);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Exception Occured Connecting to Database: ClassNotFound");
		}
	}
}
