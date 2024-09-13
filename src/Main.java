import properties.Properties;
import properties.JsonToProperties;
import properties.enums.Rooms;
import logic.Functional;

import java.io.IOException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException {
        final List<Properties> propertiesList = JsonToProperties.convertJsonToProperties("dataset.json");

        Functional.immobilienAusgeben(propertiesList, 10);
        Functional.immobilienAusgebenKompakt(propertiesList, 10);

        Functional.anzahlVerkäufe(propertiesList);
        Functional.anzahlVerkäufeProBezirk(propertiesList);
        Functional.anzahlVerkäufeProJahr(propertiesList);
        Functional.anzahlVerkäufeProJahrZimmerzahl(propertiesList);
        Functional.anzahlVerkäufeProJahrBezirk(propertiesList);

        Functional.teuersteImmobilienPreis(propertiesList, 15);
        Functional.teuersteImmobilien(propertiesList, 15);

        Functional.durchschnittsPreisProZimmer(propertiesList);
        Functional.durchschnittsPreisProJahr(propertiesList, Rooms.ONE);
        Functional.durchschnittspreisProJahrBezirk(propertiesList);

        Functional.preisDifferenzProJahr(propertiesList, Rooms.FIVE_PLUS, Rooms.ONE);
        Functional.preisDifferenzProBezirk(propertiesList, Rooms.FIVE_PLUS, Rooms.ONE);

        Functional.preisEntwicklungProBezirk(propertiesList, 2012, 2023);
        Functional.preisEntwicklungProZimmerzahl(propertiesList, 2012, 2023);
    }
}
