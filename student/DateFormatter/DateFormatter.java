public class DateFormatter {
    private long date; // seconds since epoch (UTC)
    private String format; // "DD/MM/YYYY", "DD.MM.YYYY", "DD Month YYYY" (canonical case)
    private String formattedDate; // formatted in UTC

    private static final String[] MONTHS = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
    };

    private static String canon(String f) {
        if (f == null)
            return null;
        String s = f.trim().replaceAll("\\s+", " ").toUpperCase();
        if (s.equals("DD/MM/YYYY"))
            return "DD/MM/YYYY";
        if (s.equals("DD.MM.YYYY"))
            return "DD.MM.YYYY";
        if (s.equals("DD MONTH YYYY"))
            return "DD Month YYYY";
        return null;
    }

    private static String two(int n) {
        return (n < 10 ? "0" : "") + n;
    }

    private void recompute() {
        if (format == null)
            return;
        java.util.Calendar c = java.util.Calendar.getInstance(java.util.TimeZone.getTimeZone("UTC"));
        c.setTime(new java.util.Date(date * 1000L));
        int d = c.get(java.util.Calendar.DAY_OF_MONTH);
        int m = c.get(java.util.Calendar.MONTH); // 0..11
        int y = c.get(java.util.Calendar.YEAR);

        if (format.equals("DD/MM/YYYY")) {
            formattedDate = two(d) + "/" + two(m + 1) + "/" + y;
        } else if (format.equals("DD.MM.YYYY")) {
            formattedDate = two(d) + "." + two(m + 1) + "." + y;
        } else { 
            formattedDate = two(d) + " " + MONTHS[m] + " " + y;
        }
    }

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
            this.format = "DD/MM/YYYY"; // sane default
        recompute();
    }

    public long getDate() {
        return date;
    }

    public String getFormat() {
        return format;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setDate(long date) {
        this.date = date;
        recompute();
    }

    public void setFormat(String fmt) {
        String c = canon(fmt);
        if (c == null)
            return; // invalid: do nothing
        this.format = c;
        recompute();
    }
}
