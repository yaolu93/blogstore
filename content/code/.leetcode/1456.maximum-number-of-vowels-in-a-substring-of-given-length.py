#
# @lc app=leetcode id=1456 lang=python3
#
# [1456] Maximum Number of Vowels in a Substring of Given Length
#
# https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
#
# algorithms
# Medium (61.47%)
# Likes:    3897
# Dislikes: 151
# Total Accepted:    652.8K
# Total Submissions: 1.1M
# Testcase Example:  '"abciiidef"\n3'
#
# Given a string s and an integer k, return the maximum number of vowel letters
# in any substring of s with length k.
# 
# Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
# 
# 
# Example 1:
# 
# 
# Input: s = "abciiidef", k = 3
# Output: 3
# Explanation: The substring "iii" contains 3 vowel letters.
# 
# 
# Example 2:
# 
# 
# Input: s = "aeiou", k = 2
# Output: 2
# Explanation: Any substring of length 2 contains 2 vowels.
# 
# 
# Example 3:
# 
# 
# Input: s = "leetcode", k = 3
# Output: 2
# Explanation: "lee", "eet" and "ode" contain 2 vowels.
# 
# 
# 
# Constraints:
# 
# 
# 1 <= s.length <= 10^5
# s consists of lowercase English letters.
# 1 <= k <= s.length
# 
# 
#

# @lc code=start
class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        isVowel = set('aeiou')
        sum_count = 0
        n = len(s)
       
        sum_count = sum(1 for i in range(k) if s[i] in isVowel)
        result_count = sum_count 
        for i in range(k, n):
            if s[i] in isVowel:
                sum_count += 1
            
            if s[i - k] in isVowel:
                sum_count -= 1
            
            result_count = max(sum_count, result_count)
        
        return result_count
        
# @lc code=end

