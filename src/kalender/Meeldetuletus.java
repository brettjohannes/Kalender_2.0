package kalender;

import java.time.LocalDateTime;

public class Meeldetuletus extends Sissekanne {
    private String kirjeldus;
    private LocalDateTime mtAeg;

    //kasutaja sisend
    public Meeldetuletus(String kirjeldus, String kuupäev, String kellaaeg) {
        this.kirjeldus = kirjeldus;
        this.mtAeg = LocalDateTime.parse("2021/" + kuupäev + " - " + kellaaeg, formaat);
    }

    //failist
    public Meeldetuletus(String kirjeldus, LocalDateTime aeg) {
        this.kirjeldus = kirjeldus;
        this.mtAeg = aeg;
    }

    public String toFileString() {
        return "M; " + kirjeldus + "; " + mtAeg;
    }

    @Override
    public String toString() {
        return kirjeldus + " - " + ajaTõlgendaja(mtAeg);
    }
}
