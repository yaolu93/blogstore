/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 *
 * https://leetcode.com/problems/longest-common-prefix/description/
 *
 * algorithms
 * Easy (39.38%)
 * Likes:    7880
 * Dislikes: 2981
 * Total Accepted:    1.6M
 * Total Submissions: 4M
 * Testcase Example:  '["flower","flow","flight"]'
 *
 * Write a function to find the longest common prefix string amongst an array
 * of strings.
 * 
 * If there is no common prefix, return an empty string "".
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] consists of only lower-case English letters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return null;
        }
        
        int num = strs[0].toCharArray().length;
        int lengths = strs.length;

        for (int i = 0; i < num; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < lengths; j++) {
                if (c != strs[j].charAt(i)  || i == strs[j].length()) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
// @lc code=end

