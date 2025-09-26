public class RotateArray {
    public static Integer[] rotate(Integer[] arr, int rotationCount) {
        java.util.Collections.rotate(java.util.Arrays.asList(arr), rotationCount);
        return arr;
    }
}