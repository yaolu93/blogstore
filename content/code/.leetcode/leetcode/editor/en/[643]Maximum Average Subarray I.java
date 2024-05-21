//You are given an integer array nums consisting of n elements, and an integer 
//k. 
//
// Find a contiguous subarray whose length is equal to k that has the maximum 
//average value and return this value. Any answer with a calculation error less 
//than 10⁻⁵ will be accepted. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,12,-5,-6,50,3], k = 4
//Output: 12.75000
//Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
// 
//
// Example 2: 
//
// 
//Input: nums = [5], k = 1
//Output: 5.00000
// 
//
// 
// Constraints: 
//
// 
// n == nums.length 
// 1 <= k <= n <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// Related Topics Array Sliding Window 👍 3428 👎 309


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        // If the array has only one element, return the element itself as the maximum average
        if (nums.length == 1)
            return (double) nums[0];

        int start = 0;
        int end = k;
        double average = 0;

        // Calculate the initial average of the first k elements
        for (int i = 0; i < k; i++) {
            average = average + (double) nums[i] / k;
        }

        // Set the initial maximum average to the calculated average
        double max = average;

        while (end < nums.length) {
            // 尾巴减了，头部再加，就是滑动窗口
            average = average - (double) nums[start++] / k;
            average = average + (double) nums[end++] / k;

            // Update the maximum average if the current average is greater
            max = Math.max(average, max);
        }

        return max;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
//public double findMaxAverage(int[] nums, int k) {
//    double maxAverage = Double.NEGATIVE_INFINITY;
//    for (int left = 0; left <= nums.length - k; left++) {
//        double sum = 0.0;
//        int i = left;
//        int right = left + k;
//
//        while (i < right) {
//            sum += nums[i];
//            i++;
//        }
//
//        double average = sum / k;
//        maxAverage = Math.max(maxAverage, average);
//    }
//
//    return maxAverage;
//}


//public double findMaxAverage(int[] nums, int k) {
//    double maxAverage = Double.NEGATIVE_INFINITY;
//    ArrayList<Integer> numsList = new ArrayList<>();
//    for (int indexValue : nums) {
//        numsList.add(indexValue);
//    }
//
//    for (int i = 0; i <= nums.length - k; i++) {
//        double sum = 0.0;
//        int left = i;
//        int right = i + k;
//        while (left < right) {
//            sum += (double) numsList.get(left);
//            left++;
//        }
//        double average = sum / k;
//        maxAverage = Math.max(maxAverage, average);
//    }
//    return maxAverage;
//}