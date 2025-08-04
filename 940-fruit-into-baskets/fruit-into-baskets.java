public class Solution {
    public int totalFruit(int[] fruits) {
        int max = 0, left = 0;
        Map<Integer, Integer> basket = new HashMap<>();

        for (int right = 0; right < fruits.length; right++) {
            basket.put(fruits[right], basket.getOrDefault(fruits[right], 0) + 1);

            while (basket.size() > 2) {
                basket.put(fruits[left], basket.get(fruits[left]) - 1);
                if (basket.get(fruits[left]) == 0) {
                    basket.remove(fruits[left]);
                }
                left++;
            }

            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] fruits = {1, 2, 1};
        System.out.println(new Solution().totalFruit(fruits)); // Output: 3
    }
}