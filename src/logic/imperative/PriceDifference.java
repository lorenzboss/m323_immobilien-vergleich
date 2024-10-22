package logic.imperative;

import java.util.*;
import java.util.stream.Collectors;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

public class PriceDifference {

  public static void priceDifferencePerYear(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    // Dynamischer Jahrbereich
    int startYear = propertyList.stream().mapToInt(Property::year).min().orElse(2011);
    int endYear = propertyList.stream().mapToInt(Property::year).max().orElse(2022);

    // Durchgehen der Jahre
    for (int year = startYear; year <= endYear; year++) {
      double totalPriceA = calculateTotalPrice(propertyList, roomsA, year);
      double totalPriceB = calculateTotalPrice(propertyList, roomsB, year);
      int countA = countProperties(propertyList, roomsA, year);
      int countB = countProperties(propertyList, roomsB, year);

      // Durchschnittspreise und Preisdifferenz
      double avgPriceA = countA > 0 ? totalPriceA / countA : 0.0;
      double avgPriceB = countB > 0 ? totalPriceB / countB : 0.0;
      double priceDifference = avgPriceA - avgPriceB;

      System.out.printf("Year: %d, Price difference: %9.2f%n", year, priceDifference);
    }
  }

  public static void priceDifferencePerDistrict(
          List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per district between " + roomsA + " and " + roomsB);

    Map<District, Double> averageA =
            propertyList.stream()
                    .filter(property -> property.rooms() == roomsA && property.price() != null) // Null-Prüfung hier
                    .collect(
                            Collectors.groupingBy(
                                    Property::district, Collectors.averagingInt(Property::price)));

    Map<District, Double> averageB =
            propertyList.stream()
                    .filter(property -> property.rooms() == roomsB && property.price() != null) // Null-Prüfung hier
                    .collect(
                            Collectors.groupingBy(
                                    Property::district, Collectors.averagingInt(Property::price)));

    Set<District> districts = new TreeSet<>(averageA.keySet());
    districts.addAll(averageB.keySet());

    Map<District, Double> priceDifferences = new HashMap<>();

    for (District district : districts) {
      double avgPriceA = averageA.getOrDefault(district, 0.0);
      double avgPriceB = averageB.getOrDefault(district, 0.0);
      double priceDifference = avgPriceA - avgPriceB;

      if (avgPriceA > 0 || avgPriceB > 0) {
        priceDifferences.put(district, priceDifference);
      }
    }

    // Ausgabe sortiert nach Preisunterschied
    priceDifferences.entrySet().stream()
            .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
            .forEach(entry ->
                    System.out.printf(
                            "District: %10s, Price Difference: %10.2f%n",
                            entry.getKey(), entry.getValue()));
  }


  private static double calculateTotalPrice(List<Property> propertyList, Rooms rooms, int year) {
    double totalPrice = 0.0;
    for (Property property : propertyList) {
      if (property.year() == year && property.rooms() == rooms && property.price() != null) {
        totalPrice += property.price();
      }
    }
    return totalPrice;
  }

  private static int countProperties(List<Property> propertyList, Rooms rooms, int year) {
    int count = 0;
    for (Property property : propertyList) {
      if (property.year() == year && property.rooms() == rooms && property.price() != null) {
        count++;
      }
    }
    return count;
  }

  // Methode zur Zählung der Properties pro Bezirk und Zimmeranzahl
  private static Map<District, Integer> countPropertiesByDistrict(
      List<Property> propertyList, Rooms rooms) {
    Map<District, Integer> countMap = new HashMap<>();
    for (Property property : propertyList) {
      if (property.rooms() == rooms) {
        countMap.put(property.district(), countMap.getOrDefault(property.district(), 0) + 1);
      }
    }
    return countMap;
  }

  // Methode zur Berechnung des Gesamtpreises pro Bezirk und Zimmeranzahl
  private static Map<District, Double> calculateTotalPriceByDistrict(
      List<Property> propertyList, Rooms rooms) {
    Map<District, Double> priceMap = new HashMap<>();
    for (Property property : propertyList) {
      if (property.rooms() == rooms && property.price() != null) {
        priceMap.put(
            property.district(),
            priceMap.getOrDefault(property.district(), 0.0) + property.price());
      }
    }
    return priceMap;
  }
}
