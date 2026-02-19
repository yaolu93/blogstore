#
# @lc app=leetcode id=438 lang=python3
#
# [438] Find All Anagrams in a String
#
# https://leetcode.com/problems/find-all-anagrams-in-a-string/description/
#
# algorithms
# Medium (53.25%)
# Likes:    13136
# Dislikes: 374
# Total Accepted:    1.2M
# Total Submissions: 2.2M
# Testcase Example:  '"cbaebabacd"\n"abc"'
#
# Given two strings s and p, return an array of all the start indices of p's
# anagrams in s. You may return the answer in any order.
# 
# 
# Example 1:
# 
# 
# Input: s = "cbaebabacd", p = "abc"
# Output: [0,6]
# Explanation:
# The substring with start index = 0 is "cba", which is an anagram of "abc".
# The substring with start index = 6 is "bac", which is an anagram of "abc".
# 
# 
# Example 2:
# 
# 
# Input: s = "abab", p = "ab"
# Output: [0,1,2]
# Explanation:
# The substring with start index = 0 is "ab", which is an anagram of "ab".
# The substring with start index = 1 is "ba", which is an anagram of "ab".
# The substring with start index = 2 is "ab", which is an anagram of "ab".
# 
# 
# 
# Constraints:
# 
# 
# 1 <= s.length, p.length <= 3 * 10^4
# s and p consist of lowercase English letters.
# 
# 
#
from collections import Counter
# @lc code=start
class Solution:
    def findAnagrams(self, s: str, p: str) -> List[int]:
        if len(p) > len(s):
            return []
        result = []
        need_hashMap = Counter(p)
        window_hashMap = Counter(s[:len(p)])

        if need_hashMap == window_hashMap:
            result.append(0)
        
        for i in range(len(p),len(s)):
            window_hashMap[s[i]] += 1
            window_hashMap[s[i - len(p)]] -= 1
            
            if window_hashMap[s[i - len(p)]] == 0:
                del window_hashMap[s[i - len(p)]]
            
            if window_hashMap == need_hashMap:
                result.append(i - len(p) + 1)

            
        return result
# @lc code=end

