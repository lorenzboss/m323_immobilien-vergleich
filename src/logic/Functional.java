package logic;

import properties.Properties;
import properties.enums.District;
import properties.enums.Rooms;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {

  // --------------------------------- Ausgaben ---------------------------------

  // Ausgabe der Ergebnisse
  public static void immobilienAusgeben(List<Properties> propertiesList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null ? "Alle Immobilien:" : String.format("Die ersten %d Immobilien", limit));
    propertiesList.stream()
        .limit(limit != null ? limit : propertiesList.size())
        .forEach(
                properties -> {
              System.out.println("Jahr:              " + properties.getYear());
              System.out.println("Bezirk Nummer:     " + properties.getDistrictNumber());
              System.out.println("Bezirk:            " + properties.getDistrict());
              System.out.println("Zimmerzahl:        " + properties.getRooms());
              System.out.println("Verkaufspreis CHF: " + properties.getPrice());
              System.out.println();
            });
  }

  // Kompakte Ausgaben der Immobilien
  public static void immobilienAusgebenKompakt(List<Properties> propertiesList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null
            ? "Alle Immobilien in kompakter Form"
            : String.format("Die ersten %d Immobilien in kompakter Form:", limit));

    propertiesList.stream()
        .limit(limit != null ? limit : propertiesList.size())
        .forEach(
                properties ->
                System.out.printf(
                    "%d %-10s %-9s %7d%n",
                    properties.getYear(),
                    properties.getDistrict(),
                    properties.getRooms(),
                    properties.getPrice()));
  }

  // --------------------------------- Verkäufe ---------------------------------

  // Anzahl der verkauften Immobilien
  public static void anzahlVerkäufe(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der verkauften Immobilien: " + propertiesList.size());
  }

  // Anzahl der verkauften Immobilien pro Bezirk sortiert nach Anzahl
  public static void anzahlVerkäufeProBezirk(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der Immobilien pro Bezirk sortiert nach Anzahl");
    propertiesList.stream()
        .collect(Collectors.groupingBy(Properties::getDistrict, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf("Bezirk: %-10s Anzahl: %d%n", entry.getKey(), entry.getValue()));
  }

  // Anzahl der verkauften Immobilien pro Jahr sortiert nach Jahr
  public static void anzahlVerkäufeProJahr(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der verkauften Immobilien pro Jahr");
    propertiesList.stream()
        .collect(Collectors.groupingBy(Properties::getYear, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(
            entry ->
                System.out.printf(
                    "%d, Verkauften Immobilien: %d%n", entry.getKey(), entry.getValue()));
  }

  // Anzahl der verschiedenen Arten der verkauften Immobilien pro Jahr
  public static void anzahlVerkäufeProJahrZimmerzahl(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println(
        "Anzahl der verschiedenen Arten der verkauften Immobilien pro Jahr und Zimmerzahl");

    Map<Integer, Map<Rooms, Long>> data =
        propertiesList.stream()
            .collect(
                Collectors.groupingBy(
                    Properties::getYear,
                    Collectors.groupingBy(Properties::getRooms, Collectors.counting())));

    // Print header row
    System.out.print("Jahr");
    data.values().stream()
        .flatMap(map -> map.keySet().stream())
        .distinct()
        .sorted()
        .forEach(zimmerzahl -> System.out.printf("%11s", zimmerzahl));
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
                      zimmerzahl ->
                          System.out.printf("%11d", entry.getValue().getOrDefault(zimmerzahl, 0L)));
              System.out.println();
            });
  }

  // Gesamtzahl der Verkäufe pro Jahr und Bezirk
  public static void anzahlVerkäufeProJahrBezirk(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Gesamtzahl der Verkäufe pro Jahr und Bezirk");

    final Map<Integer, Map<District, Long>> verkäufeProJahrUndBezirk =
        propertiesList.stream()
            .collect(
                Collectors.groupingBy(
                    Properties::getYear,
                    Collectors.groupingBy(Properties::getDistrict, Collectors.counting())));

    // Ermitteln der einzigartigen Jahre und Bezirke
    final Set<Integer> jahre = new TreeSet<>(verkäufeProJahrUndBezirk.keySet());
    final Set<District> bezirke = new TreeSet<>();
    verkäufeProJahrUndBezirk.values().forEach(map -> bezirke.addAll(map.keySet()));

    // Erstellen der Tabelle als Liste von Strings
    Stream.concat(
            Stream.of(
                "Jahr"
                    + bezirke.stream()
                        .map(district -> String.format("%12s", district))
                        .collect(Collectors.joining())),
            jahre.stream()
                .map(
                    jahr ->
                        String.format("%4d", jahr)
                            + bezirke.stream()
                                .map(
                                        district ->
                                        String.format(
                                            "%12d",
                                            verkäufeProJahrUndBezirk
                                                .getOrDefault(jahr, Collections.emptyMap())
                                                .getOrDefault(district, 0L)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }

  // --------------------------------- Preise ---------------------------------

  // Berechnung des Verkaufspreises der zehn teuersten immobilien
  public static void teuersteImmobilienPreis(List<Properties> propertiesList, int anzahl) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("Der Verkaufspreis der zehn teuersten Immobilien");
    propertiesList.stream()
        .map(Properties::getPrice)
        .filter(Objects::nonNull)
        .sorted(Comparator.reverseOrder())
        .limit(anzahl)
        .toList()
        .forEach(
            preis ->
                System.out.printf("%3d: Verkaufspreis CHF: %d%n", index.getAndIncrement(), preis));
  }

  // Berechnung der zehn teuersten Immobilien
  public static void teuersteImmobilien(List<Properties> propertiesList, int anzahl) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("Die zehn teuersten Immobilien");
    propertiesList.stream()
        .filter(properties -> properties.getPrice() != null)
        .sorted(Comparator.comparing(Properties::getPrice).reversed())
        .limit(anzahl)
        .toList()
        .forEach(
            immobilie ->
                System.out.printf(
                    "%3d: Jahr: %d, Bezirk: %10s, Zimmerzahl: %9s, Verkaufspreis CHF: %d%n",
                    index.getAndIncrement(),
                    immobilie.getYear(),
                    immobilie.getDistrict(),
                    immobilie.getRooms(),
                    immobilie.getPrice()));
  }

  // --------------------------------- Durchschnittspreise ---------------------------------

  // Durchschnittspreis pro Zimmer sortiert nach Preis
  public static void durchschnittsPreisProZimmer(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Durchschnittspreis pro Zimmer");
    propertiesList.stream()
        .filter(properties -> properties.getPrice() != null)
        .collect(
            Collectors.groupingBy(
                Properties::getRooms,
                Collectors.averagingInt(Properties::getPrice)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "Zimmeranzahl: %10s, Durchschnittspreis: %f%n",
                    entry.getKey(), entry.getValue()));
  }

  // Durchschnittsverkaufspreis pro Jahr für eine bestimmte Zimmerzahl sortiert nach Jahr
  public static void durchschnittsPreisProJahr(
          List<Properties> propertiesList, Rooms rooms) {
    System.out.println("\n\n");
    System.out.println(
        "Durchschnittsverkaufspreis pro Jahr für " + rooms + " Zimmer-Wohnungen");

    propertiesList.stream()
        .filter(properties -> properties.getRooms() == rooms)
        .filter(properties -> properties.getPrice() != null)
        .collect(
            Collectors.groupingBy(
                Properties::getYear, Collectors.averagingInt(Properties::getPrice)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue())
        .toList()
        .forEach(
            integerDoubleEntry ->
                System.out.printf(
                    "Jahr: %d, Durchschnittsverkaufspreis: %f%n",
                    integerDoubleEntry.getKey(), integerDoubleEntry.getValue()));
  }

  // Durchschnittsverkaufspreis pro Jahr und Bezirk sortiert nach Jahr
  public static void durchschnittspreisProJahrBezirk(List<Properties> propertiesList) {
    System.out.println("\n\n");
    System.out.println("Durchschnittsverkaufspreis pro Jahr und Bezirk");

    // Sammeln der Daten in einer verschachtelten Map
    final Map<Integer, Map<District, Double>> verkäufeProJahrUndBezirk =
        propertiesList.stream()
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getYear,
                    Collectors.groupingBy(
                        Properties::getDistrict,
                        Collectors.averagingInt(Properties::getPrice))));

    // Ermitteln der einzigartigen Jahre und Bezirke
    final Set<Integer> jahre = new TreeSet<>(verkäufeProJahrUndBezirk.keySet());
    final Set<District> bezirke = new TreeSet<>();
    verkäufeProJahrUndBezirk.values().forEach(map -> bezirke.addAll(map.keySet()));

    // Erstellen der Tabelle als Liste von Strings
    Stream.concat(
            Stream.of(
                "Jahr"
                    + bezirke.stream()
                        .map(district -> String.format("%12s", district))
                        .collect(Collectors.joining())),
            jahre.stream()
                .map(
                    jahr ->
                        String.format("%4d", jahr)
                            + bezirke.stream()
                                .map(
                                        district ->
                                        String.format(
                                            "%12.2f",
                                            verkäufeProJahrUndBezirk
                                                .getOrDefault(jahr, Collections.emptyMap())
                                                .getOrDefault(district, 0.0)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }

  // --------------------------------- Preisdifferenzen ---------------------------------

  // Preisdifferenz pro Jahr zwischen zwei Zimmerzahlen sortiert nach Jahr
  public static void preisDifferenzProJahr(
          List<Properties> propertiesList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Preisdifferenz pro Jahr zwischen " + roomsA + " und " + roomsB);

    Map<Integer, Double> durchschnittA =
        propertiesList.stream()
            .filter(properties -> properties.getRooms() == roomsA)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getYear, Collectors.averagingInt(Properties::getPrice)));

    Map<Integer, Double> durchschnittB =
        propertiesList.stream()
            .filter(properties -> properties.getRooms() == roomsB)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getYear, Collectors.averagingInt(Properties::getPrice)));

    Set<Integer> jahre = new TreeSet<>(durchschnittA.keySet());
    jahre.addAll(durchschnittB.keySet());

    jahre.forEach(
        jahr -> {
          Double preisA = durchschnittA.getOrDefault(jahr, 0.0);
          Double preisB = durchschnittB.getOrDefault(jahr, 0.0);
          Double differenz = preisA - preisB;
          System.out.printf("Jahr: %d, Preisdifferenz: %9.2f%n", jahr, differenz);
        });
  }

  // Preisdifferenz pro Jahr zwischen zwei Zimmerzahlen sortiert nach Preis
  public static void preisDifferenzProBezirk(
          List<Properties> propertiesList, Rooms roomsA, Rooms roomsB) {
    System.out.println("\n\n");
    System.out.println("Preisdifferenz pro Jahr zwischen " + roomsA + " und " + roomsB);

    Map<District, Double> durchschnittA =
        propertiesList.stream()
            .filter(properties -> properties.getRooms() == roomsA)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getDistrict,
                    Collectors.averagingInt(Properties::getPrice)));

    Map<District, Double> durchschnittB =
        propertiesList.stream()
            .filter(properties -> properties.getRooms() == roomsB)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getDistrict,
                    Collectors.averagingInt(Properties::getPrice)));

    Set<District> bezirke = new TreeSet<>(durchschnittA.keySet());
    bezirke.addAll(durchschnittB.keySet());

    Map<District, Double> durchschnitt =
        bezirke.stream()
            .collect(
                Collectors.toMap(
                        district -> district,
                        district ->
                        durchschnittA.getOrDefault(district, 0.0)
                            - durchschnittB.getOrDefault(district, 0.0)));

    durchschnitt.entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "Bezirk: %10s, Preisdifferenz: %10.2f%n", entry.getKey(), entry.getValue()));
  }

  // --------------------------------- Preisentwicklung ---------------------------------

  // Preisentwicklung in Prozent pro Bezirk zwischen zwei Jahren sortiert nach Preisentwicklung
  public static void preisEntwicklungProBezirk(
          List<Properties> propertiesList, int jahrA, int jahrB) {
    System.out.println("\n\n");
    System.out.println(
        "Preisentwicklung in Prozent pro Bezirk zwischen " + jahrA + " und " + jahrB);

    Map<District, Double> preisA =
        propertiesList.stream()
            .filter(properties -> properties.getYear() == jahrA)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getDistrict,
                    Collectors.averagingInt(Properties::getPrice)));
    Map<District, Double> preisB =
        propertiesList.stream()
            .filter(properties -> properties.getYear() == jahrB)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getDistrict,
                    Collectors.averagingInt(Properties::getPrice)));

    Set<District> bezirke = new TreeSet<>(preisA.keySet());
    bezirke.addAll(preisB.keySet());

    bezirke.stream()
        .collect(
            Collectors.toMap(
                    district -> district,
                    district -> {
                  Double preisAValue = preisA.getOrDefault(district, 0.0);
                  Double preisBValue = preisB.getOrDefault(district, 0.0);
                  return preisAValue != 0.0 && preisBValue != 0.0
                      ? (preisBValue - preisAValue) / preisAValue * 100
                      : 0.0;
                }))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "Bezirk: %10s, Preisentwicklung: %6.2f%%%n", entry.getKey(), entry.getValue()));
  }

    // Preisentwicklung in Prozent pro Zimmerzahl zwischen zwei Jahren sortiert nach Preisentwicklung
  public static void preisEntwicklungProZimmerzahl(
          List<Properties> propertiesList, int jahrA, int jahrB) {
    System.out.println("\n\n");
    System.out.println(
        "Preisentwicklung in Prozent pro Zimmerzahl zwischen " + jahrA + " und " + jahrB);

    Map<Rooms, Double> preisA =
        propertiesList.stream()
            .filter(properties -> properties.getYear() == jahrA)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getRooms,
                    Collectors.averagingInt(Properties::getPrice)));
    Map<Rooms, Double> preisB =
        propertiesList.stream()
            .filter(properties -> properties.getYear() == jahrB)
            .filter(properties -> properties.getPrice() != null)
            .collect(
                Collectors.groupingBy(
                    Properties::getRooms,
                    Collectors.averagingInt(Properties::getPrice)));

    Set<Rooms> zimmerzahlen = new TreeSet<>(preisA.keySet());
    zimmerzahlen.addAll(preisB.keySet());

    zimmerzahlen.stream()
        .collect(
            Collectors.toMap(
                zimmerzahl -> zimmerzahl,
                zimmerzahl -> {
                  Double preisAValue = preisA.getOrDefault(zimmerzahl, 0.0);
                  Double preisBValue = preisB.getOrDefault(zimmerzahl, 0.0);
                  return preisAValue != 0.0 && preisBValue != 0.0
                      ? (preisBValue - preisAValue) / preisAValue * 100
                      : 0.0;
                }))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf(
                    "Zimmerzahl: %10s, Preisentwicklung: %6.2f%%%n",
                    entry.getKey(), entry.getValue()));
  }
}
