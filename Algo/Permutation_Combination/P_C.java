package Algo.Permutation_Combination;

public class P_C {
    public static class Solution {
        private static final int MOD = 1_000_000_007;
        private static final int MX = 100_001;

        private static final long[] F = new long[MX];
        private static final long[] INV_F = new long[MX];

        static {
            F[0] = 1;
            for (int i = 1; i < MX; i++) {
                F[i] = F[i - 1] * i % MOD;
            }

            INV_F[MX - 1] = pow(F[MX - 1], MOD - 2);
            for (int i = MX - 2; i >= 0; i--) {
                INV_F[i] = INV_F[i + 1] * (i + 1) % MOD;
            }
        }

        private static long pow(long x, int n) {
            long res = 1;
            while (n > 0) {
                if ((n & 1) == 1) {
                    res = res * x % MOD;
                }
                x = x * x % MOD;
                n >>= 1;
            }
            return res;
        }

        // C(n, m): 从 n 个数中选 m 个数的组合数
        public long comb(int n, int m) {
            return (m < 0 || m > n) ? 0 : F[n] * INV_F[m] % MOD * INV_F[n - m] % MOD;
        }

        // P(n, m): 从 n 个数中选 m 个数进行排列的个数
        public long permu(int n, int m) {
            return (m < 0 || m > n) ? 0 : F[n] * INV_F[n - m] % MOD;
        }

        // 示例：对 nums 中所有不同的数统计组合数
        public int solve(int[] nums) {
            int n = nums.length;
            long totalComb = 0;
            for (int i = 0; i <= n; i++) {
                totalComb = (totalComb + comb(n, i)) % MOD;
            }
            return (int) totalComb;
        }
    }

}
