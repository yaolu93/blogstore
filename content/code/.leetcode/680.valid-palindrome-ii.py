#
# @lc app=leetcode id=680 lang=python3
#
# [680] Valid Palindrome II
#
# https://leetcode.com/problems/valid-palindrome-ii/description/
#
# algorithms
# Easy (43.92%)
# Likes:    8927
# Dislikes: 510
# Total Accepted:    1.1M
# Total Submissions: 2.5M
# Testcase Example:  '"aba"'
#
# Given a string s, return true if the s can be palindrome after deleting at
# most one character from it.
# 
# 
# Example 1:
# 
# 
# Input: s = "aba"
# Output: true
# 
# 
# Example 2:
# 
# 
# Input: s = "abca"
# Output: true
# Explanation: You could delete the character 'c'.
# 
# 
# Example 3:
# 
# 
# Input: s = "abc"
# Output: false
# 
# 
# 
# Constraints:
# 
# 
# 1 <= s.length <= 10^5
# s consists of lowercase English letters.
# 
# 
#

# @lc code=start
class Solution:
    def validPalindrome(self, s: str) -> bool:
    
        if len(s) == 0:
            return True
        
        left = 0
        right = len(s) - 1

        def isPalindrome(left, right):
            while left < right:
                if s[left] != s[right]:
                    return False
                left += 1
                right -= 1
            return True
        
        while left < right:
            if s[left] != s[right]:
               return (isPalindrome(left + 1, right) or isPalindrome(left, right - 1))
               left += 1
               right -= 1
               
            return True
        
    
        
# @lc code=end

