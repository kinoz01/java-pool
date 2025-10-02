public class IterativeFactorial extends Factorial {
    @Override
    public long calculate(int n) {
        int r = 1;
        for (int i=2; i<=n; i++) r *= i;
        return r;
    }
}