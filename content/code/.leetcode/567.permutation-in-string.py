#
# @lc app=leetcode id=567 lang=python3
#
# [567] Permutation in String
#
# https://leetcode.com/problems/permutation-in-string/description/
#
# algorithms
# Medium (48.41%)
# Likes:    12896
# Dislikes: 517
# Total Accepted:    1.4M
# Total Submissions: 2.9M
# Testcase Example:  '"ab"\n"eidbaooo"'
#
# Given two strings s1 and s2, return true if s2 contains a permutation of s1,
# or false otherwise.
# 
# In other words, return true if one of s1's permutations is the substring of
# s2.
# 
# 
# Example 1:
# 
# 
# Input: s1 = "ab", s2 = "eidbaooo"
# Output: true
# Explanation: s2 contains one permutation of s1 ("ba").
# 
# 
# Example 2:
# 
# 
# Input: s1 = "ab", s2 = "eidboaoo"
# Output: false
# 
# 
# 
# Constraints:
# 
# 
# 1 <= s1.length, s2.length <= 10^4
# s1 and s2 consist of lowercase English letters.
# 
# 
#
from collections import Counter

# @lc code=start
class Solution:
    def checkInclusion(self, s1: str, s2: str) -> bool:
        if len(s1) > len(s2):
            return False
        
        need_hashMap = Counter(s1)
        window_hashMap = Counter(s2[:len(s1)])
        
        if need_hashMap == window_hashMap:
            return True
        
        for i in range(len(s1), len(s2)):
            window_hashMap[s2[i]] += 1
            window_hashMap[s2[i - len(s1)]] -= 1
            
            if window_hashMap[s2[i - len(s1)]] == 0:
                del window_hashMap[s2[i - len(s1)]]
                
            if window_hashMap == need_hashMap:
                return True
        
        return False
        
# @lc code=end

