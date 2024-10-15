package logic.imperative;

import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AveragePrice {
  public static void averagePricePerNumberOfRooms(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per number of rooms");

    double oneRoomTotal = 0;
    int oneRoomCount = 0;
    double twoRoomTotal = 0;
    int twoRoomCount = 0;
    double threeRoomTotal = 0;
    int threeRoomCount = 0;
    double fourRoomTotal = 0;
    int fourRoomCount = 0;
    double fiveRoomTotal = 0;
    int fiveRoomCount = 0;
    double totalRoomTotal = 0;
    int totalRoomCount = 0;

    // Schleife durch die Liste der Immobilien
    for (int i = 0; i < propertyList.size(); i++) {
      Property property = propertyList.get(i);

      if (property.price() != null) {
        switch (property.rooms()) {
          case ONE -> {
            oneRoomTotal += property.price();
            oneRoomCount++;
          }
          case TWO -> {
            twoRoomTotal += property.price();
            twoRoomCount++;
          }
          case THREE -> {
            threeRoomTotal += property.price();
            threeRoomCount++;
          }
          case FOUR -> {
            fourRoomTotal += property.price();
            fourRoomCount++;
          }
          case FIVE_PLUS -> {
            fiveRoomTotal += property.price();
            fiveRoomCount++;
          }
          case TOTAL -> {
            totalRoomTotal += property.price();
            totalRoomCount++;
          }
        }
      }
    }

    double averagePrice = 0;
    System.out.println("\nAverage price per number of rooms");

    if (fiveRoomCount > 0) {
      averagePrice = fiveRoomTotal / fiveRoomCount;
      System.out.printf("Rooms: FIVE_PLUS, price: %.2f\n", averagePrice);
    }
    if (fourRoomCount > 0) {
      averagePrice = fourRoomTotal / fourRoomCount;
      System.out.printf("Rooms:      FOUR, price: %.2f\n", averagePrice);
    }
    if (totalRoomCount > 0) {
      averagePrice = totalRoomTotal / totalRoomCount;
      System.out.printf("Rooms:    TOTAL, price: %.2f\n", averagePrice);
    }
    if (threeRoomCount > 0) {
      averagePrice = threeRoomTotal / threeRoomCount;
      System.out.printf("Rooms:     THREE, price: %.2f\n", averagePrice);
    }
    if (twoRoomCount > 0) {
      averagePrice = twoRoomTotal / twoRoomCount;
      System.out.printf("Rooms:       TWO, price: %.2f\n", averagePrice);
    }
    if (oneRoomCount > 0) {
      averagePrice = oneRoomTotal / oneRoomCount;
      System.out.printf("Rooms:       ONE, price: %.2f\n", averagePrice);
    }
  }

  public static void averagePricePerYear(List<Property> propertyList, Rooms rooms) {
    System.out.println("\n\n");
    System.out.println("Average price per year for " + rooms + " room properties");

    int startYear = 2011; // Startjahr
    int endYear = 2022;   // Endjahr
    double[] totalPrices = new double[endYear + 1]; // Array zur Speicherung der Gesamtsumme pro Jahr
    int[] counts = new int[endYear + 1]; // Array zur Speicherung der Anzahl der Immobilien pro Jahr

    // Schleife durch die Liste der Immobilien
    for (Property property : propertyList) {
      if (property.rooms() == rooms && property.price() != null) {
        int year = property.year();

        // Gesamtsumme und Zähler für das Jahr aktualisieren, nur wenn das Jahr im gültigen Bereich liegt
        if (year >= startYear && year <= endYear) {
          totalPrices[year] += property.price();
          counts[year]++;
        }
      }
    }

    // Durchschnittspreise berechnen und ausgeben
    for (int year = startYear; year <= endYear; year++) {
      if (counts[year] > 0) { // Nur Jahre mit Immobilien berücksichtigen
        double averagePrice = totalPrices[year] / counts[year];
        System.out.printf("Year: %d, Average Price: %.2f%n", year, averagePrice);
      } else {
        System.out.printf("Year: %d, Average Price: N/A%n", year); // Bei fehlenden Daten "N/A" ausgeben
      }
    }
  }


  public static void averagePricePerYearDistrict(List<Property> propertyList) {
    System.out.println("\n\n");
    System.out.println("Average price per year and district");

    // Maximale Jahre und die Bezirke
    int startYear = 2011; // Startjahr
    int endYear = 2022; // Endjahr
    District[] districts = District.values(); // Alle Bezirke abrufen

    // Arrays zur Speicherung der Gesamtsumme der Preise und der Zähler für jedes Jahr und jeden
    // Bezirk
    double[][] totalPrices = new double[endYear + 1][districts.length];
    int[][] counts = new int[endYear + 1][districts.length];

    // Schleife durch die Immobilien
    for (Property property : propertyList) {
      if (property.price() != null) {
        int year = property.year();
        District district = property.district();

        // Bezirk-Index finden
        int districtIndex = getDistrictIndex(districts, district);

        // Wenn das Jahr im gültigen Bereich und der Bezirk gefunden wurde
        if (year >= startYear && year <= endYear && districtIndex != -1) {
          totalPrices[year][districtIndex] += property.price(); // Preis zur Gesamtsumme hinzufügen
          counts[year][districtIndex]++; // Zähler erhöhen
        }
      }
    }

    // Kopfzeile ausgeben
    System.out.print("Year");
    for (District district : districts) {
      System.out.printf("%12s", district);
    }
    System.out.println();

    // Durchschnittspreise berechnen und ausgeben
    for (int year = startYear; year <= endYear; year++) {
      System.out.printf("%4d", year); // Jahr ausgeben
      for (int districtIndex = 0; districtIndex < districts.length; districtIndex++) {
        if (counts[year][districtIndex] > 0) {
          double averagePrice = totalPrices[year][districtIndex] / counts[year][districtIndex];
          System.out.printf("%12.2f", averagePrice); // Durchschnittspreis ausgeben
        } else {
          System.out.printf("%12s", "N/A"); // Bei fehlenden Daten "N/A" ausgeben
        }
      }
      System.out.println(); // Neue Zeile nach jedem Jahr
    }
  }

  private static int getDistrictIndex(District[] districts, District district) {
    for (int i = 0; i < districts.length; i++) {
      if (districts[i] == district) {
        return i; // Index zurückgeben, wenn Bezirk gefunden
      }
    }
    return -1;
  }
}
