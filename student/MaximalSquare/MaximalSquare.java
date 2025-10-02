public class MaximalSquare {
    public int maximalSquare(char[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0)
            return 0;
        int cols = m[0].length, best = 0, prev = 0;
        int[] dp = new int[cols + 1];
        for (char[] row : m) {
            prev = 0;
            for (int j = 1; j <= cols; j++) {
                int tmp = dp[j];
                dp[j] = row[j - 1] == '1' ? Math.min(Math.min(dp[j], dp[j - 1]), prev) + 1 : 0;
                best = Math.max(best, dp[j]);
                prev = tmp;
            }
        }
        return best * best;
    }
}
