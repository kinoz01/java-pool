import  java.time.*;
import  java.time.format.DateTimeFormatter;

public class DateFormatter {
    private long date;
    private String format;

    public DateFormatter() {
    }
    
    public DateFormatter(long date) {
        this(date, "DD/MM/YYYY");
    }
    
    public DateFormatter(long date, String format) {
        this.date = date;
        if (format != null) this.format = format.toUpperCase();
    }
    
    public long getDate() {
        return date;
    }
    
    public String getFormat() {
        return format;
    }
    
    public void setDate(long date) {
        this.date = date;
    }

    public void setFormat(String fmt) {
        if (fmt != null) this.format = fmt.toUpperCase();
    }

    public String getFormattedDate() {
        LocalDate d = LocalDate.ofEpochDay(date / 86400); // 24*60*60
        return switch (format) {
            case "DD.MM.YYYY"    -> d.format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
            case "DD MONTH YYYY" -> d.format(DateTimeFormatter.ofPattern("dd MMMM uuuu"));
            case "DD/MM/YYYY"    -> d.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            default              -> d.format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        };
    }
}