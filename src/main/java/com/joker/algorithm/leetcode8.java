package com.joker.algorithm;

import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.Objects;

/**
 * <p>
 * 字符串转换整数 (atoi)
 * </p>
 *
 * @author admin
 * @date 2023/7/5
 */
public class leetcode8 {

    private static final String NULL = "null";
    public static void main(String[] args) {
        User user = new User();
        System.out.println(NULL.equals(user.getName()));
        System.out.println(Objects.isNull(user.getName()));

    }


    static class User{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    /**
     * 解法一：
     */
//    private static class Solution01 {
//
//        public int myAtoi(String s) {
//
//        }
//
//    }

}
