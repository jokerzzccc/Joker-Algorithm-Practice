import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * <p>
 * 基本计算器 II
 * </p>
 *
 * @author admin
 * @date 2023/7/21
 */
public class leetcode227 {

    public static void main(String[] args) {
        String s = "3+2*2";

        Solution01 solution01 = new Solution01();
        System.out.println(solution01.calculate(s));

    }

    /**
     * 解法一：双栈
     */
    private static class Solution01 {

        /**
         * 使用 map 维护一个运算符优先级
         * 这里的优先级划分按照「数学」进行划分即可
         */
        Map<Character, Integer> opsPrioritymap = new HashMap<Character, Integer>() {{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
            put('%', 2);
            put('^', 3);
        }};

        /**
         * 数字栈：存放所有的数字
         */
        LinkedList<Integer> nums = new LinkedList<>();
        /**
         * 操作符栈：存放所有「非数字以外」的操作
         */
        LinkedList<Character> ops = new LinkedList<>();

        public int calculate(String s) {
            // 将所有的空格去掉
            s = s.replaceAll(" ", "");
            int n = s.length();
            char[] strChars = s.toCharArray();

            // 为了防止第一个数为负数，先往 nums 加个 0
            nums.addLast(0);

            for (int i = 0; i < n; i++) {
                char curChar = strChars[i];

                if (curChar == '(') {
                    ops.addLast(curChar);
                } else if (curChar == ')') {
                    // 计算到最近一个左括号为止
                    while (!ops.isEmpty()) {
                        if (ops.peekLast() != '(') {
                            calculate();
                        } else {
                            // 遇到最近一个左括号，出栈，并结束计算
                            ops.pollLast();
                            break;
                        }
                    }
                } else {
                    if (Character.isDigit(curChar)) { // 是数字时
                        int curSerialNum = 0;
                        int j = i;
                        // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                        while (j < n && Character.isDigit(strChars[j])) {
                            curSerialNum = curSerialNum * 10 + (strChars[j++] - '0');
                        }

                        // 加入值栈
                        nums.addLast(curSerialNum);
                        // 因为j++ 加到 strChas[j] 不为数字了。
                        i = j - 1;
                    } else { // 是运算符时
                        // 为防止 () 内出现的首个字符为运算符，将所有的空格去掉，并将 (- 替换为 (0-，(+ 替换为 (0+
                        if (i > 0 && (strChars[i - 1] == '(' || strChars[i - 1] == '+' || strChars[i - 1] == '-')) {
                            nums.addLast(0);
                        }

                        // 需要将操作放入 ops 中，在放入之前先把栈内可以算的都算掉（只有「栈内运算符」比「当前运算符」优先级高/同等，才进行运算）
                        while (!ops.isEmpty() && ops.peekLast() != '(') {
                            char prev = ops.peekLast();
                            if (opsPrioritymap.get(prev) >= opsPrioritymap.get(curChar)) {
                                calculate();
                            } else {
                                break;
                            }
                        }

                        // 运算符入栈
                        ops.addLast(curChar);
                    }

                }

            }

            // 将剩余的计算完
            while (!ops.isEmpty()) {
                calculate();
            }

            return nums.peekLast();
        }

        /**
         * 计算当前可计算的数字
         */
        void calculate() {
            if (nums.isEmpty() || nums.size() < 2) return;
            if (ops.isEmpty()) return;

            int b = nums.pollLast(), a = nums.pollLast();
            char op = ops.pollLast();
            int ans = 0;
            if (op == '+') ans = a + b;
            else if (op == '-') ans = a - b;
            else if (op == '*') ans = a * b;
            else if (op == '/') ans = a / b;

            nums.addLast(ans);
        }

    }

}
