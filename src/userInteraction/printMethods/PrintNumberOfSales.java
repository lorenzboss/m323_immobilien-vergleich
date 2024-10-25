package userInteraction.printMethods;

import static userInteraction.HelperMethods.askYesNoQuestion;

import java.util.*;
import properties.Property;
import userInteraction.LogicOption;

/**
 * This class is responsible for printing the number of sales.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class PrintNumberOfSales {
  public static void start(List<Property> propertyList, LogicOption logicOption) {

    System.out.println("You can filter by year, district, or rooms.");
    boolean filterByYear = askYesNoQuestion("Would you like to filter by year? (y/n): ");

    boolean filterByDistrict = askYesNoQuestion("Would you like to filter by district? (y/n): ");

    boolean filterByRooms =
        !filterByDistrict && askYesNoQuestion("Would you like to filter by rooms? (y/n): ");

    if (!filterByYear && !filterByDistrict && !filterByRooms) {
      System.out.println("No filters were chosen, displaying unfiltered data.");
    }

    printNumberOfSales(propertyList, filterByYear, filterByDistrict, filterByRooms, logicOption);
  }

  private static void printNumberOfSales(
      List<Property> propertyList,
      boolean perYear,
      boolean perDistrict,
      boolean perRooms,
      LogicOption logicOption) {
    if (perYear) {
      printNumberOfSalesPerYear(propertyList, perDistrict, perRooms, logicOption);
    } else if (perDistrict) {
      printNumberOfSalesPerDistrict(propertyList, logicOption);
    } else if (perRooms) {
      printNumberOfSalesPerRooms(propertyList, logicOption);
    } else {
      printNumberOfSalesNoFilters(propertyList, logicOption);
    }
  }

  private static void printNumberOfSalesPerYear(
      List<Property> propertyList, boolean perDistrict, boolean perRooms, LogicOption logicOption) {
    if (perDistrict) {
      logic.functional.NumberOfSales.numberOfSalesPerYearDistrict(propertyList);
    } else if (perRooms) {
      logic.functional.NumberOfSales.numberOfSalesPerYearRooms(propertyList);
    } else {
      logic.functional.NumberOfSales.numberOfSalesPerYear(propertyList);
    }
  }

  private static void printNumberOfSalesPerDistrict(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.NumberOfSales.numberOfSalesPerDistrict(propertyList);
    } else {
      logic.imperative.NumberOfSales.numberOfSalesPerDistrict(propertyList);
    }
  }

  private static void printNumberOfSalesPerRooms(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.NumberOfSales.numberOfSalesPerRooms(propertyList);
    } else {
      logic.imperative.NumberOfSales.numberOfSalesPerRooms(propertyList);
    }
  }

  private static void printNumberOfSalesNoFilters(
      List<Property> propertyList, LogicOption logicOption) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.NumberOfSales.numberOfSales(propertyList);
    } else {
      logic.imperative.NumberOfSales.numberOfSales(propertyList);
    }
  }
}
