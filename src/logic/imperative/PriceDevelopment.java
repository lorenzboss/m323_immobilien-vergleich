package logic.imperative;

import java.util.*;
import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

/**
 * This class is responsible for calculating the price development between properties.
 *
 * @author Leandro Aebi
 * @version 1.0
 */
public class PriceDevelopment {
  /**
   * Calculates the price development per district between two years.
   *
   * @param propertyList the list of properties
   * @param yearA the first year
   * @param yearB the second year
   */
  public static void priceDevelopmentPerDistrict(
      List<Property> propertyList, int yearA, int yearB) {
    System.out.println("\n\n");
    System.out.println(
        "Price development in percent per district between " + yearA + " and " + yearB);

    Map<District, Double> priceA = new EnumMap<>(District.class);
    Map<District, Double> priceB = new EnumMap<>(District.class);
    Map<District, Integer> countA = new EnumMap<>(District.class);
    Map<District, Integer> countB = new EnumMap<>(District.class);

    // Preise für Jahr A und Jahr B berechnen
    for (Property property : propertyList) {
      if (property.price() != null) {
        District district = property.district();
        if (property.year() == yearA) {
          priceA.put(district, priceA.getOrDefault(district, 0.0) + property.price());
          countA.put(district, countA.getOrDefault(district, 0) + 1);
        } else if (property.year() == yearB) {
          priceB.put(district, priceB.getOrDefault(district, 0.0) + property.price());
          countB.put(district, countB.getOrDefault(district, 0) + 1);
        }
      }
    }

    // Ausgabe sortieren und entwickeln
    List<Map.Entry<District, Double>> developments = new ArrayList<>();
    for (District district : District.values()) {
      double avgPriceA =
          countA.getOrDefault(district, 0) > 0 ? priceA.get(district) / countA.get(district) : 0;
      double avgPriceB =
          countB.getOrDefault(district, 0) > 0 ? priceB.get(district) / countB.get(district) : 0;

      if (avgPriceA != 0.0) {
        double development = (avgPriceB - avgPriceA) / avgPriceA * 100;
        developments.add(new AbstractMap.SimpleEntry<>(district, development));
      } else {
        developments.add(new AbstractMap.SimpleEntry<>(district, null));
      }
    }

    // Sortierung nach Preisentwicklung
    developments.sort(
        (entry1, entry2) -> {
          if (entry1.getValue() == null) return 1;
          if (entry2.getValue() == null) return -1;
          return Double.compare(entry2.getValue(), entry1.getValue());
        });

    // Ergebnisse ausgeben
    for (Map.Entry<District, Double> entry : developments) {
      if (entry.getValue() != null) {
        System.out.printf(
            "District: %10s, Price development: %6.2f%%%n", entry.getKey(), entry.getValue());
      } else {
        System.out.printf("District: %10s, Price development: no data%n", entry.getKey());
      }
    }
  }

  /**
   * Calculates the price development per number of rooms between two years.
   *
   * @param propertyList the list of properties
   * @param yearA the first year
   * @param yearB the second year
   */
  public static void priceDevelopmentPerNumberOfRooms(
      List<Property> propertyList, int yearA, int yearB) {
    System.out.println("\n\n");
    System.out.println(
        "Price development in percent per number of rooms between " + yearA + " und " + yearB);

    Map<Rooms, Double> priceA = new EnumMap<>(Rooms.class);
    Map<Rooms, Double> priceB = new EnumMap<>(Rooms.class);
    Map<Rooms, Integer> countA = new EnumMap<>(Rooms.class);
    Map<Rooms, Integer> countB = new EnumMap<>(Rooms.class);

    // Durchschnittspreise und Zählungen in einer Schleife berechnen
    for (Property property : propertyList) {
      if (property.price() != null) {
        Rooms rooms = property.rooms();
        if (property.year() == yearA) {
          priceA.put(rooms, priceA.getOrDefault(rooms, 0.0) + property.price());
          countA.put(rooms, countA.getOrDefault(rooms, 0) + 1);
        } else if (property.year() == yearB) {
          priceB.put(rooms, priceB.getOrDefault(rooms, 0.0) + property.price());
          countB.put(rooms, countB.getOrDefault(rooms, 0) + 1);
        }
      }
    }

    List<Map.Entry<Rooms, Double>> developments = new ArrayList<>();
    for (Rooms rooms : Rooms.values()) {
      double avgPriceA =
          countA.getOrDefault(rooms, 0) > 0 ? priceA.get(rooms) / countA.get(rooms) : 0;
      double avgPriceB =
          countB.getOrDefault(rooms, 0) > 0 ? priceB.get(rooms) / countB.get(rooms) : 0;

      if (avgPriceA != 0.0) {
        double development = (avgPriceB - avgPriceA) / avgPriceA * 100;
        developments.add(new AbstractMap.SimpleEntry<>(rooms, development));
      } else {
        developments.add(new AbstractMap.SimpleEntry<>(rooms, null));
      }
    }

    developments.sort(
        (entry1, entry2) -> {
          if (entry1.getValue() == null) return 1;
          if (entry2.getValue() == null) return -1;
          return Double.compare(entry2.getValue(), entry1.getValue());
        });

    for (Map.Entry<Rooms, Double> entry : developments) {
      if (entry.getValue() != null) {
        System.out.printf(
            "Rooms: %10s, Price development: %6.2f%%%n", entry.getKey(), entry.getValue());
      } else {
        System.out.printf("Rooms: %10s, Price development: no data%n", entry.getKey());
      }
    }
  }
}
