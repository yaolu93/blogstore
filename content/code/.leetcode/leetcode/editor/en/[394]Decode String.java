<<<<<<< HEAD
//Given an encoded string, return its decoded string. 
//
// The encoding rule is: k[encoded_string], where the encoded_string inside the 
//square brackets is being repeated exactly k times. Note that k is guaranteed to 
//be a positive integer. 
//
// You may assume that the input string is always valid; there are no extra 
//white spaces, square brackets are well-formed, etc. Furthermore, you may assume 
//that the original data does not contain any digits and that digits are only for 
//those repeat numbers, k. For example, there will not be input like 3a or 2[4]. 
//
// The test cases are generated so that the length of the output will never 
//exceed 10⁵. 
//
// 
// Example 1: 
//
// 
//Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
// 
//
// Example 2: 
//
// 
//Input: s = "3[a2[c]]"
//Output: "accaccacc"
// 
//
// Example 3: 
//
// 
//Input: s = "2[abc]3[cd]ef"
//Output: "abcabccdcdcdef"
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 30 
// s consists of lowercase English letters, digits, and square brackets '[]'. 
// s is guaranteed to be a valid input. 
// All the integers in s are in the range [1, 300]. 
// 
//
// Related Topics String Stack Recursion 👍 12531 👎 596


import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();

        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            } else {
                res += s.charAt(idx++);
            }
        }
        return res;
=======
import java.util.Stack;

/**
Given an encoded string, return its decoded string. 

 The encoding rule is: k[encoded_string], where the encoded_string inside the 
square brackets is being repeated exactly k times. Note that k is guaranteed to 
be a positive integer. 

 You may assume that the input string is always valid; there are no extra white 
spaces, square brackets are well-formed, etc. Furthermore, you may assume that 
the original data does not contain any digits and that digits are only for those 
repeat numbers, k. For example, there will not be input like 3a or 2[4]. 

 The test cases are generated so that the length of the output will never 
exceed 10⁵. 

 
 Example 1: 

 
Input: s = "3[a]2[bc]"
Output: "aaabcbc"
 

 Example 2: 

 
Input: s = "3[a2[c]]"
Output: "accaccacc"
 

 Example 3: 

 
Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

 
 Constraints: 

 
 1 <= s.length <= 30 
 s consists of lowercase English letters, digits, and square brackets '[]'. 
 s is guaranteed to be a valid input. 
 All the integers in s are in the range [1, 300]. 
 

 Related Topics String Stack Recursion 👍 12520 👎 595

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String decodeString(String s) {
        Stack<Object> objects = new Stack<>();
>>>>>>> 41f8f84ac21137086c2bb0cadf3e5d1370d3a6de
    }
}
//leetcode submit region end(Prohibit modification and deletion)
