import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
	private static Technician techPtr;

	public static boolean login() {
		// Variable Declaration
		String query;
		ResultSet rs;

		String name = null;
		int techID = -1;
		boolean admin = false;

		// Try to get current user.
		try {
			query = "SELECT current_user;";
			Statement stmt = Connector.connection.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) { // have to advance the pointer
				name = rs.getString("current_user");
			}
		}
		catch(SQLException e) {
			System.out.println("Exception occured on login.");
			return false;
		}

		// Try to get techID and admin status of current user.
		try {
			query = "SELECT TechID, Admin FROM technicians WHERE name = ?;";
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  name);
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

		// Complete the local login
		if (admin == true) {
			techPtr = new Admin(name, techID);
		}
		else {
			techPtr = new Technician(name, techID);
		}

		// Hark!
		System.out.println("Login Successful!");
		return true;
	}


	public static Technician getTechPtr() { return techPtr; }
}
