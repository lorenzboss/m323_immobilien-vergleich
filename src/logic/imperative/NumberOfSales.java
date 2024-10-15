package logic.imperative;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

import java.util.*;

public class NumberOfSales {

  public static void numberOfSales(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of properties sold: " + propertyList.size());
  }

  public static void numberOfSalesPerDistrict(List<Property> propertyList) {
    System.out.println("\n\nNumber of sales per district:");

    // Zähler für jede District
    long laufenCount = 0;
    long liestalCount = 0;
    long sissachCount = 0;
    long waldenburgCount = 0;
    long arlesheimCount = 0;


    // Zählen der Verkäufe
    for (Property property : propertyList) {
      District district = property.district();

      switch (district) {
        case LAUFEN -> laufenCount++;
        case LIESTAL -> liestalCount++;
        case SISSACH -> sissachCount++;
        case WALDENBURG -> waldenburgCount++;
        case ARLESHEIM -> arlesheimCount++;

      }
    }

    // Zähler und Bezirksnamen in Arrays speichern
    long[] counts = {
      laufenCount, liestalCount, sissachCount, waldenburgCount, arlesheimCount
    };
    String[] districtNames = {"LAUFEN", "LIESTAL", "SISSACH", "WALDENBURG", "ARLESHEIM"};

    // Ausgabe aller Bezirke und ihrer Verkaufszahlen
    for (int i = 0; i < counts.length; i++) {
      if (counts[i] > 0) {
        System.out.printf("District: %-10s Sales: %d%n", districtNames[i], counts[i]);
      }
    }
  }

  public static void numberOfSalesPerYear(List<Property> propertyList) {
    System.out.println("\n\nNumber of sales per year:");

    int startYear = 2011;
    int endYear = 2022;
    int[] salesCountPerYear = new int[endYear - startYear + 1];

    // Verkäufe pro Jahr
    for (Property property : propertyList) {
      int year = property.year();

      // Jahr im erwarteten Bereich
      if (year >= startYear && year <= endYear) {
        salesCountPerYear[year - startYear]++;
      }
    }

    // Ausgabe der Verkaufszahlen pro Jahr
    for (int i = 0; i < salesCountPerYear.length; i++) {
      int year = startYear + i;
      if (salesCountPerYear[i] > 0) {
        System.out.printf("%d: Sold properties: %d%n", year, salesCountPerYear[i]);
      }
    }
  }

  public static void numberOfSalesPerYearRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and number of rooms: ");

    // Angenommener Zeitraum
    int startYear = 2010;
    int endYear = 2024;
    int yearsCount = endYear - startYear + 1;

    // Array für die Zimmeranzahl, angenommen sind 6 Zimmerkategorien
    long[][] salesCount = new long[yearsCount][Rooms.values().length];

    // Zählen der Verkäufe pro Jahr und Zimmeranzahl
    for (Property property : propertyList) {
      int year = property.year();
      Rooms rooms = property.rooms();

      if (year >= startYear && year <= endYear) {
        int yearIndex = year - startYear;
        int roomIndex = rooms.ordinal(); // Verwende den Enum-Index für die Zimmeranzahl
        salesCount[yearIndex][roomIndex]++;
      }
    }

    // Überschrift ausgeben
    System.out.print("Year");
    for (Rooms rooms : Rooms.values()) {
      System.out.printf("%11s", rooms);
    }
    System.out.println();

    // Ausgeben der Verkaufszahlen
    for (int i = 0; i < salesCount.length; i++) {
      System.out.printf("%4d", startYear + i);
      for (int j = 0; j < salesCount[i].length; j++) {
        System.out.printf("%11d", salesCount[i][j]);
      }
      System.out.println();
    }
  }

  public static void numberOfSalesPerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Number of sales per year and district: ");

    // Angenommener Zeitraum
    int startYear = 2010;
    int endYear = 2024;
    int yearsCount = endYear - startYear + 1;
    int districtCount = District.values().length;

    // Array zur Zählung der Verkäufe pro Jahr und Bezirk
    long[][] salesCount = new long[yearsCount][districtCount];

    // Verkäufe pro Jahr und Bezirk
    for (Property property : propertyList) {
      int year = property.year();
      District district = property.district();

      if (year >= startYear && year <= endYear) {
        int yearIndex = year - startYear;
        int districtIndex = district.ordinal(); // Verwende den Enum-Index für den Bezirk
        salesCount[yearIndex][districtIndex]++;
      }
    }

    System.out.print("Year");
    for (District district : District.values()) {
      System.out.printf("%12s", district);
    }
    System.out.println();


    for (int i = 0; i < salesCount.length; i++) {
      int year = startYear + i;
      System.out.printf("%4d", year);
      for (int j = 0; j < salesCount[i].length; j++) {
        System.out.printf("%12d", salesCount[i][j]);
      }
      System.out.println();
    }
  }

  public static void numberOfSalesPerRooms(List<Property> propertyList) {
    // TODO: Implement this method
  }
  }
