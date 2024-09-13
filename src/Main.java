import java.io.IOException;
import java.util.List;
import logic.functional.*;
import properties.JsonToProperties;
import properties.Property;
import properties.enums.Rooms;

public class Main {

  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    PrintProperties.printProperties(propertyList, 10);
    PrintProperties.printPropertiesCompact(propertyList, 10);

    NumberOfSales.numberOfSales(propertyList);
    NumberOfSales.numberOfSalesPerDistrict(propertyList);
    NumberOfSales.numberOfSalesPerYear(propertyList);
    NumberOfSales.numberOfSalesPerYearRooms(propertyList);
    NumberOfSales.numberOfSalesPerYearDistrict(propertyList);

    HighestPrice.mostExpensivePropertiesPrice(propertyList, 15);
    HighestPrice.mostExpensiveProperties(propertyList, 15);

    AveragePrice.averagePricePerNumberOfRooms(propertyList);
    AveragePrice.averagePricePerYear(propertyList, Rooms.ONE);
    AveragePrice.averagePricePerYearDistrict(propertyList);

    PriceDifference.priceDifferencePerYear(propertyList, Rooms.FIVE_PLUS, Rooms.ONE);
    PriceDifference.priceDifferencePerDistrict(propertyList, Rooms.FIVE_PLUS, Rooms.ONE);

    PriceDevelopment.priceDevelopmentPerDistrict(propertyList, 2012, 2023);
    PriceDevelopment.priceDevelopmentPerNumberOfRooms(propertyList, 2012, 2023);
  }
}
