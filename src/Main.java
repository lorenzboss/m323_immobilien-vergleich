import immobilien.Immobilien;
import immobilien.JsonToImmobilien;
import logic.TestFunctions;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final List<Immobilien> immobilienList = JsonToImmobilien.convertJsonToImmobilien("dataset.json");

        TestFunctions.anzahlImmobilien(immobilienList);
        TestFunctions.immobilienAusgeben(immobilienList);
        TestFunctions.immobilienAusgebenKompakt(immobilienList);
        TestFunctions.verkaufspreisSechzehnTeuersteImmobilien(immobilienList);
        TestFunctions.sechzehnTeuersteImmobilien(immobilienList);
        TestFunctions.anzahlImmobilienProBezirk(immobilienList);
        TestFunctions.anzahlImmobilienProBezirkSortiert(immobilienList);
        TestFunctions.durchschnittspreisProZimmer(immobilienList);
        TestFunctions.durchschnittspreisProZimmerSortiert(immobilienList);

    }
}
