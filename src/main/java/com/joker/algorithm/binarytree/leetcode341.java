package com.joker.algorithm.binarytree;

import java.util.*;

/**
 * <p>
 * 扁平化嵌套列表迭代器
 * </p>
 *
 * @author admin
 * @date 2023/6/22
 */
public class leetcode341 {

    public static void main(String[] args) {

    }

    /**
     * 解法一：深度优先搜索
     * 惰性求值
     */
    public class NestedIterator implements Iterator<Integer> {

        private LinkedList<NestedInteger> list;

        public NestedIterator(List<NestedInteger> nestedList) {
            // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
            // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
            list = new LinkedList<>(nestedList);

        }

        @Override
        public Integer next() {
            // hasNext 方法保证了第一个元素一定是整数类型
            return list.remove(0).getInteger();
        }

        @Override
        public boolean hasNext() {
            // 循环拆分列表元素，直到列表第一个元素是整数类型
            while (!list.isEmpty() && !list.getFirst().isInteger()) {
                // 当列表开头第一个元素是列表类型时，进入循环
                List<NestedInteger> first = list.remove(0).getList();
                // 将第一个列表打平并按顺序添加到开头
                for (int i = first.size() - 1; i >= 0; i--) {
                    list.addFirst(first.get(i));
                }
            }
            return !list.isEmpty();

        }

    }

    /**
     * 题目的数据结构体
     */
    private static class NestedInteger {

        private Integer val;
        private List<NestedInteger> list;

        public NestedInteger(Integer val) {
            this.val = val;
            this.list = null;
        }

        public NestedInteger(List<NestedInteger> list) {
            this.list = list;
            this.val = null;
        }

        /**
         * 如果其中存的是一个整数，则返回 true，否则返回 false
         *
         * @return true if this NestedInteger holds a single integer, rather than a nested list.
         */
        public boolean isInteger() {
            return val != null;
        }

        /**
         * 如果其中存的是一个整数，则返回这个整数，否则返回 null
         *
         * @return the single integer that this NestedInteger holds, if it holds a single integer
         * Return null if this NestedInteger holds a nested list
         */

        public Integer getInteger() {
            return this.val;
        }

        /**
         * 如果其中存的是一个列表，则返回这个列表，否则返回 null
         *
         * @return the nested list that this NestedInteger holds, if it holds a nested list
         * Return empty list if this NestedInteger holds a single integer
         */
        //
        public List<NestedInteger> getList() {
            return this.list;
        }

    }

}
