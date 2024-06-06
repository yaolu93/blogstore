/*
 * @lc app=leetcode id=125 lang=java
 *
 * [125] Valid Palindrome
 *
 * https://leetcode.com/problems/valid-palindrome/description/
 *
 * algorithms
 * Easy (41.76%)
 * Likes:    3727
 * Dislikes: 5246
 * Total Accepted:    1.3M
 * Total Submissions: 3.1M
 * Testcase Example:  '"A man, a plan, a canal: Panama"'
 *
 * A phrase is a palindrome if, after converting all uppercase letters into
 * lowercase letters and removing all non-alphanumeric characters, it reads the
 * same forward and backward. Alphanumeric characters include letters and
 * numbers.
 * 
 * Given a string s, return true if it is a palindrome, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "A man, a plan, a canal: Panama"
 * Output: true
 * Explanation: "amanaplanacanalpanama" is a palindrome.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "race a car"
 * Output: false
 * Explanation: "raceacar" is not a palindrome.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = " "
 * Output: true
 * Explanation: s is an empty string "" after removing non-alphanumeric
 * characters.
 * Since an empty string reads the same forward and backward, it is a
 * palindrome.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 2 * 10^5
 * s consists only of printable ASCII characters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean isPalindrome(String s) {

        String fixed_string = "";
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) || Character.isLetter(c)) {
                fixed_string += c;
            }

            fixed_string = fixed_string.toLowerCase();
        }
        
        int a_pointer = 0;
        int b_pointer = fixed_string.length() - 1;

        while (a_pointer <= b_pointer) {
            if (fixed_string.charAt(a_pointer) != fixed_string.charAt(b_pointer)) {
                return false;
            }

            a_pointer += 1;
            b_pointer -= 1;
        }
        return true;

    }
}
// @lc code=end

