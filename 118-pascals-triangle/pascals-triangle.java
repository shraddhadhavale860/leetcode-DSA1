import java.util.*;

public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>(Arrays.asList(new Integer[i + 1]));
            row.set(0, 1);
            row.set(i, 1);

            for (int j = 1; j < i; j++) {
                int val = triangle.get(i - 1).get(j - 1) + triangle.get(i - 1).get(j);
                row.set(j, val);
            }

            triangle.add(row);
        }
        return triangle;
    }
}