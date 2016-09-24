
import java.util.Scanner;

public class main {
	public final static void main(String[] args) {
		Connector connector = new Connector(); // call this to get logged into the database
		Account account = new Account(); // call this to get logged into the client, later
		Technician tech = null; // placeholder
		Manage manage = null; // placeholder

		if (account.login()) { // if we login successfully
			tech = account.getTechPtr(); // find out who we are
			manage = new Manage(); // start new manage connection
		}

		//Scanner in = new Scanner(System.in);

		for (int i = 1; i > 0; --i) {
			if (tech.getClass().equals("Admin")) {
				((Admin)tech).createTech("Will", true);
				((Admin)tech).changeEmployment("Will", false);
			}
		}
	}
}

