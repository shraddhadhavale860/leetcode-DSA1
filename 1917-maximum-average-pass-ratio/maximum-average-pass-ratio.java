import java.util.PriorityQueue;

public class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Priority queue to store [gain, pass, total] with max gain at the top
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(b[0], a[0]));

        // Initialize the heap with gain for each class
        for (int[] cls : classes) {
            int pass = cls[0], total = cls[1];
            double gain = getGain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }

        // Assign extra students one by one
        while (extraStudents-- > 0) {
            double[] top = pq.poll();
            int pass = (int) top[1] + 1;
            int total = (int) top[2] + 1;
            double gain = getGain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }

        // Calculate final average pass ratio
        double sum = 0;
        for (double[] cls : pq) {
            sum += cls[1] / cls[2];
        }

        return sum / classes.length;
    }

    // Helper method to calculate gain from adding one student
    private double getGain(int pass, int total) {
        return (double)(pass + 1) / (total + 1) - (double)pass / total;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] classes1 = {{1,2},{3,5},{2,2}};
        int extra1 = 2;
        System.out.printf("Output: %.5f\n", sol.maxAverageRatio(classes1, extra1)); // 0.78333

        int[][] classes2 = {{2,4},{3,9},{4,5},{2,10}};
        int extra2 = 4;
        System.out.printf("Output: %.5f\n", sol.maxAverageRatio(classes2, extra2)); // 0.53485
    }
}