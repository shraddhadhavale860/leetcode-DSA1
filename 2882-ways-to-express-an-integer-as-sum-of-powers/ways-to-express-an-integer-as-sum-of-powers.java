import java.util.*;

public class Solution {
    static final int MOD = 1000000007;

    public int numberOfWays(int n, int x) {
        // Step 1: Generate all powers i^x <= n
        List<Integer> powers = new ArrayList<>();
        int base = 1;
        while (true) {
            long pow = (long) Math.pow(base, x);
            if (pow > n) break;
            powers.add((int) pow);
            base++;
        }

        // Step 2: DP array
        int[] dp = new int[n + 1];
        dp[0] = 1; // One way to make sum 0 — choose nothing

        // Step 3: 0/1 knapsack over powers
        for (int p : powers) {
            for (int sum = n; sum >= p; sum--) {
                dp[sum] = (dp[sum] + dp[sum - p]) % MOD;
            }
        }

        return dp[n];
    }
}
