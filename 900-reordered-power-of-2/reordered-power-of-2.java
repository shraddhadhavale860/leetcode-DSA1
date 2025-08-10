import java.util.Arrays;

public class Solution {
    public boolean reorderedPowerOf2(int n) {
        char[] target = sortDigits(n);

        for (int i = 0; i < 31; i++) { // 2^0 to 2^30 covers up to 10^9
            int powerOfTwo = 1 << i;
            if (Arrays.equals(sortDigits(powerOfTwo), target)) {
                return true;
            }
        }
        return false;
    }

    private char[] sortDigits(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);
        return digits;
    }
}