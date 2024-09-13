package logic;

import immobilien.Immobilien;
import immobilien.enums.Bezirk;
import immobilien.enums.Zimmerzahl;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Functional {

  // --------------------------------- Ausgaben ---------------------------------

  // Ausgabe der Ergebnisse
  public static void immobilienAusgeben(List<Immobilien> immobilienList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null ? "Alle Immobilien:" : String.format("Die ersten %d Immobilien", limit));
    immobilienList.stream()
        .limit(limit != null ? limit : immobilienList.size())
        .forEach(
            immobilien -> {
              System.out.println("Jahr:              " + immobilien.getJahr());
              System.out.println("Bezirk Nummer:     " + immobilien.getBezirkNummer());
              System.out.println("Bezirk:            " + immobilien.getBezirk());
              System.out.println("Zimmerzahl:        " + immobilien.getZimmerzahl());
              System.out.println("Verkaufspreis CHF: " + immobilien.getVerkaufspreisChf());
              System.out.println();
            });
  }

  // Kompakte Ausgaben der Immobilien
  public static void immobilienAusgebenKompakt(List<Immobilien> immobilienList, Integer limit) {
    System.out.println("\n\n");
    System.out.println(
        limit == null
            ? "Alle Immobilien in kompakter Form"
            : String.format("Die ersten %d Immobilien in kompakter Form:", limit));

    immobilienList.stream()
        .limit(limit != null ? limit : immobilienList.size())
        .forEach(
            immobilien ->
                System.out.printf(
                    "%d %-10s %-9s %7d%n",
                    immobilien.getJahr(),
                    immobilien.getBezirk(),
                    immobilien.getZimmerzahl(),
                    immobilien.getVerkaufspreisChf()));
  }

  // --------------------------------- Verkäufe ---------------------------------

  // Anzahl der verkauften Immobilien
  public static void anzahlVerkäufe(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der verkauften Immobilien: " + immobilienList.size());
  }

  // Anzahl der verkauften Immobilien pro Bezirk sortiert nach Anzahl
  public static void anzahlVerkäufeProBezirk(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der Immobilien pro Bezirk sortiert nach Anzahl");
    immobilienList.stream()
        .collect(Collectors.groupingBy(Immobilien::getBezirk, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(
            entry ->
                System.out.printf("Bezirk: %-10s Anzahl: %d%n", entry.getKey(), entry.getValue()));
  }

  // Anzahl der verkauften Immobilien pro Jahr sortiert nach Jahr
  public static void anzahlVerkäufeProJahr(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Anzahl der verkauften Immobilien pro Jahr");
    immobilienList.stream()
        .collect(Collectors.groupingBy(Immobilien::getJahr, Collectors.counting()))
        .entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .forEach(
            entry ->
                System.out.printf(
                    "%d, Verkauften Immobilien: %d%n", entry.getKey(), entry.getValue()));
  }

  // Anzahl der verschiedenen Arten der verkauften Immobilien pro Jahr
  public static void anzahlVerkäufeProJahrZimmerzahl(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println(
        "Anzahl der verschiedenen Arten der verkauften Immobilien pro Jahr und Zimmerzahl");

    Map<Integer, Map<Zimmerzahl, Long>> data =
        immobilienList.stream()
            .collect(
                Collectors.groupingBy(
                    Immobilien::getJahr,
                    Collectors.groupingBy(Immobilien::getZimmerzahl, Collectors.counting())));

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
  public static void anzahlVerkäufeProJahrBezirk(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Gesamtzahl der Verkäufe pro Jahr und Bezirk");

    final Map<Integer, Map<Bezirk, Long>> verkäufeProJahrUndBezirk =
        immobilienList.stream()
            .collect(
                Collectors.groupingBy(
                    Immobilien::getJahr,
                    Collectors.groupingBy(Immobilien::getBezirk, Collectors.counting())));

    // Ermitteln der einzigartigen Jahre und Bezirke
    final Set<Integer> jahre = new TreeSet<>(verkäufeProJahrUndBezirk.keySet());
    final Set<Bezirk> bezirke = new TreeSet<>();
    verkäufeProJahrUndBezirk.values().forEach(map -> bezirke.addAll(map.keySet()));

    // Erstellen der Tabelle als Liste von Strings
    Stream.concat(
            Stream.of(
                "Jahr"
                    + bezirke.stream()
                        .map(bezirk -> String.format("%12s", bezirk))
                        .collect(Collectors.joining())),
            jahre.stream()
                .map(
                    jahr ->
                        String.format("%4d", jahr)
                            + bezirke.stream()
                                .map(
                                    bezirk ->
                                        String.format(
                                            "%12d",
                                            verkäufeProJahrUndBezirk
                                                .getOrDefault(jahr, Collections.emptyMap())
                                                .getOrDefault(bezirk, 0L)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }

  // --------------------------------- Preise ---------------------------------

  // Berechnung des Verkaufspreises der zehn teuersten immobilien
  public static void teuersteImmobilienPreis(List<Immobilien> immobilienList, int anzahl) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("Der Verkaufspreis der zehn teuersten Immobilien");
    immobilienList.stream()
        .map(Immobilien::getVerkaufspreisChf)
        .filter(Objects::nonNull)
        .sorted(Comparator.reverseOrder())
        .limit(anzahl)
        .toList()
        .forEach(
            preis ->
                System.out.printf("%3d: Verkaufspreis CHF: %d%n", index.getAndIncrement(), preis));
  }

  // Berechnung der zehn teuersten Immobilien
  public static void teuersteImmobilien(List<Immobilien> immobilienList, int anzahl) {
    AtomicInteger index = new AtomicInteger(1);
    System.out.println("\n\n");
    System.out.println("Die zehn teuersten Immobilien");
    immobilienList.stream()
        .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
        .sorted(Comparator.comparing(Immobilien::getVerkaufspreisChf).reversed())
        .limit(anzahl)
        .toList()
        .forEach(
            immobilie ->
                System.out.printf(
                    "%3d: Jahr: %d, Bezirk: %10s, Zimmerzahl: %9s, Verkaufspreis CHF: %d%n",
                    index.getAndIncrement(),
                    immobilie.getJahr(),
                    immobilie.getBezirk(),
                    immobilie.getZimmerzahl(),
                    immobilie.getVerkaufspreisChf()));
  }

  // --------------------------------- Durchschnittspreise ---------------------------------

  // Durchschnittspreis pro Zimmer sortiert nach Preis
  public static void durchschnittsPreisProZimmer(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Durchschnittspreis pro Zimmer");
    immobilienList.stream()
        .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
        .collect(
            Collectors.groupingBy(
                Immobilien::getZimmerzahl,
                Collectors.averagingInt(Immobilien::getVerkaufspreisChf)))
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
      List<Immobilien> immobilienList, Zimmerzahl zimmerzahl) {
    System.out.println("\n\n");
    System.out.println(
        "Durchschnittsverkaufspreis pro Jahr für " + zimmerzahl + " Zimmer-Wohnungen");

    immobilienList.stream()
        .filter(immobilien -> immobilien.getZimmerzahl() == zimmerzahl)
        .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
        .collect(
            Collectors.groupingBy(
                Immobilien::getJahr, Collectors.averagingInt(Immobilien::getVerkaufspreisChf)))
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
  public static void durchschnittspreisProJahrBezirk(List<Immobilien> immobilienList) {
    System.out.println("\n\n");
    System.out.println("Durchschnittsverkaufspreis pro Jahr und Bezirk");

    // Sammeln der Daten in einer verschachtelten Map
    final Map<Integer, Map<Bezirk, Double>> verkäufeProJahrUndBezirk =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getJahr,
                    Collectors.groupingBy(
                        Immobilien::getBezirk,
                        Collectors.averagingInt(Immobilien::getVerkaufspreisChf))));

    // Ermitteln der einzigartigen Jahre und Bezirke
    final Set<Integer> jahre = new TreeSet<>(verkäufeProJahrUndBezirk.keySet());
    final Set<Bezirk> bezirke = new TreeSet<>();
    verkäufeProJahrUndBezirk.values().forEach(map -> bezirke.addAll(map.keySet()));

    // Erstellen der Tabelle als Liste von Strings
    Stream.concat(
            Stream.of(
                "Jahr"
                    + bezirke.stream()
                        .map(bezirk -> String.format("%12s", bezirk))
                        .collect(Collectors.joining())),
            jahre.stream()
                .map(
                    jahr ->
                        String.format("%4d", jahr)
                            + bezirke.stream()
                                .map(
                                    bezirk ->
                                        String.format(
                                            "%12.2f",
                                            verkäufeProJahrUndBezirk
                                                .getOrDefault(jahr, Collections.emptyMap())
                                                .getOrDefault(bezirk, 0.0)))
                                .collect(Collectors.joining())))
        .toList()
        .forEach(System.out::println);
  }

  // --------------------------------- Preisdifferenzen ---------------------------------

  // Preisdifferenz pro Jahr zwischen zwei Zimmerzahlen sortiert nach Jahr
  public static void preisDifferenzProJahr(
      List<Immobilien> immobilienList, Zimmerzahl zimmerzahlA, Zimmerzahl zimmerzahlB) {
    System.out.println("\n\n");
    System.out.println("Preisdifferenz pro Jahr zwischen " + zimmerzahlA + " und " + zimmerzahlB);

    Map<Integer, Double> durchschnittA =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getZimmerzahl() == zimmerzahlA)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getJahr, Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

    Map<Integer, Double> durchschnittB =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getZimmerzahl() == zimmerzahlB)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getJahr, Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

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
      List<Immobilien> immobilienList, Zimmerzahl zimmerzahlA, Zimmerzahl zimmerzahlB) {
    System.out.println("\n\n");
    System.out.println("Preisdifferenz pro Jahr zwischen " + zimmerzahlA + " und " + zimmerzahlB);

    Map<Bezirk, Double> durchschnittA =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getZimmerzahl() == zimmerzahlA)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getBezirk,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

    Map<Bezirk, Double> durchschnittB =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getZimmerzahl() == zimmerzahlB)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getBezirk,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

    Set<Bezirk> bezirke = new TreeSet<>(durchschnittA.keySet());
    bezirke.addAll(durchschnittB.keySet());

    Map<Bezirk, Double> durchschnitt =
        bezirke.stream()
            .collect(
                Collectors.toMap(
                    bezirk -> bezirk,
                    bezirk ->
                        durchschnittA.getOrDefault(bezirk, 0.0)
                            - durchschnittB.getOrDefault(bezirk, 0.0)));

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
      List<Immobilien> immobilienList, int jahrA, int jahrB) {
    System.out.println("\n\n");
    System.out.println(
        "Preisentwicklung in Prozent pro Bezirk zwischen " + jahrA + " und " + jahrB);

    Map<Bezirk, Double> preisA =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getJahr() == jahrA)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getBezirk,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));
    Map<Bezirk, Double> preisB =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getJahr() == jahrB)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getBezirk,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

    Set<Bezirk> bezirke = new TreeSet<>(preisA.keySet());
    bezirke.addAll(preisB.keySet());

    bezirke.stream()
        .collect(
            Collectors.toMap(
                bezirk -> bezirk,
                bezirk -> {
                  Double preisAValue = preisA.getOrDefault(bezirk, 0.0);
                  Double preisBValue = preisB.getOrDefault(bezirk, 0.0);
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
      List<Immobilien> immobilienList, int jahrA, int jahrB) {
    System.out.println("\n\n");
    System.out.println(
        "Preisentwicklung in Prozent pro Zimmerzahl zwischen " + jahrA + " und " + jahrB);

    Map<Zimmerzahl, Double> preisA =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getJahr() == jahrA)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getZimmerzahl,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));
    Map<Zimmerzahl, Double> preisB =
        immobilienList.stream()
            .filter(immobilien -> immobilien.getJahr() == jahrB)
            .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
            .collect(
                Collectors.groupingBy(
                    Immobilien::getZimmerzahl,
                    Collectors.averagingInt(Immobilien::getVerkaufspreisChf)));

    Set<Zimmerzahl> zimmerzahlen = new TreeSet<>(preisA.keySet());
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
