//import java.util.Scanner;

public class main {
	public final static void main(String[] args) {
		// Initialize Static classes
		Utility util = new Utility();
		Connector connector = new Connector();
		Account account = new Account();

		// Put placeholders in place.
		Technician tech = null; // placeholder
		Manage manage = null; // placeholder

		// Login
		if (Connector.dbLogin() && Account.login()) {
			tech = Account.getTechPtr();
		}

		ASCIIint ascii = new ASCIIint(); // call the ASCII interface

		while(ascii.renderMenu()) {} // show ASCII until exit

	}
}
