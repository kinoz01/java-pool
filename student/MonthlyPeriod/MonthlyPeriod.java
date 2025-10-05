import java.time.*;

public class MonthlyPeriod {
    public String calculatePeriod(String startDate, String endDate) {
        try {
            LocalDate a = LocalDate.parse(startDate), b = LocalDate.parse(endDate);
            if (a.isAfter(b)) {
                LocalDate t = a;
                a = b;
                b = t;
            }
            Period p = Period.between(a, b);
            int y = p.getYears(), m = p.getMonths();
            String ys = y == 0 ? "" : y + " year" + (y == 1 ? "" : "s");
            String ms = m == 0 ? "" : m + " month" + (m == 1 ? "" : "s");
            return ys.isEmpty() ? (ms.isEmpty() ? "" : ms) : (ms.isEmpty() ? ys : ys + " and " + ms);
        } catch (Exception e) {
            return "Error";
        }
    }
}
