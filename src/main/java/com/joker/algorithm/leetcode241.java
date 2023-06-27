package com.joker.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 为运算表达式设计优先级
 * </p>
 *
 * @author admin
 * @date 2023/6/27
 */
public class leetcode241 {

    public static void main(String[] args) {
        String expression = "2*3-4*5";

        Solution01 solution01 = new Solution01();
        List<Integer> waysToCompute01 = solution01.diffWaysToCompute(expression);
        waysToCompute01.stream().forEach(System.out::println);

    }

    /**
     * 解法一：分治法
     */
    private static class Solution01 {

        // 备忘录
        HashMap<String, List<Integer>> memo = new HashMap<>();

        public List<Integer> diffWaysToCompute(String expression) {
            // 避免重复计算
            if (memo.containsKey(expression)) {
                return memo.get(expression);
            }
            List<Integer> res = new ArrayList<>();
            for (int i = 0; i < expression.length(); i++) {
                char c = expression.charAt(i);
                // 扫描算式 input 中的运算符
                if (c == '+' || c == '-' || c == '*') {
                    /****** 分 ******/
                    // 以运算符为中心，分割成两个字符串，分别递归计算
                    List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                    List<Integer> right = diffWaysToCompute(expression.substring(i + 1));

                    /****** 治 ******/
                    // 通过子问题的结果，合成原问题的结果
                    for (int a : left) {
                        for (int b : right) {
                            if (c == '+') {
                                res.add(a + b);
                            } else if (c == '-') {
                                res.add(a - b);
                            } else if (c == '*')
                                res.add(a * b);
                        }
                    }
                }
            }

            // base case
            // 如果 res 为空，说明算式是一个数字，没有运算符
            if (res.isEmpty()) {
                res.add(Integer.parseInt(expression));
            }
            // 将结果添加进备忘录
            memo.put(expression, res);

            return res;
        }

    }

}
