/*
 * Class for Admin's of the database. Allows the creation and termination of technicians.
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Scanner;

public class Admin extends Technician {
	// Constructor
	public Admin(String name, int techID) {
		super(name, techID);
	}
	
	// Allow Admins to create other techs/admins on the database
	public int createTech(String name, boolean Admin) {
		String query = "INSERT INTO technicians (Name, Admin, Employed) VALUES "
				+ "(?, FALSE, TRUE) RETURNING TechID;";
	
		// Check constraints
		if (name.length() <= 2) {
			System.out.println("Name must be at least 2 characters .");
			return -1;
		}
		if (name.length() > 20) {
			System.out.println("Name must be less than 20 characters.");
			return -1;
		}

		// Try to add the technician account
		try {
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1,  name);
			pstmt.setBoolean(2, true);

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
	
	// Allow Admins to mark people unemployed
	public boolean changeEmployment(String name, boolean state) {
		Scanner in = new Scanner(System.in);
		int techID = -1;
		
		// check that user exists
		String query = "SELECT TechID FROM technicians WHERE Name = ?";
		try {
			PreparedStatement pstmt = Connector.connection.prepareStatement(query);
			pstmt.setString(1, name);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				techID = rs.getInt("TechID");
			}
			else {
				System.out.println("The technician " + name + " does not exist.");
			}
		}
		catch (SQLException e) {
			System.out.println("An error occured checking existance of user.");
		}
		// new query
		query = "UPDATE technicians SET Employed = ? WHERE TechID = ? RETURNING TRUE";
	
		// check that we want to mark this tech as not employed
		System.out.println("Are you sure you want to mark technician " + name + " as " + (state == true ? "employed" : "unemployed") + ". Y/N");
		String choice = null;
		choice = in.nextLine().toUpperCase();
		if (choice.length() >= 1) {
			if (choice.toCharArray()[0] == 'Y') { // if the first character returned is 'y'
				try { // try to update the technician
					PreparedStatement pstmt = Connector.connection.prepareStatement(query);
					pstmt.setBoolean(1, state);
					pstmt.setInt(2, techID);
					ResultSet rs = pstmt.executeQuery();
					System.out.println(name + " was marked unemployed.");
					return rs.getBoolean("Employed");					
				}
				catch (SQLException e) {
					System.out.println("An SQL error occured trying to update the employement status");
				}
			}
			else {
				System.out.println("The technician was not marked unemployeed.");
				return false;
			}
		}	
		return false;	
	}
}
