import java.time.*;

public class DayOfWeekFinder {
    public String findNextDayOfWeek(String startDate, String dayOfWeek) {
        try {
            LocalDate d = LocalDate.parse(startDate);
            DayOfWeek w = DayOfWeek.valueOf(dayOfWeek.toUpperCase());
            return d.with(java.time.temporal.TemporalAdjusters.next(w)).toString();
        } catch (Exception e) {
            return "Error";
        }
    }
}
