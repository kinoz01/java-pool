public class SortArgs {
    public static void sort(String[] a) {
        java.util.Arrays.sort(a, java.util.Comparator.comparingInt(Integer::parseInt));
        System.out.println(String.join(" ", a));
    }
}