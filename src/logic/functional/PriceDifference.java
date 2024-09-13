package logic.functional;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

import java.util.*;
import java.util.stream.Collectors;

public class PriceDifference {
  public static void priceDifferencePerYear(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    Map<Integer, Double> averageA =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsA)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(Property::year, Collectors.averagingInt(Property::price)));

    Map<Integer, Double> averageB =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(Property::year, Collectors.averagingInt(Property::price)));

    Set<Integer> years = new TreeSet<>(averageA.keySet());
    years.addAll(averageB.keySet());

    years.forEach(
        year -> {
          Double priceA = averageA.getOrDefault(year, 0.0);
          Double priceB = averageB.getOrDefault(year, 0.0);
          Double difference = priceA - priceB;
          System.out.printf("Year: %d, Price difference: %9.2f%n", year, difference);
        });
  }

  public static void priceDifferencePerDistrict(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    Map<District, Double> averageA =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsA)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::district, Collectors.averagingInt(Property::price)));

    Map<District, Double> averageB =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::district, Collectors.averagingInt(Property::price)));

    Set<District> districts = new TreeSet<>(averageA.keySet());
    districts.addAll(averageB.keySet());

    Map<District, Double> average =
        districts.stream()
            .collect(
                Collectors.toMap(
                    district -> district,
                    district ->
                        averageA.getOrDefault(district, 0.0)
                            - averageB.getOrDefault(district, 0.0)));

    average.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "District: %10s, Price Difference: %10.2f%n",
                    entry.getKey(), entry.getValue()));
  }
}
