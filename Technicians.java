
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Technicians {
	private static String username;
	private static int techID;
	private static boolean admin;

	public boolean login() {
		// Variable Declaration
		String query;
		ResultSet rs;

		// Try to get current user.
		try {
			query = "SELECT current_user;";
			Statement stmt = Connector.connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) { // have to advance the pointer
				username = rs.getString("current_user");
			}
		}
		catch(SQLException e) {
			System.out.println("Exception occured on login.");
			return false;
		}

		// Try to get techID of current user.
		try {
			query = "SELECT TechID, ADMIN FROM technicians WHERE Username = ?;";
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  username);
			rs = pstmt.executeQuery();
			if (rs.next()) { // Again, have to advance pointer
				techID = rs.getInt("TechID");
				admin = rs.getBoolean("Admin");
			}
		}
		catch(SQLException e) {
			System.out.println("Exception occured on login.");
			return false;
		}

		System.out.println("Login Successful!");
		return true;
	}

	public static String getUsername() { return username; }
	public static int getTechID() { return techID; }
	public static boolean isAdmin() { return admin; }
}
