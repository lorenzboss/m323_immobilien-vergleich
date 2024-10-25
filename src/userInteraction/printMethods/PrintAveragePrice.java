package userInteraction.printMethods;

import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.*;
import properties.Property;
import userInteraction.LogicOption;

/**
 * This class is responsible for printing the average price of the properties.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class PrintAveragePrice {
  public static void start(List<Property> propertyList, LogicOption logicOption) {

    System.out.println("You can filter by year, district, or rooms.");
    boolean filterByYear = askYesNoQuestion("Would you like to filter by year? (y/n): ");

    boolean filterByYearDistrict =
        filterByYear && askYesNoQuestion("Would you like to filter by district as well? (y/n): ");

    boolean filterByRooms =
        !filterByYear && askYesNoQuestion("Would you like to filter by rooms? (y/n): ");

    if (!filterByYear && !filterByRooms) {
      System.out.println("No filters were chosen, process was aborted.");
    } else {
      printAveragePrice(
          propertyList, filterByYear, filterByYearDistrict, filterByRooms, logicOption);
    }
  }

  public static void printAveragePrice(
      List<Property> propertyList,
      boolean perYear,
      boolean perYearDistrict,
      boolean perRooms,
      LogicOption logicOption) {
    if (perYearDistrict) {
      printAveragePricePerYearDistrict(propertyList, logicOption);
    } else if (perYear) {
      printAveragePricePerYear(propertyList, logicOption);
    } else if (perRooms) {
      printAveragePricePerNumberOfRooms(propertyList, logicOption);
    } else {
      throw new IllegalArgumentException("No filters were chosen, process was aborted.");
    }
  }

  public static void printAveragePricePerYearDistrict(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.AveragePrice.averagePricePerYearDistrict(propertyList);
    } else {
      logic.imperative.AveragePrice.averagePricePerYearDistrict(propertyList);
    }
  }

  public static void printAveragePricePerYear(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.AveragePrice.averagePricePerYear(propertyList);
    } else {
      logic.imperative.AveragePrice.averagePricePerYear(propertyList);
    }
  }

  public static void printAveragePricePerNumberOfRooms(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.AveragePrice.averagePricePerNumberOfRooms(propertyList);
    } else {
      logic.imperative.AveragePrice.averagePricePerNumberOfRooms(propertyList);
    }
  }
}
