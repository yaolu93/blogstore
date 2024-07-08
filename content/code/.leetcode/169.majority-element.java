/*
 * @lc app=leetcode id=169 lang=java
 *
 * [169] Majority Element
 *
 * https://leetcode.com/problems/majority-element/description/
 *
 * algorithms
 * Easy (65.00%)
 * Likes:    19114
 * Dislikes: 624
 * Total Accepted:    3M
 * Total Submissions: 4.7M
 * Testcase Example:  '[3,2,3]'
 *
 * Given an array nums of size n, return the majority element.
 * 
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.
 * 
 * 
 * Example 1:
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 * 
 * 
 * Constraints:
 * 
 * 
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 * 
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 */

// @lc code=start

import java.util.Arrays;

class Solution {
    public int majorityElement(int[] nums) {

        int count = 0;
        int result = 0;
        
        for (int num : nums) {
            if(count == 0){
                result = num;
            }

            if(num != result){
                count--;
            } else{
                count++;
            }
        }
        return result;
    }
}
// @lc code=end



// class Solution {
//     public int majorityElement(int[] nums) {
//         int len = nums.length;
    // wtf? this one can be passed?
//         Arrays.sort(nums);
//         int index = (len / 2);
//         return nums[index];
//     }
// }
