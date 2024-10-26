package logic;

import java.util.List;
import properties.Property;
import properties.enums.Rooms;

/**
 * This class executes all logic methods once for the functional and once for the imperative logic.
 *
 * @author Lorenz Boss
 * @version 1.0
 */
public class TestFunctions {

  /**
   * Executes all logic methods once for the functional and once for the imperative logic.
   *
   * @param propertyList the list of properties
   */
  public static void testFunction(List<Property> propertyList) {
    logic.functional.PropertiesInfo.propertiesInfo(propertyList, 5);
    logic.functional.PropertiesInfo.propertiesInfoCompact(propertyList, 5);
    logic.functional.NumberOfSales.numberOfSales(propertyList);
    logic.functional.NumberOfSales.numberOfSalesPerDistrict(propertyList);
    logic.functional.NumberOfSales.numberOfSalesPerRooms(propertyList);
    logic.functional.NumberOfSales.numberOfSalesPerYear(propertyList);
    logic.functional.NumberOfSales.numberOfSalesPerYearRooms(propertyList);
    logic.functional.NumberOfSales.numberOfSalesPerYearDistrict(propertyList);
    logic.functional.HighestPrice.mostExpensivePropertiesPrice(propertyList, 5);
    logic.functional.HighestPrice.mostExpensiveProperties(propertyList, 5);
    logic.functional.AveragePrice.averagePricePerNumberOfRooms(propertyList);
    logic.functional.AveragePrice.averagePricePerYear(propertyList);
    logic.functional.AveragePrice.averagePricePerYearDistrict(propertyList);
    logic.functional.PriceDifference.priceDifferencePerYear(propertyList, Rooms.TWO, Rooms.ONE);
    logic.functional.PriceDifference.priceDifferencePerDistrict(propertyList, Rooms.TWO, Rooms.ONE);
    logic.functional.PriceDevelopment.priceDevelopmentPerDistrict(propertyList, 2020, 2021);
    logic.functional.PriceDevelopment.priceDevelopmentPerNumberOfRooms(propertyList, 2013, 2021);

    System.out.println("--------------------");

    logic.imperative.PropertiesInfo.propertiesInfo(propertyList, 5);
    logic.imperative.PropertiesInfo.propertiesInfoCompact(propertyList, 5);
    logic.imperative.NumberOfSales.numberOfSales(propertyList);
    logic.imperative.NumberOfSales.numberOfSalesPerDistrict(propertyList);
    logic.imperative.NumberOfSales.numberOfSalesPerRooms(propertyList);
    logic.imperative.NumberOfSales.numberOfSalesPerYear(propertyList);
    logic.imperative.NumberOfSales.numberOfSalesPerYearRooms(propertyList);
    logic.imperative.NumberOfSales.numberOfSalesPerYearDistrict(propertyList);
    logic.imperative.HighestPrice.mostExpensivePropertiesPrice(propertyList, 5);
    logic.imperative.HighestPrice.mostExpensiveProperties(propertyList, 5);
    logic.imperative.AveragePrice.averagePricePerNumberOfRooms(propertyList);
    logic.imperative.AveragePrice.averagePricePerYear(propertyList);
    logic.imperative.AveragePrice.averagePricePerYearDistrict(propertyList);
    logic.imperative.PriceDifference.priceDifferencePerYear(propertyList, Rooms.TWO, Rooms.ONE);
    logic.imperative.PriceDifference.priceDifferencePerDistrict(propertyList, Rooms.TWO, Rooms.ONE);
    logic.imperative.PriceDevelopment.priceDevelopmentPerDistrict(propertyList, 2020, 2021);
    logic.imperative.PriceDevelopment.priceDevelopmentPerNumberOfRooms(propertyList, 2013, 2021);
  }
}
