import java.util.*;

public class Solution {
    static final int MOD = 1_000_000_007;

    // Step 1: Decompose n into powers of 2
    private List<Integer> getPowers(int n) {
        List<Integer> powers = new ArrayList<>();
        int power = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                powers.add(power);
            }
            n >>= 1;
            power <<= 1;
        }
        return powers;
    }

    // Step 2: Precompute prefix products modulo MOD
    private long[] computePrefixProducts(List<Integer> powers) {
        long[] prefix = new long[powers.size() + 1];
        prefix[0] = 1;
        for (int i = 0; i < powers.size(); i++) {
            prefix[i + 1] = (prefix[i] * powers.get(i)) % MOD;
        }
        return prefix;
    }

    // Step 3: Modular inverse using Fermat's Little Theorem
    private long modInverse(long x) {
        return powMod(x, MOD - 2);
    }

    private long powMod(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    // Step 4: Answer each query using prefix products
    public int[] productQueries(int n, int[][] queries) {
        List<Integer> powers = getPowers(n);
        long[] prefix = computePrefixProducts(powers);
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            long product = (prefix[right + 1] * modInverse(prefix[left])) % MOD;
            result[i] = (int) product;
        }

        return result;
    }
}