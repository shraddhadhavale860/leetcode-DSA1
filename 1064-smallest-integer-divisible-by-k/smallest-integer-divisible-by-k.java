class Solution {
    public int smallestRepunitDivByK(int k) {
        // If k is divisible by 2 or 5, impossible
        if (k % 2 == 0 || k % 5 == 0) return -1;
        
        int remainder = 1 % k;
        int length = 1;
        
        // Loop at most k times (pigeonhole principle)
        while (remainder != 0) {
            remainder = (remainder * 10 + 1) % k;
            length++;
        }
        
        return length;
    }
}