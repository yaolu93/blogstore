#
# @lc app=leetcode id=27 lang=python
#
# [27] Remove Element
#
import os

# @lc code=start
class Solution(object):
    def removeElement(self, nums, val):
        """
        :type nums: List[int]
        :type val: int
        :rtype: int
        """
        left = 0
        right = 0
        
        while right < len(nums): 
            if nums[right] != val: 
                nums[left] = nums[right]
                left += 1
            right += 1

        return left
# @lc code=end
