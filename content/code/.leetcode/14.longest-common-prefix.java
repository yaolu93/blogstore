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
        //之前的题解zhen'sh
        int lengths = strs.length;
        String prefix = strs[0];
        
        for (int i = 1; i < lengths; i++) {
            while(!strs[i].startsWith(prefix)){
                prefix = prefix.substring(0, prefix.length()-1);
            }
            // while(strs[i].indexOf(prefix) != 0){
            //     prefix = prefix.substring(0, prefix.length()-1);
            // }
        }
        return prefix;

    }
}
// this one has not passed
// @lc code=end

