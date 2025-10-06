import java.time.*;
import java.time.format.*;
import java.util.Locale;

public class ParseDate {
    private static final DateTimeFormatter FULL = new DateTimeFormatterBuilder()
            .parseCaseInsensitive().appendPattern("EEEE d MMMM yyyy")
            .toFormatter(Locale.ENGLISH);

    public static LocalDateTime parseIsoFormat(String s) {
        if (s == null) return null;
        return LocalDateTime.parse(s);
    }

    public static LocalDate parseFullTextFormat(String s) {
        if (s == null) return null;
        return LocalDate.parse(s, FULL);
    }

    public static LocalTime parseTimeFormat(String s) {
        if (s == null)
            return null;
        String parsableString = s.replace("morning", "AM").replace("evening", "PM");
        DateTimeFormatter dtf = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("hh 'hours in the' a, mm 'minutes and' ss 'seconds'")
                .toFormatter(Locale.ENGLISH);
        return LocalTime.parse(parsableString, dtf);
    }
}
