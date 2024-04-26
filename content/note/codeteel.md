


F12: console: javascript:document.cookie

leetcode-cli:

leetcode show 1 -g -l java

lc version -v
lc stat
lc config -a
Login with your leetcode account        $ leetcode user -l
Browse all questions                    $ leetcode list
Choose one question                     $ leetcode show 1 -g -l cpp
Coding it!
Run test(s) and pray...                 $ leetcode test ./two-sum.cpp -t '[3,2,4]\n7'
Submit final solution!                  $ leetcode submit ./two-sum.cpp
##codespace
gh codespace list

https://docs.github.com/en/codespaces/developing-in-a-codespace/using-github-codespaces-with-github-cli  

###
异或运算就是无进位相加

0^N = N  | N^N = 0
满足交换律和结合律

### 内存需要是独立的
a = a^b;
b = a^b;
a = a^b;

### P3:
递归实际上利用的是系统栈
Integer -127 ~ 127（==） 相等值传递， 128就不相等引用传递，传递地址值。 就用 equal（）才行。
没有天赋不天赋的，牛逼的都去搞数学物理了。

### P4：
讲的排序是基于左讲的衍生的
### P5:
修改堆比较器的时候，一般都是需要手动修改的， 不然时间复杂度都不会达到理想的效果。 图之中对继续讲

### P6:
前缀树（数）非常重要的哇，在后面会讲，二叉树的所有套路

### P7：

### P8:
二叉树的递归套路

### P9:
二叉樹递归套路可以解決面試中絕大多數的二叉樹問題（95%以上），尤其是樹形DP問題

就两个套路： 二叉树的递归套路，暴力递归改动态规划
本質是利用递归遍歷二叉樹的便利性
* 1）假设以X节点为头，假设可以向左数和右树要信息。X
* 2）在上一步的假设下，讨论X以为头节点的树，得到答案的可行性（最重要）
* 3）列出所有可能性后，确定到底需要向左树和右树要什么信息
* 4）把左树和右树信息求全集，就是任何一颗子树都需要返回的信息S
* 5）递归函数都返回S，每一颗子树都这么要求
* 6）写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息。

### P10:
打表技巧和矩阵处理技巧
就刷前150 高频题目就可以了
讲基础算法的意义是，就是哪怕是题目变一下，也是一样的解法

### P11:
并查集，和图相关的算法 Disjoint-Set or Union-find 具体算法推论25年才推论出来，所以不要深究
#### 贪心算法，
* 1）最自然智慧的算法
* 2）用一种局部最功利的标准，总是做出在当前看来是最好的选择 
* 3）难点在于证明布局最功利的标准可以得到全局最优解
* 4）对于贪心算法的学习主要以增加阅历和经验为主

显示描述暴力递归，后来一步步展示动态规划过程
### P12:
#### 暴力递归
暴力递归就是尝试
#### 图？

### P13:
#### 动态规划
设计暴力递归：重要原则+4种常见尝试模型
### P14:

### P15:

### P16:
### P17:
#### 动态规划
动态规划最后一集，我们应该怎么猜问题
