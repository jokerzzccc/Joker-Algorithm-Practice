package com.joker.algorithm;

/**
 * <p>
 *
 * </p>
 *
 * @author jokerzzccc
 * @date 2023/4/28
 */
public class offer10 {

    public static void main(String[] args) {
        System.out.println(fib(45));
    }
    public static int fib(int n) {
        int[] ret = {0, 1};
        if (n < 2) {
            return ret[n];
        }
        int fibNMinusOne = 1;
        int fibNMinusTwo = 0;
        int fibN = 0;

        for (int i = 2; i <= n; ++i) {
            fibN = fibNMinusOne + fibNMinusTwo;

            fibNMinusTwo = fibNMinusOne;
            fibNMinusOne = fibN;
        }

        return fibN;
    }

}
