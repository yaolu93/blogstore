#
# @lc app=leetcode id=187 lang=python3
#
# [187] Repeated DNA Sequences
#
# https://leetcode.com/problems/repeated-dna-sequences/description/
#
# algorithms
# Medium (52.84%)
# Likes:    3602
# Dislikes: 569
# Total Accepted:    514.6K
# Total Submissions: 973.9K
# Testcase Example:  '"AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"'
#
# The DNA sequence is composed of a series of nucleotides abbreviated as 'A',
# 'C', 'G', and 'T'.
# 
# 
# For example, "ACGAATTCCG" is a DNA sequence.
# 
# 
# When studying DNA, it is useful to identify repeated sequences within the
# DNA.
# 
# Given a string s that represents a DNA sequence, return all the
# 10-letter-long sequences (substrings) that occur more than once in a DNA
# molecule. You may return the answer in any order.
# 
# 
# Example 1:
# Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
# Output: ["AAAAACCCCC","CCCCCAAAAA"]
# Example 2:
# Input: s = "AAAAAAAAAAAAA"
# Output: ["AAAAAAAAAA"]
# 
# 
# Constraints:
# 
# 
# 1 <= s.length <= 10^5
# s[i] is either 'A', 'C', 'G', or 'T'.
# 
# 
#

# @lc code=start
class Solution:
    def findRepeatedDnaSequences(self, s: str) -> List[str]:
        
        n = len(s)
        seen = set()
        output = set()

        for i in range(n - 9):
            ten_letter_seq = s[i: i+10]  
            if ten_letter_seq in seen:
                output.add(ten_letter_seq)
            else:
                seen.add(ten_letter_seq)
        
        return list(output)
        
        

# @lc code=end

