/*
 * @lc app=leetcode id=1 lang=java
 *
 * [1] Two Sum
 *
 * https://leetcode.com/problems/two-sum/description/
 *
 * algorithms
 * Easy (48.53%)
 * Likes:    31605
 * Dislikes: 1003
 * Total Accepted:    6.5M
 * Total Submissions: 13.4M
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers nums and an integer target, return indices of the
 * two numbers such that they add up to target.
 * 
 * You may assume that each input would have exactly one solution, and you may
 * not use the same element twice.
 * 
 * You can return the answer in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= nums.length <= 10^4
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 * 
 * 
 * 
 * Follow-up: Can you come up with an algorithm that is less than O(n^2) time
 * complexity?
 */

// @lc code=start
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // test class here
        int numslength = nums.length;
        for (int i = 0; i < numslength; i++) {
            System.out.print("hello, World");
            for (int j = i+1; j < numslength; j++) {
                //why is while, it is not if
                while (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
		
            }
        }

        return null;

    }
}
// @lc code=end



// class Solution {
//     public int[] twoSum(int[] nums, int target) {
//         int lengths = nums.length;
//         for (int i = 0; i < lengths; i++) {
//             for (int j = 1+i; j < lengths; j++) {
//                 if (nums[i] + nums[j] == target) {
//                     return new int[]{i,j};
//                 }
//             }
//         }
//         return null;
//     }
// }

