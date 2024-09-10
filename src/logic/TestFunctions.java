package logic;

import immobilien.Immobilien;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class TestFunctions {

    // Ausgabe der Ergebnisse
    public static void immobilienAusgeben(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Alle Immobilien");
        immobilienList.forEach(immobilien -> {
            System.out.println("Jahr:              " + immobilien.getJahr());
            System.out.println("Bezirk Nummer:     " + immobilien.getBezirkNummer());
            System.out.println("Bezirk:            " + immobilien.getBezirk());
            System.out.println("Zimmerzahl:        " + immobilien.getZimmerzahl());
            System.out.println("Verkaufspreis CHF: " + immobilien.getVerkaufspreisChf());
            System.out.println();
        });
    }

    // Kompakte Ausgaben der Immobilien
    public static void immobilienAusgebenKompakt(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Alle Immobilien in kompakter Form");
        immobilienList.forEach(immobilien -> System.out.printf("%d %-10s %-9s %7d%n",
                immobilien.getJahr(), immobilien.getBezirk(), immobilien.getZimmerzahl(), immobilien.getVerkaufspreisChf()));
    }

    // Anzahl der Datensätze
    public static void anzahlImmobilien(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Anzahl der Datensätze: " + immobilienList.size());
    }

    // Berechnung des Verkaufspreises der zehn teuersten immobilien
    public static void verkaufspreisSechzehnTeuersteImmobilien(List<Immobilien> immobilienList) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("\n\n");
        System.out.println("Der Verkaufspreis der zehn teuersten Immobilien");
        immobilienList.stream()
                .map(Immobilien::getVerkaufspreisChf)
                .filter(Objects::nonNull)
                .sorted(Comparator.reverseOrder())
                .limit(15)
                .toList().forEach(preis -> System.out.printf("%3d: Verkaufspreis CHF: %d%n", index.getAndIncrement(), preis));
    }

    // Die zehn teuersten immobilien
    public static void sechzehnTeuersteImmobilien(List<Immobilien> immobilienList) {
        AtomicInteger index = new AtomicInteger(1);
        System.out.println("\n\n");
        System.out.println("Die zehn teuersten Immobilien");
        immobilienList.stream()
                .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
                .sorted(Comparator.comparing(Immobilien::getVerkaufspreisChf).reversed())
                .limit(15)
                .toList().forEach(immobilie -> System.out.printf("%3d: Jahr: %d, Bezirk: %10s, Zimmerzahl: %9s, Verkaufspreis CHF: %d%n",
                        index.getAndIncrement(), immobilie.getJahr(), immobilie.getBezirk(), immobilie.getZimmerzahl(), immobilie.getVerkaufspreisChf()));
    }

    // Anzahl der immobilien pro Bezirk
    public static void anzahlImmobilienProBezirk(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Anzahl der Immobilien pro Bezirk");
        immobilienList.stream()
                .collect(Collectors.groupingBy(Immobilien::getBezirk, Collectors.counting()))
                .forEach((bezirk, anzahl) -> System.out.printf("Bezirk: %-10s Anzahl: %d%n", bezirk, anzahl));
    }

    // Anzahl der immobilien pro Bezirk sortiert nach Anzahl
    public static void anzahlImmobilienProBezirkSortiert(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Anzahl der Immobilien pro Bezirk sortiert nach Anzahl");
        immobilienList.stream()
                .collect(Collectors.groupingBy(Immobilien::getBezirk, Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("Bezirk: %-10s Anzahl: %d%n", entry.getKey(), entry.getValue()));

    }

    // Durchschnittspreis pro Zimmer
    public static void durchschnittspreisProZimmer(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Durchschnittspreis pro Zimmer");
        immobilienList.stream()
                .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
                .collect(Collectors.groupingBy(Immobilien::getZimmerzahl, Collectors.averagingInt(Immobilien::getVerkaufspreisChf)))
                .forEach((zimmerzahl, durchschnittspreis) -> System.out.printf("Zimmeranzahl: %10s, Durchschnittspreis: %f%n", zimmerzahl, durchschnittspreis));
    }

    // Durchschnittspreis pro Zimmer sortiert
    public static void durchschnittspreisProZimmerSortiert(List<Immobilien> immobilienList) {
        System.out.println("\n\n");
        System.out.println("Durchschnittspreis pro Zimmer");
        immobilienList.stream()
                .filter(immobilien -> immobilien.getVerkaufspreisChf() != null)
                .collect(Collectors.groupingBy(Immobilien::getZimmerzahl, Collectors.averagingInt(Immobilien::getVerkaufspreisChf)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.printf("Zimmeranzahl: %10s, Durchschnittspreis: %f%n", entry.getKey(), entry.getValue()));
    }

}
