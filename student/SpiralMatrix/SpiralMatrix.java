public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        int[][] a = new int[n][n];
        int t = 0, b = n - 1, l = 0, r = n - 1, k = 1;
        while (l <= r && t <= b) {
            for (int j = l; j <= r;)
                a[t][j++] = k++;
            t++;
            for (int i = t; i <= b;)
                a[i++][r] = k++;
            r--;
            for (int j = r; j >= l && t <= b;)
                a[b][j--] = k++;
            b--;
            for (int i = b; i >= t && l <= r;)
                a[i--][l] = k++;
            l++;
        }
        return a;
    }
}
