/*
 * Class for Admin's of the database. Allows the creation and termination of new technicians.
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
	public int createTech(String username, String name) {
		String query = "INSERT INTO technicians (Username, Name, Admin, Employed) VALUES "
				+ "(?, ?, FALSE, TRUE) RETURNING TechID;";
	
		// Check constraints
		if (username.length() != 7) {
			System.out.println("Name must be 7-characters.");
			return -1;
		}
		if (name.length() > 20) {
			System.out.println("Name must be less than 20-characters.");
			return -1;
		}

		// Try to add the technician account
		try {
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  username);
			pstmt.setString(2,  name);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) { // if the resultset has something
				return rs.getInt("TechID"); // return the new TechID
			}
		}
		catch(SQLException e) {
			System.out.println("There was an error creating the technician.");
			return -1;
		}
		return -1;
	}
}
