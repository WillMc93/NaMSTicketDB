//import java.util.Scanner;

public class main {
	public final static void main(String[] args) {
		Utility util = new Utility(); // start the Utility class
		Connector connector = new Connector(); // call this to get logged into the database
		Account account = new Account(); // call this to get logged into the client, later
		Technician tech = null; // placeholder
		Manage manage = null; // placeholder

		ASCIIint ascii = new ASCIIint();

		while(ascii.renderMenu()) {}
		
	}
}
