package userInteraction.printMethods;

import static userInteraction.HelperMethods.*;

import java.util.*;
import properties.Property;
import userInteraction.LogicOption;

/**
 * This class is responsible for printing the price development between two years.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class PrintPriceDevelopment {

  /**
   * Starts the process of printing the price development between two years.
   *
   * @param propertyList the list of properties
   * @param logicOption the logic option
   */
  public static void start(List<Property> propertyList, LogicOption logicOption) {
    System.out.println("Here you can get the price development between two years (2011-2023).");
    int yearA = askForInt("Please enter the first year: ", 2023, 2011);
    int yearB = askForInt("Please enter the second year: ", 2023, 2011);

    if (yearA >= yearB) {
      System.out.println("Please make sure that the second year is greater than the first year.");
    } else {
      System.out.println("You have selected the years " + yearA + " and " + yearB + ".");
      System.out.println("Now you can filter the properties by district or number of rooms.");
      boolean filterByDistrict = askYesNoQuestion("Do you want to filter by district? (y/n): ");
      boolean filterByRooms =
          !filterByDistrict
              && askYesNoQuestion("Do you want to filter by number of rooms? (y/n): ");
      printPriceDevelopment(
          propertyList, logicOption, yearA, yearB, filterByDistrict, filterByRooms);
    }
  }

  private static void printPriceDevelopment(
      List<Property> propertyList,
      LogicOption logicOption,
      int yearA,
      int yearB,
      boolean filterByDistrict,
      boolean filterByRooms) {
    if (filterByDistrict) {
      printPriceDevelopmentByDistrict(propertyList, logicOption, yearA, yearB);
    } else if (filterByRooms) {
      printPriceDevelopmentByRooms(propertyList, logicOption, yearA, yearB);
    } else {
      System.out.println("No filters were chosen, process was aborted.");
    }
  }

  private static void printPriceDevelopmentByDistrict(
      List<Property> propertyList, LogicOption logicOption, int yearA, int yearB) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PriceDevelopment.priceDevelopmentPerDistrict(propertyList, yearA, yearB);
    } else {
      logic.imperative.PriceDevelopment.priceDevelopmentPerDistrict(propertyList, yearA, yearB);
    }
  }

  private static void printPriceDevelopmentByRooms(
      List<Property> propertyList, LogicOption logicOption, int yearA, int yearB) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PriceDevelopment.priceDevelopmentPerNumberOfRooms(
          propertyList, yearA, yearB);
    } else {
      logic.imperative.PriceDevelopment.priceDevelopmentPerNumberOfRooms(
          propertyList, yearA, yearB);
    }
  }
}
