public class ComputeArray {
    public static int[] computeArray(int[] a) {
        return a == null ? null
                : java.util.Arrays.stream(a)
                        .map(v -> {
                            return v % 3 == 0 ? v * 5 : v % 3 == 1 ? v + 7 : v;
                        })
                        .toArray();
    }
}
