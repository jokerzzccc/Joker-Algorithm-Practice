# 目录

[toc]

# offer-07-重建二叉树

- 时间：2023-04-24
- 参考链接：
  - https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-di-gui-fa-qin/





# 1、题目





# 2、题解

思路：





算法：





代码：

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
    int[] preorder;//保留的先序遍历，方便递归时依据索引查看先序遍历的值

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //三个索引分别为
        //当前根的的索引
        //递归树的左边界，即数组左边界
        //递归树的右边界，即数组右边界
        return recur(0,0,inorder.length-1);
    }

    TreeNode recur(int pre_root, int in_left, int in_right){
        if(in_left > in_right) return null;// 相等的话就是自己
        TreeNode root = new TreeNode(preorder[pre_root]);//获取root节点
        int idx = map.get(preorder[pre_root]);//获取在中序遍历中根节点所在索引，以方便获取左子树的数量
        //左子树的根的索引为先序中的根节点+1 
        //递归左子树的左边界为原来的中序in_left
        //递归左子树的右边界为中序中的根节点索引-1
        root.left = recur(pre_root+1, in_left, idx-1);
        //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
        //递归右子树的左边界为中序中当前根节点+1
        //递归右子树的右边界为中序中原来右子树的边界
        root.right = recur(pre_root + (idx - in_left) + 1, idx+1, in_right);
        return root;

    }

}

```



debug 版：

```java


/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/4/24
 */
public class offer07 {

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder  = {9,3,15,20,7};
        Solution solution = new Solution();
        Solution.TreeNode treeNode = solution.buildTree(preorder, inorder);

        System.out.println(treeNode.toString());
    }

    static class Solution {
        HashMap<Integer, Integer> map = new HashMap<>();//标记中序遍历
        int[] preorder;//保留的先序遍历，方便递归时依据索引查看先序遍历的值

        public TreeNode buildTree(int[] preorder, int[] inorder) {
            this.preorder = preorder;
            //将中序遍历的值及索引放在map中，方便递归时获取左子树与右子树的数量及其根的索引
            for (int i = 0; i < inorder.length; i++) {
                map.put(inorder[i], i);
            }
            //三个索引分别为
            //当前根的的索引
            //递归树的左边界，即数组左边界
            //递归树的右边界，即数组右边界
            return recur(0, 0, inorder.length - 1);
        }

        TreeNode recur(int pre_root, int in_left, int in_right) {
            if (in_left > in_right) {
                return null;// 相等的话就是自己
            }
            TreeNode root = new TreeNode(preorder[pre_root]);//获取root节点
            int idx = map.get(preorder[pre_root]);//获取在中序遍历中根节点所在索引，以方便获取左子树的数量
            //左子树的根的索引为先序中的根节点+1
            //递归左子树的左边界为原来的中序in_left
            //递归左子树的右边界为中序中的根节点索引-1
            root.left = recur(pre_root + 1, in_left, idx - 1);
            //右子树的根的索引为先序中的 当前根位置 + 左子树的数量 + 1
            //递归右子树的左边界为中序中当前根节点+1
            //递归右子树的右边界为中序中原来右子树的边界
            root.right = recur(pre_root + (idx - in_left) + 1, idx + 1, in_right);
            return root;

        }

        class TreeNode {

            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }

        }
    }


}

```

