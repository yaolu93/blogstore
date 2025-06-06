/**
Given a string s, reverse only all the vowels in the string and return it. 

 The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower 
and upper cases, more than once. 

 
 Example 1: 
 Input: s = "hello"
Output: "holle"
 
 Example 2: 
 Input: s = "leetcode"
Output: "leotcede"
 
 
 Constraints: 

 
 1 <= s.length <= 3 * 10⁵ 
 s consist of printable ASCII characters. 
 

 Related Topics Two Pointers String 👍 4455 👎 2771

*/

//leetcode submit region begin(Prohibit modification and deletion)
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
//leetcode submit region end(Prohibit modification and deletion)
