package logic.imperative;



import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import properties.Property;

/**
 * @author Leandro Aebi
 *
 *
 */
public class HighestPrice {
  public static void mostExpensivePropertiesPrice(
      List<Property> propertyList, int limit, boolean onlyPrice) {
    System.out.println("\n\n");
    System.out.println("The price of the most expensive properties");
    propertyList.sort(
        (p1, p2) -> {
          if (p1.price() == null && p2.price() == null) {
            return 0;
          }
          if (p1.price() == null) {
            return 1;
          }
          if (p2.price() == null) {
            return -1;
          }
          return p2.price() - p1.price();
        });

    if (!onlyPrice) {
      System.out.printf(
          "%3s | %10s | %15s | %9s | %12s%n", "No", "Year", "District", "Rooms", "Price");
      System.out.println("------------------------------------------------------------");
    } else {
      System.out.printf("%7s", "Price \n");
    }

    for (int i = 0; i < limit && i < propertyList.size(); i++) {
      Property property = propertyList.get(i);

      if (property.price() != null && property.price() > 0 && !onlyPrice) {
        System.out.printf(
            "%3d | %10d | %15s | %10s | %12d%n",
            i + 1, property.year(), property.district(), property.rooms(), property.price());
      } else if (property.price() != null && property.price() > 0) {
        System.out.println(property.price());
      }
    }
  }
}
