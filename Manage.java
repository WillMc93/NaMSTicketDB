/*
 * Class for adding, modifying, and completing tickets.
 * 
 * ToDo: add option for admins to delete tickets
 */

import java.time.LocalDate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.Types;

public class Manage {
	// Method to add a new ticket to a database
	public boolean addTicket(String desc, char[] roomNum, char[] reporter,  int techID) {
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		ResultSet rs; // hold resulting set
		int ticketNum; // current ticketNumber

		// forge queries in the furnaces of Vulcan
		// add new ticket to "tickets"
		query = "INSERT INTO tickets (Description, RoomNum, Reporter) VALUES (?, ?, ?);"; // ticketID, AddedDate, AddedBy
		try {		
			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1, desc);
			pstmt.setNull(2, Types.VARCHAR);
			pstmt.setNull(3, Types.VARCHAR);

			rs = pstmt.executeQuery(); // add ticket and record ResultSet
		}
		catch (SQLException e) {
			System.out.println("Exception occured creating entry in DB.");
			return false;
		}

		// Try to add new ticket to "added"
		query = "INSERT INTO added (ticketID, AddedDate, AddedBy) VALUES (?, ?, ?);"; // make new query for "added"
		try {
			ticketNum = rs.getInt("ticketID"); // get ticketID
			ldate = LocalDate.now(); // get today's date

			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setInt(1, ticketNum);
			pstmt.setDate(2, Date.valueOf(ldate));
			pstmt.setInt(3, techID);

			pstmt.executeQuery(); // add "added" record
		}
		catch (SQLException e) {
			System.out.println("Exception occured creating entry in DB.");
			return false;
		}

		return true; // we're going to assume that if no exception was thrown that everything is kosher
	}

	// Method to modify the description of a ticket.
	public boolean modTicket(int ticketID, String desc) { 
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		ResultSet rs; // hold resulting set

		String descString;

		// isolate old string value
		query = "SELECT ticketID FROM tickets WHERE ticketID = ?;";
		try {
			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setInt(1, ticketID);

			rs = pstmt.executeQuery();
		}
		catch (SQLException e) {
			System.out.println("Exception occured getting old string from ticket.");
			return false;
		}

		query = "UPDATE tickets SET description = ? WHERE ticketID = ?;";
		ldate = LocalDate.now(); // get today's date
		
		// Try to modify description
		try {
			// append to old string with today's date and new description
			descString = rs.getString("Description");
			descString = "UPDATE: " + ldate.toString() + ": " + desc + "\n" + descString;

			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  descString);
			pstmt.setInt(2, ticketID);

			pstmt.executeQuery();
		}
		catch (SQLException e) {
			System.out.println("Exception occured");
			return false;
		}

		return true;
	}

	public boolean completeTicket(int ticketID, int techID) {
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		
		// All we have to do is add an entry to completed
		query = "INSERT INTO completed VALUES (?, ?, ?);"; // ticketID, CompletedDate, CompletedBy
		
		// Get today's date
		ldate = LocalDate.now();
		
		// Try to execute add to completed
		try {
			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setInt(1, ticketID);
			pstmt.setDate(2,  Date.valueOf(ldate));
			pstmt.setInt(3,  techID);
		}
		catch (SQLException e) {
			System.out.println("SQLException occured marking completed.");
			return false;
		}
		
		return true;
	}

}
