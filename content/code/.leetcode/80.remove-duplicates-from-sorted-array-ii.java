/*
 * @lc app=leetcode id=80 lang=java
 *
 * [80] Remove Duplicates from Sorted Array II
 */

// @lc code=start
class Solution {
    public int removeDuplicates(int[] nums) {
        int length = nums.length;
        int left = 0;
        // int right = 0;

        for (int right = 0; right < length ; right++) {
            if(left < 2 || nums[left - 2] != nums[right]){
                nums[left] = nums[right];
                left++;
            }
        }

        return left;

    }
}
// @lc code=end

 
// class Solution {
//     public int removeDuplicates(int[] nums) {
        
//         return process(nums, 2);
//     }

//     int process(int[] nums, int count) {
//         int index = 0; 
//         for (int ele : nums) {
//             if (index < count || nums[index - count] != ele) {
//                 nums[index++] = ele;
//             }
//         }
//         return index;
//     }

// }


