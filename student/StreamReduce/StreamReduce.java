import java.util.stream.Stream;

public class StreamReduce {
    public static Integer sumAll(Stream<Integer> s) {
        return s.mapToInt(Integer::intValue).sum();
    }

    public static Integer divideAndAddElements(Stream<Integer> s, int divider) {
        return s.mapToInt(x -> x / divider).sum();
    }
}