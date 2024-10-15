import java.io.IOException;
import java.util.List;
import logic.functional.*;
import logic.imperative.AveragePrice;
import properties.JsonToProperties;
import properties.Property;
import properties.enums.Rooms;
import userInteraction.ConsoleApplication;

import java.io.IOException;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    //ConsoleApplication.start(propertyList);
//    AveragePrice.averagePricePerNumberOfRooms(propertyList);
//    logic.functional.AveragePrice.averagePricePerNumberOfRooms(propertyList);

//    NumberOfSales.numberOfSalesPerDistrict(propertyList);
//    NumberOfSales.numberOfSalesPerDistrict(propertyList);

//    AveragePrice.averagePricePerNumberOfRooms(propertyList);
//   AveragePrice.averagePricePerYearDistrict(propertyList);
//    AveragePrice.averagePricePerYear(propertyList, Rooms.THREE);

    logic.functional.AveragePrice.averagePricePerNumberOfRooms(propertyList);
    logic.functional.AveragePrice.averagePricePerYearDistrict(propertyList);
//    logic.functional.AveragePrice.averagePricePerYear(propertyList, Rooms.THREE);
//
//
    /**
     * Muss noch die Ausgabe richtig angepasst werden!
     */
//    HighestPrice.mostExpensivePropertiesPrice(propertyList, 5);
//    HighestPrice.mostExpensiveProperties(propertyList, 5);
//    logic.imperative.HighestPrice.mostExpensivePropertiesPrice(propertyList, 5, true);
//    logic.imperative.HighestPrice.mostExpensivePropertiesPrice(propertyList, 5, false);

//    logic.imperative.NumberOfSales.numberOfSalesPerYear(propertyList);
//    logic.imperative.NumberOfSales.numberOfSalesPerYearDistrict(propertyList);
//    logic.imperative.NumberOfSales.numberOfSales(propertyList);
//    logic.imperative.NumberOfSales.numberOfSalesPerYearRooms(propertyList);
//    logic.imperative.NumberOfSales.numberOfSalesPerDistrict(propertyList);

//    logic.imperative.PriceDevelopment.priceDevelopmentPerDistrict(propertyList, 2011, 2020);
//    PriceDevelopment.priceDevelopmentPerDistrict(propertyList, 2011, 2020);

//    logic.imperative.PriceDevelopment.priceDevelopmentPerNumberOfRooms(propertyList, 2011, 2015);
//    PriceDevelopment.priceDevelopmentPerNumberOfRooms(propertyList, 2011, 2015);

    logic.imperative.PriceDifference.priceDifferencePerYear(propertyList, Rooms.THREE, Rooms.FOUR);
    logic.imperative.PriceDifference.priceDifferencePerDistrict(propertyList, Rooms.FIVE_PLUS, Rooms.FOUR);

    PriceDifference.priceDifferencePerYear(propertyList, Rooms.THREE, Rooms.FOUR);
    PriceDifference.priceDifferencePerDistrict(propertyList, Rooms.THREE, Rooms.FOUR);


  }
}
