package kalender;

import java.time.LocalDateTime;

public class Sündmus extends Sissekanne {
    private String kirjeldus;
    private LocalDateTime sdAegAlg;
    private LocalDateTime sdAegLõpp;

    //kasutaja sisend
    public Sündmus(String kirjeldus, String kuupäevAlgus, String kellaaegAlgus, String kuupäevLõpp, String kellaaegLõpp) {
        this.kirjeldus = kirjeldus;
        this.sdAegAlg = LocalDateTime.parse("2021/" + kuupäevAlgus + " - " + kellaaegAlgus, formaat);
        this.sdAegLõpp = LocalDateTime.parse("2021/" + kuupäevLõpp + " - " + kellaaegLõpp, formaat);
    }

    //failist
    public Sündmus(String kirjeldus, LocalDateTime algus, LocalDateTime lõpp) {
        this.kirjeldus = kirjeldus;
        this.sdAegAlg = algus;
        this.sdAegLõpp = lõpp;
    }

    public String toFileString() {
        return "S; " + kirjeldus + "; " + sdAegAlg + "; " + sdAegLõpp;
    }

    @Override
    public String toString() {
        return kirjeldus + " - " + ajaTõlgendaja(sdAegAlg) + " kuni " + ajaTõlgendaja(sdAegLõpp);
    }
}
