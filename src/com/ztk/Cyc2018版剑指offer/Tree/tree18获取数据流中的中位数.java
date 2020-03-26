package com.ztk.Cyc2018版剑指offer.Tree;

import java.util.PriorityQueue;

/**
 *   （思路明白，但对左右堆的设计似乎有点迷糊）题目：如何得到一个数据流中的中位数？
 *   分析：若从数据流中读出奇数个数值，那么中位数就是所有值排序之后位于中间的数值，
 *        若从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数值。
 *
 * （1）关于优先队列PriorityQueue:它是Queue接口的实现，可以对其中元素进行排序，
 * 可以放基本数据类型的包装类（如：Integer，Long等）或自定义的类；
 * 对于基本数据类型的包装器类，优先队列中元素默认排列顺序是升序排列（则输出也是升序），
 * 但对于自己定义的类来说，需要自己定义比较器
 * 常用方法有：
 * peek()//返回队首元素（但队首元素还在队列当中）
 * poll()//返回队首元素，队首元素出队列
 * add()//添加元素
 * size()//返回队列元素个数
 * isEmpty()//判断队列是否为空，为空返回true,不空返回false
 *
 * （2）关于堆（排序）：分为大顶堆和小顶堆
 * 大顶堆：每个结点的值都大于或等于其左右孩子结点的值，但并没有要求其左右孩子的值有确定的大小关系
 * 小顶堆：每个结点的值都小于或等于其左右孩子结点的值，但并没有要求其左右孩子的值有确定的大小关系
 *
 * （3）注意：堆用优先队列实现
 */

public class tree18获取数据流中的中位数 {
    /* 大顶堆，存储左半边元素 */
    //自定义了一个比较器：降序（则输出也是降序，第一个元素为最大），实现大顶堆的特点
    private PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    /* 小顶堆，存储右半边元素，并且右半边元素都大于左半边 */
    private PriorityQueue<Integer> right = new PriorityQueue<>();//默认排序：升序（则输出也是升序，第一个元素为最小）
    /* 当前数据流读入的元素个数 */
    private int N = 0;

    public void Insert(Integer val) {//传入的数据流，一个一个传入
        /* 插入要保证两个堆存于平衡状态 */
        if (N % 2 == 0) {
            /* N 为偶数的情况下插入到右半边。
             * 因为右半边元素都要大于左半边，但是新插入的元素不一定比左半边元素来的大，
             * 因此需要先将元素插入左半边，然后利用左半边为大顶堆的特点，取出堆顶元素即为最大元素，此时插入右半边 */
            left.add(val);
            right.add(left.poll()); //（没太懂）
        } else {//当为奇数时，则插入到左半边
            //left.poll():从left边返回队首元素，且队首元素出队列，即从left边取出了那个最大的元素放入right边
            // 即此时中位数在right边了，而left边则比right少了一个元素
            right.add(val);
            left.add(right.poll());
        }
        N++;
    }

    public Double GetMedian() {
        if (N % 2 == 0)//当为偶数时，取平均值
            return (left.peek() + right.peek()) / 2.0;
        else//当为奇数时，取中间那个数，放在了右边堆
            return (double) right.peek();
    }
}
