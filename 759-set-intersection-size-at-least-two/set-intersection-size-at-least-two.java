import java.util.*;

public class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        // Sort by end ascending, start descending
        Arrays.sort(intervals, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        List<Integer> selected = new ArrayList<>();

        for (int[] interval : intervals) {
            int start = interval[0], end = interval[1];

            // Count how many selected numbers are in the current interval
            int count = 0;
            for (int num : selected) {
                if (num >= start && num <= end) {
                    count++;
                }
            }

            // Add missing numbers (at most 2)
            for (int i = end; count < 2 && i >= start; i--) {
                if (!selected.contains(i)) {
                    selected.add(i);
                    count++;
                }
            }
        }

        return selected.size();
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        int[][] test1 = {{1,3},{3,7},{8,9}};
        System.out.println("Output: " + sol.intersectionSizeTwo(test1)); // Expected: 5

        int[][] test2 = {{1,3},{1,4},{2,5},{3,5}};
        System.out.println("Output: " + sol.intersectionSizeTwo(test2)); // Expected: 3

        int[][] test3 = {{1,2},{2,3},{2,4},{4,5}};
        System.out.println("Output: " + sol.intersectionSizeTwo(test3)); // Expected: 5
    }
}
