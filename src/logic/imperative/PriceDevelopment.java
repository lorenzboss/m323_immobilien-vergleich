package logic.imperative;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

import java.util.*;
import java.util.stream.Collectors;

public class PriceDevelopment {
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
                Collectors.groupingBy(Property::rooms, Collectors.averagingInt(Property::price)));
    Map<Rooms, Double> priceB =
        propertyList.stream()
            .filter(property -> property.year() == yearB)
            .filter(property -> property.price() != null)
            .collect(
                Collectors.groupingBy(Property::rooms, Collectors.averagingInt(Property::price)));

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
