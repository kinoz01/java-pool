```java
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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
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


public class ExerciseRunner {
    public static void main(String[] args) {

        ProjectTime shortProject = new ProjectTime("2023-05-14 09:00", "2023-05-14 09:30");
        System.out.println("Short Project Total Logged Time: " + shortProject.getHoursLogged());

        ProjectTime overnightProject = new ProjectTime("2023-05-14 20:00", "2023-05-15 08:00");
        System.out.println("Overnight Project Total Logged Time: " + overnightProject.getHoursLogged());

        ProjectTime fullDayProject = new ProjectTime("2023-05-14 00:00", "2023-05-15 00:00");
        System.out.println("Full Day Project Total Logged Time: " + fullDayProject.getHoursLogged());

        ProjectTime errorProject = new ProjectTime("2023-05-14", "2023-05-15 08:00");
        System.out.println("Error Project Total Logged Time: " + errorProject.getHoursLogged());
    }
}
```