import java.util.*;

class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int val : basket1) freq.put(val, freq.getOrDefault(val, 0) + 1);
        for (int val : basket2) freq.put(val, freq.getOrDefault(val, 0) + 1);

        for (int count : freq.values())
            if (count % 2 != 0) return -1;  // Uneven count → impossible

        Map<Integer, Integer> count1 = new HashMap<>();
        Map<Integer, Integer> count2 = new HashMap<>();
        for (int val : basket1) count1.put(val, count1.getOrDefault(val, 0) + 1);
        for (int val : basket2) count2.put(val, count2.getOrDefault(val, 0) + 1);

        List<Integer> excess1 = new ArrayList<>();
        List<Integer> excess2 = new ArrayList<>();

        for (int val : freq.keySet()) {
            int total = freq.get(val) / 2;
            int extra1 = count1.getOrDefault(val, 0) - total;
            int extra2 = count2.getOrDefault(val, 0) - total;

            for (int i = 0; i < extra1; i++) excess1.add(val);
            for (int i = 0; i < extra2; i++) excess2.add(val);
        }

        if (excess1.isEmpty()) return 0;  // Already equal

        Collections.sort(excess1);
        Collections.sort(excess2, Collections.reverseOrder());
        int minVal = Math.min(Arrays.stream(basket1).min().getAsInt(), Arrays.stream(basket2).min().getAsInt());

        long cost = 0;
        for (int i = 0; i < excess1.size(); i++) {
            int a = excess1.get(i);
            int b = excess2.get(i);
            cost += Math.min(Math.min(a, b), 2 * minVal);
        }

        return cost;
    }
}