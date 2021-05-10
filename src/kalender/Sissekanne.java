package kalender;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Sissekanne {
    public static final DateTimeFormatter formaat = DateTimeFormatter.ofPattern("yyyy/dd/MM - HH:mm");

    public static String ajaTõlgendaja(LocalDateTime aeg) {
        int aasta = aeg.getYear();
        //one-liner if laused nulliga numbri ette lisamiseks kui tegu on 10 väiksema arvuga
        String kuu = aeg.getMonthValue() < 10 ? "0" + aeg.getMonthValue() : Integer.toString(aeg.getMonthValue());
        String päev = aeg.getDayOfMonth() < 10 ? "0" + aeg.getDayOfMonth() : Integer.toString(aeg.getDayOfMonth());
        String tund = aeg.getHour() < 10 ? "0" + aeg.getHour() : Integer.toString(aeg.getHour());
        String minut = aeg.getMinute() < 10 ? "0" + aeg.getMinute() : Integer.toString(aeg.getMinute());
        return aasta + "/" + kuu + "/" + päev + " " + tund + ":" + minut;
    }
}
