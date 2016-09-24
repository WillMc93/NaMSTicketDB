/*
 * Class for holding and accessing technician data.
 */

public class Technician {
	private static String name;
	private static int techID;
	//private static boolean admin;
	
	private boolean changePassword;
	
	public Technician() {
		Technician.name = null;
		Technician.techID = -1;
	}
	
	public Technician(String name, int techID) {
		Technician.name = name;
		Technician.techID = techID;
	}

	public static String getName() { return Technician.name; }
	public static int getTechID() { return Technician.techID; }
}
