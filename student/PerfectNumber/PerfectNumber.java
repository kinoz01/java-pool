public class PerfectNumber {
    public boolean isPerfectNumber(int n) {
        if (n <= 1) return false;
        int sum = 1;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                int j = n / i;
                sum += i;
                if (j != i) sum += j;
            }
        }
        return sum == n;
    }
}
