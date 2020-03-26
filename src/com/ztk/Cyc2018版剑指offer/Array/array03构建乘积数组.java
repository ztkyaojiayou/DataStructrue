package com.ztk.Cyc2018版剑指offer.Array;

/**
 * （懂了）题目：给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B 中的元素 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]（刚好无A[i]），不能使用除法。
 *
 * 【例】： 已知A[]={1,2,3}，求B[]
 *B[0]=A[1]×A[2]=2×3=6，没有A[0]
 *B[1]=A[0]×A[2]=1×3=3，没有A[1]
 *B[2]=A[0]×A[1]=1×2=2，没有A[2]
 *所以 B 数组为{6,3,2}
 *
 * （1）分析：易知即数组B[]中的B[i]项等于A数组所有数的乘积，但是去除A[i]项，则每一个B[i]其实都是由A[]中的（n-1）项相乘所得
 *          （若把跳过的那一项（也即A[i]项）看成常数1的话，则每个B[i]都是n项的乘积）
 * （2）方法：把每一个B[i]项都拆分成（前i项的乘积：A[0]*A[1]...*A[i-1]）和（后n-i-1项的乘积：A[i+1]*...*A[n-2]*A[n-1]）的乘积
 * （因为中间跳过了A[i]项，所以从A[i-1]过渡到了A[i+1]）
 */
public class array03构建乘积数组 {
    public int[] multiply(int[] A) {
        int length = A.length;//A数组
        int[] B = new int[length];//B数组
        if(length != 0 ){
            B[0] = 1;//B[0]取1
            // 第一步：先求前i项的乘积【B[i]】
            for(int i = 1; i < length; i++){
                B[i] = B[i-1] * A[i-1];//用的递推，即求B[i]时，用到B[i-1]的结果
            }
            int temp = 1;// tmp保存后n-i-1项的乘积：A[i+1]*A[i+2]*...*A[n-2]*A[n-1]的结果

            // 第二步：再计算后n-i-1项的乘积：A[i+1]*...*A[n-2]*A[n-1]
            // 【B[i]】的结果已经计算出来，所以从A.length-2（也即n-2）开始乘

            for(int j = length-2; j >= 0; j--){
                temp *= A[j+1];//这里是从最后一项往前乘，即先乘最后一项
                // 因为后n-i-1项的乘积是乘到A[n-1]项，所以易知最后一项的下标应为：A.length-2（也即n-2），此时A[j+1]即为最后一项A[n-1]）
                B[j] *= temp;//再把之前的B[i]乘起来即可
            }
        }
        return B;
    }
}
