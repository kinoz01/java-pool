import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;

public class DifferenceBetweenDate {

    public static Duration durationBetweenTime(LocalTime t1, LocalTime t2) {
        if (t1 == null || t2 == null) return null;
        return Duration.between(t1, t2).abs();
    }

    public static Period periodBetweenDate(LocalDate d1, LocalDate d2) {
        if (d1 == null || d2 == null) return null;
        if (d1.isAfter(d2)) { LocalDate x = d1; d1 = d2; d2 = x; }
        return Period.between(d1, d2);
    }

    public static Long numberOfHoursBetweenDateTime(LocalDateTime dt1, LocalDateTime dt2) {
        if (dt1 == null || dt2 == null) return null;
        return Duration.between(dt1, dt2).abs().toHours();
    }
}
