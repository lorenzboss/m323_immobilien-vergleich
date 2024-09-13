import java.io.IOException;
import java.util.List;
import logic.Functional;
import properties.JsonToProperties;
import properties.Property;
import properties.enums.Rooms;

public class Main {

  public static void main(String[] args) throws IOException {
    final List<Property> propertyList = JsonToProperties.convertJsonToProperties("dataset.json");

    Functional.printProperties(propertyList, 10);
    Functional.printPropertiesCompact(propertyList, 10);

    Functional.numberOfSales(propertyList);
    Functional.numberOfSalesPerDistrict(propertyList);
    Functional.numberOfSalesPerYear(propertyList);
    Functional.numberOfSalesPerYearRooms(propertyList);
    Functional.numberOfSalesPerYearDistrict(propertyList);

    Functional.mostExpensivePropertiesPrice(propertyList, 15);
    Functional.mostExpensiveProperties(propertyList, 15);

    Functional.averagePricePerNumberOfRooms(propertyList);
    Functional.averagePricePerYear(propertyList, Rooms.ONE);
    Functional.averagePricePerYearDistrict(propertyList);

    Functional.priceDifferencePerYear(propertyList, Rooms.FIVE_PLUS, Rooms.ONE);
    Functional.priceDifferencePerDistrict(propertyList, Rooms.FIVE_PLUS, Rooms.ONE);

    Functional.priceDevelopmentPerDistrict(propertyList, 2012, 2023);
    Functional.priceDevelopmentPerNumberOfRooms(propertyList, 2012, 2023);
  }
}
