package com.ztk.Cyc2018版剑指offer.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印一行。
 *
 * 【解】：
 *  和tree14的代码基本相同，只是少了反转操作
 * （剧透：后面还有一个类似的题，即题37：从上到下打印二叉树，思路也差不多一样）
 */

class TreeNode15 {
    int val = 0;
    TreeNode15 left = null;
    TreeNode15 right = null;

    public TreeNode15(int val) {
        this.val = val;

    }

}

public class tree15把二叉树打印成多行 {
    ArrayList<ArrayList<Integer>> Print(TreeNode15 pRoot) {

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        Queue<TreeNode15> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()) {
            ArrayList<Integer> list = new ArrayList<>();
            int cnt = queue.size();
            while (cnt-- > 0) {
                TreeNode15 node = queue.poll();
                if (node == null)
                    continue;
                list.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            //和tree14的代码基本相同，只是少了下面的反转操作
//            if (reverse) {
//                //反转list，用于逆序打印，此时list已经反转
//                Collections.reverse(list);
//            }
//            //由之前的分析知，奇数行顺序打印，偶数行逆序打印
//            // 又因为第一行为奇数行，则顺序打印，打印完之后通过语句reverse = !reverse反转一次，则第二行就变成了逆序打印啦
//            reverse = !reverse;//设置为true，使得下一行变成逆序打印
            if (list.size() != 0)
                ret.add(list);
        }
        return ret;
    }
}
