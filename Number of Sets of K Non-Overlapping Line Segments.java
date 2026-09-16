class Solution {
    private static final int MOD = 1_000_000_007;

    private long power(long base, long exp) {
        long res = 1;
        base %= MOD;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res;
    }

    private long modInverse(long n) {
        return power(n, MOD - 2);
    }

    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;

        if (R > N) return 0;

        long num = 1, den = 1;
        for (int i = 0; i < R; i++) {
            num = (num * (N - i)) % MOD;
            den = (den * (i + 1)) % MOD;
        }

        return (int) ((num * modInverse(den)) % MOD);
    }
}
