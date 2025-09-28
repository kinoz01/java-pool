import java.time.*;
import java.time.format.*;

public class ProjectTime {
    private String startTime, endTime;
    private long mins;
    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public ProjectTime(String start, String end) {
        startTime = start;
        endTime = end;
        recalc();
    }

    public void setStartTime(String s) {
        startTime = s;
        recalc();
    }

    public void setEndTime(String e) {
        endTime = e;
        recalc();
    }

    public String getHoursLogged() {
        if (mins < 0)
            return "-1";
        long h = mins / 60, d = h / 24;
        return mins < 120 ? mins + " m" : h < 120 ? h + " h" : d < 120 ? d + " d" : (d / 30) + " mo";
    }

    private void recalc() {
        try {
            long diff = Duration.between(LocalDateTime.parse(startTime, F), LocalDateTime.parse(endTime, F))
                    .toMinutes();
            mins = diff < 0 ? -1 : diff;
        } catch (Exception e) {
            mins = -1;
        }
    }
}
