import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatDate {

    private static final DateTimeFormatter FR_FULL = DateTimeFormatter
            .ofPattern("'Le' d MMM 'de l''an' uuuu 'à' HH'h'mm'm et' ss's'", Locale.FRENCH);

    private static final DateTimeFormatter IT_SIMPLE = DateTimeFormatter.ofPattern("MMMM d yy", Locale.ITALIAN);

    public static String formatToFullText(LocalDateTime dateTime) {
        if (dateTime == null)
            return null;
        return dateTime.format(FR_FULL);
    }

    public static String formatSimple(LocalDate date) {
        if (date == null)
            return null;
        return date.format(IT_SIMPLE);
    }

    public static String formatIso(LocalTime time) {
        if (time == null)
            return null;
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }
}
