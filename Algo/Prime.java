package Algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Prime {
    //共三种做法 三种的效率都要依据数据来判断
    //1 试除法 用于判断单个数是不是 质数
    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2; // 1 不是质数
    }


    //2 埃氏筛 适合数据量小的（<10^7） 范围的数据
    public List<Integer> eratosthenesSieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int m = p * p; m <= n; m += p) {
                    isPrime[m] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }

    //3 线性筛 欧拉筛 适合大数据量的数据 如果把ans换为数组更快
    public List<Integer> getP(int ri){
        List<Integer> ans = new ArrayList();
        boolean[] isP = new boolean[ri+1];
        Arrays.fill(isP,true);
        for(int i = 2;i<=ri;i++){
            if(isP[i]) ans.add(i);
            for(int j : ans){
                int m = j*i;
                if(m>ri) break;
                isP[m] = false;
                if(j%i==0) break;
            }
        }
        return ans;
    }


}
