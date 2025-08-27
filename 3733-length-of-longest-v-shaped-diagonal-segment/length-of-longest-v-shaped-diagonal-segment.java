import java.util.*;

public class Solution {
    static int[][] directions = {
        {1, 1},   // ↘
        {1, -1},  // ↙
        {-1, -1}, // ↖
        {-1, 1}   // ↗
    };

    static int[] turnMap = {1, 2, 3, 0}; // Clockwise turn mapping

    public int lenOfVDiagonal(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != 1) continue;

                for (int d = 0; d < 4; d++) {
                    List<int[]> path = new ArrayList<>();
                    int r = i, c = j;
                    int expected = 2;
                    path.add(new int[]{r, c});

                    // First leg
                    while (true) {
                        int nr = r + directions[d][0];
                        int nc = c + directions[d][1];
                        if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != expected) break;
                        r = nr;
                        c = nc;
                        expected = (expected == 2) ? 0 : 2;
                        path.add(new int[]{r, c});
                    }

                    maxLen = Math.max(maxLen, path.size());

                    // Try turning at every pivot
                    int turnDir = turnMap[d];

                    for (int k = 0; k < path.size(); k++) {
                        int[] pivot = path.get(k);
                        int tr = pivot[0], tc = pivot[1];
                        int len2 = 0;
                        int exp = (k == 0) ? 2 : (grid[pivot[0]][pivot[1]] == 2 ? 0 : 2);

                        while (true) {
                            int nr = tr + directions[turnDir][0];
                            int nc = tc + directions[turnDir][1];
                            if (nr < 0 || nr >= n || nc < 0 || nc >= m || grid[nr][nc] != exp) break;
                            tr = nr;
                            tc = nc;
                            len2++;
                            exp = (exp == 2) ? 0 : 2;
                        }

                        if (len2 > 0) {
                            maxLen = Math.max(maxLen, k + 1 + len2);
                        }
                    }
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2,2,2,2,2},
            {2,0,2,2,0},
            {2,0,1,1,0},
            {1,0,2,2,2},
            {2,0,0,2,2}
        };

        Solution sol = new Solution();
        System.out.println(sol.lenOfVDiagonal(grid)); // ✅ Expected: 4
    }
}