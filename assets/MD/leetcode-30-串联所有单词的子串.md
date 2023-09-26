# 目录

[toc]

# leetcode-30-串联所有单词的子串

- 时间：2023-07-13
- 参考链接：



# 1、题目

- 题目：https://leetcode.cn/problems/substring-with-concatenation-of-all-words/
- 难度：困难

给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 **长度相同**。

 s 中的 **串联子串** 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。

- 例如，如果 words = ["ab","cd","ef"]， 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。

返回所有串联字串在 s 中的开始索引。你可以以 **任意顺序** 返回答案。

![image-20230713210313410](https://2021-joker.oss-cn-shanghai.aliyuncs.com/java_img/image-20230713210313410.png)



# 2、题解

## 题目分析



## 解法一：滑动窗口

### 算法分析

此题是「[438. 找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)」的进阶版。不同的是第438题的元素是字母，而比题的元素是
单问。可以用类似「[438. 找到字符串中所有字母异位词的官方题解](https://leetcode.cn/problems/find-all-anagrams-in-a-string/solution/zhao-dao-zi-fu-chuan-zhong-suo-you-zi-mu-xzin/)」的方法二的滑动窗口来解这题。
记`words`的长度为m,words中每个单词的长度为n，s的长度为ls。

首先需要将 s 划分为单词组，每个单词的大小均为n(首尾除外)。这样的划分方法有n种，即先删去前`i(i=0~n一1)`个字母后，将剩下的字母进行划分，如果未尾有不到个字母也删去。对这种划分得到的单词数组分别使用滑动窗口对 words 进行类似于「字母异位词」的搜寻。
划分成单词组后，一个窗口包含s中前m个单词，用一个**哈希表 differ** 表示窗口中单词频次和 words 中单词频次之差。初始化 differ 时，出现在窗口中的单词，每出现一次，相应的值增加1，出现在 words 中的单词，每出现一次，相应的值减少1。然后将窗口右移，右侧会加入一个单词，左侧会移出一个单词，并对differ 做相应的更新。窗口移动时，若出现 differ 中值不为0的键的数量为0，则表示这个窗口中的单词频次和 words 中单词频次相同，窗口的左端点是一个待求的起始位置。划分的方法有种，做次滑动窗口后，即可找到所有的起始位置。



### 代码

```java


/**
 * <p>
 * 串联所有单词的子串
 * </p>
 *
 * @author admin
 * @date 2023/7/13
 */
public class leetcode30 {

    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};

        Solution01 solution01 = new Solution01();
        solution01.findSubstring(s, words).forEach(System.out::println);

    }

    /**
     * 解法一：滑动窗口
     * 核心原理就是：抱枕窗口的长度不变，s 窗口里对应单词出现的频次，与 words 中 word 出现的频次一样，则找到一个解，
     * 因为是任意排列，不用考虑顺序。
     */
    private static class Solution01 {

        public List<Integer> findSubstring(String s, String[] words) {
            int sLen = s.length();
            int wordNum = words.length;
            int wordLen = words[0].length();

            List<Integer> res = new ArrayList<>();

            // 分组，考虑单词划分可以从 0,1,...,wordLen-1 开始
            for (int i = 0; i < wordLen; i++) {
                // 最少保证s中需要划分的单词长度大于 words 字符总长
                if (i + wordNum * wordLen > sLen) {
                    break;
                }

                // differ 表示窗口中的单词频次和 words 中的单词频次之差（s的频次 - words 的频次），如果为0，说明窗口和words是完全匹配的
                // 窗口大小=单词大小*单词数量（即 words 字符总长）
                Map<String, Integer> differ = new HashMap<>();
                // 初始化窗口，窗口长度为 wordNum * wordLen (即 words 字符总长), 依次计算窗口里对 s 每个切分的单词的频次
                for (int j = 0; j < wordNum; j++) {
                    String word = s.substring(i + j * wordLen, i + (j + 1) * wordLen);
                    differ.put(word, differ.getOrDefault(word, 0) + 1);
                }
                // 遍历 words 中的 word ，对窗口里每个单词计算频度差值
                for (String word : words) {
                    differ.put(word, differ.getOrDefault(word, 0) - 1);
                    // 差值为0时，移除掉这个word
                    if (differ.get(word) == 0) {
                        differ.remove(word);
                    }
                }

                // 开始向右滑动窗口，每次滑动一个 wordLen 的长度
                for (int start = i; start < sLen - wordNum * wordLen + 1; start += wordLen) {
                    // start == i 时，就是可以判断初始状态是否匹配，不用滑动
                    if (start != i) {
                        // 窗口每次向右移动，右边进来的单词频度+1，左边出去的单词频度-1
                        // 右边的单词滑进来
                        String word = s.substring(start + (wordNum - 1) * wordLen, start + wordNum * wordLen);
                        differ.put(word, differ.getOrDefault(word, 0) + 1);
                        if (differ.get(word) == 0) {
                            differ.remove(word);
                        }
                        // 左边的单词滑出去
                        word = s.substring(start - wordLen, start);
                        differ.put(word, differ.getOrDefault(word, 0) - 1);
                        if (differ.get(word) == 0) {
                            differ.remove(word);
                        }
                    }

                    // 频次相同时，差值都为0，即全部被移除掉，就代表被匹配上了
                    // 窗口匹配的单词数等于 words 中对应的单词数，添加进结果集
                    if (differ.isEmpty()) {
                        res.add(start);
                    }
                }
            }

            return res;
        }

    }

}

```

输出：

```sh
0
9
```





### 复杂度分析

- 时间复杂度：O(ls * n),其中 ls 是输入 s 的长度，n 是 words 中每个单词的长度。需要做 n 次滑动窗
  口，每次需要遍历一次s。
- 空间复杂度：O(m×n),其中 m 是 words 的单词数，n 是 words 中每个单词的长度。每次滑动窗口时，需要用一个哈希表保存单词频次。







# THE END