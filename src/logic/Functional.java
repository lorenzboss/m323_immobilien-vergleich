package logic;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {

  // --------------------------------- Printing ---------------------------------

  public static void printProperties(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null ? "All properties:" : String.format("The first %d properties", limit));
    propertyList.stream()
        .limit(limit != null ? limit : propertyList.size())
        .forEach(
                property -> {
              System.out.println("Year:     " + property.year());
              System.out.println("District: " + property.district());
              System.out.println("Rooms:    " + property.rooms());
              System.out.println("Price:    " + property.price());
              System.out.println();
            });
  }

  public static void printPropertiesCompact(List<Property> propertyList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null
            ? "All properties in compact form:"
            : String.format("The first %d properties in compact form:", limit));

    propertyList.stream()
        .limit(limit != null ? limit : propertyList.size())
        .forEach(
                property ->
                System.out.printf(
                    "%d %-10s %-9s %7d%n",
                    property.year(),
                    property.district(),
                    property.rooms(),
                    property.price()));
  }

  // --------------------------------- Sales ---------------------------------

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
                System.out.printf(
                    "District: %-10s Sales: %d%n", entry.getKey(), entry.getValue()));
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
                    Property::year,
                    Collectors.groupingBy(Property::rooms, Collectors.counting())));

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

  // --------------------------------- Price ---------------------------------

  public static void mostExpensivePropertiesPrice(List<Property> propertyList, int limit) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("The price of the most expensive properties");
    propertyList.stream()
        .map(Property::price)
        .filter(Objects::nonNull)
        .sorted(Comparator.reverseOrder())
        .limit(limit)
        .toList()
        .forEach(
            price ->
                System.out.printf("%3d: selling price CHF: %d%n", index.getAndIncrement(), price));
  }

  public static void mostExpensiveProperties(List<Property> propertyList, int limit) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("The most expensive properties");
    propertyList.stream()
        .filter(property -> property.price() != null)
        .sorted(Comparator.comparing(Property::price).reversed())
        .limit(limit)
        .toList()
        .forEach(
            property ->
                System.out.printf(
                    "%3d: year: %d, district: %10s, rooms: %9s, price CHF: %d%n",
                    index.getAndIncrement(),
                    property.year(),
                    property.district(),
                    property.rooms(),
                    property.price()));
  }

  // --------------------------------- Average Price ---------------------------------

  public static void averagePricePerNumberOfRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per number of rooms");
    propertyList.stream()
        .filter(property -> property.price() != null)
        .collect(
            Collectors.groupingBy(
                Property::rooms, Collectors.averagingInt(Property::price)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf("Rooms: %10s, price: %f%n", entry.getKey(), entry.getValue()));
  }

  public static void averagePricePerYear(List<Property> propertyList, Rooms rooms) {
    System.out.println("\n\n");
    System.out.println("average price per year for " + rooms + " room properties");

    propertyList.stream()
        .filter(property -> property.rooms() == rooms)
        .filter(property -> property.price() != null)
        .collect(
            Collectors.groupingBy(
                Property::year, Collectors.averagingInt(Property::price)))
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

  // --------------------------------- Price Difference ---------------------------------

  public static void priceDifferencePerYear(
          List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    Map<Integer, Double> averageA =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsA)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::year, Collectors.averagingInt(Property::price)));

    Map<Integer, Double> averageB =
        propertyList.stream()
            .filter(property -> property.rooms() == roomsB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::year, Collectors.averagingInt(Property::price)));

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

  // --------------------------------- Price Development ---------------------------------

  public static void priceDevelopmentPerDistrict(
          List<Property> propertyList, int yearA, int yearB) {
    System.out.println("\n\n");
    System.out.println(
        "Price development in percent per district between " + yearA + " and " + yearB);

    Map<District, Double> priceA =
        propertyList.stream()
            .filter(property -> property.year() == yearA)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::district, Collectors.averagingInt(Property::price)));
    Map<District, Double> priceB =
        propertyList.stream()
            .filter(property -> property.year() == yearB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::district, Collectors.averagingInt(Property::price)));

    Set<District> districts = new TreeSet<>(priceA.keySet());
    districts.addAll(priceB.keySet());

    districts.stream()
        .collect(
            Collectors.toMap(
                district -> district,
                district -> {
                  Double priceAValue = priceA.getOrDefault(district, 0.0);
                  Double priceBValue = priceB.getOrDefault(district, 0.0);
                  return priceAValue != 0.0 && priceBValue != 0.0
                      ? (priceBValue - priceAValue) / priceAValue * 100
                      : 0.0;
                }))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "District: %10s, Price development: %6.2f%%%n",
                    entry.getKey(), entry.getValue()));
  }

  public static void priceDevelopmentPerNumberOfRooms(
          List<Property> propertyList, int yearA, int yearB) {
    System.out.println("\n\n");
    System.out.println(
        "Price development in percent per number of rooms between " + yearA + " und " + yearB);

    Map<Rooms, Double> priceA =
        propertyList.stream()
            .filter(property -> property.year() == yearA)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::rooms, Collectors.averagingInt(Property::price)));
    Map<Rooms, Double> priceB =
        propertyList.stream()
            .filter(property -> property.year() == yearB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(
                    Property::rooms, Collectors.averagingInt(Property::price)));

    Set<Rooms> numberOfRooms = new TreeSet<>(priceA.keySet());
    numberOfRooms.addAll(priceB.keySet());

    numberOfRooms.stream()
        .collect(
            Collectors.toMap(
                rooms -> rooms,
                rooms -> {
                  Double priceAValue = priceA.getOrDefault(rooms, 0.0);
                  Double priceBValue = priceB.getOrDefault(rooms, 0.0);
                  return priceAValue != 0.0 && priceBValue != 0.0
                      ? (priceBValue - priceAValue) / priceAValue * 100
                      : 0.0;
                }))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "Rooms: %10s, Price development: %6.2f%%%n", entry.getKey(), entry.getValue()));
  }
}
