class Solution {

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int maxTotal = 0;
        int total = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            total += fruits[right][1];

            while (left <= right &&
                   Math.min(
                       Math.abs(startPos - fruits[left][0]) + (fruits[right][0] - fruits[left][0]),
                       Math.abs(startPos - fruits[right][0]) + (fruits[right][0] - fruits[left][0])
                   ) > k) {
                total -= fruits[left][1];
                left++;
            }

            maxTotal = Math.max(maxTotal, total);
        }

        return maxTotal;
    }
}