package com.joker.algorithm.huaweiod.luojifenxi;

import java.util.Scanner;

/**
 * 经典屏保问题
 * 题目描述
 * DVD机在视频输出时，为了保护电视显像管，在待机状态会显示“屏保动画”，如下图所示，DVD Logo在屏幕内来回运动，碰到边缘会反弹。
 * 请根据如下要求，实现屏保Logo坐标的计算算法。
 * 屏幕是一个800*600像素的矩形，规定屏幕的左上角点坐标原点，沿横边向右方向为X轴，沿竖边向下方向为Y轴
 * Logo是一个50*25像素的矩形，初始状态下，左上角点坐标记做(x，y)，它在X和Y方向上均以1像素/秒的速度开始运动
 * 遇到屏幕四个边缘后，会发生镜面反弹，即以45°碰撞边缘，再改变方向以45°弹出
 * 当Logo和四个角碰撞时，两个边缘同时反弹的效果是Logo会原路返回
 * 请编码实现，t秒后Logo左上角点的坐标。
 * <p>
 * 输入描述
 * 输入3个数字，以空格分隔：
 * x y t
 * 第一个数字表示Logo左上角点的初始X坐标；
 * 第二个数字表示Logo左上角点的初始Y坐标；
 * 第三个数字表示时间 t，题目要求即求 t 秒后Logo左上角点的位置。
 * <p>
 * 输出描述
 * 输出2个数字，以空格分隔:
 * x y
 * 第一个数字表示 t 秒后，Logo左上角点的X坐标
 * 第二个数字表示 t 秒后，Logo左上角点的Y坐标
 * 备注
 * 所有用例均保证:
 * 输入的x和y坐标会保证整个Logo都在屏幕范围内，Logo不会出画；
 * 所有输入数据都是合法的数值，且不会出现负数；
 * t 的最大值为100000。
 * <p>
 * 用例1
 * 输入
 * 0 0 10
 * 输出
 * <p>
 * 10 10
 * 输入样例表示Logo初始位置在屏幕的左上角点，10s后，Logo在X和Y方向都移动了10像素，因此输出10 10。
 * <p>
 * 用例2
 * 输入
 * 500 570 10
 * 输出
 * 510 570
 * 输入样例表示初始状态下，Logo的下边缘再有5像素就碰到屏幕下边缘了，5s后，会与屏幕碰撞，碰撞后，斜向45弹出，又经过5s后，Logo与起始位置相比，水平移动了10像素，垂直方向回到了原来的高度。
 * ————————————————
 * 【中等】逻辑分析 + 坐标轴对称计算坐标
 * 思路：
 * 得到初始 x,y 后，我们可以直接让
 * x+=t
 * y+=t
 * 这其实就是相当于让红外线穿透镜面(如果足够大)之后，我们检查logo是否越界
 * ·若x+50>800,其中x+50是logo的右下角横坐标，则说明越界，此时我们需要沿着x=800轴进行对称反转，反转后x+50坐标变为了800-(x+50-800),进而得到反转后x=800-(x+50-800)-50
 * ·若y+25>600,其中y+25是logo的右下角纵坐标，则说明越界，此时我们y=600轴进行对称反转，反转后y+25坐标变为了600-
 * (y+25-600),进而得到反转后y=600-(y+25-600)-25
 * 当然除了上面logo越界情况，还有两个越界情况，如下：
 * ·若x<0,其中x是logo的左上角横坐标，则说明越界，此时我们需要沿着x=0轴进行对称反转，即反转后x=-x
 * ·若y<0,其中y是logo的左上角纵坐标，则说明越界，此时我们需要沿着y=0轴进行对称反转，即反转后y=-y
 * <p>
 * 理论知识补充：
 * 镜像变换是指将图形对象沿一条直线（称为对称轴）进行镜像。镜像变换的数学表示为：
 * [x', y'] = [2 * ax - x, 2 * ay - y]
 * 其中，(x, y) 是镜像前的坐标，(x’, y’) 是镜像后的坐标，(ax, ay) 是对称轴的坐标。
 *
 * @author jokerzzccc
 * @date 2025/3/29
 */
public class OD25A51 {

    /**
     * LOGO 左上角 x 坐标范围 [0 , 750]
     */
    public static final int X_UPPER = 750;
    public static final int X_LOWER = 0;
    public static final int Y_UPPER = 575;
    public static final int Y_LOWER = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x_startPos = scanner.nextInt();
        int y_startPos = scanner.nextInt();
        int time = scanner.nextInt();
        scanner.close();

        //
        int x = x_startPos + time;
        int y = y_startPos + time;
        // 一直进行镜面反弹，直到不超出边界
        while (x < X_LOWER || x > X_UPPER || y < Y_LOWER || y > Y_UPPER) {
            if (y > Y_UPPER) {
                y = 2 * Y_UPPER - y;
            }
            if (x > X_UPPER) {
                x = 2 * X_UPPER - x;
            }
            if (y < Y_LOWER) {
                y = -y;
            }
            if (x < X_LOWER) {
                x = -x;
            }

        }

        // 输出结果
        System.out.println(x + " " + y);

    }

}
