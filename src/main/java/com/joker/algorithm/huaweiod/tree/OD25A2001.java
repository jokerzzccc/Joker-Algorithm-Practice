package com.joker.algorithm.huaweiod.tree;

import java.util.Scanner;
import java.util.Stack;

/**
 * 二叉树中序遍历
 * <p>
 * 题目描述
 * 根据给定的二叉树结构描述字符串，输出该二叉树按照中序遍历结果字符串。中序遍历顺序为：左子树，根结点，右子树。
 * 输入描述
 * 大小写字母、左右大括号、逗号组成的字符串:字母代表一个节点值，左右括号内包含该节点的子节点。
 * 左右子节点使用逗号分隔，逗号前为空则表示左子节点为空，没有逗号则表示右子节点为空。
 * 二叉树节点数最大不超过100。
 * 注:输入字符串格式是正确的，无需考虑格式错误的情况。
 * 输出描述
 * 一个字符串为二叉树中序遍历各节点值的拼接结果。
 * <p>
 * 示例1
 * 输入
 * a{b{d,e{g,h{,i}}},c{f}}
 * 输出
 * dbgehiafc
 * 说明
 * ————————————————
 * <p>
 * 题型分析
 * 【困难】 栈 + 二叉树中序遍历
 * 思路：利用栈结构，找到匹配的得{,}，然后提取出根、以及其左右子树
 *
 * @author jokerzzccc
 * @date 2025/4/1
 */
public class OD25A2001 {

    public static final Character LEFT_BRACKET = '{';
    public static final Character RIGHT_BRACKET = '}';

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input_str = scanner.nextLine().trim();
        scanner.close();

        // 用于存储字符信息
        Stack<String> nodeStack = new Stack<>();
        // 记录左括号的位置
        Stack<Integer> leftIndicesStack = new Stack<>();

        for (char ch : input_str.toCharArray()) {
            if (ch == RIGHT_BRACKET) {
                // 当遇到右括号时， 弹出最近的左括号的位置
                int leftBraceIndex = leftIndicesStack.pop();
                StringBuilder subTree = new StringBuilder();

                // 拼接最近左括号后面的字符值
                while (nodeStack.size() > leftBraceIndex + 1) {
                    subTree.insert(0, nodeStack.pop());
                }

                // 移除左当前左括号
                nodeStack.pop();

                // 获取当前子树根节点
                String root = nodeStack.pop();
                // 分割子树，分为左子树和右子树
                String[] parts = subTree.toString().split(",", 2);
                String leftSubTree = parts.length > 0 ? parts[0] : "";
                String rightSubTree = parts.length > 1 ? parts[1] : "";

                // 拼接中序遍历结果，并将结果压入栈中
                nodeStack.push(leftSubTree + root + rightSubTree);
            } else if (ch == LEFT_BRACKET) {
                // 记录左括号的位置索引
                leftIndicesStack.push(nodeStack.size());
                nodeStack.push("{");
            } else {
                // 普通字符直接压入栈
                nodeStack.push(String.valueOf(ch));
            }
        }

        // 输出结果
        System.out.println(nodeStack.isEmpty() ? "" : nodeStack.pop());

    }

}
