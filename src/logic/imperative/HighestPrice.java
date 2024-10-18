package logic.imperative;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import properties.Property;

public class HighestPrice {
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

    // Gib die ersten 'limit' Elemente aus
    AtomicInteger index = new AtomicInteger(1);
    for (int i = 0; i < Math.min(limit, propertyList.size()); i++) {
      Property property = propertyList.get(i);
      System.out.printf(
          "%3d: year: %d, district: %10s, rooms: %9s, price CHF: %d%n",
          index.getAndIncrement(),
          property.year(),
          property.district(),
          property.rooms(),
          property.price());
    }
  }
}
