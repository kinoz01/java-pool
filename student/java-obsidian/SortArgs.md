```java
public class SortArgs {
    public static void sort(String[] a) {
        java.util.Arrays.sort(a, java.util.Comparator.comparingInt(Integer::parseInt));
        System.out.println(String.join(" ", a));
    }
}

import java.io.*;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        var defaultOut = System.out;

        System.setOut(printStream);
        SortArgs.sort(new String[] { "4", "2", "1", "3" });
        System.setOut(defaultOut);

        String output = outputStream.toString();
        System.out.println(output.equals("1 2 3 4\n"));
    }
}
```
