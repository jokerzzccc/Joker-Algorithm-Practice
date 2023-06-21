package com.joker.algorithm.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * 二叉树的序列化与反序列化
 * </p>
 *
 * @author admin
 * @date 2023/6/21
 */
public class leetcode297 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        Codec01 codec01 = new Codec01();
        String serialize01 = codec01.serialize(root);
        System.out.println(serialize01);
        System.out.println(codec01.deserialize(serialize01));

        Codec02 codec02 = new Codec02();
        String serialize02 = codec02.serialize(root);
        System.out.println(serialize02);
        System.out.println(codec02.deserialize(serialize02));

        Codec03 codec03 = new Codec03();
        String serialize03 = codec03.serialize(root);
        System.out.println(serialize03);
        System.out.println(codec03.deserialize(serialize03));

    }

    /**
     * 解法一：前序遍历
     * 画图理解
     */
    static class Codec01 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            // 用于拼接字符串
            StringBuilder sb = new StringBuilder();
            doSerialize(root, sb);
            return sb.toString();
        }

        /* 辅助函数，将二叉树存入 StringBuilder */
        void doSerialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }

            /****** 前序遍历位置 ******/
            sb.append(root.val).append(SEP);
            /***********************/

            doSerialize(root.left, sb);
            doSerialize(root.right, sb);
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {

            // 将字符串转化成列表
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.addLast(s);
            }
            return doDeserialize(nodes);

        }

        /* 辅助函数，通过 nodes 列表构造二叉树 */
        private TreeNode doDeserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;

            String first = nodes.removeFirst();
            if (first.equals(NULL)) {
                return null;
            }
            /****** 前序遍历位置 ******/
            // 列表最左侧就是根节点
            TreeNode root = new TreeNode(Integer.parseInt(first));
            /***********************/

            root.left = doDeserialize(nodes);
            root.right = doDeserialize(nodes);

            return root;
        }

    }

    /**
     * 解法二：后序遍历
     */
    static class Codec02 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            // 用于拼接字符串
            StringBuilder sb = new StringBuilder();
            doSerialize(root, sb);
            return sb.toString();
        }

        /* 辅助函数，将二叉树存入 StringBuilder */
        void doSerialize(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(SEP);
                return;
            }

            doSerialize(root.left, sb);
            doSerialize(root.right, sb);

            /****** 后序遍历位置 ******/
            sb.append(root.val).append(SEP);
            /***********************/
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {
            // 将字符串转化成列表
            LinkedList<String> nodes = new LinkedList<>();
            for (String s : data.split(SEP)) {
                nodes.addLast(s);
            }
            return doDeserialize(nodes);

        }

        /* 辅助函数，通过 nodes 列表构造二叉树 */
        private TreeNode doDeserialize(LinkedList<String> nodes) {
            if (nodes.isEmpty()) return null;

            // 从后往前取出元素
            String last = nodes.removeLast();
            if (last.equals(NULL)) {
                return null;
            }
            // 因为是后序遍历的字符串，列表最右侧就是根节点
            TreeNode root = new TreeNode(Integer.parseInt(last));

            // 先构造右子树，后构造左子树
            root.right = doDeserialize(nodes);
            root.left = doDeserialize(nodes);

            return root;
        }

    }

    /**
     * 解法三：层序遍历
     */
    static class Codec03 {

        // 代表分隔符的字符
        String SEP = ",";
        // 代表 null 空指针的字符
        String NULL = "#";

        // Encodes a tree to a single string.
        // 把一棵二叉树序列化成字符串
        public String serialize(TreeNode root) {
            if (root == null) return "";
            StringBuilder sb = new StringBuilder();
            // 初始化队列，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            while (!q.isEmpty()) {
                TreeNode cur = q.poll();
                /* 层级遍历代码位置 */
                if (cur == null) {
                    sb.append(NULL).append(SEP);
                    continue;
                }
                sb.append(cur.val).append(SEP);
                /*****************/

                q.offer(cur.left);
                q.offer(cur.right);
            }

            return sb.toString();
        }

        // Decodes your encoded data to tree.
        // 把字符串反序列化成二叉树
        public TreeNode deserialize(String data) {
            if (data.isEmpty()) return null;
            String[] nodes = data.split(SEP);
            // 第一个元素就是 root 的值
            TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));

            // 队列 q 记录父节点，将 root 加入队列
            Queue<TreeNode> q = new LinkedList<>();
            q.offer(root);

            for (int i = 1; i < nodes.length; ) {
                // 队列中存的都是父节点
                TreeNode parent = q.poll();
                // 父节点对应的左侧子节点的值
                String left = nodes[i++];
                if (!left.equals(NULL)) {
                    parent.left = new TreeNode(Integer.parseInt(left));
                    q.offer(parent.left);
                } else {
                    parent.left = null;
                }

                // 父节点对应的右侧子节点的值
                String right = nodes[i++];
                if (!right.equals(NULL)) {
                    parent.right = new TreeNode(Integer.parseInt(right));
                    q.offer(parent.right);
                } else {
                    parent.right = null;
                }
            }

            return root;
        }

    }

}
