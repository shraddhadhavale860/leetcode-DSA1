public class Solution {
    public int maximum69Number(int num) {
        // Convert the number to a character array
        char[] digits = String.valueOf(num).toCharArray();

        // Change the first '6' to '9' to maximize the number
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // Only one change allowed
            }
        }

        // Convert the modified array back to an integer
        return Integer.parseInt(new String(digits));
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        System.out.println(sol.maximum69Number(9669)); // Output: 9969
        System.out.println(sol.maximum69Number(9996)); // Output: 9999
        System.out.println(sol.maximum69Number(9999)); // Output: 9999
    }
}