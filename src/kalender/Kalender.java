package kalender;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Kalender {
    List<Sündmus> sündmused = new ArrayList<>();
    List<Meeldetuletus> meeldetuletused = new ArrayList<>();
    List<Ülesanne> ülesanded = new ArrayList<>();
    final int[] päevadKuus2021 = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    final String[] nädalapäevad = {"esmaspäev", "teisipäev", "kolmapäev", "neljapäev", "reede", "laupäev", "pühapäev"};
    final String[] pühad = {"01/01 - Uusaasta",
            "24/02 - Eesti Vabariigi aastapäev",
            "01/05 - Kevadpüha",
            "24/06 - Jaanipäev",
            "20/08 - Taasiseseisvumispäev",
            "24/12 - Jõululaupäev"};

    //FAILI AVAMINE JA KIRJUTAMINE

    public void salvestaKalender(String failinimi) {
        //faili loomine või leidmine
        try {
            File fail = new File(failinimi);
            if (fail.createNewFile()) {
                System.out.println("Loodud uus fail: " + failinimi);
            } else {
                System.out.println("Fail nimega " + failinimi + " juba olemas, kirjutan üle.");
            }
        } catch (IOException e) {
            System.out.println("\nFaili loomisel tekkis error.");
        }
        //faili kirjutamine
        try {
            FileWriter fw = new FileWriter(failinimi);
            System.out.print("Salvestan sündmused...");
            for (Sündmus sündmus : sündmused) {
                fw.write(sündmus.toFileString() + "\n");
            }
            System.out.println(" Tehtud!");
            System.out.print("Salvestan meeldetuletused...");
            for (Meeldetuletus meeldetuletus : meeldetuletused) {
                fw.write(meeldetuletus.toFileString() + "\n");
            }
            System.out.println(" Tehtud!");
            System.out.print("Salvestan ülesanded...");
            for (Ülesanne ülesanne : ülesanded) {
                fw.write(ülesanne.toFileString() + "\n");
            }
            System.out.println(" Tehtud!");
            fw.close();
            System.out.println("Kalender edukalt salvestatud faili " + failinimi);
        } catch (IOException e) {
            System.out.println("\nFaili kirjutamisel tekkis error.");
        }
    }

    //FAILI AVAMINE JA LUGEMINE

    public void loeKalender(String failinimi) throws IOException {
        File fail = new File(failinimi);
        try (Scanner sc = new Scanner(fail, StandardCharsets.UTF_8)) {
            System.out.print("Loen faili " + failinimi + "...");
            while (sc.hasNextLine()) {
                String rida = sc.nextLine();
                String[] andmed = rida.split("; ");
                switch (andmed[0]) {
                    case "S" -> this.lisaSissekanne(new Sündmus(andmed[1], LocalDateTime.parse(andmed[2]), LocalDateTime.parse(andmed[3])));
                    case "M" -> this.lisaSissekanne(new Meeldetuletus(andmed[1], LocalDateTime.parse(andmed[2])));
                    case "Ü" -> this.lisaSissekanne(new Ülesanne(andmed[1], LocalDateTime.parse(andmed[2])));
                    default -> {}
                        //ei midagi
                }
            }
            System.out.println("Edukalt loetud fail " + failinimi);
        } catch (FileNotFoundException e) {
            System.out.println("Faili " + failinimi + " ei leitud.");
        }
    }

    //LISAMINE JA EEMDALDAMINE

    public void lisaSissekanne(Sündmus a) {
        sündmused.add(a);
    }

    public void lisaSissekanne(Meeldetuletus a) {
        meeldetuletused.add(a);
    }

    public void lisaSissekanne(Ülesanne a) {
        ülesanded.add(a);
    }

    public void eemaldaSissekanne(String toString) {
        for (int i = 0; i < sündmused.size(); i++) {
            if (toString.equals(sündmused.get(i).toString())) {
                sündmused.remove(i);
                return;
            }
        }
        for (int i = 0; i < meeldetuletused.size(); i++) {
            if (toString.equals(meeldetuletused.get(i).toString())) {
                meeldetuletused.remove(i);
                return;
            }
        }
        for (int i = 0; i < ülesanded.size(); i++) {
            if (toString.equals(ülesanded.get(i).toString())) {
                ülesanded.remove(i);
                return;
            }
        }
    }

    public void väljastaSündmused() {
        for (Sündmus sündmus : sündmused) {
            System.out.println(sündmus);
        }
    }

    public ObservableList<String> tagastaSündmused() {
        List<String> list = new ArrayList<>();
        for (Sündmus sündmus : sündmused) {
            list.add(sündmus.toString());
        }
        return FXCollections.observableList(list);
    }

    public void väljastaMeeldetuletused() {
        for (Meeldetuletus meeldetuletus : meeldetuletused) {
            System.out.println(meeldetuletus);
        }
    }

    public ObservableList<String> tagastaMeeldetuletused() {
        List<String> list = new ArrayList<>();
        for (Meeldetuletus meeldetuletus : meeldetuletused) {
            list.add(meeldetuletus.toString());
        }
        return FXCollections.observableList(list);
    }

    public void väljastaÜlesanded() {
        for (Ülesanne ülesanne : ülesanded) {
            System.out.println(ülesanne);
        }
    }

    public ObservableList<String> tagastaÜlesanded() {
        List<String> list = new ArrayList<>();
        for (Ülesanne ülesanne : ülesanded) {
            list.add(ülesanne.toString());
        }
        return FXCollections.observableList(list);
    }

    //KALENDRI ENDA MEETODID

    void väljastaKalender() {
        System.out.println("Sündmused:");
        väljastaSündmused();
        System.out.println("Meeldetuletused:");
        väljastaMeeldetuletused();
        System.out.println("Ülesanded:");
        väljastaÜlesanded();
    }

    void väljastaPüha() {
        int suvakas = (int) (Math.random() * pühad.length);
        System.out.println("Kas teadsid, et Eestis on riigipüha...");
        System.out.println(pühad[suvakas]);
    }

    void kuuNädalaPäev(String kuupäev) {
        int kuu = Integer.parseInt(kuupäev.substring(3));
        int päev = Integer.parseInt(kuupäev.substring(0, 2));
        int päevadSumma = 0;
        int aastaAlgus = 4; //mis indeksil on aasta esimene nädalapäev
        //päevade leidmine
        for (int i = 0; i < kuu - 1; i++) {
            päevadSumma = päevadSumma + päevadKuus2021[i];
        }
        päevadSumma = päevadSumma + päev;
        //nädalapäeva määramine
        //jaan 1 - reede (aastal 2021)
        int jääk = (päevadSumma + aastaAlgus - 1) % 7;
        System.out.println("Kuupäeval " + kuupäev + " on " + nädalapäevad[jääk] + ".");
    }
}
