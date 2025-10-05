import java.time.*;

public class AgeFinder {
    public int calculateAge(String date) {
        try {
            LocalDate bday = LocalDate.parse(date), today = LocalDate.now();
            if (bday.isAfter(today)) return -1;
            return Period.between(bday, today).getYears();
        } catch (Exception e) {
            return -1;
        }
    }
}