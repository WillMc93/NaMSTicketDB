import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Types;

public class Manage {
	public boolean addTicket(String desc, char[] roomNum, char[] reporter,  int TechID) {
		// variable declaration
		LocalDate ldate;
		Date sqlDate;
		int ticketNum;
		
		// get date
		ldate = LocalDate.now();
		sqlDate = Date.valueOf(ldate);
		
		// forge query in the furnaces of vulcan
		String query = "INSERT INTO tickets (Description, RoomNum, Reporter) values (?, ?, ?)";
		try {		
			PreparedStatement pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setString(1, desc);
			pstmt.setNull(2, Types.VARCHAR);
			pstmt.setNull(3,  Types.VARCHAR);
			
			synchronized(this) {
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					ticketNum = rs.getInt(0);
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Exception occured adding ticket to database.");
			return false;
		}
		
		
		return true;
	}
}
