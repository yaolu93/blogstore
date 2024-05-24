//Given a binary array nums and an integer k, return the maximum number of 
//consecutive 1's in the array if you can flip at most k 0's. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
//Output: 6
//Explanation: [1,1,1,0,0,1,1,1,1,1,1]
//Bolded numbers were flipped from 0 to 1. The longest subarray is underlined. 
//
// Example 2: 
//
// 
//Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
//Output: 10
//Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
//Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// nums[i] is either 0 or 1. 
// 0 <= k <= nums.length 
// 
//
// Related Topics Array Binary Search Sliding Window Prefix Sum 👍 8380 👎 123


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestOnes(int[] nums, int k) {
        int maxConsecutive = 0;
        int zerosCount = 0;
        int left = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zerosCount++;
            }

            while (zerosCount > k) {
                if (nums[left] == 0) {
                    zerosCount--;
                }
                left++;
            }

            maxConsecutive = Math.max(maxConsecutive, right - left + 1);
        }

        return maxConsecutive;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
