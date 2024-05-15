//Given two strings s and t, return true if s is a subsequence of t, or false 
//otherwise. 
//
// A subsequence of a string is a new string that is formed from the original 
//string by deleting some (can be none) of the characters without disturbing the 
//relative positions of the remaining characters. (i.e., "ace" is a subsequence of 
//"abcde" while "aec" is not). 
//
// 
// Example 1: 
// Input: s = "abc", t = "ahbgdc"
//Output: true
// 
// Example 2: 
// Input: s = "axc", t = "ahbgdc"
//Output: false
// 
// 
// Constraints: 
//
// 
// 0 <= s.length <= 100 
// 0 <= t.length <= 10⁴ 
// s and t consist only of lowercase English letters. 
// 
//
// 
//Follow up: Suppose there are lots of incoming 
//s, say 
//s1, s2, ..., sk where 
//k >= 10⁹, and you want to check one by one to see if 
//t has its subsequence. In this scenario, how would you change your code?
//
// Related Topics Two Pointers String Dynamic Programming 👍 9458 👎 517


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isSubsequence(String s, String t) {
        int sizeT = t.length();
        int sizeS = s.length();

        int i = 0;
        int j = 0;
        // s is smaller then t , i is smaller then j
        while ( j < sizeT && i < sizeS){ //之所以 i < sizeS 那是因为防止 sizeS 为 null
            if(s.charAt(i) == t.charAt(j)){
                i++;
            }
            j++;
        }

        return i == s.length();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//在上述示例中，我们将char类型的字符'A'使用String.valueOf()方法转换为String对象，由于String实现了CharSequence接口，因此该String对象也可以被视为CharSequence类型。