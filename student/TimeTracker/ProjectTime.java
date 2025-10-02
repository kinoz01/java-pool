import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTime {
    private String startTime, endTime;

    public ProjectTime(String start, String end) {
        startTime = start;
        endTime = end;
    }
    
    public void setEndTime(String s) {
        endTime = s;
    }

    public String getHoursLogged() {
        try {
            SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date start = F.parse(startTime), end = F.parse(endTime);
            Long mins = (end.getTime() - start.getTime()) / 60000;
            if (mins < 0) return "-1";
            long h = mins/60, d = h/24;
            return mins < 120 ? mins + " m" : h < 120 ? h + " h" : d < 120 ? d + " d" : (d/30) + " mo";
        } catch (Exception e){
            return "-1";
        }
    }
}