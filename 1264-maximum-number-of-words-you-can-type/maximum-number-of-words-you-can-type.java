import java.util.*;

public class Solution {
    public int canBeTypedWords(String text, String brokenLetters) {
        // Store broken letters in a HashSet for fast lookup
        Set<Character> broken = new HashSet<>();
        for (char ch : brokenLetters.toCharArray()) {
            broken.add(ch);
        }

        String[] words = text.split(" ");
        int count = 0;

        for (String word : words) {
            boolean canType = true;
            for (char c : word.toCharArray()) {
                if (broken.contains(c)) {
                    canType = false;
                    break;   // stop checking this word
                }
            }
            if (canType) count++;
        }

        return count;
    }
}
