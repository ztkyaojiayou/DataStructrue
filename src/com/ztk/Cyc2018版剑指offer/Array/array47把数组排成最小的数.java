package com.ztk.Cyc2018版剑指offer.Array;

import java.util.Arrays;

/**
 * 题目：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * （注意：每一个单独的数是一个整体，不可拆分和重排）
 * 例如输入数组{3， 32, 321}
 * 易知扫描输出这 3 个数字能排成数的可能情况为：
 * 3 32 321 即332321
 * 3 321 32 即332132
 * 32 3 321 即323321
 * 32 321 3 即323213
 * 321 3 32 即321332
 * 321 32 3 即321323（最小，即为所求）
 * 则易知最小数字为：321323。
 *
 *【解】：自定义排序规则，
 *      两个数字 m和n
 *      mn>nm： m>n
 *      mn==nm：m=n
 *      mn<nm： m<n
 *      但是这里隐含着一个大数问题，（mn拼接之后有可能超出int范围）
 *      所以，我们可以把数值的比较大小 直接转化为字符串的compare
 *      但这里我们先不考虑，好像牛客网也让过
 *
 *思路：可以看成是一个排序问题，先将各个元素从小到大进行排序，排序之后再把他们串联起来就可以了
 * 在这里自定义一个比较大小的函数，比较两个字符串s1, s2大小的时候，先将它们拼接起来，比较s1+s2,和s2+s1哪个大，
 * 如果s1+s2大，那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面
 */
public class array47把数组排成最小的数 {

    public String PrintMinNumber(int [] numbers) {
        //1.数组题的模板，为了严密起见，都要先把它写上
        if (numbers == null || numbers.length == 0)
            return "";

        /**
         * 2.通过两个变量对数组进行遍历，将数组中的前后两个数进行正反拼接之后再比较二者的大小，以此来调整这两个数的顺序
         *   根据规则，易知：若s1+s2>s2+s1（注意：这里的+号代表两个字符串作拼接，而不是数学运算）
         *   则把s2放在s1前面，也即把二者调换位置，依次类推
         */

        //2.1先遍历，找出两个数字
        for(int i=0; i < numbers.length; i++){
            for(int j = i+1; j < numbers.length; j++){
                /**
                 * Integer. valueOf()可以将基本类型int转换为包装类型Integer
                 * 或者将String转换成Integer，String如果为Null或“”都会报错
                 */
                //2.2再把其分别拼接成正反两个字符串，再转换为数字
                int append1 = Integer.valueOf(numbers[i]+""+numbers[j]);
                int append2 = Integer.valueOf(numbers[j]+""+numbers[i]);
                //2.3再对两个拼接后的数字进行比较，调换其对应的值的位置（而不是此拼接数，这只是一个辅助值），用到一个临时变量temp进行交换（老生常谈了）
                if(append1 > append2){
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }
        //3.此时原数组中的数已经是按照拼接后为最小数的顺序排列好了的，因此只需要把其一个一个拼接成一个字符串即可
        String str = new String("");
        for(int i=0; i < numbers.length; i++)
            str = str + numbers[i];//string中的+号是拼接字符串，而不是数学运算
        return str;
    }
}
