/*
 * @lc app=leetcode id=345 lang=java
 *
 * [345] Reverse Vowels of a String
 *
 * https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 *
 * algorithms
 * Easy (46.98%)
 * Likes:    1633
 * Dislikes: 1853
 * Total Accepted:    353.6K
 * Total Submissions: 751.8K
 * Testcase Example:  '"hello"'
 *
 * Given a string s, reverse only all the vowels in the string and return it.
 * 
 * The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both
 * cases.
 * 
 * 
 * Example 1:
 * Input: s = "hello"
 * Output: "holle"
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s.length <= 3 * 10^5
 * s consist of printable ASCII characters.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String reverseVowels(String s) {

        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n-1;
        while( i<j ){
            while( i<n && !isVowel(arr[i])){
                ++i;
            }
            while( j>0 && !isVowel(arr[j]) ){
                --j;
            }

            if( i < j ){
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
        
    }

    public boolean isVowel(char ch){
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j){
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
// @lc code=end

