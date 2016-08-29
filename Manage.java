/*
 * Class for adding, modifying, and completing tickets.
 * 
 * ToDo: add option for admins to delete tickets
 */

import java.lang.IllegalArgumentException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.time.LocalDate;


public class Manage {
	// Method to add a new ticket to a database
	public boolean addTicket(String desc, String roomNum, String reporter) {
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		ResultSet rs; // hold resulting set
		int ticketNum = -1; // current ticketNumber
		
		/*
		// Clean strings and Transform to char[]
		if (roomNum.length() > 4) {
			throw new IllegalArgumentException("Room number too long!");
		}
		else if (roomNum.length() <= 4 && roomNum.length() >= 2) {
			String temp = new String();
			for (int l = 4 - roomNum.length(); l > 0; l--) { // pad with 0's
				temp += "0";
			}
			temp = temp + roomNum;
			rN = temp.toCharArray();
		}
		else {
			throw new IllegalArgumentException("Room number too short!");
		}
		
		if (reporter.length() > 20) {
			throw new IllegalArgumentException("Reporter Name is too long!");
		}
		else if (reporter.length() < 4) {
			throw new IllegalArgumentException("Reporter Name is too short!");
		}
		rep = reporter.toCharArray();
		*/

		// forge queries in the furnaces of Vulcan
		// add new ticket to "tickets"
		query = "INSERT INTO tickets (Description, RoomNum, Reporter) VALUES (?, ?, ?) RETURNING TicketID;"; // ticketID, AddedDate, AddedBy
		try {		
			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1, desc);
			pstmt.setString(2, roomNum);
			pstmt.setString(3, reporter);

			rs = pstmt.executeQuery(); // add ticket and record ResultSet
		}
		catch (SQLException e) {
			System.out.println("A1: Exception occured creating entry in DB.");
			return false;
		}

		// Try to add new ticket to "added"
		query = "INSERT INTO added (ticketID, AddedDate, AddedBy) VALUES (?, ?, ?) RETURNING TicketID;"; // make new query for "added"
		try {
			if (rs.next()) {
				ticketNum = rs.getInt("ticketid"); // get ticketID
			}
			ldate = LocalDate.now(); // get today's date

			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setInt(1, ticketNum);
			pstmt.setDate(2, Date.valueOf(ldate));
			pstmt.setInt(3, Technicians.getTechID());

			pstmt.executeQuery(); // add "added" record
		}
		catch (SQLException e) {
			System.out.println("A2: Exception occured creating entry in DB.");
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
		query = "SELECT ticketID, Description FROM tickets WHERE ticketID = ?;";
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

			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println(e);
			return false;
		}

		return true;
	}

	public boolean completeTicket(int ticketID) {
		// variable declaration
		LocalDate ldate; // today's date
		String query; // query container
		PreparedStatement pstmt;
		
		// All we have to do is add an entry to completed
		query = "INSERT INTO completed VALUES (?, ?, ?) RETURNING TicketID;"; // ticketID, CompletedDate, CompletedBy
		
		// Get today's date
		ldate = LocalDate.now();
		
		// Try to execute add to completed
		try {
			pstmt = Connector.connection.prepareStatement(query);
			pstmt.setInt(1, ticketID);
			pstmt.setDate(2,  Date.valueOf(ldate));
			pstmt.setInt(3,  Technicians.getTechID());
		}
		catch (SQLException e) {
			System.out.println("SQLException occured marking completed.");
			return false;
		}
		
		return true;
	}
}
