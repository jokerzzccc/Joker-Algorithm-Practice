package com.joker.algorithm;

/**
 * <p>
 * 表示数值的字符串
 * </p>
 *
 * @author admin
 * @date 2023/8/20
 */
public class offer20 {

    public static void main(String[] args) {
        String s = "3.1416";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.isNumber(s));

    }

    /**
     * 解法一：枚举模拟
     */
    private static class Solution01 {

        public boolean isNumber(String s) {
            // s为空对象或 s长度为0(空字符串)时, 不能表示数值
            if (s == null || s.length() == 0) {
                return false;
            }

            // 标记是否遇到数位、小数点、‘e’或'E'
            boolean isNum = false, isDot = false, ise_or_E = false;
            // 删除字符串头尾的空格，转为字符数组，方便遍历判断每个字符
            char[] str = s.trim().toCharArray();

            // 遍历，判断是否为合法字符串
            for (int i = 0; i < str.length; i++) {
                if (str[i] >= '0' && str[i] <= '9') { // 判断当前字符是否为 0~9 的数位
                    isNum = true;
                } else if (str[i] == '.') { // 遇到小数点
                    if (isDot || ise_or_E) return false; // 小数点之前可以没有整数，但是不能重复出现小数点、或出现‘e’、'E'
                    isDot = true; // 标记已经遇到小数点
                } else if (str[i] == 'e' || str[i] == 'E') { // 遇到‘e’或'E'
                    if (!isNum || ise_or_E) return false; // ‘e’或'E'前面必须有整数，且前面不能重复出现‘e’或'E'
                    ise_or_E = true; // 标记已经遇到‘e’或'E'
                    isNum = false; // 重置isNum，因为‘e’或'E'之后也必须接上整数，防止出现 123e或者123e+的非法情况
                } else if (str[i] == '-' || str[i] == '+') {
                    if (i != 0 && str[i - 1] != 'e' && str[i - 1] != 'E')
                        return false; // 正负号只可能出现在第一个位置，或者出现在‘e’或'E'的后面一个位置
                } else {
                    return false; // 其它情况均为不合法字符
                }
            }

            return isNum;
        }

    }

}
