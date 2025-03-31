package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 简易内存池
 * <p>
 * 题目描述
 * 请实现一个简易内存池,根据请求命令完成内存分配和释放。
 * 内存池支持两种操作命令，REQUEST和RELEASE，其格式为：
 * REQUEST=请求的内存大小 表示请求分配指定大小内存，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error。
 * RELEASE=释放的内存首地址 表示释放掉之前分配的内存，释放成功无需输出，如果释放不存在的首地址则输出error。
 * 注意：
 * 内存池总大小为100字节。
 * 内存池地址分配必须是连续内存，并优先从低地址分配。
 * 内存释放后可被再次分配，已释放的内存在空闲时不能被二次释放。
 * 不会释放已申请的内存块的中间地址。
 * 释放操作只是针对首地址所对应的单个内存块进行操作，不会影响其它内存块。
 * 输入描述
 * 首行为整数 N , 表示操作命令的个数，取值范围：0 < N <= 100。
 * 接下来的N行, 每行将给出一个操作命令，操作命令和参数之间用 “=”分割。
 * 输出描述
 * 请求分配指定大小内存时，如果分配成功，返回分配到的内存首地址；如果内存不足，或指定的大小为0，则输出error
 * 释放掉之前分配的内存时，释放成功无需输出，如果释放不存在的首地址则输出error。
 * <p>
 * 示例1
 * 输入
 * 2
 * REQUEST=10
 * REQUEST=20
 * 输出
 * 0
 * 10
 * 说明
 * 无
 * <p>
 * 示例2
 * 输入
 * 5
 * REQUEST=10
 * REQUEST=20
 * RELEASE=0
 * REQUEST=20
 * REQUEST=10
 * 输出
 * 0
 * 10
 * 30
 * 0
 * 说明
 * 第一条指令，申请地址0~9的10个字节内存，返回首地址0
 * 第二条指令，申请地址10~29的20字节内存，返回首地址10
 * 第三条指令，释放首地址为0的内存申请，0~9地址内存被释放，变为空闲，释放成功，无需输出
 * 第四条指令，申请20字节内存，09地址内存连续空间不足20字节，往后查找到3049地址，返回首地址30
 * 第五条指令，申请10字节，0~9地址内存空间足够，返回首地址0
 * ————————————————
 * 题型分析：
 * 【困难】模拟 + 区间问题 + map + 一维数组
 *
 * @author jokerzzccc
 * @date 2025/3/31
 */
public class OD25A2007 {

    public static final int TOTAL_SIZE = 100;
    public static final String REQUEST = "REQUEST";
    public static final String RELEASE = "RELEASE";
    // key为分配的首地址， value为分配的长度
    // 主要是为了方便 release 操作
    private static final Map<Integer, Integer> m_used_map = new HashMap<>();
    // 内存分配数组： 0-未分配，1-已分配
    public static final int[] memory_used_arr = new int[TOTAL_SIZE];

    static {
        Arrays.fill(memory_used_arr, 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().trim().split("=");
            String ops = input[0];
            int memory = Integer.parseInt(input[1]);
            switch (ops) {
                case REQUEST: {
                    int result = request(memory);
                    if (result != -1) { // 如果成功分配到内存
                        System.out.println(result); // 输出分配到的内存首地址
                    } else { // 如果没有分配到内存
                        System.out.println("error"); // 输出错误信息
                    }
                    break;
                }
                case RELEASE: {
                    boolean isSuccess = release(memory);
                    if (!isSuccess) {
                        System.out.println("error");
                    }
                    break;
                }

            }
        }

        scanner.close();
    }

    private static boolean release(int startAddr) {
        // 如果该首地址没有被分配，返回false
        if (!m_used_map.containsKey(startAddr)) {
            return false;
        }

        int size = m_used_map.get(startAddr);
        // 释放地址
        for (int i = startAddr; i < startAddr + size; i++) {
            memory_used_arr[i] = 0;
        }
        m_used_map.remove(startAddr);

        return true;
    }

    private static int request(int memorySize) {
        // 分配的内存首地址（-1 表示无法分配）
        int ans = -1;
        // 如果内存大小小于等于0，直接返回-1
        if (memorySize <= 0) {
            return ans;
        }

        // 寻找合适的内存区间：从第一个开始找，长度为 memorySize 的 可分配内存
        for (int left = 0; left < TOTAL_SIZE - memorySize; left++) {
            int cnt = 0;
            while (cnt < memorySize) {
                if (memory_used_arr[left + cnt] == 1) {
                    break;
                }
                cnt++;
            }

            // 如果循环到了size，说明可以分配地址
            if (cnt == memorySize) {
                ans = left;
                break;
            }
        }

        if (ans != -1) {
            // 找到了合适的可分配地址
            // 更新内存分配数组
            m_used_map.put(ans, memorySize);
            for (int i = ans; i < ans + memorySize; i++) {
                memory_used_arr[i] = 1;
            }
        }

        return ans;
    }

}
