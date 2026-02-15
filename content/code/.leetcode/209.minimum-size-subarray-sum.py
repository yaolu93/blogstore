#
# @lc app=leetcode id=209 lang=python3
#
# [209] Minimum Size Subarray Sum
#
# https://leetcode.com/problems/minimum-size-subarray-sum/description/
#
# algorithms
# Medium (50.93%)
# Likes:    14213
# Dislikes: 533
# Total Accepted:    1.8M
# Total Submissions: 3.5M
# Testcase Example:  '7\n[2,3,1,2,4,3]'
#
# Given an array of positive integers nums and a positive integer target,
# return the minimal length of a subarray whose sum is greater than or equal to
# target. If there is no such subarray, return 0 instead.
# 
# 
# Example 1:
# 
# 
# Input: target = 7, nums = [2,3,1,2,4,3]
# Output: 2
# Explanation: The subarray [4,3] has the minimal length under the problem
# constraint.
# 
# 
# Example 2:
# 
# 
# Input: target = 4, nums = [1,4,4]
# Output: 1
# 
# 
# Example 3:
# 
# 
# Input: target = 11, nums = [1,1,1,1,1,1,1,1]
# Output: 0
# 
# 
# 
# Constraints:
# 
# 
# 1 <= target <= 10^9
# 1 <= nums.length <= 10^5
# 1 <= nums[i] <= 10^4
# 
# 
# 
# Follow up: If you have figured out the O(n) solution, try coding another
# solution of which the time complexity is O(n log(n)).
#

# @lc code=start
class Solution:
    def minSubArrayLen(self, target: int, nums: List[int]) -> int:
        left = 0
        right = 0
        n = len(nums)
        min_length = float('inf')
        sum = 0
        
        for right in range(n):
            sum += nums[right]
            while sum >= target:
                min_length = min(min_length, right - left + 1) 
                sum -= nums[left]
                left += 1
                
            
        
        return min_length if min_length != float('inf') else 0 
        
# @lc code=end

