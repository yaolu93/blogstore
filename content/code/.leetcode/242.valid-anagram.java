import java.util.Arrays;

/*
 * @lc app=leetcode id=242 lang=java
 *
 * [242] Valid Anagram
 *
 * https://leetcode.com/problems/valid-anagram/description/
 *
 * algorithms
 * Easy (59.97%)
 * Likes:    3648
 * Dislikes: 190
 * Total Accepted:    1M
 * Total Submissions: 1.7M
 * Testcase Example:  '"anagram"\n"nagaram"'
 *
 * Given two strings s and t, return true if t is an anagram of s, and false
 * otherwise.
 * 
 * 
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length, t.length <= 5 * 10^4
 * s and t consist of lowercase English letters.
 * 
 * 
 * 
 * Follow up: What if the inputs contain Unicode characters? How would you
 * adapt your solution to such a case?
 * 
 */

// @lc code=start
class Solution {
    public boolean isAnagram(String s, String t) {

     if(s.length() != t.length()) return false;
     
     int[] count_index = new int[26];

     for(int i=0; i < s.length(); i++){
         count_index[s.charAt(i)-'a']++;
         count_index[t.charAt(i)-'a']--;
     }
     for (int count : count_index) {
         if (count != 0) {
             return false;
         }
     }
     return true;
    }
}
// @lc code=end

