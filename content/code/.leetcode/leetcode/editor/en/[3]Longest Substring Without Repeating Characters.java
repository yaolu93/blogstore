//Given a string s, find the length of the longest substring without repeating 
//characters. 
//
// 
// Example 1: 
//
// 
//Input: s = "abcabcbb"
//Output: 3
//Explanation: The answer is "abc", with the length of 3.
// 
//
// Example 2: 
//
// 
//Input: s = "bbbbb"
//Output: 1
//Explanation: The answer is "b", with the length of 1.
// 
//
// Example 3: 
//
// 
//Input: s = "pwwkew"
//Output: 3
//Explanation: The answer is "wke", with the length of 3.
//Notice that the answer must be a substring, "pwke" is a subsequence and not a 
//substring.
// 
//
// 
// Constraints: 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s consists of English letters, digits, symbols and spaces. 
// 
//
// Related Topics Hash Table String Sliding Window 👍 39188 👎 1849


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0;
        int[] charIndex = new int[128];
        Arrays.fill(charIndex, -1);
        int left = 0;

        for (int right = 0; right < n; right++) {
            if (charIndex[s.charAt(right)] >= left) {
                left = charIndex[s.charAt(right)] + 1;
            }
            charIndex[s.charAt(right)] = right;
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


//4.動態規劃

//public int lengthOfLongestSubstring(String s) {
//    //1.滑動窗口
//    //2.哈希表
//    //3.雙指針
//    int n = s.length();
//    int left = 0;
//    Set<Character> charSet = new HashSet<>();
//
//    int maxLength = 0;
//
//    for (int right = 0; right < n; right++) {
//        if (!charSet.contains(s.charAt(right))) {
//            charSet.add(s.charAt(right));
//            maxLength = Math.max(maxLength, right - left + 1);
//        } else {
//            while (charSet.contains(s.charAt(right))) {
//                charSet.remove(s.charAt(left));
//                left++;
//            }
//            charSet.add(s.charAt(right));
//        }
//    }
//    return maxLength;
//}


//public int lengthOfLongestSubstring(String s) {
//    //HashMap
//    int num = s.length();
//    int left = 0;
//    int maxLength = 0;
//
//    Map<Character, Integer> charMap = new HashMap<>();
//
//    for (int right = 0; right < num; right++) {
//        if(!charMap.containsKey(s.charAt(right))){
//            charMap.put(s.charAt(right), right);
//            maxLength = Math.max(maxLength, right - left +1);
//        } else {
//            while(charMap.containsKey(s.charAt(right))){
//                charMap.remove((s.charAt(left)));
//                left++;
//            }
//            charMap.put(s.charAt(right), right);
//
//        }
//    }
//    return maxLength;
//}


//public int lengthOfLongestSubstring(String s) {
//    //HashMap
//    int num = s.length();
//    int left = 0;
//    int maxLength = 0;
//
//    Map<Character, Integer> charMap = new HashMap<>();
//
//    for (int right = 0; right < num; right++) {
//        if(!charMap.containsKey(s.charAt(right)) || charMap.get(s.charAt(right)) < left){
//            // 因为这里的键不允许重复，所以，不能纯粹用 right来写，应该用，right 作为下标所获取的值来真正确定
//            charMap.put(s.charAt(right), right);
//            maxLength = Math.max(maxLength, right - left +1);
//        } else {
//            left = charMap.get(s.charAt(right)) + 1;
//            charMap.put(s.charAt(right), right);
//
//        }
//    }
//    return maxLength;
//}