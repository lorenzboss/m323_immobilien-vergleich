package immobilien;

import immobilien.enums.Bezirk;
import immobilien.enums.Zimmerzahl;

public class Immobilien {
    private final int jahr;
    private final int bezirk_nummer;
    private final Bezirk bezirk;
    private final Zimmerzahl zimmerzahl;
    private final Integer verkaufspreis_chf;

    public Immobilien(int jahr, int bezirk_nummer, Bezirk bezirk, Zimmerzahl zimmerzahl, Integer verkaufspreis_chf) {
        this.jahr = jahr;
        this.bezirk_nummer = bezirk_nummer;
        this.bezirk = bezirk;
        this.zimmerzahl = zimmerzahl;
        this.verkaufspreis_chf = verkaufspreis_chf;
    }

    public int getJahr() {
        return jahr;
    }

    public int getBezirkNummer() {
        return bezirk_nummer;
    }

    public Bezirk getBezirk() {
        return bezirk;
    }

    public Zimmerzahl getZimmerzahl() {
        return zimmerzahl;
    }

    public Integer getVerkaufspreisChf() {
        return verkaufspreis_chf;
    }
}
