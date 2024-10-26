package userInteraction;

import java.util.Scanner;
import properties.enums.Rooms;

/**
 * This class contains helper methods for user interaction.
 *
 * @author Lorenz Boss
 * @version 2.0
 */
public class HelperMethods {
  private static final Scanner scanner = new Scanner(System.in);

  /**
   * Asks the user a yes or no question.
   *
   * @param question the question
   * @return true if the user answers with yes, false if the user answers with no
   */
  public static boolean askYesNoQuestion(String question) {
    while (true) {
      System.out.print(question);
      char userInput = scanner.next().charAt(0);
      if (userInput == 'y') {
        return true;
      } else if (userInput == 'n') {
        return false;
      } else {
        System.out.println("Invalid option\n");
      }
    }
  }

  /**
   * Asks the user for an integer.
   *
   * @param question the question
   * @param maxLimit the maximum limit
   * @param minLimit the minimum limit
   * @return the user input
   */
  public static Integer askForInt(String question, int maxLimit, int minLimit) {
    while (true) {
      System.out.print(question);
      int userInput = scanner.nextInt();
      if (userInput >= minLimit && userInput <= maxLimit) {
        return userInput;
      } else {
        System.out.println("Invalid option\n");
      }
    }
  }

  /**
   * Asks the user for the logic option.
   *
   * @return the option if the user selects functional or imperative logic
   */
  public static LogicOption askForLogicOption() {
    while (true) {
      System.out.print("Please select an option: ");
      char userInput = scanner.next().charAt(0);

      if (userInput == 'f') {
        System.out.println("You have selected the functional logic.\n");
        return LogicOption.FUNCTIONAL;
      } else if (userInput == 'i') {
        System.out.println("You have selected the imperative logic.\n");
        return LogicOption.IMPERATIVE;
      } else {
        System.out.println("Invalid option\n");
      }
    }
  }

    /**
     * Asks the user for the number of rooms.
     *
     * @return the number of rooms
     */
  public static Rooms askForNumberOfRooms() {
    while (true) {
      System.out.print("Choose between '1', '2', '3', '4', '5' (5+ Rooms), 't' (Total): ");
      char userInput = scanner.next().charAt(0);

      switch (userInput) {
        case '1':
          System.out.println("You have selected 1 room.\n");
          return Rooms.ONE;
        case '2':
          System.out.println("You have selected 2 rooms.\n");
          return Rooms.TWO;
        case '3':
          System.out.println("You have selected 3 rooms.\n");
          return Rooms.THREE;
        case '4':
          System.out.println("You have selected 4 rooms.\n");
          return Rooms.FOUR;
        case '5':
          System.out.println("You have selected 5+ rooms.\n");
          return Rooms.FIVE_PLUS;
        case 't':
          System.out.println("You have selected the total number of rooms.\n");
          return Rooms.TOTAL;
        default:
          System.out.println("Invalid option\n");
      }
    }
  }
}
