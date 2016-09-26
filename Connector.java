/*
 * Class for connecting to database.
 *
 * All method calls are static because we assume we only have this one connection per instance.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
	private static String dbDriver;
	private static String dbServer;
	private static String dbUser;
	private static String dbPass;
	private static String dbName;

	public static Connection connection;

	Connector() {
		Connector.dbDriver = "org.postgresql.Driver";
		Connector.dbServer = "jdbc:postgresql://127.0.0.1:5432/";
		Connector.dbName  = "Test";
	}

	public static boolean dbLogin() {
		// Get Username
		System.out.print("Username: ");
		Connector.dbUser = Utility.getLine();

		// Get Password (not hidden this way)
		System.out.print("Password: ");
		Connector.dbPass = Utility.getLine();

		// try to open connection to the database
		try {
			Class.forName(Connector.dbDriver);
			connection = DriverManager.getConnection(Connector.dbServer + Connector.dbName, Connector.dbUser, Connector.dbPass);
		}

		catch (SQLException e) {
			System.out.println("SQLException Occured Connecting to Database.");
			return false;
			//System.out.println(e);
		}

		catch (ClassNotFoundException e) {
			System.out.println("Exception Occured Connecting to Database: ClassNotFound");
			return false;
		}

		return true;
	}

}
