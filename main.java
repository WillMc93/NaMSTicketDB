
public class main {
	public final static void main(String[] args) {
		Connector connector = new Connector();
		Technicians tech = new Technicians();
		tech.login();
	
		System.out.println(tech.getUsername());
	
	}
}
