//Given an integer array nums, move all 0's to the end of it while maintaining 
//the relative order of the non-zero elements. 
//
// Note that you must do this in-place without making a copy of the array. 
//
// 
// Example 1: 
// Input: nums = [0,1,0,3,12]
//Output: [1,3,12,0,0]
// 
// Example 2: 
// Input: nums = [0]
//Output: [0]
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -2³¹ <= nums[i] <= 2³¹ - 1 
// 
//
// 
//Follow up: Could you minimize the total number of operations done?
//
// Related Topics Array Two Pointers 👍 16430 👎 439


import static java.util.Collections.swap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void moveZeroes(int[] nums) {
        if (nums.length == 1) {
            return;
        }

        int nonZeroIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex++;
            }
        }
        // 這個方法很暴力哇
        while(nonZeroIndex<nums.length){
            nums[nonZeroIndex] = 0;
            nonZeroIndex++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


//public void moveZeroes(int[] nums) {
//    int size = nums.length;
//    int left = 0;
//    for (int right = 0; right < size; right++) {
//        if (nums[right] != 0){
//            swap(nums, left, right);
//            left++;
//        }
//    }
//}
//
//private void swap(int[] nums, int left, int right) {
//    int tmp = nums[left];
//    System.out.println(tmp);
//
//    nums[left] = nums[right];
//    System.out.println(nums[left]);
//
//    nums[right] = tmp;
//    System.out.println(nums[right]);
//}