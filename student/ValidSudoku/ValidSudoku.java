public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][10];
        boolean[][] cols = new boolean[9][10];
        boolean[][] box = new boolean[9][10];

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.')
                    continue;
                int d = ch - '0';
                int b = (r / 3) * 3 + (c / 3);
                if (rows[r][d] || cols[c][d] || box[b][d])
                    return false;
                rows[r][d] = cols[c][d] = box[b][d] = true;
            }
        }
        return true;
    }
}
