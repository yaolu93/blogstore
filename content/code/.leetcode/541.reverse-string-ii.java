/*
 * @lc app=leetcode id=541 lang=java
 *
 * [541] Reverse String II
 *
 * https://leetcode.com/problems/reverse-string-ii/description/
 *
 * algorithms
 * Easy (50.16%)
 * Likes:    1010
 * Dislikes: 2457
 * Total Accepted:    154.9K
 * Total Submissions: 308.6K
 * Testcase Example:  '"abcdefg"\n2'
 *
 * Given a string s and an integer k, reverse the first k characters for every
 * 2k characters counting from the start of the string.
 * 
 * If there are fewer than k characters left, reverse all of them. If there are
 * less than 2k but greater than or equal to k characters, then reverse the
 * first k characters and leave the other as original.
 * 
 * 
 * Example 1:
 * Input: s = "abcdefg", k = 2
 * Output: "bacdfeg"
 * Example 2:
 * Input: s = "abcd", k = 2
 * Output: "bacd"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 10^4
 * s consists of only lowercase English letters.
 * 1 <= k <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseStr(String s, int k) {

        int length = s.length();
        char [] cs = s.toCharArray();

        for (int l = 0; l < length; l = l + 2*k) {
            int r = l + k - 1;

            reverse(cs, l , Math.min(r, length - 1));

        }
        return String.valueOf(cs);

    }

    private void reverse(char[] cs, int l, int r) {
        while (l < r) {
            char tmp = cs[l];
            cs[l] = cs[r];
            cs[r] = tmp;
            l++;
            r--;
        }
    }



}
// @lc code=end

// class Solution {
//     public String reverseStr(String s, int k) {
//         char[] cs = s.toCharArray();
//         int length = s.length();

//         for (int l = 0; l < length; l = 2*k + l) {
//             int r = l + k - 1;

//             reverse(cs, l, Math.min(r, length - 1));
//             // reverse(cs, l, r);
//             //
//         }
//         return String.valueOf(cs);
        
//     }

//     public void reverse(char[] cs, int l , int r){
//         while(l < r ){

//             char tmp = cs[l];
//             cs[l] = cs[r];
//             cs[r] = tmp;
//             l++;
//             r--;
//         }
//     }
// }

