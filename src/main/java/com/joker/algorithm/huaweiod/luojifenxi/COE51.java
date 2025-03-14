package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.*;

/**
 * VLAN资源池
 * 题目描述
 * VLAN是一种对局域网设备进行逻辑划分的技术，为了标识不同的VLAN，引入VLAN ID(1-4094之间的整数)的概念。
 * 定义一个VLAN ID的资源池(下称VLAN资源池)，资源池中连续的VLAN用开始VLAN-结束VLAN表示，不连续的用单个整数表示，所有的VLAN用英文逗号连接起来。
 * 现在有一个VLAN资源池，业务需要从资源池中申请一个VLAN，需要你输出从VLAN资源池中移除申请的VLAN后的资源池。
 * 输入描述
 * 第一行为字符串格式的VLAN资源池，第二行为业务要申请的VLAN，VLAN的取值范围为[1,4094]之间的整数。
 * 输出描述
 * 从输入VLAN资源池中移除申请的VLAN后字符串格式的VLAN资源池，输出要求满足题目描述中的格式，并且按照VLAN从小到大升序输出。
 * 如果申请的VLAN不在原VLAN资源池内，输出原VLAN资源池升序排序后的字符串即可。
 * 示例1
 * 输入
 * 1-5
 * 2
 * 输出
 * 1,3-5
 * 说明
 * 原VLAN资源池中有VLAN 1、2、3、4、5，从资源池中移除2后，剩下VLAN 1、3、4、5，按照题目描述格式并升序后的结果为1,3-5
 * 示例2
 * 输入
 * 20-21,15,18,30,5-10
 * 15
 * 输出
 * 5-10,18,20-21,30
 * 说明
 * 原VLAN资源池中有VLAN 5、6、7、8、9、10、15、18、20、21、30，从资源池中移除15后，资源池中剩下的VLAN为 5、6、7、8、9、10、18、20、21、30，按照题目描述格式并升序后的结果为5-10,18,20-21,30。
 * 示例3
 * 输入
 * 5,1-3
 * 10
 * 输出
 * 1-3,5
 * 说明
 * <p>
 * 原VLAN资源池中有VLAN 1、2、3，5，申请的VLAN 10不在原资源池中，将原资源池按照题目描述格式并按升序排序后输出的结果为1-3,5。
 * <p>
 * ————————————————
 * 题型分析：
 * 【简单】模拟 + 字符串 +
 *
 * @author jokerzzccc
 * @date 2025/3/13
 */
public class COE51 {

    public static final String symbol = "-";

    public static void main(String[] args) {
        // 1. 输入处理
        Scanner input = new Scanner(System.in);
        String[] inputArr = input.nextLine().split(",");
        // 存储输入的VLAN资源池：将连续的拆开为单个数字
        List<Integer> singleVlanList = new ArrayList<>();
        for (String s : inputArr) {
            if (s.contains(symbol)) {
                int[] split = Arrays.stream(s.split(symbol)).mapToInt(Integer::parseInt).toArray();
                for (int i = split[0]; i <= split[1]; i++) {
                    singleVlanList.add(i);
                }
            } else {
                singleVlanList.add(Integer.parseInt(s));
            }
        }
        // 申请的资源池ID
        Integer applyVlan = Integer.parseInt(input.nextLine().trim());

        // 升序排序
        Collections.sort(singleVlanList);
        // 移除申请的资源池ID
        singleVlanList.remove(applyVlan);

        // 按顺序重新输出 VLAN 资源池
        StringBuilder result = new StringBuilder();
        int size = singleVlanList.size();
        // 将第一个数字添加进去
        result.append(singleVlanList.get(0));
        Integer last = singleVlanList.get(0);
        for (int i = 1; i < size; i++) {
            Integer curr = singleVlanList.get(i);

            if (curr - last == 1) {
                // 当前数字与上一个数字相差1，则说明是连续的
                if (result.toString().endsWith(symbol + last)) {
                    // 上一个与上上一个本就是连续的，则替换
                    result.replace(result.lastIndexOf(last.toString()), result.length(), curr.toString());
                } else {
                    result.append(symbol).append(curr);
                }
            } else {
                result.append(",").append(curr);
            }

            last = curr;
        }

        System.out.println(result);
        input.close();

    }

}
