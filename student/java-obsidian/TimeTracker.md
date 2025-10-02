```java
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProjectTime {
    private String startTime, endTime;

    public ProjectTime(String start, String end) {
        this.startTime = start;
        this.endTime = end;
    }

    public void setStartTime(String s) {
        this.startTime = s;
    }

    public void setEndTime(String e) {
        this.endTime = e;
    }

    public String getHoursLogged() {
        SimpleDateFormat F = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date start = F.parse(startTime), end = F.parse(endTime);
            Long mins = (end.getTime() - start.getTime()) / 60000;
            if (mins < 0) return "-1";
            long h = mins / 60, d = h / 24;
            return mins < 120 ? mins + " m" : h < 120 ? h + " h" : d < 120 ? d + " d" : (d / 30) + " mo";
        } catch (Exception e) {
            return "-1";
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

> Tests are only using `setEndTime(String e)`, we can omit writing the other setter and getters.

   A `SimpleDateFormat` object `F` is used to parse the date-time strings into `Date` objects.
    
-   The difference in milliseconds between the `end` and `start` times is calculated and converted to **minutes**.
    
-   If the result is negative (end time is before start), return `-1`.
    
-   Based on the number of minutes:
    
    -   If less than 120 minutes → return in minutes.
        
    -   If less than 120 hours → return in hours.
        
    -   If less than 120 days → return in days.
        
    -   Otherwise → return in months (using 30 days per month approximation).
        
-   If parsing fails (invalid format), it catches the exception and returns `-1`.

### Note: long vs Long

The difference between `Long` and `long` in Java lies in their **type category** and **behavior**:

####  `long` — **Primitive Data Type**

-   A basic data type (not an object).
    
-   Stores a 64-bit signed integer.
    
-   Default value: `0`
    
-   Faster and uses less memory.
    
-   Cannot be `null`.
    

**Example:**

```java
long a = 100L;
```

#### 🔹 `Long` — **Wrapper Class (Object)**

-   Part of Java's `java.lang` package.
    
-   Wraps a `long` value in an object.
    
-   Can be `null`.
    
-   Supports methods like `Long.parseLong()`, `Long.valueOf()`, etc.
    
-   Used when objects are required (e.g., collections like `ArrayList<Long>`).
    

**Example:**

```java
Long b = 100L;
```

---

####  Key Differences Table:

| Feature | `long` (primitive) | `Long` (wrapper class) |
| --- | --- | --- |
| Type | Primitive | Object |
| Nullable | No | Yes |
| Default Value | `0` | `null` |
| Use in Generics | Not allowed | Allowed (e.g., `List<Long>`) |
| Memory Usage | Lower | Higher |
| Method Support | No methods | Has utility methods |

---

#### Autoboxing / Unboxing:

Java automatically converts between `long` and `Long` when needed:

```java
Long obj = 5L;       // autoboxing: primitive → object
long val = obj;      // unboxing: object → primitive
```


####  When to Use:

-   Use `**long**` for performance-sensitive or simple numerical calculations.
    
-   Use `**Long**` when working with collections, nullability, or needing object features.