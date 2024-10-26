package logic.imperative;

import java.util.*;
import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

/**
 * This class is responsible for calculating the number of sales in different ways.
 *
 * @author Leandro Aebi
 * @version 1.0
 */
public class NumberOfSales {

  /**
   * Calculates the number of sales.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSales(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of properties sold: " + propertyList.size());
  }

  /**
   * Calculates the number of sales per district.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSalesPerDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per district: ");

    // Eine Map zur Speicherung der Verkaufsanzahl pro Bezirk
    Map<District, Integer> districtSalesMap = new HashMap<>();

    // Durch die Liste der Properties iterieren und die Verkaufszahlen pro Bezirk sammeln
    for (Property property : propertyList) {
      District district = property.district();
      if (districtSalesMap.containsKey(district)) {
        districtSalesMap.put(district, districtSalesMap.get(district) + 1);
      } else {
        districtSalesMap.put(district, 1);
      }
    }

    // Die Einträge der Map in eine Liste umwandeln und sortieren (absteigend nach Verkaufszahlen)
    List<Map.Entry<District, Integer>> entryList = new ArrayList<>(districtSalesMap.entrySet());
    entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

    // Die sortierte Liste der Bezirke und Verkaufszahlen ausgeben
    for (Map.Entry<District, Integer> entry : entryList) {
      System.out.printf("District: %-10s Sales: %d%n", entry.getKey(), entry.getValue());
    }
  }

  /**
   * Calculates the number of sales per rooms.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSalesPerRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per rooms: ");

    // Eine Map zur Speicherung der Verkaufsanzahl pro Anzahl der Zimmer
    Map<Rooms, Integer> roomsSalesMap = new HashMap<>();

    // Durch die Liste der Properties iterieren und die Verkaufszahlen pro Anzahl der Zimmer sammeln
    for (Property property : propertyList) {
      Rooms rooms = property.rooms();
      roomsSalesMap.put(rooms, roomsSalesMap.getOrDefault(rooms, 0) + 1);
    }

    // Die Einträge der Map in eine Liste umwandeln und sortieren (nach Zimmer in
    // getSortOrder-Reihenfolge)
    List<Map.Entry<Rooms, Integer>> entryList = new ArrayList<>(roomsSalesMap.entrySet());
    entryList.sort(Map.Entry.comparingByKey(Comparator.comparingInt(Rooms::getSortOrder)));

    // Die sortierte Liste der Zimmeranzahl und Verkaufszahlen ausgeben
    for (Map.Entry<Rooms, Integer> entry : entryList) {
      System.out.printf("Rooms: %-10s Sales: %d%n", entry.getKey(), entry.getValue());
    }
  }

  /**
   * Calculates the number of sales per year.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSalesPerYear(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year: ");

    // Eine Map zur Speicherung der Verkaufsanzahl pro Jahr
    Map<Integer, Integer> yearSalesMap = new HashMap<>();

    // Durch die Liste der Properties iterieren und die Verkaufszahlen pro Jahr sammeln
    for (Property property : propertyList) {
      int year = property.year();
      yearSalesMap.put(year, yearSalesMap.getOrDefault(year, 0) + 1);
    }

    // Die Einträge der Map in eine Liste umwandeln und nach Jahr sortieren
    List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(yearSalesMap.entrySet());
    entryList.sort(Map.Entry.comparingByKey());

    // Die sortierte Liste der Jahre und Verkaufszahlen ausgeben
    for (Map.Entry<Integer, Integer> entry : entryList) {
      System.out.printf("%d: Sold properties: %d%n", entry.getKey(), entry.getValue());
    }
  }

  /**
   * Calculates the number of sales per year and rooms.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSalesPerYearRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and number of rooms: ");

    // Eine Map zur Speicherung der Verkaufsanzahl pro Jahr und Zimmeranzahl
    Map<Integer, Map<Rooms, Integer>> salesPerYearRooms = new HashMap<>();

    // Durch die Liste der Properties iterieren und die Verkaufszahlen pro Jahr und Zimmeranzahl
    // sammeln
    for (Property property : propertyList) {
      int year = property.year();
      Rooms rooms = property.rooms();

      salesPerYearRooms.putIfAbsent(year, new HashMap<>());
      Map<Rooms, Integer> roomsMap = salesPerYearRooms.get(year);
      roomsMap.put(rooms, roomsMap.getOrDefault(rooms, 0) + 1);
    }

    // Print header row
    System.out.print("Year");
    Set<Rooms> roomsSet = new TreeSet<>(Comparator.comparingInt(Rooms::getSortOrder));
    for (Map<Rooms, Integer> map : salesPerYearRooms.values()) {
      roomsSet.addAll(map.keySet());
    }
    for (Rooms rooms : roomsSet) {
      System.out.printf("%11s", rooms);
    }
    System.out.println();

    // Nach Jahr sortieren und die Zeilen ausgeben
    List<Integer> sortedYears = new ArrayList<>(salesPerYearRooms.keySet());
    Collections.sort(sortedYears); // Jahre sortieren

    for (Integer year : sortedYears) {
      System.out.printf("%4d", year);
      Map<Rooms, Integer> roomsMap = salesPerYearRooms.get(year);
      for (Rooms rooms : roomsSet) {
        System.out.printf("%11d", roomsMap.getOrDefault(rooms, 0));
      }
      System.out.println();
    }
  }

  /**
   * Calculates the number of sales per year and district.
   *
   * @param propertyList the list of properties
   */
  public static void numberOfSalesPerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and district: ");

    // Eine Map zur Speicherung der Verkaufsanzahl pro Jahr und Bezirk
    Map<Integer, Map<District, Integer>> salesPerYearDistrict = new HashMap<>();

    // Durch die Liste der Properties iterieren und die Verkaufszahlen pro Jahr und Bezirk sammeln
    for (Property property : propertyList) {
      int year = property.year();
      District district = property.district();

      salesPerYearDistrict.putIfAbsent(year, new HashMap<>());
      Map<District, Integer> districtMap = salesPerYearDistrict.get(year);
      districtMap.put(district, districtMap.getOrDefault(district, 0) + 1);
    }

    // Header ausgeben
    System.out.print("Year");
    Set<District> districts = new TreeSet<>(Comparator.comparingInt(District::getSortOrder));
    for (Map<District, Integer> map : salesPerYearDistrict.values()) {
      districts.addAll(map.keySet());
    }
    for (District district : districts) {
      System.out.printf("%12s", district);
    }
    System.out.println();

    // Die Jahre sortieren und die Zeilen nach sortierten Jahren und Bezirken ausgeben
    List<Integer> sortedYears = new ArrayList<>(salesPerYearDistrict.keySet());
    Collections.sort(sortedYears); // Jahre sortieren

    for (Integer year : sortedYears) {
      System.out.printf("%4d", year);
      Map<District, Integer> districtMap = salesPerYearDistrict.get(year);
      for (District district : districts) {
        System.out.printf("%12d", districtMap.getOrDefault(district, 0));
      }
      System.out.println();
    }
  }
}
