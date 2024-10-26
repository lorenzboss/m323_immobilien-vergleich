package logic.imperative;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import properties.Property;

/**
 * This class is responsible for calculating the highest price of properties in different ways.
 *
 * @author Leandro
 * @version 1.0
 */
public class HighestPrice {

  /**
   * Calculates the price of the most expensive properties.
   *
   * @param propertyList the list of properties
   * @param limit the number of properties to print
   */
  public static void mostExpensivePropertiesPrice(List<Property> propertyList, int limit) {
    // Manuelles Herausfiltern von Properties ohne Preis
    for (int i = 0; i < propertyList.size(); i++) {
      if (propertyList.get(i).price() == null) {
        propertyList.remove(i);
        i--; // Damit wir das nächste Element nach dem Entfernen korrekt ansteuern
      }
    }

    // Sortiere die Liste nach Preis in absteigender Reihenfolge
    propertyList.sort(Comparator.comparing(Property::price).reversed());

    System.out.println("\n\n");
    System.out.println("The price of the most expensive properties");

    // Gib die ersten 'limit' Elemente aus
    AtomicInteger index = new AtomicInteger(1);
    for (int i = 0; i < Math.min(limit, propertyList.size()); i++) {
      Property property = propertyList.get(i);
      System.out.printf("%3d: selling price CHF: %d%n", index.getAndIncrement(), property.price());
    }
  }

  /**
   * Calculates the most expensive properties.
   *
   * @param propertyList the list of properties
   * @param limit the number of properties to print
   */
  public static void mostExpensiveProperties(List<Property> propertyList, int limit) {
    // Manuelles Herausfiltern von Properties ohne Preis
    for (int i = 0; i < propertyList.size(); i++) {
      if (propertyList.get(i).price() == null) {
        propertyList.remove(i);
        i--; // Damit wir das nächste Element nach dem Entfernen korrekt ansteuern
      }
    }

    // Sortiere die Liste nach Preis in absteigender Reihenfolge
    propertyList.sort(Comparator.comparing(Property::price).reversed());

    System.out.println("\n\n");
    System.out.println("The most expensive properties");

    // Header für die Tabelle ausgeben
    System.out.printf(
        "%-4s %-5s %-10s %-10s %-10s%n", "", "Year", "District", "Rooms", "Price CHF");

    AtomicInteger index = new AtomicInteger(1);
    for (int i = 0; i < Math.min(limit, propertyList.size()); i++) {
      Property property = propertyList.get(i);
      System.out.printf(
          "%-4d %-5d %-10s %-10s %-10d%n",
          index.getAndIncrement(),
          property.year(),
          property.district(),
          property.rooms(),
          property.price());
    }
  }
}
