import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import java.util.Scanner;

public class ConnectHandler {

	static String dbDriver = "org.postgresql.Driver";
	static String dbServer = "jdbc:postgresql://127.0.0.1:5432/";
	static String dbUser;
	static String dbPass;
	static String dbName = "Test";
	
	static Connection connection;

	ConnectHandler() {
		Scanner in = new Scanner(System.in);
		try {
			Class.forName(ConnectHandler.dbDriver);
			System.out.print("Username: ");
			dbUser = in.nextLine();
			System.out.print("Password: "); // plain text for the time being FIX!
			dbPass = in.nextLine();
			
			connection = DriverManager.getConnection(dbServer + dbName, dbUser, dbPass);
		}
		catch (SQLException e) {
			System.out.println("Exception Occured Connecting to Database.");
		}
		catch (ClassNotFoundException e) {
			System.out.println("Exception Occured Connecting to Database: ClassNotFound");
		}
	}
}
