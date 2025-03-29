package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 素数之积
 * <p>
 * 题目描述
 * RSA加密算法在网络安全世界中无处不在，它利用了极大整数因数分解的困难度，数据越大，安全系数越高，给定一个32位正整数，请对其进行因数分解，找出是哪两个素数的乘积。
 * 输入描述
 * 一个正整数num，0 < num <= 2147483647
 * 输出描述
 * 如果成功找到，以单个空格分割，从小到大输出两个素数，分解失败，请输出-1, -1
 * <p>
 * 用例
 * 输入	15
 * 输出	3 5
 * 输入	27
 * 输出	-1 -1
 * ————————————————
 * <p>
 * 【简单】 逻辑分析 + 素数判断
 *
 * @author jokerzzccc
 * @date 2025/3/28
 */
public class OD25A09 {

    public static void main(String[] args) {
        // 输入处理
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine().trim());
        scanner.close();
        //
        if (isPrime(num)) {
            System.out.println("-1 -1");
            return;
        }

        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                int j = num / i;
                if (isPrime(i) && isPrime(j)) {
                    System.out.println(i < j ? i + " " + j : j + " " + i);
                    return;
                }
            }
        }

        System.out.println("-1 -1");

    }

    /**
     * 判断一个数是否为素数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        if (num <= 3) {
            return num > 1;
        }
        if (num % 6 != 1 && num % 6 != 5) {
            return false;
        }
        for (int i = 5; i <= Math.sqrt(num); i += 6) {
            if (num % i == 0 || num % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

}
