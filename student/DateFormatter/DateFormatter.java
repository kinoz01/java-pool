import  java.time.*;
import  java.time.format.DateTimeFormatter;
import  java.util.Locale;

public class DateFormatter {
    private long date;
    private String format;

    public DateFormatter() {
        this(System.currentTimeMillis() / 1000L, "DD/MM/YYYY");
    }

    public DateFormatter(long date) {
        this(date, "DD/MM/YYYY");
    }

    public DateFormatter(long date, String format) {
        this.date = date;
        this.format = canon(format);
        if (this.format == null)
            this.format = "DD/MM/YYYY";
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
        String c = canon(fmt);
        if (c != null)
            this.format = c;
    }

    public String getFormattedDate() {
        ZonedDateTime zdt = Instant.ofEpochSecond(date).atZone(ZoneOffset.UTC);

        switch (format) {
            case "DD/MM/YYYY":
                return zdt.format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withLocale(Locale.ENGLISH));
            case "DD.MM.YYYY":
                return zdt.format(DateTimeFormatter.ofPattern("dd.MM.uuuu").withLocale(Locale.ENGLISH));
            case "DD Month YYYY":
                return zdt.format(DateTimeFormatter.ofPattern("dd MMMM uuuu").withLocale(Locale.ENGLISH));
            default:
                return zdt.format(DateTimeFormatter.ofPattern("dd/MM/uuuu").withLocale(Locale.ENGLISH));
        }
    }

    private static String canon(String f) {
        if (f == null)
            return null;
        String s = f.trim().replaceAll("\\s+", " ").toUpperCase(Locale.ROOT);
        if (s.equals("DD/MM/YYYY"))
            return "DD/MM/YYYY";
        if (s.equals("DD.MM.YYYY"))
            return "DD.MM.YYYY";
        if (s.equals("DD MONTH YYYY"))
            return "DD Month YYYY";
        return null;
    }
}
