package com.joker.algorithm.huaweiod.shuangzhizhen;

import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

/**
 * 单向链表中间节点
 * 题目描述
 * 给定一个单链表 L，请编写程序输出 L 中间结点保存的数据。
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。
 * 例如：
 * 给定 L 为 1→7→5，则输出应该为 7；
 * 给定 L 为 1→2→3→4，则输出应该为 3。
 * 输入描述
 * 每个输入包含 1 个测试用例。每个测试用例:
 * 第 1 行给出链表首结点的地址、结点总个数正整数 N (≤105)。
 * 结点的地址是 5 位非负整数，NULL 地址用 −1 表示。
 * 接下来有 N 行，每行格式为：
 * Address Data Next
 * 其中 Address 是结点地址，Data 是该结点保存的整数数据(0 ≤ Data ≤ 108)，Next 是下一结点的地址。
 * 输出描述
 * 对每个测试用例，在一行中输出 L 中间结点保存的数据。
 * 如果有两个中间结点，则输出第二个中间结点保存的数据。
 * ( 如果奇数个节点取中间，偶数个取偏右边的那个值)
 * <p>
 * 示例1
 * 输入
 * 00010 4
 * 00000 3 -1
 * 00010 5 12309
 * 11451 6 00000
 * 12309 7 11451
 * 输出
 * 6
 * 说明
 * 无
 * <p>
 * 示例2
 * 输入
 * 10000 3
 * 76892 7 12309
 * 12309 5 -1
 * 10000 1 76892
 * 输出
 * 7
 * <p>
 * 示例3
 * 输入
 * 00100 4
 * 00000 4 -1
 * 00100 1 12309
 * 33218 3 00000
 * 12309 2 33218
 * 输出
 * 3
 * ————————————————
 * 题型分析
 * 类似 leetcode 876 链表的中间节点
 * 【中等】快慢指针
 * 思考：用 Map 来存储链表
 *
 * @author jokerzzccc
 * @date 2025/3/18
 */
public class COE55 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        String headAddr = firstLine[0];
        int n = Integer.parseInt(firstLine[1]);

        // 每个节点的值和下一个节点的地址
        // K-当前节点地址，V-【数据，下一个节点的地址】
        HashMap<String, String[]> nodeMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] currRow = scanner.nextLine().split(" ");
            nodeMap.put(currRow[0], new String[]{currRow[1], currRow[2]});
        }

        // 初始化慢指针和快指针，均指向头节点
        String slow = headAddr;
        String fast = headAddr;
        // 快指针每次走两步，慢指针每次走一步，直到快指针到达链表末尾
        while (fast != null && nodeMap.containsKey(fast) && !Objects.equals(nodeMap.get(fast)[1], "-1")) {
            // fast 先走一步
            fast = nodeMap.get(fast)[1];
            fast = nodeMap.get(fast)[1]; // 快指针再走一步
            slow = nodeMap.get(slow)[1]; // 慢指针走一步
        }

        System.out.println(nodeMap.get(slow)[0]);
    }

}
