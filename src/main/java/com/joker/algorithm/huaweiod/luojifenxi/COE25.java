package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 特异性双端队列
 * 题目描述
 * 有一个特异性的双端队列，该队列可以从头部或尾部添加数据，但是只能从头部移出数据。
 * <p>
 * 小A依次执行2n个指令往队列中添加数据和移出数据。其中n个指令是添加数据（可能从头部添加、也可能从尾部添加），依次添加1到n；n个指令是移出数据。
 * <p>
 * 现在要求移除数据的顺序为1到n。
 * <p>
 * 为了满足最后输出的要求，小A可以在任何时候调整队列中数据的顺序。
 * <p>
 * 请问 小A 最少需要调整几次才能够满足移除数据的顺序正好是1到n。
 * <p>
 * 输入描述
 * 第一行一个数据n，表示数据的范围。
 * <p>
 * 接下来的2n行，其中有n行为添加数据，指令为：
 * <p>
 * head add x表示从头部添加数据 x，
 * tail add x 表示从尾部添加数据x，
 * 另外 n 行为移出数据指令，指令为：remove 的形式，表示移出1个数据；
 * <p>
 * 1 ≤ n ≤ 3 * 10^5。
 * <p>
 * 所有的数据均合法。
 * <p>
 * 输出描述
 * 一个整数，表示 小A 要调整的最小次数。
 * <p>
 * 示例1
 * 输入
 * <p>
 * 5
 * head add 1
 * tail add 2
 * remove
 * head add 3
 * tail add 4
 * head add 5
 * remove
 * remove
 * remove
 * remove
 * <p>
 * 输出
 * <p>
 * 1
 * ————————————————
 * 题型分析
 * 双端队列 + 模拟
 *
 * @author jokerzzccc
 * @date 2025/3/12
 */
public class COE25 {

    public static final String HEAD = "head";
    public static final String TAIL = "tail";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        // 模拟题目中的双端队列
        Queue<Integer> queue = new LinkedList<>();
        // 是否可以按顺序删除
        boolean isOrder = true;
        // 最小的调整顺序次数
        int modifyCount = 0;

        for (int i = 0; i < 2 * n; i++) {
            String input_str = scanner.nextLine();
            String[] operation = input_str.split("\\s+");

            if (operation[0].equals(HEAD)) {
                // 因为添加是从 1-N 顺序添加的，如果从头部添加，必然是逆序的的，所以需要调整顺序
                // 所以就是没按顺序添加
                if (!queue.isEmpty() && isOrder) {
                    isOrder = false;
                }
                queue.add(Integer.parseInt(operation[2]));
            } else if (operation[0].equals(TAIL)) {
                // 从尾部添加就是可以按顺序删除的
                queue.add(Integer.parseInt(operation[2]));
            } else {
                if (queue.isEmpty()) {
                    continue;
                }
                // 不按顺序删除
                if (!isOrder) {
                    modifyCount++;
                    isOrder = true;
                }
                queue.poll();
            }

        }
        System.out.println(modifyCount);

    }

}
