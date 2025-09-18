import java.util.*;

public class Solution {
    static final int MOD = 1_000_000_007;
    static final int MAX = 10005;
    static long[][] comb = new long[MAX][16];

    public int idealArrays(int n, int maxValue) {
        // Precompute combinations C(n, k) using Pascal's triangle
        for (int i = 0; i < MAX; i++) {
            comb[i][0] = 1;
            for (int j = 1; j < 16; j++) {
                if (j <= i) {
                    comb[i][j] = (comb[i - 1][j] + comb[i - 1][j - 1]) % MOD;
                }
            }
        }

        // dp[val][len] = number of sequences ending with val of length len
        int[][] dp = new int[maxValue + 1][16];
        for (int i = 1; i <= maxValue; i++) {
            dp[i][1] = 1;
        }

        for (int len = 2; len < 16; len++) {
            for (int i = 1; i <= maxValue; i++) {
                for (int j = 2 * i; j <= maxValue; j += i) {
                    dp[j][len] = (dp[j][len] + dp[i][len - 1]) % MOD;
                }
            }
        }

        long result = 0;
        for (int val = 1; val <= maxValue; val++) {
            for (int len = 1; len < 16; len++) {
                if (len <= n) {
                    result = (result + dp[val][len] * comb[n - 1][len - 1]) % MOD;
                }
            }
        }

        return (int) result;
    }
}