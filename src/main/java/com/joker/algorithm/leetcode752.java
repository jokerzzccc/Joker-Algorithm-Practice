package com.joker.algorithm;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * <p>
 * 打开转盘锁
 * </p>
 *
 * @author admin
 * @date 2023/6/25
 */
public class leetcode752 {

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        String target = "0202";

        Solution01 solution01 = new Solution01();
        int openLock01 = solution01.openLock(deadends, target);
        System.out.println(openLock01);

    }

    /**
     * 解法一：BFS
     */
    private static class Solution01 {

        public int openLock(String[] deadends, String target) {
            // 记录需要跳过的死亡密码
            Set<String> deads = new HashSet<>();
            for (String s : deadends) deads.add(s);
            // 记录已经穷举过的密码，防止走回头路
            Set<String> visited = new HashSet<>();
            Queue<String> queue = new LinkedList<>();
            // 从起点开始启动广度优先搜索
            int step = 0;
            queue.offer("0000");
            visited.add("0000");

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    String cur = queue.poll();

                    // 属于 dead, 直接跳过
                    if (deads.contains(cur)) {
                        continue;
                    }
                    /* 判断是否到达终点 */
                    if (cur.equals(target)) {
                        return step;
                    }

                    /* 将一个节点的未遍历相邻节点加入队列 */
                    for (int j = 0; j < 4; j++) {
                        // 将 s[j] 向上拨动一次
                        String up = plusOne(cur, j);
                        if (!visited.contains(up)) {
                            queue.offer(up);
                            visited.add(up);
                        }
                        // 将 s[j] 向下拨动一次
                        String down = minusOne(cur, j);
                        if (!visited.contains(down)) {
                            queue.offer(down);
                            visited.add(down);
                        }
                    }
                }
                /* 在这里增加步数 */
                step++;
            }

            return -1;
        }

        // 将 s[j] 向上拨动一次
        String plusOne(String s, int j) {
            char[] ch = s.toCharArray();
            if (ch[j] == '9')
                ch[j] = '0';
            else
                ch[j] += 1;
            return new String(ch);
        }

        // 将 s[i] 向下拨动一次
        String minusOne(String s, int j) {
            char[] ch = s.toCharArray();
            if (ch[j] == '0')
                ch[j] = '9';
            else
                ch[j] -= 1;
            return new String(ch);
        }

    }

}
