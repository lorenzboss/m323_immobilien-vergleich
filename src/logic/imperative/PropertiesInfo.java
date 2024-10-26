package logic.imperative;

import java.util.List;
import properties.Property;

/**
 * This class is responsible for printing the properties in different formats.
 *
 * @author Leandro Aebi
 * @version 1.0
 */
public class PropertiesInfo {

  /**
   * Prints the properties in the normal format.
   *
   * @param propertyList the list of properties
   * @param limit the number of properties to print
   */
  public static void propertiesInfo(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");

    if (limit == null) {
      System.out.println("All properties:");
      limit = propertyList.size();
    } else if (limit > propertyList.size() || limit < 1) {
      System.out.println("Please enter a valid number!");
      return;
    } else {
      System.out.printf("The first %d properties%n", limit);
    }

    for (int i = 0; i < limit; i++) {
      System.out.println("Year:     " + propertyList.get(i).year());
      System.out.println("District: " + propertyList.get(i).district());
      System.out.println("Rooms:    " + propertyList.get(i).rooms());
      System.out.println("Price:    " + propertyList.get(i).price());
      System.out.println();
    }
  }

  /**
   * Prints the properties in a compact format.
   *
   * @param propertyList the list of properties
   * @param limit the number of properties to print
   */
  public static void propertiesInfoCompact(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");

    if (limit == null) {
      System.out.println("All properties:");
      limit = propertyList.size();
    } else if (limit > propertyList.size() || limit < 1) {
      System.out.println("Please enter a valid number!");
      return;
    } else {
      System.out.printf("The first %d properties in compact form:%n", limit);
    }
    System.out.printf("%s %-10s %-9s %7s%n", "Year", "Distract", "Rooms", "Price");
    for (int i = 0; i < limit; i++) {
      System.out.printf(
          "%d %-10s %-9s %7d%n",
          propertyList.get(i).year(),
          propertyList.get(i).district(),
          propertyList.get(i).rooms(),
          propertyList.get(i).price());
    }
  }
}
