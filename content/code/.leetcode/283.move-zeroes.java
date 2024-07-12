/*
 * @lc app=leetcode.cn id=283 lang=java
 *
 * [283] Move Zeroes
 *
 * https://leetcode.cn/problems/move-zeroes/description/
 *
 * algorithms
 * Easy (63.51%)
 * Likes:    2368
 * Dislikes: 0
 * Total Accepted:    1.4M
 * Total Submissions: 2.2M
 * Testcase Example:  '[0,1,0,3,12]'
 *
 * Given an integer array nums, move all 0's to the end of it while maintaining
 * the relative order of the non-zero elements.
 * 
 * Note that you must do this in-place without making a copy of the array.
 * 
 * 
 * Example 1:
 * Input: nums = [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * Example 2:
 * Input: nums = [0]
 * Output: [0]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 
 * 
 * 
 * Follow up: Could you minimize the total number of operations done?
 */

// @lc code=start



class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
        
    }
}
// @lc code=end



// I still like this solution but it just not matched the request
// import java.util.Arrays;

// class Solution {
//     public void moveZeroes(int[] nums) {
//         int left = 0;
//         int right = nums.length - 1;

//         Arrays.sort(nums);

//         reverse(nums, left, right);
//     }

//     public static void reverse(int[] nums, int left, int right){
//         while (left < right) {
//             int temp = nums[left];
//             nums[left] = nums[right];
//             nums[right] = temp;

//             left++;
//             right--;
//         }
//     }
// }


// class Solution {
//     public void moveZeroes(int[] nums) {

//         int snowball = 0;
        
//         for (int i = 0; i < nums.length; i++) {
//             if(nums[i] == 0){
//                 snowball++;
//             }
//             else if(snowball > 0) {
//                 // I am really hard to think about else if condition
//                 int temp = nums[i];
//                 nums[i] = 0;
//                 nums[i - snowball] = temp;
//             }
//         } 
//     }
// }



class Solution {
    public void moveZeroes(int[] nums) {

        if(nums == null || nums.length == 0){
            return;
        }

        int insertPos = 0;
        for (int num : nums ) {
            if(num != 0){
                nums[insertPos++] = num;
            }
        }

        while (insertPos < nums.length ) {
            nums[insertPos++] = 0;
        } 
    }
}


// same one
class Solution {
    public void moveZeroes(int[] nums) {
        if(nums==null) {
            return;
        }

        int j = 0;
        for(int i=0;i<nums.length;++i) {
            if(nums[i]!=0) {
                nums[j++] = nums[i];
            }
        }
        for(int i=j;i<nums.length;++i) {
            nums[i] = 0;
        }
    }
}	
