/*
* ASCIIinterface provides for basic interaction with the database program.
*/
import IllegalArgumentException;

public class ASCIIint {
  private Manage manage;
  public ASCIIint () { manage = new Manage();}

  public boolean renderMenu() throws IllegalArgumentException{
    int choice = 0;
    System.out.println("<********************************>");
    System.out.println("1. View Tickets");
    System.out.println("2. Add Ticket");
    System.out.println("3. Complete Ticket");
    System.out.println("<********************************>");

    choice = Utility.getInt();

    switch (choice) {
      case 1:
      return viewTickets();
      break;

      case 2:
      return addTicket();
      break;

      case 3:
      return completeTicket();
      break;

      default: throw new IllegalArgumentException;
    }

    return true;
  }

  private boolean viewTickets() {
    return true;
  }

  private boolean addTicket() {
    return true;
  }

  private boolean completeTicket() {
    return true;
  }
}
