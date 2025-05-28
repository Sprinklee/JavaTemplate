package Algo.String;

public class Kmp {

    //KMP指的是一种在目标字符串中匹配目标字符串的技术
    //最核心的是在目标字符串中的指针不会向后只会向前
    //这里说太麻烦了
    //逻辑见 https://www.bilibili.com/video/BV1AY4y157yL/?spm_id_from=333.337.search-card.all.click
    //代码参考 https://leetcode.cn/discuss/post/3144832/fen-xiang-gun-ti-dan-zi-fu-chuan-kmpzhan-ugt4/

    public static int[] getNext(String p){
        int cnt = 0;
        char[] pattern = p.toCharArray();

        int n = pattern.length;

        int[] next = new int[n];

        //这里的cnt指的是此时最长的前后缀长度
        //next数组指的是当索引为i的时候0 - i子串的最长前后缀长度
        for(int i = 1; i < n; i++){
            while(cnt > 0 && pattern[i] != pattern[cnt]){
                cnt = next[cnt-1];
            }
            if(pattern[i] == pattern[cnt]){
                next[i] = ++cnt;
            }
            next[i] = cnt;
        }

        return next;

    }

    public boolean[] KMP(String t,String p){
        int cnt = 0;

        char[] target = t.toCharArray();
        char[] pattern = p.toCharArray();

        int n = target.length;
        int m = pattern.length;

        int[] next = getNext(p);

        boolean[] find = new boolean[n];

        //这里的cnt指的是之前和find重复匹配了多长

        for(int i = 0;i<n;i++){
            while(cnt > 0 && target[i] != pattern[cnt]){
                cnt = next[cnt-1];
            }
            if(target[i] == pattern[cnt]){
                cnt ++;
            }
            if(cnt == m){
                find[i] = true;
                cnt = next[cnt-1];
            }
        }

        return find;

    }
}
