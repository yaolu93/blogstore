//Given a string s and an integer k, return the maximum number of vowel letters 
//in any substring of s with length k. 
//
// Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'. 
//
// 
// Example 1: 
//
// 
//Input: s = "abciiidef", k = 3
//Output: 3
//Explanation: The substring "iii" contains 3 vowel letters.
// 
//
// Example 2: 
//
// 
//Input: s = "aeiou", k = 2
//Output: 2
//Explanation: Any substring of length 2 contains 2 vowels.
// 
//
// Example 3: 
//
// 
//Input: s = "leetcode", k = 3
//Output: 2
//Explanation: "lee", "eet" and "ode" contain 2 vowels.
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 10⁵ 
// s consists of lowercase English letters. 
// 1 <= k <= s.length 
// 
//
// Related Topics String Sliding Window 👍 3427 👎 123


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxVowels(String s, int k) {
        String vowel = "aeiou";
        char[] input = s.toCharArray();
        int count = 0;
        int maxCount = 0;

        for (int left = 0; left < k; left++) {
            if (vowel.contains(String.valueOf(input[left]))) {
                count++;
            }
        }
        maxCount = count;

        new HashMap<>();
        for (int left = 0; left < input.length - k; left++) {
            int right = left + k ;
            // 下面的逻辑就是滑动窗口
            if (vowel.contains(String.valueOf(input[right]))) {
                count++;
            }
            if (vowel.contains(String.valueOf(input[left]))) {
                count--;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

//public int maxVowels(String s, int k) {
//    String vowel = "aeiou";
//    char[] input = s.toCharArray();
//    int count = 0;
//    for (int left = 0; left < k; left++) {
//        int right = left + k;
//        int i = left;
//        while (i <= right) {
//            if (vowel.contains(String.valueOf(input[left]))) {
//                count++;
//                System.out.println(count);
//                i++;
//            }
//            count = Math.max(count, 0);
//        }
//    }
//    return count;
//}


//public int maxVowels(String s, int k) {
//    int vowel = 0;
//    for(int i =0; i< k; i++)
//        if(isVowel(s.charAt(i))) vowel++;
//    int max = vowel;
//    for(int i = k; i < s.length(); i++){
//        if(isVowel(s.charAt(i))) vowel++;
//        if(isVowel(s.charAt(i-k))) vowel--;
//        max = Math.max(max, vowel);
//    }
//    return max;
//}
//private boolean isVowel(char c){
//    if((c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')) return true;
//    return false;
//}