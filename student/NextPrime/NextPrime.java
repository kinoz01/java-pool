public class NextPrime {
    public static Integer nextPrime(Integer n) {
        System.out.println(n);
        int m = (n == null ? 0 : n) + 1;
        if (m < 2) m = 2;
        while (!isPrime(m)) m++;
        return m;
    }

    private static boolean isPrime(int m) {
        if (m % 2 == 0) return m == 2;
        for (int i = 3; i * i <= m; i += 2) if (m % i == 0) return false;
        return true;
    }
}