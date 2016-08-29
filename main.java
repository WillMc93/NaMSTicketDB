
import java.util.Scanner;

public class main {
	public final static void main(String[] args) {
		Connector connector = new Connector();
		Technicians tech = new Technicians();

		Manage manage = null;

		if (tech.login()) {
			manage = new Manage();
		}

		Scanner in = new Scanner(System.in);

		for (int i = 1; i > 0; --i) {


			manage.addTicket("TEST", "015A", "Jerome");
			manage.modTicket(1, "TESTTESTTEST");
			manage.completeTicket(1);

		}
	}	
}

