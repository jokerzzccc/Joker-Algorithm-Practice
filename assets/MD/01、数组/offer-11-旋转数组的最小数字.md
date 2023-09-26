# 目录

[toc]

# offer-11-旋转数组的最小数字

- 时间：2023-04-29
- 



# 1、题目

- ​	https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
- 



# 2、解法

算法思路：

- 有序数组，采用二分法查找
- 

![image-20230429184811796](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230429184811796.png)



法一：

代码：

```java
class Solution {

    public int findMin(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > nums[j]) {
                i = m + 1;
            } else if (nums[m] < nums[j]) {
                j = m;
            } else {
                j--;
            }
        }
        return nums[i];

    }

}
```



法二：

![image-20230429185404905](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230429185404905.png)

最优解:

```java
class Solution {

    public int findMin(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int m = (i + j) / 2;
            if (nums[m] > nums[j]) {
                i = m + 1;
            } else if (nums[m] < nums[j]) {
                j = m;
            } else {
               int x = i;
                for (int k = i + 1; k < j; k++) {
                    if (nums[k] < nums[x]) {
                        x = k;
                    }
                }
                return nums[x];
            }
        }
        return nums[i];

    }

}

```

