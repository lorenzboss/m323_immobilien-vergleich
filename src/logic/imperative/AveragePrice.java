package logic.imperative;

import java.util.*;
import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

/**
 * This class is responsible for calculating the average price of properties.
 *
 * @author Leandro
 * @version 1.0
 */
public class AveragePrice {

  /**
   * Calculates the average price per number of rooms.
   *
   * @param propertyList the list of properties
   */
  public static void averagePricePerNumberOfRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per number of rooms");

    // Eine Map zur Speicherung der Gesamtsummen und Zählungen der Preise pro Zimmeranzahl
    Map<Rooms, Double> totalPriceMap = new HashMap<>();
    Map<Rooms, Integer> countMap = new HashMap<>();

    // Iteriere durch die Properties, um die Preise zu summieren und die Zählungen zu erhöhen
    for (Property property : propertyList) {
      if (property.price() != null) {
        Rooms rooms = property.rooms();
        totalPriceMap.put(rooms, totalPriceMap.getOrDefault(rooms, 0.0) + property.price());
        countMap.put(rooms, countMap.getOrDefault(rooms, 0) + 1);
      }
    }

    // Berechne den Durchschnitt und speichere die Ergebnisse in einer Liste
    List<Map.Entry<Rooms, Double>> averagePriceList = new ArrayList<>();
    for (Rooms rooms : totalPriceMap.keySet()) {
      double averagePrice = totalPriceMap.get(rooms) / countMap.get(rooms);
      averagePriceList.add(new AbstractMap.SimpleEntry<>(rooms, averagePrice));
    }

    // Sortiere die Ergebnisse nach Durchschnittspreis in absteigender Reihenfolge
    averagePriceList.sort((e1, e2) -> Double.compare(e2.getValue(), e1.getValue()));

    // Gib die Ergebnisse aus
    for (Map.Entry<Rooms, Double> entry : averagePriceList) {
      System.out.printf("Rooms: %9s, price: %.2f%n", entry.getKey(), entry.getValue());
    }
  }

  /**
   * Calculates the average price per year.
   *
   * @param propertyList the list of properties
   */
  public static void averagePricePerYear(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per year for properties.");

    // Eine Map zur Speicherung der Gesamtsummen und Zählungen der Preise pro Jahr
    Map<Integer, Double> totalPriceMap = new HashMap<>();
    Map<Integer, Integer> countMap = new HashMap<>();

    // Iteriere durch die Properties, um die Preise zu summieren und die Zählungen zu erhöhen
    for (Property property : propertyList) {
      if (property.price() != null) {
        int year = property.year();
        totalPriceMap.put(year, totalPriceMap.getOrDefault(year, 0.0) + property.price());
        countMap.put(year, countMap.getOrDefault(year, 0) + 1);
      }
    }

    // Sortiere die Jahre
    List<Integer> sortedYears = new ArrayList<>(totalPriceMap.keySet());
    Collections.sort(sortedYears);

    // Gib die Durchschnittspreise aus
    for (int year : sortedYears) {
      double averagePrice = totalPriceMap.get(year) / countMap.get(year);
      System.out.printf("year: %d, average price: %.2f%n", year, averagePrice);
    }
  }

  /**
   * Calculates the average price per year and district.
   *
   * @param propertyList the list of properties
   */
  public static void averagePricePerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per year and district");

    // Eine Map zur Speicherung der Gesamtsummen und Zählungen der Preise pro Jahr und Bezirk
    Map<Integer, Map<District, Double>> salesPerYearDistrict = new HashMap<>();
    Map<Integer, Map<District, Integer>> countMap = new HashMap<>();

    // Iteriere durch die Properties, um die Preise zu summieren und die Zählungen zu erhöhen
    for (Property property : propertyList) {
      if (property.price() != null) {
        int year = property.year();
        District district = property.district();

        salesPerYearDistrict.putIfAbsent(year, new HashMap<>());
        countMap.putIfAbsent(year, new HashMap<>());

        salesPerYearDistrict
            .get(year)
            .put(
                district,
                salesPerYearDistrict.get(year).getOrDefault(district, 0.0) + property.price());
        countMap.get(year).put(district, countMap.get(year).getOrDefault(district, 0) + 1);
      }
    }

    // Header ausgeben
    System.out.print("year");
    Set<District> districts = new TreeSet<>(Comparator.comparingInt(District::getSortOrder));
    for (Map<District, Double> map : salesPerYearDistrict.values()) {
      districts.addAll(map.keySet());
    }

    // Bezirke sortieren und ausgeben
    List<District> sortedDistricts = new ArrayList<>(districts);
    for (District district : sortedDistricts) {
      System.out.printf("%12s", district);
    }
    System.out.println();

    // Berechne und gib die Durchschnittspreise aus
    List<Integer> sortedYears = new ArrayList<>(salesPerYearDistrict.keySet());
    Collections.sort(sortedYears); // Jahre sortieren

    for (int year : sortedYears) {
      System.out.printf("%4d", year);
      for (District district : sortedDistricts) {
        double averagePrice =
            salesPerYearDistrict.get(year).getOrDefault(district, 0.0)
                / countMap.get(year).getOrDefault(district, 1);
        System.out.printf("%12.2f", averagePrice);
      }
      System.out.println();
    }
  }
}
