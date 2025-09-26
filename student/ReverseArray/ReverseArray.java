public class ReverseArray {
    public static Integer[] reverse(Integer[] arr) {
        java.util.Collections.reverse(java.util.Arrays.asList(arr));
        return arr;
    }
}