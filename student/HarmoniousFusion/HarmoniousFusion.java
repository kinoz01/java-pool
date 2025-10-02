import java.util.Arrays;
import java.util.stream.IntStream;

public class HarmoniousFusion {
    public int[] merge(int[] a, int[] b) {
        return IntStream
                .concat(Arrays.stream(a), Arrays.stream(b))
                .sorted()
                .toArray();
    }
}
