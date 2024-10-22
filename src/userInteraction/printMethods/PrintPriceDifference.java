package userInteraction.printMethods;

import static userInteraction.HelperMethods.*;

import java.util.*;
import properties.Property;
import properties.enums.Rooms;
import userInteraction.LogicOption;

public class PrintPriceDifference {
  public static void start(List<Property> propertyList, LogicOption logicOption) {
    System.out.println("Here you can compare the price difference between two number of rooms.");
    System.out.println("Please enter the first number of rooms (the more expensive one).");
    Rooms roomsA = askForNumberOfRooms();
    System.out.println("Please enter the second number of rooms (the cheaper one).");
    Rooms roomsB = askForNumberOfRooms();

    if (roomsA == roomsB) {
      System.out.println("Please make sure to enter two different numbers of rooms.");
    } else {
      System.out.println("You have selected the rooms " + roomsA + " and " + roomsB + ".");
      System.out.println("Now you can filter the properties by year or district.");
      boolean filterByYear = askYesNoQuestion("Do you want to filter by year? (y/n): ");
      boolean filterByDistrict =
          !filterByYear && askYesNoQuestion("Do you want to filter by district? (y/n): ");
      printPriceDifference(
          propertyList, logicOption, roomsA, roomsB, filterByYear, filterByDistrict);
    }
  }

  public static void printPriceDifference(
      List<Property> propertyList,
      LogicOption logicOption,
      Rooms roomsA,
      Rooms roomsB,
      boolean filterByYear,
      boolean filterByDistrict) {
    if (filterByYear) {
      printPriceDifferenceByYear(propertyList, logicOption, roomsA, roomsB);
    } else if (filterByDistrict) {
      printPriceDifferenceByDistrict(propertyList, logicOption, roomsA, roomsB);
    } else {
      System.out.println("No filters were chosen, process was aborted.");
    }
  }

  public static void printPriceDifferenceByYear(
      List<Property> propertyList, LogicOption logicOption, Rooms roomsA, Rooms roomsB) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PriceDifference.priceDifferencePerYear(propertyList, roomsA, roomsB);
    } else {
      logic.imperative.PriceDifference.priceDifferencePerYear(propertyList, roomsA, roomsB);
    }
  }

  public static void printPriceDifferenceByDistrict(
      List<Property> propertyList, LogicOption logicOption, Rooms roomsA, Rooms roomsB) {
    if (logicOption == LogicOption.FUNCTIONAL) {
      logic.functional.PriceDifference.priceDifferencePerDistrict(propertyList, roomsA, roomsB);
    } else {
      logic.imperative.PriceDifference.priceDifferencePerDistrict(propertyList, roomsA, roomsB);
    }
  }
}
