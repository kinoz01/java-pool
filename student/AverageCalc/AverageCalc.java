public class AverageCalc {
    public static int average(int start, int end, int step) {
        if (step <= 0 || step > 0 ? start > end : start < end)
            return 0;
        int last = start + ((end - start) / step) * step;
        return (start + last) / 2;
    }
}
