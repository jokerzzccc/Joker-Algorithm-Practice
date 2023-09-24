package com.joker.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 树上的操作
 * </p>
 *
 * @author: chenbolin
 * @date: 2023/9/24
 **/
public class leetcode1993 {

    public static void main(String[] args) {
        int[] parent = {-1, 0, 0, 1, 1, 2, 2};
        LockingTree lockingTree = new LockingTree(parent);
        System.out.println(lockingTree.lock(2, 2));    // 返回 true ，因为节点 2 未上锁。
        // 节点 2 被用户 2 上锁。
        System.out.println(lockingTree.unlock(2, 3));  // 返回 false ，因为用户 3 无法解锁被用户 2 上锁的节点。
        System.out.println(lockingTree.unlock(2, 2));  // 返回 true ，因为节点 2 之前被用户 2 上锁。
        // 节点 2 现在变为未上锁状态。
        System.out.println(lockingTree.lock(4, 5));    // 返回 true ，因为节点 4 未上锁。
        // 节点 4 被用户 5 上锁。
        System.out.println(lockingTree.upgrade(0, 1)); // 返回 true ，因为节点 0 未上锁且至少有一个被上锁的子孙节点（节点 4）。
        // 节点 0 被用户 1 上锁，节点 4 变为未上锁。
        System.out.println(lockingTree.lock(0, 1));    // 返回 false ，因为节点 0 已经被上锁了。
    }

    /**
     * 解法一：DFS
     */
    private static class LockingTree {


        /**
         * 记录每个节点的锁定状态，其中 locked[i] 表示节点 i 的锁定状态，
         * 如果节点 i 未被上锁，则 locked[i]=−1，否则 locked[i] 为锁定节点 i 的用户编号。
         */
        private int[] locked;
        /**
         * 记录每个节点的父节点
         */
        private int[] parent;
        /**
         * 记录每个节点的子节点
         */
        private List<Integer>[] childs;

        public LockingTree(int[] parent) {
            int n = parent.length;
            locked = new int[n];
            this.parent = parent;
            childs = new List[n];
            Arrays.fill(locked, -1);
            Arrays.setAll(childs, i -> new ArrayList<>());
            for (int i = 1; i < n; i++) {
                childs[parent[i]].add(i);
            }

        }

        public boolean lock(int num, int user) {
            if (locked[num] == -1) {
                locked[num] = user;
                return true;
            }
            return false;
        }

        public boolean unlock(int num, int user) {
            if (locked[num] == user) {
                locked[num] = -1;
                return true;
            }
            return false;
        }

        public boolean upgrade(int num, int user) {
            int x = num;
            // 判断当前节点及祖先节点有无上锁
            while (x != -1) {
                if (locked[x] != -1) {
                    return false;
                }
                x = parent[x];
            }

            // find: 是否至少有一个上锁状态的子节点
            boolean[] find = new boolean[1];
            dfs(num, find);
            if (!find[0]) {
                return false;
            }

            // 三个条件都满足的情况下，升级锁
            locked[num] = user;
            return true;
        }

        /**
         * 判断子节点，是否有无上锁的节点：
         * 如果有，则解锁，并返回符合条件的标记；
         *
         * @param num
         * @param find
         */
        private void dfs(int num, boolean[] find) {
            for (int y : childs[num]) {
                if (locked[y] != -1) {
                    locked[y] = -1;
                    find[0] = true;
                }
                dfs(y, find);
            }
        }
    }

}
