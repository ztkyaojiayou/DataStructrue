package com.ztk.Cyc2018版剑指offer.BitOperation;

import java.util.BitSet;

/**
 * 题目：在字符串中找出第一个只出现一次的字符。
 * 例如输入"abaccdeff"，则输出'b'。
 *
 * 【解】：
 *     用HashMap统计每个字符出现的次数
 *     然后再扫描一次字符数组，当此字符出现次数为1时，直接return
 *     时间复杂度：2 * O(N)
 *
 * 最直观的解法是使用 HashMap 对出现次数进行统计，但是考虑到要统计的字符范围有限，
 * 因此可以使用整型数组【int[256]】代替 HashMap，从而将空间复杂度由 O(N) 降低为 O(1)。
 *
 * 这题我们看到题目后可以发现，因为他的字符串长度是有限的，而且我们需要找到只出现一次的字符，我们可以首先考虑用Hashmap这样的数据结构来把所有出现过的字符和他们对应的出现次数记录下来。接着，因为我们需要找到第一个只出现一次的字符，所以我们还需要把整个输入的字符串再遍历一遍，然后遍历时我们检测当前字符的出现次数在我们的hashmap里是不是1，如果我们一旦遇到符合条件的字符，直接返回他的位置，因为我们只要第一个符合条件的字符。所以这题分两步：
 *
 * 创建一个hashmap，然后遍历整个字符串一遍，记录下每个字符出现的次数
 * 再次遍历整个字符串，根据我们前面存储的hashmap找哪个字符只出现过一次，直接返回他的位置
 * 这里有个重点是，题目里说，字符需要区分大小写，因为我们这里是直接存储的字符，所以我们并没有改变他的大小写，所以不用进行额外的操作。但是如果题目产生变形，不需要区分大小写，我们可以在存储时把所有的字符都变成大写或者小写，放进hashmap的keyset里，然后找的时候也进行相应的变形，就可以了。
 */
public class BitOperation49第一个只出现一次的字符 {
    //方法一：利用整形数组，但还是使用HashMap的思想
    public int FirstNotRepeatingChar(String str) {
        //1.先创建一个整形数组，然后遍历整个字符串一遍，记录下每个字符出现的次数
        int[] cnts = new int[256];
        for (int i = 0; i < str.length(); i++)
            //这就是具体做法：把值当索引
            //即把字符串的每一个索引处的值当做我们自定义的整形数组的索引，
            //每来一个重复的值，就在数组中把该索引（就是这个值）的值加1，即相当于统计了字符串中每个值的个数
            cnts[str.charAt(i)]++;
        //2.再再遍历一次这个数组，把只出现过一次的值的索引直接返回，即为它的位置
        for (int i = 0; i < str.length(); i++)
            if (cnts[str.charAt(i)] == 1)
                return i;
        //3.否则返回-1，说明没有找到
        return -1;
    }
    //方法二：利用位运算（没太懂）
    //以上实现的空间复杂度还不是最优的。
    //考虑到只需要找到只出现一次的字符，那么需要统计的次数信息只有 0,1,更大，使用两个比特位就能存储这些信息。
    public int FirstNotRepeatingChar2(String str) {
        BitSet bs1 = new BitSet(256);
        BitSet bs2 = new BitSet(256);
        for (char c : str.toCharArray()) {//先把字符串转化为字符串数组，再遍历
            if (!bs1.get(c) && !bs2.get(c))
                bs1.set(c);     // 0 0 -> 0 1
            else if (bs1.get(c) && !bs2.get(c))
                bs2.set(c);     // 0 1 -> 1 1
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (bs1.get(c) && !bs2.get(c))  // 0 1
                return i;
        }
        return -1;
    }

}
