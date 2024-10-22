package logic.functional;

import java.util.List;
import properties.Property;

public class PropertiesInfo {
  public static void propertiesInfo(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null ? "All properties:" : String.format("The first %d properties", limit));
    propertyList.stream()
        .limit(limit != null ? limit : propertyList.size())
        .forEach(
            property -> {
              System.out.println("Year:     " + property.year());
              System.out.println("District: " + property.district());
              System.out.println("Rooms:    " + property.rooms());
              System.out.println("Price:    " + property.price());
              System.out.println();
            });
  }

  public static void propertiesInfoCompact(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null
            ? "All properties in compact form:"
            : String.format("The first %d properties in compact form:", limit));

    System.out.printf("%s %-10s %-9s %7s%n", "Year", "Distract", "Rooms", "Price");

    propertyList.stream()
        .limit(limit != null ? limit : propertyList.size())
        .forEach(
            property ->
                System.out.printf(
                    "%d %-10s %-9s %7d%n",
                    property.year(), property.district(), property.rooms(), property.price()));
  }
}
