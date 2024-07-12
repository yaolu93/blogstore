/*
 * @lc app=leetcode id=217 lang=java
 *
 * [217] Contains Duplicate
 *
 * https://leetcode.com/problems/contains-duplicate/description/
 *
 * algorithms
 * Easy (61.93%)
 * Likes:    11965
 * Dislikes: 1298
 * Total Accepted:    4.1M
 * Total Submissions: 6.7M
 * Testcase Example:  '[1,2,3,1]'
 *
 * Given an integer array nums, return true if any value appears at least twice
 * in the array, and return false if every element is distinct.
 * 
 * 
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: true
 * Example 2:
 * Input: nums = [1,2,3,4]
 * Output: false
 * Example 3:
 * Input: nums = [1,1,1,3,3,4,3,2,4,2]
 * Output: true
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.Arrays;
class Solution {
    public boolean containsDuplicate(int[] nums) {
        
    }
}
// @lc code=end



// import java.util.Arrays;
// class Solution {
//     public boolean containsDuplicate(int[] nums) {
//         int length = nums.length -1; // be careful of the corner case
        
//         Arrays.sort(nums);
//         int left = 0;
//         for (int right = 1; right <= length ; right++) {
//             if(nums[left] == nums[right]){
//                 return true;
//             } else {
//                 left = right;
//             }
//         } 
//         return false;
//     }
    
// }


// time limit exceeded

//     public boolean containsDuplicate(int[] nums) {
//         // todo, double forloop method
//         boolean flag = false;

//         for (int i = 0; i < nums.length; i++) {
//             for (int j = i + 1; j < nums.length; j++) {
//                 if(nums[i] == nums[j]){
//                     flag = true;
//                 }
//              } 
//         }
//         return flag;
//     }
