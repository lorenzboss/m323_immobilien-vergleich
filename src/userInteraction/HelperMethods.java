package userInteraction;

import java.util.Scanner;
import properties.enums.Rooms;

public class HelperMethods {
  private static final Scanner scanner = new Scanner(System.in);

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
