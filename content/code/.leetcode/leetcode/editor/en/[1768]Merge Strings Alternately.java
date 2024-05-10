//You are given two strings word1 and word2. Merge the strings by adding 
//letters in alternating order, starting with word1. If a string is longer than the 
//other, append the additional letters onto the end of the merged string. 
//
// Return the merged string. 
//
// 
// Example 1: 
//
// 
//Input: word1 = "abc", word2 = "pqr"
//Output: "apbqcr"
//Explanation: The merged string will be merged as so:
//word1:  a   b   c
//word2:    p   q   r
//merged: a p b q c r
// 
//
// Example 2: 
//
// 
//Input: word1 = "ab", word2 = "pqrs"
//Output: "apbqrs"
//Explanation: Notice that as word2 is longer, "rs" is appended to the end.
//word1:  a   b 
//word2:    p   q   r   s
//merged: a p b q   r   s
// 
//
// Example 3: 
//
// 
//Input: word1 = "abcd", word2 = "pq"
//Output: "apbqcd"
//Explanation: Notice that as word1 is longer, "cd" is appended to the end.
//word1:  a   b   c   d
//word2:    p   q 
//merged: a p b q c   d
// 
//
// 
// Constraints: 
//
// 
// 1 <= word1.length, word2.length <= 100 
// word1 and word2 consist of lowercase English letters. 
// 
//
// Related Topics Two Pointers String 👍 3700 👎 86


import java.lang.reflect.Array;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String mergeAlternately(String word1, String word2) {
        int l = word1.length();
        int m = word2.length();
        int size = Math.max(l, m);
        Array[] arr = new Array[size];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            if(i < l ){
                sb.append(word1.charAt(i));
            }
            if(i < m ){
                sb.append(word2.charAt(i));
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//  cute method
//public String mergeAlternately(String word1, String word2) {
//    StringBuilder sb = new StringBuilder();
//    int n = word1.length();
//    int m = word2.length();
//    int i=0;
//    while(i<n && i<m){
//        sb.append(word1.charAt(i)).append(word2.charAt(i));
//        i++;
//    }
//    sb.append(word1.substring(i, n)).append(word2.substring(i, m));
//    return sb.toString();
//}