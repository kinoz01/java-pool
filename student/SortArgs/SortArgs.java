import java.util.Arrays;

public class SortArgs {
    public static void sort(String[] a) {
        Arrays.sort(a, (b, c) -> Integer.parseInt(b) - Integer.parseInt(c));     
        System.out.println(String.join(" ", a));
    }
}