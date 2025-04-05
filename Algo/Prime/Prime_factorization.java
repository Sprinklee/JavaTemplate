package Algo.Prime;

import java.util.HashSet;

public class Prime_factorization {
    //可以提前预处理取得最小质因数
    private static final int MX = 1_000_001;
    private static final int[] lpf = new int[MX];

    static {
        for (int i = 2; i < MX; i++) {
            if (lpf[i] == 0) {
                for (int j = i; j < MX; j += i) {
                    if (lpf[j] == 0) {
                        lpf[j] = i;
                    }
                }
            }
        }
    }

    //或者你也可以直接取用
    //求有几个素数
    public int distinctPrimeFactors(int[] nums) {
        HashSet<Integer> h = new HashSet();
        for(int num : nums){
            for(int i = 2;i*i<=num;i++){
                //是否存在此素数
                if(num%i==0){
                    h.add(i);
                }
                //去除关于此素数及其倍数的情况
                while(num % i == 0){
                    num /= i;
                }
            }
            //要注意之后还是一个质数的情况
            if(num>1) h.add(num);
        }

        return h.size();
    }
}
