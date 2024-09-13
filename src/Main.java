import immobilien.Immobilien;
import immobilien.JsonToImmobilien;
import immobilien.enums.Zimmerzahl;
import logic.Functional;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        final List<Immobilien> immobilienList = JsonToImmobilien.convertJsonToImmobilien("dataset.json");

        Functional.immobilienAusgeben(immobilienList, 10);
        Functional.immobilienAusgebenKompakt(immobilienList, 10);

        Functional.anzahlVerkäufe(immobilienList);
        Functional.anzahlVerkäufeProBezirk(immobilienList);
        Functional.anzahlVerkäufeProJahr(immobilienList);
        Functional.anzahlVerkäufeProJahrZimmerzahl(immobilienList);
        Functional.anzahlVerkäufeProJahrBezirk(immobilienList);

        Functional.teuersteImmobilienPreis(immobilienList, 15);
        Functional.teuersteImmobilien(immobilienList, 15);

        Functional.durchschnittsPreisProZimmer(immobilienList);
        Functional.durchschnittsPreisProJahr(immobilienList, Zimmerzahl.EINS);
        Functional.durchschnittspreisProJahrBezirk(immobilienList);

        Functional.preisDifferenzProJahr(immobilienList, Zimmerzahl.FÜNF_PLUS, Zimmerzahl.EINS);
        Functional.preisDifferenzProBezirk(immobilienList, Zimmerzahl.FÜNF_PLUS, Zimmerzahl.EINS);

        Functional.preisEntwicklungProBezirk(immobilienList, 2012, 2023);
        Functional.preisEntwicklungProZimmerzahl(immobilienList, 2012, 2023);
    }
}
