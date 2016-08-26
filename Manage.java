import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Types;

public class Manage {
	public boolean addTicket(String desc, char[] roomNum, char[] reporter,  int techID) {
		// variable declaration
		LocalDate ldate;
		Date sqlDate;
		int ticketNum = 0;
		String query;
		PreparedStatement pstmt;
		
		// get date
		ldate = LocalDate.now();
		sqlDate = Date.valueOf(ldate);
		
		// forge queries in the furnaces of vulcan
		query = "INSERT INTO tickets (Description, RoomNum, Reporter) VALUES (?, ?, ?)";
		try {		
			pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setString(1, desc);
			pstmt.setNull(2, Types.VARCHAR);
			pstmt.setNull(3,  Types.VARCHAR);
			
			synchronized(this) {
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					ticketNum = rs.getInt(0);
					query = "INSERT INTO added (TicketID, AddedDate, AddedBy) VALUES (?, ?, ?)";
					try {
						pstmt = ConnectHandler.connection.prepareStatement(query);
						pstmt.setInt(1, ticketNum);
						pstmt.setDate(2, sqlDate);
						pstmt.setInt(3, techID);
						
						pstmt.executeQuery();
					}
					catch (SQLException e) {
						System.out.println("Exception occured creating added entry in DB.");
					}
				}
			}
		}
		catch (SQLException e) {
			System.out.println("Exception occured creating tickets entry in DB.");
			return false;
		}
		
		return true;
	}
}
