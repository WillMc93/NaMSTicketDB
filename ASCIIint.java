/*
* ASCIIinterface provides for basic interaction with the database program.
*/

public class ASCIIint {
  public ASCIIint () {
    renderMenu();
  }

  public boolean renderMenu() {
    int choice = 0;
    System.out.println("<********************************>");
    System.out.println("1. Login");
    System.out.println("2. View Tickets");
    System.out.println("3. Add Ticket");
    System.out.println("4. Complete Ticket");
    System.out.println("<********************************>");

    choice = Utility.getInt();

    return true;
  }
}
