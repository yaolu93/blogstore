/*
 * @lc app=leetcode id=229 lang=java
 *
 * [229] Majority Element II
 *
 * https://leetcode.com/problems/majority-element-ii/description/
 *
 * algorithms
 * Medium (51.94%)
 * Likes:    9642
 * Dislikes: 429
 * Total Accepted:    719.2K
 * Total Submissions: 1.4M
 * Testcase Example:  '[3,2,3]'
 *
 * Given an integer array of size n, find all elements that appear more than ⌊
 * n/3 ⌋ times.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3,2,3]
 * Output: [3]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1]
 * Output: [1]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [1,2]
 * Output: [1,2]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 * 
 * Follow up: Could you solve the problem in linear time and in O(1) space?
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> majorityElement(int[] nums) {
        if(nums == null || nums.length == 0){
            return new ArrayList<>();
        }

        List<Integer> res = new ArrayList<>();
        int number1 = 0, number2 = 0;
        int count1 = 0, count2 = 0;

        for (int i = 0; i < nums.length ; i++) {

            if(nums[i] == number1){
                count1++;
            }else if(nums[i] == number2){
                count2++;
            }else if(count1 == 0){
                number1 = nums[i];
                count1 = 1;
            }else if(count2 == 0){
                number2 = nums[i];
                count2 = 1;
            } else{
                count1--;
                count2--;
            }
        }

        count1 = 0;
        count2 = 0;

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == number1){
                count1++;
            } else if(nums[i] == number2){
                count2++;
            }
        }

        if(count1 > nums.length /3){
            res.add(number1);
        }
        if(count2 > nums.length /3){
            res.add(number2);
        }

        return res;

    }
}
// @lc code=end
