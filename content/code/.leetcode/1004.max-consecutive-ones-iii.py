#
# @lc app=leetcode id=1004 lang=python3
#
# [1004] Max Consecutive Ones III
#
# https://leetcode.com/problems/max-consecutive-ones-iii/description/
#
# algorithms
# Medium (67.23%)
# Likes:    10317
# Dislikes: 182
# Total Accepted:    1.3M
# Total Submissions: 1.9M
# Testcase Example:  '[1,1,1,0,0,0,1,1,1,1,0]\n2'
#
# Given a binary array nums and an integer k, return the maximum number of
# consecutive 1's in the array if you can flip at most k 0's.
# 
# 
# Example 1:
# 
# 
# Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
# Output: 6
# Explanation: [1,1,1,0,0,1,1,1,1,1,1]
# Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
# 
# Example 2:
# 
# 
# Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
# Output: 10
# Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
# Bolded numbers were flipped from 0 to 1. The longest subarray is
# underlined.
# 
# 
# 
# Constraints:
# 
# 
# 1 <= nums.length <= 10^5
# nums[i] is either 0 or 1.
# 0 <= k <= nums.length
# 
# 
#

# @lc code=start
class Solution:
    def longestOnes(self, nums: List[int], k: int) -> int:
        max_length = 0
        left = 0
        right = 0
        n = len(nums)
        count = 0
        
        for right in range(n):
            
            if nums[right] == 0:
                count += 1
                
            while count > k:
                if nums[left] == 0:
                    count -= 1
                left += 1
                
            max_length = max(max_length, right - left + 1)
        
        return max_length
            
        
# @lc code=end

