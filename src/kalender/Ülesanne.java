package kalender;

import java.time.LocalDateTime;

public class Ülesanne extends Sissekanne {
    private String kirjeldus;
    private LocalDateTime ülAeg;

    //kasutaja sisend
    public Ülesanne(String kirjeldus, String kuupäev, String kellaaeg) {
        this.kirjeldus = kirjeldus;
        this.ülAeg = LocalDateTime.parse("2021/" + kuupäev + " - " + kellaaeg, formaat);
    }

    //failist
    public Ülesanne(String kirjeldus, LocalDateTime aeg) {
        this.kirjeldus = kirjeldus;
        this.ülAeg = aeg;
    }

    public String kauaVeelÜlLõpuni() {
        LocalDateTime praegu = LocalDateTime.now();
        String erand = "0 päeva, 0 tundi, 0 minutit";

        //mitu päeva
        int jäänudPäevi = ülAeg.getDayOfYear() - praegu.getDayOfYear();
        //möödunud tähtaeg 1
        if (jäänudPäevi < 0) return erand;

        //mitu tundi
        int jäänudTunde = ülAeg.getHour() - praegu.getHour();
        //möödunud tähtaeg 2
        if (jäänudPäevi == 0 && jäänudTunde < 0) return erand;
        //tunnid korrektsioon
        if (jäänudTunde < 0) {
            jäänudTunde = 24 + jäänudTunde; //liita sest jäänudTunde on neg.
            jäänudPäevi--;
        }

        //mitu minutit
        int jäänudMinuteid = ülAeg.getMinute() - praegu.getMinute();
        //möödunud tähtaeg 3
        if (jäänudPäevi == 0 && jäänudTunde == 0 && jäänudMinuteid < 0) return erand;
        //minutid korrektsioon
        if (jäänudMinuteid < 0) {
            jäänudMinuteid = 60 + jäänudMinuteid; //liita sest neg.
            jäänudTunde = jäänudTunde - 1;
            if (jäänudTunde < 0) {
                jäänudTunde = 24 + jäänudTunde; //liita sest jäänudTunde on neg.
                jäänudPäevi--;
            }
        }
        //tulemuse loomine
        //koos keelelise korrektsusega arvestamisega
        String päevad;
        if (jäänudPäevi == 1) {
            päevad = "1 päev";
        } else {
            päevad = jäänudPäevi + " päeva";
        }
        String tunnid;
        if (jäänudTunde == 1) {
            tunnid = "1 tund";
        } else {
            tunnid = jäänudTunde + " tundi";
        }
        String minutid;
        if (jäänudMinuteid == 1) {
            minutid = "1 minut";
        } else {
            minutid = jäänudMinuteid + " minutit";
        }
        return päevad + ", " + tunnid + ", " + minutid;
    }

    public String toFileString() {
        return "Ü; " + kirjeldus + "; " + ülAeg;
    }

    @Override
    public String toString() {
        return kirjeldus + " - " + ajaTõlgendaja(ülAeg) + ", aega jäänud " + this.kauaVeelÜlLõpuni() + ".";
    }
}
