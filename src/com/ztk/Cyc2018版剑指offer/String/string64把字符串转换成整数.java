package com.ztk.Cyc2018版剑指offer.String;

/**
 * （正数时（isNegtive == 1），digit > 7 越界，负数时（isNegtive == -1），digit > 8 越界没太懂）
 * 题目：实现一个函数 stringToInt，实现把字符串转换成整数这个功能，
 *      要求不能使用 字符串转换整数（atoi） 或者其他类似的库函数。
 *      输入：输入一个字符串,包括数字字母符号,可以为空
 *      输出：如果是合法的数值表达则返回该数字，否则返回0
 *      如：分别输入两个字符串“+2147483647”和“1a33”
 *      则对应的输出分别为：“2147483647”和“0”
 *
 * 【解】：
 *   原理很简单，就是从前往后遍历，前一个数×10，加上当前数。
 *
 *   但要注意特殊输入：
 *    - 空指针null
 *    - 空字符串""
 *    - 正负号
 *    - 溢出（这一点非常重要，以前可以不考虑，现在要考虑了）
 *    - 非数字字符
 *
 *
 * 【分析】越界的简单解决方案：让符号位参与运算，并合理利用 INT_MAX/10
 *
 * 众所周知，这道题逻辑并不复杂，只是要判断结果是否出界却有点让人手忙脚乱，这里给出以下两种情景的解决方案：
 *
 * 能够处理最小负数：-2147483648
 * 判断是否超出最小负数 ~ 最大正数的范围
 * 一、关于最小负数：从true、false到 -1、1
 * 一般而言，我们习惯性地使用 boolean 类型来表示数字是否为负数（标志位 isNegtive），并在计算过程中使用正数来表示中间结果，只有在最后一步才根据标志 isNegtive 将结果取负，这样一样，因为最大正数的绝对值小于最小负数绝对值，所以当值为 INT_MIN 时，将导致结果出错
 * 事实上， 只要将正负标志的类型由 boolean 类型改成 int 类型，当符号为正时，标志为1，符号为负时，标志为 -1，从而使正负标志位可以参与到运算过程中去，就可以巧妙地解决这一问题，：
 *
 * 原先中间值的计算：value = value * 10 + digit;
 * 此时中间值的计算：value = value * 10 + isNegtive * digit;
 * 如此一来，当数值为负时，中间结果也为负，并执行的是减法操作，就可以取得 -2147483648 的值
 * 而当数值为正时，中间结果为正，执行的是加法操作。
 *
 * 二、关于数值越界：合理利用 INT_MAX/10
 * 数值越界，即大于 2147483647，或小于 -2147483648。通过观察程序结构，我们发现，每次循环时 value 的值都会扩大10倍（乘以10），那么，我们是否就可以利用 INT_MAX/10 的值来提前一步判断是否会越界呢？这里我们以正数的越界为例进行解释：
 *
 * 当 value > INT_MAX/10 时，说明本轮扩大10倍后，value 必将越界（超过 INT_MAX）；
 * 当 value == INT_MAX/10 时，说明扩大10倍后，value 可能越界，也可能不越界，需要利用当前的加数 digit 来进行进一步的判断：当 digit > 7 时（以正数为例），越界；
 * 否则，当 value < INT_MAX/10 时，本轮循环必不越界（扩大10倍后也小于 INT_MAX）；
 * 三、将正数、负数的越界判断合并起来：
 * 为了保证代码简洁高效，这里我们不得不寻求一种方法，使正数、负数的越界判断可以合并起来进行（同样，这里我们也利用了数值化的正负标记位 isNegtive）：
 * 我们设置一个变量 overValue 来表示当前的值和 INT_MAX/10 的差，因为 INT_MAX/10 为正数，所以当当前值为负数时，需要统一转化为正数，故而有：
 * overValue = isNegtive*value - INT_MAX/10;
 * 这样，当 overValue > 0 时，越界，overValue < 0 时，不越界，而当 overValue == 0 时：
 * 正数时（isNegtive == 1），digit > 7 越界，负数时（isNegtive == -1），digit > 8 越界，通过 (isNegtive+1)/2 来将 -1、1转换为0、1，从而使有关 digit 的判断统一转化为：
 *
 * 当 (isNegtive+1)/2 + digit > 8 时，数值越界；
 * 综上，令：
 * overValue = isNegtive*value - INT_MAX/10
 *           + (((isNegtive+1)/2 + digit > 8) ? 1:0);
 * 则当 overValue > 0 时，数值将会越界，反之，则不会
 */
public class string64把字符串转换成整数 {

    public int StrToInt(String str) {
        //最优解
        if(str == null || "".equals(str.trim()))return 0;
        str = str.trim();
        char[] arr = str.toCharArray();//先把传入的目标字符串转化为数组，方便处理
        int i = 0;//数组的索引
        int flag = 1;//用于标记正负号（正号：1，负号：-1）
        int res = 0;//返回结果
        if(arr[i] == '-'){//专门判断第一个字符的正负号
            flag = -1;
        }
        if( arr[i] == '+' || arr[i] == '-'){//确定第一个字符是是否为正负号
            i++;
        }
        while(i<arr.length ){//一个一个遍历
            //1.若是数字，分正负考虑
            if(isNum(arr[i])){//注意:这里的i很巧妙，当第一个字符是正负号时，这里的i则从第二个字符开始，否则，i还是从0开始
                int cur = arr[i] - '0';
                //预先处理其有可能越界
                //若为正数，则当res
                if(flag == 1 && (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && cur >7)){//“>7也是越界”是什么意思？
                    return 0;
                }
                //若为负数
                if(flag == -1 && (res > Integer.MAX_VALUE/10 || res == Integer.MAX_VALUE/10 && cur >8)){
                    return 0;
                }
                res = res*10 +cur;//关键代码，即 结果=前一个数×10，加上当前数（此时没有考虑正负号，在最后考虑，见line93）
                i++;
            }else{
                //2.若不是数字，直接返回0
                return 0;
            }
        }
        return res*flag;//最终确认正负号即为所求
    }
    //判断是否为数字的方法
    public static boolean isNum(char c){
        return c>='0'&& c<='9';
    }

}

