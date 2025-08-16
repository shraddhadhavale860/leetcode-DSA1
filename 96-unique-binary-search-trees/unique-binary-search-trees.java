import java.util.Scanner;

public class Solution {
    // Method to calculate number of unique BSTs using dynamic programming
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // Base case: empty tree

        for (int nodes = 1; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++) {
                int left = root - 1;
                int right = nodes - root;
                dp[nodes] += dp[left] * dp[right];
            }
        }

        return dp[n];
    }

    // Main method to test the function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of nodes (n): ");
        int n = scanner.nextInt();

        Solution solution = new Solution();
        int result = solution.numTrees(n);

        System.out.println("Number of structurally unique BSTs with " + n + " nodes: " + result);
        scanner.close();
    }
}