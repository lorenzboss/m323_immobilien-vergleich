package logic.imperative;

import java.util.List;
import properties.Property;
import properties.enums.District;
import properties.enums.Rooms;

public class PriceDifference {

  public static void priceDifferencePerYear(
      List<Property> propertyList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Price difference per year between " + roomsA + " and " + roomsB);

    int startYear = 2011;
    int endYear = 2022;

    // Durchgehen der Jahre
    for (int year = startYear; year <= endYear; year++) {
      double totalPriceA = 0.0, totalPriceB = 0.0;
      int countA = 0, countB = 0;

      // Berechnung der Preise
      for (Property property : propertyList) {
        if (property.year() == year) {
          if (property.rooms() == roomsA && property.price() != null) {
            totalPriceA += property.price();
            countA++;
          } else if (property.rooms() == roomsB && property.price() != null) {
            totalPriceB += property.price();
            countB++;
          }
        }
      }

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

    District[] allDistricts = District.values(); // Alle möglichen Bezirke

    // Durchschnittspreises pro Bezirk
    for (District district : allDistricts) {
      double totalPriceA = 0.0;
      int countA = 0;
      double totalPriceB = 0.0;
      int countB = 0;

      // Berechnung für roomsA und roomsB
      for (Property property : propertyList) {
        if (property.district() == district) {
          if (property.rooms() == roomsA && property.price() != null) {
            totalPriceA += property.price();
            countA++;
          } else if (property.rooms() == roomsB && property.price() != null) {
            totalPriceB += property.price();
            countB++;
          }
        }
      }

      // Durchschnittspreise und der Preisdifferenz
      double avgPriceA = countA > 0 ? totalPriceA / countA : 0.0;
      double avgPriceB = countB > 0 ? totalPriceB / countB : 0.0;
      double priceDifference = avgPriceA - avgPriceB;

      if (countA > 0 || countB > 0) {
        System.out.printf("District: %10s, Price Difference: %10.2f%n", district, priceDifference);
      }
    }
  }
}
