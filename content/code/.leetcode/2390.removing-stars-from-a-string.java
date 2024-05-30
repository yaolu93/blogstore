/*
 * @lc app=leetcode id=2390 lang=java
 *
 * [2390] Removing Stars From a String
 *
 * https://leetcode.com/problems/removing-stars-from-a-string/description/
 *n
 * algorithms
 * Medium (73.50%)
 * Likes:    2831
 * Dislikes: 200
 * Total Accepted:    296.6K
 * Total Submissions: 394.6K
 * Testcase Example:  '"leet**cod*e"'
 *
 * You are given a string s, which contains stars *.
 * 
 * In one operation, you can:
 * 
 * 
 * Choose a star in s.
 * Remove the closest non-star character to its left, as well as remove the
 * star itself.
 * 
 * 
 * Return the string after all stars have been removed.
 * 
 * Note:
 * 
 * 
 * The input will be generated such that the operation is always possible.
 * It can be shown that the resulting string will always be unique.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leet**cod*e"
 * Output: "lecoe"
 * Explanation: Performing the removals from left to right:
 * - The closest character to the 1^st star is 't' in "leet**cod*e". s becomes
 * "lee*cod*e".
 * - The closest character to the 2^nd star is 'e' in "lee*cod*e". s becomes
 * "lecod*e".
 * - The closest character to the 3^rd star is 'd' in "lecod*e". s becomes
 * "lecoe".
 * There are no more stars, so we return "lecoe".
 * 
 * Example 2:
 * 
 * 
 * Input: s = "erase*****"
 * Output: ""
 * Explanation: The entire string is removed, so we return an empty string.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^5
 * s consists of lowercase English letters and stars *.
 * The operation above can be performed on s.
 * 
 * 
 */

// @lc code=start

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Stack;

class Solution {
        public String removeStars(String s) {
            Stack<Character> stack = new Stack<>();

            for(char c : s.toCharArray()){
                if(c == '*'){
                    stack.pop();

                } else {
                    stack.push(c);


                    [
                     
]
                }
            }

            StringBuilder sb = new StringBuilder();

            for(char c : stack){
                sb.append(c);
            }

            return sb.toString();
        }
}
// @lc code=end


        // public String removeStars(String s) {
        //         int n = s.length();
        //         // need to use Stack or just need to use List
        //         Stack<Character> stack = new Stack<>();
        //         for (int i = 0; i < n; i++) {
        //                 System.out.println(i);
        //                 if (s.charAt(i) != '*') {
        //                     stack.push(s.charAt(i));
        //                     System.out.println(stack);

        //                 } else {
        //                     stack.pop();
        //                     System.out.println(stack);
        //                                                 }
        //         }
        //         // Map<Integer, Integer> sMap = new HashMap<>();
        //         StringBuilder sb = new StringBuilder();
        //         for(char c : stack){
        //             sb.append(c);
        //         }
        //         return sb.toString();
        // }
