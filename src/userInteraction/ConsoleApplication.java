package userInteraction;

import java.util.List;
import java.util.Scanner;
import properties.Property;

public class ConsoleApplication {

  private static final Scanner scanner = new Scanner(System.in);

  public static void start(List<Property> propertyList) {
    LogicOption logicOption;

    System.out.println("Welcome to the Property Comparator!");
    System.out.println("We have " + propertyList.size() + " properties in our database.");
    System.out.println(
        "All of our logic in this project is created in a functional and and imperative way.");
    System.out.println("Would you like to use our functional or imperative logic?");
    System.out.println("f - functional");
    System.out.println("i - imperative");

    while (true) {
      System.out.print("Please select an option: ");
      char userInput = scanner.next().charAt(0);

      if (userInput == 'f') {
        logicOption = LogicOption.FUNCTIONAL;
        System.out.println("You have selected the functional logic.\n");
        break;
      } else if (userInput == 'i') {
        logicOption = LogicOption.IMPERATIVE;
        System.out.println("You have selected the imperative logic.\n");
        break;
      } else {
        System.out.println("Invalid option\n");
      }
    }
    handleOptionEnum(logicOption, propertyList);
  }

  private static void handleOptionEnum(LogicOption logicOption, List<Property> propertyList) {
    System.out.println("1. Print all properties");
    System.out.println("2. Print the number of sales");
    System.out.println("3. Print the most expensive properties");
    System.out.println("4. Print the average price of the properties");
    System.out.println(
        "5. Print the price difference of the properties between two number of rooms");
    System.out.println("6. Print the price development of the properties between two years");

    while (true) {
      System.out.print("Please select an option: ");
      int userInput = scanner.nextInt();

      if (userInput > 0 && userInput <= 6) {
        handleOption(userInput, propertyList, logicOption);
        break;
      } else {
        System.out.println("Invalid option\n");
      }
    }
  }

  private static void handleOption(
      int option, List<Property> propertyList, LogicOption logicOption) {
    switch (option) {
      case 1:
        PrintProperties.start(propertyList, logicOption);
        break;
      case 2:
        PrintNumberOfSales.start(propertyList, logicOption);
        break;
      case 3:
        PrintHighestPrice.start(propertyList, logicOption);
        break;
      case 4:
        PrintAveragePrice.start(propertyList, logicOption);
        break;
      case 5:
        PrintPriceDifference.start(propertyList, logicOption);
        break;
      case 6:
        PrintPriceDevelopment.start(propertyList, logicOption);
        break;
      default:
        System.out.println("Invalid option\n");
    }
  }
}
