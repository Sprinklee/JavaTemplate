package Algo.BinarySearch;

public class BinarySearch {
    //二分基本可以解决任何有单调性的问题
    //但是二分查找确实很迷惑 有时会因为边界的判断失误导致错误
    //这里带来一种思想和一种套路 保证以后二分不在出错
    //这里以在数组中二分找元素为例子
    public int BinSearch(int tar,int[] nums){
        int left = -1;
        int right = nums.length;

        //套路 : 左开右开 此思路的优点是不用去考虑是right = mid+1 还是left = mid - 1 了
        while(left+1<right){
            int mid = (left + right)/2;
            if(nums[mid] < tar){
                left = mid;
            }else{
                right = mid;
            }
        }
        //思想就是我一直记忆的 不要关注二分区间内的 要去关注二分区间外部的
        //比如说这里当left变为 mid 的时候 则说明left及其左边的区域都是小于tar的（因为是if中判断的结果）
        //同理right变为 mid 的时候 则说明right及其右边的区域都是大于等于tar的（因为是if中判断的结果）

        return right;
        //最终返回right因为right及其右边都是大于等于tar的了 所以right就是第一个大于等于tar的元素
    }

    //此思路要注意超出边界问题 如果返回 nums.length 就说明tar在nums之外 要注意
}
