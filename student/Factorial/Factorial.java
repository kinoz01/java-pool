public class Factorial {
    public static Integer factorial(Integer n) {
        int r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}