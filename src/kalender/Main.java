package kalender;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Kalender kalender = new Kalender();
        kalender.loeKalender("kalender.txt");
        Scanner scan = new Scanner(System.in);
        System.out.println("Kalender Extraordinaire!");
        System.out.println("Valikud sisestage kategoorianumbrina (nt 1).");
        System.out.println();
        algus:
        while (true) {
            System.out.println("""
                    Mida soovite vaadata?
                    1. Sündmused
                    2. Meeldetuletused
                    3. Ülesanded
                    4. Muu
                    5. Sulge""");
            int kategooria = Integer.parseInt(scan.nextLine());
            System.out.println();
            switch (kategooria) {
                case 1 -> {
                    System.out.println("""
                            Mida soovite teha?
                            1. Lisa sündmus
                            2. Eemalda sündmus
                            3. Väljasta sündmused
                            4. Tagasi""");
                    int soov = Integer.parseInt(scan.nextLine());
                    System.out.println();
                    switch (soov) {
                        case 1 -> {
                            while (true) {
                                try {
                                    System.out.println("Sisesta sündmuse kirjeldus (ärge palun kasutage semikoolonit):");
                                    String kirjeldus = scan.nextLine();
                                    if (kirjeldus.contains(";")) {
                                        System.out.println("Mida ma just semikooloni kohta ütlesin? Proovi uuesti.");
                                        continue;
                                    }
                                    System.out.println("Sisesta sündmuse alguskuupäev (formaadis dd/mm):");
                                    String alguskuupäev = scan.nextLine();
                                    System.out.println("Sisesta sündmuse alguskellaaeg (formaadis hh:mm):");
                                    String alguskellaaeg = scan.nextLine();
                                    System.out.println("Sisesta sündmuse lõppkuupäev (formaadis dd/mm):");
                                    String lõppkuupäev = scan.nextLine();
                                    System.out.println("Sisesta sündmuse lõppkellaaeg (formaadis hh:mm):");
                                    String lõppkellaaeg = scan.nextLine();
                                    Sündmus sündmus = new Sündmus(kirjeldus, alguskuupäev, alguskellaaeg, lõppkuupäev, lõppkellaaeg);
                                    kalender.lisaSissekanne(sündmus);
                                    System.out.println("Sündmus lisatud.");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 2 -> {
                            while (true) {
                                try {
                                    System.out.println("Mitmenda sündmuse soovid eemaldada? (Sisesta sündmuse järjekorranumber)");
                                    kalender.väljastaSündmused();
                                    int number = Integer.parseInt(scan.nextLine());
                                    System.out.println("Sündmus eemaldatud");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 3 -> kalender.väljastaSündmused();
                        default -> System.out.println("Ei saanud aru, proovi uuesti.");
                    }
                }
                case 2 -> {
                    System.out.println("""
                            Mida soovite teha?
                            1. Lisa meeldetuletus
                            2. Eemalda meeldetuletus
                            3. Väljasta meeldetuletused
                            4. Tagasi""");
                    int soov = Integer.parseInt(scan.nextLine());
                    switch (soov) {
                        case 1 -> {
                            while (true) {
                                try {
                                    System.out.println("Sisesta meeldetuletuse kirjeldus (ärge palun kasutage semikoolonit):");
                                    String kirjeldus = scan.nextLine();
                                    if (kirjeldus.contains(";")) {
                                        System.out.println("Mida ma just semikooloni kohta ütlesin? Proovi uuesti.");
                                        continue;
                                    }
                                    System.out.println("Sisesta kuupäev (formaadis dd/mm):");
                                    String kuupäev = scan.nextLine();
                                    System.out.println("Sisesta kellaaeg (formaadis hh/mm):");
                                    String kellaaeg = scan.nextLine();
                                    Meeldetuletus meeldetuletus = new Meeldetuletus(kirjeldus, kuupäev, kellaaeg);
                                    kalender.lisaSissekanne(meeldetuletus);
                                    System.out.println("Meeldetuletus lisatud.");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 2 -> {
                            while (true) {
                                try {
                                    System.out.println("Mitmenda meeldetuletuse soovid eemaldada? (Sisesta meeldetuletuse järjekorranumber)");
                                    kalender.väljastaMeeldetuletused();
                                    int number = Integer.parseInt(scan.nextLine());
                                    System.out.println("Meeldetuletus eemaldatud");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 3 -> kalender.väljastaMeeldetuletused();
                        default -> System.out.println("Ei saanud aru, proovi uuesti.");
                    }
                }
                case 3 -> {
                    System.out.println("""
                            Mida soovite teha?
                            1. Lisa ülesanne
                            2. Eemalda ülesanne
                            3. Väljasta ülesanded
                            4. Tagasi""");
                    int soov = Integer.parseInt(scan.nextLine());
                    switch (soov) {
                        case 1 -> {
                            while (true) {
                                try {
                                    System.out.println("Sisesta ülesande kirjeldus (ärge palun kasutage semikoolonit):");
                                    String kirjeldus = scan.nextLine();
                                    if (kirjeldus.contains(";")) {
                                        System.out.println("Mida ma just semikooloni kohta ütlesin? Proovi uuesti.");
                                        continue;
                                    }
                                    System.out.println("Sisesta ülesande kuupäev (formaadis dd/mm):");
                                    String kuupäev = scan.nextLine();
                                    System.out.println("Sisesta ülesande kellaaeg (formaadis hh/mm):");
                                    String kellaaeg = scan.nextLine();
                                    Ülesanne ülesanne = new Ülesanne(kirjeldus, kuupäev, kellaaeg);
                                    kalender.lisaSissekanne(ülesanne);
                                    System.out.println("Ülesanne lisatud.");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 2 -> {
                            while (true) {
                                try {
                                    System.out.println("Millise ülesande soovid kustutada? (Sisesta ülesande järjekorranumber)");
                                    kalender.väljastaÜlesanded();
                                    int number = Integer.parseInt(scan.nextLine());

                                    System.out.println("Ülesanne eemaldatud.");
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 3 -> kalender.väljastaÜlesanded();
                        default -> System.out.println("Ei saanud aru, proovi uuesti.");
                    }
                }
                case 4 -> {
                    System.out.println("""
                            Mida soovite teha?
                            1. Väljasta kalender
                            2. Väljasta suvaline riigipüha
                            3. Väljasta kuupäevale vastav nädalapäev
                            4. Salvesta""");
                    int soov = Integer.parseInt(scan.nextLine());
                    switch (soov) {
                        case 1 -> kalender.väljastaKalender();
                        case 2 -> kalender.väljastaPüha();
                        case 3 -> {
                            while (true) {
                                try {
                                    System.out.println("Vali kuupäev (formaadis dd/mm):");
                                    String kuupäev = scan.nextLine();
                                    kalender.kuuNädalaPäev(kuupäev);
                                    break;
                                } catch (Exception e) {
                                    System.out.println("Midagi oli valesti sisestatud, proovige uuesti.");
                                }
                            }
                        }
                        case 4 -> kalender.salvestaKalender("kalender.txt");
                    }
                }
                case 5 -> {
                    break algus;
                }
                default -> System.out.println("Ei saanud aru, proovi uuesti.");
            }
        }
    }
}
