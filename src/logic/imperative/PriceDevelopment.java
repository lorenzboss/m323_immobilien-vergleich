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

    District[] districts = District.values();
    double[] priceA = new double[districts.length];
    double[] priceB = new double[districts.length];
    int[] countA = new int[districts.length];
    int[] countB = new int[districts.length];

    //  Preise für Jahr A und Jahr B
    for (Property property : propertyList) {
      if (property.price() != null) {
        for (int i = 0; i < districts.length; i++) {
          if (property.district() == districts[i]) {
            if (property.year() == yearA) {
              priceA[i] += property.price();
              countA[i]++;
            } else if (property.year() == yearB) {
              priceB[i] += property.price();
              countB[i]++;
            }
          }
        }
      }
    }

    // Preisentwicklung pro Bezirk
    for (int i = 0; i < districts.length; i++) {
      if (countA[i] > 0) priceA[i] /= countA[i];
      if (countB[i] > 0) priceB[i] /= countB[i];

      if (priceA[i] != 0.0 && priceB[i] != 0.0) {
        double development = (priceB[i] - priceA[i]) / priceA[i] * 100;
        System.out.printf(
            "District: %10s, Price development: %6.2f%%%n", districts[i], development);
      } else {
        System.out.printf("District: %10s, Price development: no data%n", districts[i]);
      }
    }
  }

  public static void priceDevelopmentPerNumberOfRooms(
      List<Property> propertyList, int yearA, int yearB) {
    System.out.println("\n\n");
    System.out.println(
        "Price development in percent per number of rooms between " + yearA + " und " + yearB);

    // Arrays für die Zimmer und Durchschnittspreise in beiden Jahren
    double[] priceA = new double[Rooms.values().length];
    int[] countA = new int[Rooms.values().length];
    double[] priceB = new double[Rooms.values().length];
    int[] countB = new int[Rooms.values().length];

    // Durchschnittspreise für Jahr A und B berechnen
    for (Property property : propertyList) {
      if (property.price() != null) {
        Rooms rooms = property.rooms();
        if (property.year() == yearA) {
          priceA[rooms.ordinal()] += property.price();
          countA[rooms.ordinal()]++;
        } else if (property.year() == yearB) {
          priceB[rooms.ordinal()] += property.price();
          countB[rooms.ordinal()]++;
        }
      }
    }

    // Durchschnittsberechnung
    for (int i = 0; i < Rooms.values().length; i++) {
      if (countA[i] > 0) {
        priceA[i] /= countA[i];
      }
      if (countB[i] > 0) {
        priceB[i] /= countB[i];
      }
    }

    for (int i = 0; i < Rooms.values().length; i++) {
      if (priceA[i] != 0.0 && priceB[i] != 0.0) {
        double development = (priceB[i] - priceA[i]) / priceA[i] * 100;
        System.out.printf(
            "Rooms: %10s, Price development: %6.2f%%%n", Rooms.values()[i], development);
      } else {
        System.out.printf("Rooms: %10s, Price development: %6.2f%%%n", Rooms.values()[i], 0.0);
      }
    }
  }
  }