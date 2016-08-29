
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;


public class Technicians {
	private static String username;
	private static int techID;
	
	public boolean login() {
		// Variable Declaration
		String query;
		ResultSet rs;
		
		// Try to get current user.
		try {
			query = "SELECT current_user;";
			Statement stmt = Connector.connection.createStatement();
			rs = stmt.executeQuery(query);
			username = rs.getString("current_user");
		}
		catch(SQLException e) {
			System.out.println("Exception occured on login.");
			return false;
		}
		
		// Try to get techID of current user.
		try {
			query = "SELECT TechID FORM technicians WHERE Username = ?;";
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  username);
			rs = pstmt.executeQuery();
			techID = rs.getInt("TechID");
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
}
