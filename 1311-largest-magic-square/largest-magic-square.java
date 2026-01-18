class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        // Prefix sums for rows and columns
        int[][] rowPrefix = new int[m][n + 1];
        int[][] colPrefix = new int[n][m + 1];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowPrefix[i][j + 1] = rowPrefix[i][j] + grid[i][j];
                colPrefix[j][i + 1] = colPrefix[j][i] + grid[i][j];
            }
        }

        // Helper function to check if subgrid (x,y) with size k is magic
        java.util.function.Predicate<int[]> check = (params) -> {
            int x = params[0], y = params[1], k = params[2];
            Integer target = null;

            // check rows
            for (int i = x; i < x + k; i++) {
                int sum = rowPrefix[i][y + k] - rowPrefix[i][y];
                if (target == null) target = sum;
                else if (target != sum) return false;
            }

            // check cols
            for (int j = y; j < y + k; j++) {
                int sum = colPrefix[j][x + k] - colPrefix[j][x];
                if (target != sum) return false;
            }

            // check diagonals
            int d1 = 0, d2 = 0;
            for (int i = 0; i < k; i++) {
                d1 += grid[x + i][y + i];
                d2 += grid[x + i][y + k - 1 - i];
            }
            return d1 == target && d2 == target;
        };

        // Try largest k first
        for (int k = Math.min(m, n); k > 1; k--) {
            for (int i = 0; i <= m - k; i++) {
                for (int j = 0; j <= n - k; j++) {
                    if (check.test(new int[]{i, j, k})) {
                        return k;
                    }
                }
            }
        }
        return 1;
    }
}