public class RotateArray {
    public static Integer[] rotate(Integer[] arr, int c) {
        java.util.Collections.rotate(java.util.Arrays.asList(arr), c);
        return arr;
    }
}