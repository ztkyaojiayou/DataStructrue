package com.ztk.Cyc2018版剑指offer.Heap;

/**
 * 题目： 输入n个整数，找出其中最小的k个数。
 * 例如输入 4 、5 、1、6、2、7、3 、8 这 8 个数字，
 *    则最小的 4 个数字是 1 、2、3 、4
 *
 * 【法1】：O（N）(改变了数组原有的顺序)
 *     基于上一题的Partition函数：
 *     以第k个数为基准，从头开始遍历，
 *      使得比第k个数字小放在arr[k]的左边，比第k个数字大的放在右边
 *
 * 【法2】：O（N*logk) 基于堆排的思想
 *      先建立一个含有k个元素的大顶堆，
 *      每次新来一个数 和堆顶比较，如果大于堆顶，跳过
 *                               如果小于堆顶，交换，然后重新调整二叉堆
 *      直到arr结束
 *
 * Created by nibnait on 2016/9/30.
 */
public class heap44最小的k个数 {
}
