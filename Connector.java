/*
 * Class for connecting to database.
 * 
 * 
 */
import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

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
		Console console = System.console();
		
		// Get Username
		System.out.print("Username: ");
		dbUser = in.nextLine();
		in.close(); // close scanner
		
		// Get Password (hidden this way)
		dbPass = new String(console.readPassword("Password: "));
			
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
