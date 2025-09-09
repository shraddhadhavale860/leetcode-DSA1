public class Solution {
    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int MOD = 1_000_000_007;
        int[] dp = new int[n + 1]; // dp[i] = people who discover the secret on day i
        dp[1] = 1;

        long sharing = 0; // people actively sharing the secret

        for (int day = 2; day <= n; day++) {
            if (day - delay >= 1) {
                sharing = (sharing + dp[day - delay]) % MOD;
            }
            if (day - forget >= 1) {
                sharing = (sharing - dp[day - forget] + MOD) % MOD;
            }
            dp[day] = (int) sharing;
        }

        long total = 0;
        for (int day = n - forget + 1; day <= n; day++) {
            total = (total + dp[day]) % MOD;
        }

        return (int) total;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Sample test case
        int n = 6;
        int delay = 2;
        int forget = 4;

        int result = sol.peopleAwareOfSecret(n, delay, forget);
        System.out.println("People aware of the secret on day " + n + ": " + result);
    }
}