public class RecursiveFactorial extends Factorial {
    @Override public long calculate(int n) {
        return n <= 1 ? 1 : n * calculate(n-1);
    }
}