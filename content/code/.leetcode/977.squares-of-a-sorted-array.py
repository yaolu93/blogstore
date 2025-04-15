#
# @lc app=leetcode id=977 lang=python
#
# [977] Squares of a Sorted Array
#
# https://leetcode.com/problems/squares-of-a-sorted-array/description/
#
# algorithms
# Easy (73.11%)
# Likes:    9613
# Dislikes: 251
# Total Accepted:    2.2M
# Total Submissions: 2.9M
# Testcase Example:  '[-4,-1,0,3,10]'
#
# Given an integer array nums sorted in non-decreasing order, return an array
# of the squares of each number sorted in non-decreasing order.
# 
# 
# Example 1:
# 
# 
# Input: nums = [-4,-1,0,3,10]
# Output: [0,1,9,16,100]
# Explanation: After squaring, the array becomes [16,1,0,9,100].
# After sorting, it becomes [0,1,9,16,100].
# 
# 
# Example 2:
# 
# 
# Input: nums = [-7,-3,2,3,11]
# Output: [4,9,9,49,121]
# 
# 
# 
# Constraints:
# 
# 
# 1 <= nums.length <= 10^4
# -10^4 <= nums[i] <= 10^4
# nums is sorted in non-decreasing order.
# 
# 
# 
# Follow up: Squaring each element and sorting the new array is very trivial,
# could you find an O(n) solution using a different approach?
#

# @lc code=start
class Solution(object):
#   def sortedsquares(self, nums):
#       """
#       :type nums: List[int]
#       :rtype: List[int]
#       """ 
#       result = []
#       for i in range(len(nums)):
#            result.append(nums[i]*nums[i])
#       
#       print(result)
#   
#       return sorted(result)
        
    def sortedSquares(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """ 
        n = len(nums)
        result = [0] * n

        left, right = 0, n - 1
        
        for i in range(n - 1, -1 , -1):
            if abs(nums[left]) > abs(nums[right]):
               result[i] = nums[left] * nums[left]
               left += 1
            else :
                result[i] = nums[right] * nums[right]
                right -= 1
        
        return result
                
# @lc code=end

