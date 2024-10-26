package logic.imperative;

import java.util.*;
import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

/**
 * This class is responsible for calculating the price difference between properties.
 *
 * @author Leandro Aebi
 * @version 1.0
 */
public class PriceDifference {

  /**
   * Calculates the price difference per year between two room categories.
   *
   * @param propertyList the list of properties
   * @param roomsA the first room category
   * @param roomsB the second room category
   */
  public static void priceDifferencePerYear(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    // Dynamischer Jahrbereich
    int startYear = Integer.MAX_VALUE;
    int endYear = Integer.MIN_VALUE;

    // Bestimmen des minimalen und maximalen Jahres
    for (Property property : propertyList) {
      int year = property.year();
      if (year < startYear) {
        startYear = year;
      }
      if (year > endYear) {
        endYear = year;
      }
    }

    // Durchgehen der Jahre und Berechnung der Preisunterschiede
    for (int year = startYear; year <= endYear; year++) {
      double totalPriceA = calculateTotalPrice(propertyList, roomsA, year);
      double totalPriceB = calculateTotalPrice(propertyList, roomsB, year);
      int countA = countProperties(propertyList, roomsA, year);
      int countB = countProperties(propertyList, roomsB, year);

      double avgPriceA = getAveragePrice(totalPriceA, countA);
      double avgPriceB = getAveragePrice(totalPriceB, countB);
      double priceDifference = avgPriceA - avgPriceB;

      System.out.printf("Year: %d, Price difference: %9.2f%n", year, priceDifference);
    }
  }

  /**
   * Calculates the price difference per district between two room categories.
   *
   * @param propertyList the list of properties
   * @param roomsA the first room category
   * @param roomsB the second room category
   */
  public static void priceDifferencePerDistrict(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per district between " + roomsA + " and " + roomsB);

    Map<District, Double> totalPriceA = new HashMap<>();
    Map<District, Integer> countA = new HashMap<>();
    Map<District, Double> totalPriceB = new HashMap<>();
    Map<District, Integer> countB = new HashMap<>();

    // Berechnung von Gesamtpreisen und Zählungen für beide Raumkategorien
    calculateTotals(propertyList, roomsA, totalPriceA, countA);
    calculateTotals(propertyList, roomsB, totalPriceB, countB);

    List<Map.Entry<District, Double>> priceDifferences = new ArrayList<>();
    Set<District> districts = new TreeSet<>(totalPriceA.keySet());
    districts.addAll(totalPriceB.keySet());

    for (District district : districts) {
      double avgA =
          getAveragePrice(
              totalPriceA.getOrDefault(district, 0.0), countA.getOrDefault(district, 0));
      double avgB =
          getAveragePrice(
              totalPriceB.getOrDefault(district, 0.0), countB.getOrDefault(district, 0));
      double difference = avgA - avgB;

      priceDifferences.add(new AbstractMap.SimpleEntry<>(district, difference));
    }

    Collections.sort(
        priceDifferences, (entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()));

    for (Map.Entry<District, Double> entry : priceDifferences) {
      System.out.printf(
          "District: %10s, Price Difference: %10.2f%n", entry.getKey(), entry.getValue());
    }
  }

  private static double calculateTotalPrice(List<Property> propertyList, Rooms rooms, int year) {
    double totalPrice = 0.0;
    for (Property property : propertyList) {
      if (property.year() == year && property.rooms() == rooms && isPriceValid(property.price())) {
        totalPrice += property.price();
      }
    }
    return totalPrice;
  }

  private static int countProperties(List<Property> propertyList, Rooms rooms, int year) {
    int count = 0;
    for (Property property : propertyList) {
      if (property.year() == year && property.rooms() == rooms && isPriceValid(property.price())) {
        count++;
      }
    }
    return count;
  }

  private static void calculateTotals(
      List<Property> propertyList,
      Rooms rooms,
      Map<District, Double> totalPriceMap,
      Map<District, Integer> countMap) {
    for (Property property : propertyList) {
      if (property.rooms() == rooms && isPriceValid(property.price())) {
        totalPriceMap.put(
            property.district(),
            totalPriceMap.getOrDefault(property.district(), 0.0) + property.price());
        countMap.put(property.district(), countMap.getOrDefault(property.district(), 0) + 1);
      }
    }
  }

  private static boolean isPriceValid(Integer price) {
    return price != null && price > 0;
  }

  private static double getAveragePrice(double totalPrice, int count) {
    return count > 0 ? totalPrice / count : 0.0;
  }
}
