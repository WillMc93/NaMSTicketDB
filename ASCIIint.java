/*
* ASCIIinterface provides for basic interaction with the database program.
*/

public class ASCIIint {
  public ASCIIint () {}

  public boolean renderMenu() {
    int choice = 0;
    System.out.println("<********************************>");
    System.out.println("1. View Tickets");
    System.out.println("2. Add Ticket");
    System.out.println("3. Complete Ticket");
    System.out.println("<********************************>");

    choice = Utility.getInt();

    return true;
  }
}
