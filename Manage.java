/*
 * Class for adding, modifying, and completing tickets.
 * 
 * ToDo: add option for admins to delete ticktes
 */

import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Types;

public class Manage {
	public boolean addTicket(String desc, char[] roomNum, char[] reporter,  int techID) {
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		ResultSet rs; // hold resulting set
		int ticketNum; // current ticketNumber

		// forge queries in the furnaces of Vulcan
		// add new ticket to "tickets"
		query = "INSERT INTO tickets (Description, RoomNum, Reporter) VALUES (?, ?, ?);";
		try {		
			pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setString(1, desc);
			pstmt.setNull(2, Types.VARCHAR);
			pstmt.setNull(3, Types.VARCHAR);

			rs = pstmt.executeQuery(); // add ticket and record ResultSet
		}
		catch (SQLException e) {
			System.out.println("Exception occured creating \"tickets\" entry in DB.");
			return false;
		}

		// add new ticket to "added"
		query = "INSERT INTO added (TicketID, AddedDate, AddedBy) VALUES (?, ?, ?);"; // make new query for "added"
		try {
			ticketNum = rs.getInt("TicketID"); // get ticketID
			ldate = LocalDate.now(); // get today's date

			pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setInt(1, ticketNum);
			pstmt.setDate(2, Date.valueOf(ldate));
			pstmt.setInt(3, techID);

			pstmt.executeQuery(); // add "added" record
		}
		catch (SQLException e) {
			System.out.println("Exception occured creating \"added\" entry in DB.");
			return false;
		}

		return true; // we're going to assume that if no exception was thrown that everything is kosher
	}


	public boolean modTicket(int TicketID, String desc) { 
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		ResultSet rs; // hold resulting set

		String descString;

		// isolate old string value
		query = "SELECT TicketID FROM tickets WHERE TicketID = ?;";
		try {
			pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setInt(1, TicketID);
			rs = pstmt.executeQuery();
		}
		catch (SQLException e) {
			System.out.println("Exception occured getting old string from Ticket.");
			return false;
		}

		query = "UPDATE tickets SET description = ? WHERE TicketID = ?;";
		ldate = LocalDate.now(); // get today's date

		try {
			// append to old string with today's date and new description
			descString = rs.getString("Description");
			descString = "UPDATE: " + ldate.toString() + ": " + desc + "\n" + descString;

			pstmt = ConnectHandler.connection.prepareStatement(query);
			pstmt.setString(1,  descString);
			pstmt.setInt(2, TicketID);
		}
		catch (SQLException e) {
			System.out.println("Exception occured");
			return false;
		}

		return true;
	}
}
