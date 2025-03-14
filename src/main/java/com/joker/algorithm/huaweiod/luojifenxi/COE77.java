package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * 幼儿园分班
 * 题目描述
 * 儿园两个班的小朋友在排队时混在了一起，每位小朋友都知道自己是否与前面一位小朋友同班，请你帮忙把同班的小朋友找出来。
 * 小朋友的编号是整数，与前一位小朋友同班用Y表示，不同班用N表示。
 * 输入描述
 * 输入为空格分开的小朋友编号和是否同班标志。
 * 比如：6/N 2/Y 3/N 4/Y，表示4位小朋友，2和6同班，3和2不同班，4和3同班。
 * 其中，小朋友总数不超过999，每个小朋友编号大于0，小于等于999。
 * 不考虑输入格式错误问题。
 * 输出描述
 * 输出为两行，每一行记录一个班小朋友的编号，编号用空格分开，且：
 * 编号需按照大小升序排列，分班记录中第一个编号小的排在第一行。
 * 若只有一个班的小朋友，第二行为空行。
 * 若输入不符合要求，则直接输出字符串ERROR。
 * 示例1
 * 输入
 * 1/N 2/Y 3/N 4/Y
 * 输出
 * 1 2
 * 3 4
 * 说明
 * 2的同班标记为Y，因此和1同班。
 * 3的同班标记为N，因此和1、2不同班。
 * 4的同班标记为Y，因此和3同班。
 * 所以1、2同班，3、4同班，输出为
 * 1 2
 * 3 4
 * 示例2
 * 输入
 * 1/N 2/Y 3/N 4/Y 5/Y
 * 输出
 * 1 2
 * 3 4 5
 * ————————————————
 * 题型分析：
 * 【简单】 字符串处理 + 模拟
 *
 * @author jokerzzccc
 * @date 2025/3/15
 */
public class COE77 {

    public static final String SAME_CLASS = "Y";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputStrArr = scanner.nextLine().trim().split(" ");
        scanner.close();

        // 两个班级：
        // A 班的学生
        List<Integer> classA = new ArrayList<>();
        classA.add(Integer.parseInt(inputStrArr[0].split("/")[0]));
        // B 班的学生
        List<Integer> classB = new ArrayList<>();
        // K-学生编号，V-班级
        Map<Integer, String> studentClassMap = new HashMap<>();
        studentClassMap.put(Integer.parseInt(inputStrArr[0].split("/")[0]), "A");
        // 构造结果
        for (int i = 1; i < inputStrArr.length; i++) {
            String[] curr = inputStrArr[i].split("/");
            int currStuNum = Integer.parseInt(curr[0]);
            int lastStuNum = Integer.parseInt(inputStrArr[i - 1].split("/")[0]);
            // 模拟分班规则
            if (SAME_CLASS.equals(curr[1])) {
                if (studentClassMap.get(lastStuNum) == "A") {
                    classA.add(currStuNum);
                    studentClassMap.put(currStuNum, "A");
                } else {
                    classB.add(currStuNum);
                    studentClassMap.put(currStuNum, "B");
                }
            } else {
                if (studentClassMap.get(lastStuNum) == "A") {
                    classB.add(currStuNum);
                    studentClassMap.put(currStuNum, "B");
                } else {
                    classA.add(currStuNum);
                    studentClassMap.put(currStuNum, "A");
                }
            }
        }

        // 排序
        Collections.sort(classA);
        Collections.sort(classB);
        // 拼接输出字符串
        StringBuilder strA = new StringBuilder();
        StringBuilder strB = new StringBuilder();
        for (Integer item : classA) {
            strA.append(item).append(" ");
        }
        for (Integer item : classB) {
            strB.append(item).append(" ");
        }

        // 一个班为空的情况
        if (classB.size() == 0) {
            System.out.println(strA.toString().trim());
            System.out.println(strB.toString().trim());
            return;
        }

        // 排序输出
        if (classA.get(0) > classB.get(0)) {
            System.out.println(strB.toString().trim());
            System.out.println(strA.toString().trim());
        } else {
            System.out.println(strA.toString().trim());
            System.out.println(strB.toString().trim());
        }

    }

}
