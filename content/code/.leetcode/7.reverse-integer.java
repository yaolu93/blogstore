/*
 * @lc app=leetcode id=7 lang=java
 *
 * [7] Reverse Integer
 *
 * https://leetcode.com/problems/reverse-integer/description/
 *
 * algorithms
 * Medium (27.80%)
 * Likes:    11665
 * Dislikes: 12802
 * Total Accepted:    2.8M
 * Total Submissions: 10M
 * Testcase Example:  '123'
 *
 * Given a signed 32-bit integer x, return x with its digits reversed. If
 * reversing x causes the value to go outside the signed 32-bit integer range
 * [-2^31, 2^31 - 1], then return 0.
 * 
 * Assume the environment does not allow you to store 64-bit integers (signed
 * or unsigned).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: x = 123
 * Output: 321
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: x = -123
 * Output: -321
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: x = 120
 * Output: 21
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * -2^31 <= x <= 2^31 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int reverse(int x) {
        int temp = 0;
        int res = 0;
        while(x != 0){
            
            temp = x % 10;

                if(res > Integer.MAX_VALUE/10 || res < Integer.MIN_VALUE /10){
                    return 0;
                }
                        x = x/10;
            res = res* 10 + temp;
                
        }
        
        return res;
    }
}
// @lc code=end

