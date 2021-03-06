package com.ztk.Cyc2018版剑指offer.Stack;

import java.util.Stack;

/**
 * 题目： 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数。
 *       在该栈中，调用 min、push 及 pop 的时间复杂度都是 0(1)
 *       注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 *
 * 【解】：
 *
 * 要用到两个栈：dataStack和minStack
 * 1.栈dataStack用来存所有的元素，即把传入的元素入栈
 * 2.栈minStack用来在push()的时候存加入新的元素后当前dataStack中对应的最小值。
 * （易知，只需比较要存入dataStack的新元素与minStack的栈顶元素比较即可）
 *   两个栈中的元素数量始终保持一致，当新的元素小于“minStack”栈顶元素时，“minStack”像栈顶push新来的元素，否则，“minStack”向栈顶加入原栈顶元素。
 *   执行“pop”方法时，两个栈同时弹出各自的栈顶元素，把栈minStack的栈顶元素弹出是为了保证minStack的栈顶元素始终为剩余元素中的最小值。
 */

public class Stack35包含min函数的栈 {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();//定义一个辅助栈，用于存放最小值

    //1.（简单）入栈操作（即插入/添加元素）
    public void push(int node) {
        //直接入栈到dataStack即可
        dataStack.push(node);
        //但同时要把较小元素存入（push）到minStack栈中
        //当minStack.isEmpty()时即插人第一个元素，之后每push之前都必须与栈顶元素进行比较，取最小值插入
        minStack.push(minStack.isEmpty() ? node : Math.min(minStack.peek(), node));
    }

    //2.（简单）出栈操作（即移除堆栈顶部的元素）
    public void pop() {
        //直接从dataStack中弹出即可
        dataStack.pop();
        //但务必注意：
        //由于minStack中的元素和dataStack中的元素个数是相同的，只是minStack中存的都是每次存入dataStack中元素的最小值
        //因此当从dataStack中每弹出一个元素时，也要把minStack中的第一个元素弹出，以保证minStack的栈顶元素始终为剩余元素的最小值
        minStack.pop();
    }

    // 3.（简单）查看栈顶部的元素（但不从堆栈中移除它），其实就是调用peek（）方法即可；考的是我们对stack的API是否熟悉
    public int top() {
        return dataStack.peek();
    }

    //4.（关键，但也不难）获取栈的最小元素，直接从minStack中弹出第一个元素即可
    public int min() {
        return minStack.peek();
    }
}
