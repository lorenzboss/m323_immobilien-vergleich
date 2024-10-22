package logic.functional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import properties.Property;
import properties.enums.District;

public class AveragePrice {
  public static void averagePricePerNumberOfRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per number of rooms");
    propertyList.stream()
        .filter(property -> property.price() != null)
        .collect(Collectors.groupingBy(Property::rooms, Collectors.averagingInt(Property::price)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf("Rooms: %10s, price: %f%n", entry.getKey(), entry.getValue()));
  }

  public static void averagePricePerYear(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per year for properties.");

    propertyList.stream()
        .filter(property -> property.price() != null)
        .collect(Collectors.groupingBy(Property::year, Collectors.averagingInt(Property::price)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .toList()
        .forEach(
            integerDoubleEntry ->
                System.out.printf(
                    "year: %d, average price: %f%n",
                    integerDoubleEntry.getKey(), integerDoubleEntry.getValue()));
  }

  public static void averagePricePerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per year and district");

    final Map<Integer, Map<District, Double>> salesPerYearDistrict =
        propertyList.stream()
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::year,
                    Collectors.groupingBy(
                        Property::district, Collectors.averagingInt(Property::price))));

    final Set<Integer> years = new TreeSet<>(salesPerYearDistrict.keySet());
    final Set<District> districts = new TreeSet<>();
    salesPerYearDistrict.values().forEach(map -> districts.addAll(map.keySet()));

    Stream.concat(
            Stream.of(
                "year"
                    + districts.stream()
                        .map(district -> String.format("%12s", district))
                        .collect(Collectors.joining())),
            years.stream()
                .map(
                    year ->
                        String.format("%4d", year)
                            + districts.stream()
                                .map(
                                    district ->
                                        String.format(
                                            "%12.2f",
                                            salesPerYearDistrict
                                                .getOrDefault(year, Collections.emptyMap())
                                                .getOrDefault(district, 0.0)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }
}
