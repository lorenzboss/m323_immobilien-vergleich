package logic.functional;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NumberOfSales {
  public static void numberOfSales(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of properties sold: " + propertyList.size());
  }

  public static void numberOfSalesPerDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per district: ");
    propertyList.stream()
        .collect(Collectors.groupingBy(Property::district, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf("District: %-10s Sales: %d%n", entry.getKey(), entry.getValue()));
  }

  public static void numberOfSalesPerYear(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year: ");
    propertyList.stream()
        .collect(Collectors.groupingBy(Property::year, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(
            entry ->
                System.out.printf("%d: Sold properties: %d%n", entry.getKey(), entry.getValue()));
  }

  public static void numberOfSalesPerYearRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and number of rooms: ");

    Map<Integer, Map<Rooms, Long>> data =
        propertyList.stream()
            .collect(
                Collectors.groupingBy(
                    Property::year, Collectors.groupingBy(Property::rooms, Collectors.counting())));

    // Print header row
    System.out.print("Year");
    data.values().stream()
        .flatMap(map -> map.keySet().stream())
        .distinct()
        .sorted()
        .forEach(rooms -> System.out.printf("%11s", rooms));
    System.out.println();

    // Print each row
    data.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(
            entry -> {
              System.out.printf("%4d", entry.getKey());
              data.values().stream()
                  .flatMap(map -> map.keySet().stream())
                  .distinct()
                  .sorted()
                  .forEach(
                      rooms -> System.out.printf("%11d", entry.getValue().getOrDefault(rooms, 0L)));
              System.out.println();
            });
  }

  public static void numberOfSalesPerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and district: ");

    final Map<Integer, Map<District, Long>> salesPerYearDistrict =
        propertyList.stream()
            .collect(
                Collectors.groupingBy(
                    Property::year,
                    Collectors.groupingBy(Property::district, Collectors.counting())));

    final Set<Integer> years = new TreeSet<>(salesPerYearDistrict.keySet());
    final Set<District> districts = new TreeSet<>();
    salesPerYearDistrict.values().forEach(map -> districts.addAll(map.keySet()));

    Stream.concat(
            Stream.of(
                "Year"
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
                                            "%12d",
                                            salesPerYearDistrict
                                                .getOrDefault(year, Collections.emptyMap())
                                                .getOrDefault(district, 0L)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }
}
