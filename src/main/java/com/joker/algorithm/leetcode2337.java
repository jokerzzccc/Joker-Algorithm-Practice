package com.joker.algorithm;

/**
 * <p>
 * 移动片段得到字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/21
 */
public class leetcode2337 {

    public static void main(String[] args) {
        String start = "_L__R__R_";
        String target = "L______RR";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.canChange(start, target));

    }

    /**
     * 解法一：双指针
     */
    private static class Solution01 {

        public boolean canChange(String start, String target) {
            // 由于 L 和 R 无法互相穿过对方，那么去掉 _ 后的剩余字符应该是相同的，否则返回 false
            if (!start.replaceAll("_", "").equals(target.replaceAll("_", ""))) {
                return false;
            }

            // 双指针遍历 start,target，寻找为 false 的情况
            for (int i = 0, j = 0; i < start.length(); i++) {
                // 遍历到 start, target 都为 L,R 的情况
                if (start.charAt(i) == '_') {
                    continue;
                }
                while (target.charAt(j) == '_') {
                    j++;
                }

                // 如果当前字符为 L 且 i<j，由于 L 由于无法向右移动，返回 false；
                // 如果当前字符为 R 且 i>j，由于 R 由于无法向左移动，返回 false。
                if (i != j && (start.charAt(i) == 'L') == (i < j)) {
                    return false;
                }
                j++;
            }

            return true;

        }

    }

}
