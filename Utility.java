/*
* Wrapper class for Scanner.in
*/

import java.util.Scanner;

public class Utility {
  private static Scanner input;
  public Utility() {
    Utility.input = new Scanner(System.in);
  }

  public static int getInt() {
    return Utility.input.nextInt();
  }

  public static String getLine() {
    return Utility.input.nextLine();
  }

  public void close() {
    input.close();
  }
}
