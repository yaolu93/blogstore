import java.util.*;

/**
Two strings are considered close if you can attain one from the other using the 
following operations: 

 
 Operation 1: Swap any two existing characters. 
 

 
 For example, abcde -> aecdb 
 
 
 Operation 2: Transform every occurrence of one existing character into another 
existing character, and do the same with the other character.
 
 For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into 
a's) 
 
 


 You can use the operations on either string as many times as necessary. 

 Given two strings, word1 and word2, return true if word1 and word2 are close, 
and false otherwise. 

 
 Example 1: 

 
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"
 

 Example 2: 

 
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa, in any 
number of operations.
 

 Example 3: 

 
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"
 

 
 Constraints: 

 
 1 <= word1.length, word2.length <= 10⁵ 
 word1 and word2 contain only lowercase English letters. 
 

 Related Topics Hash Table String Sorting Counting 👍 3603 👎 274

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean closeStrings(String word1, String word2) {

        // edge case, by using set to comparing the set size -- return false if not equal
        //by using map api getordefault to return the value size

        char[] word1CharArray = word1.toCharArray();
        char[] word2CharArray = word2.toCharArray();

        Set<Character> word1Set = new HashSet<>();
        Set<Character> word2Set = new HashSet<>();
        for (char char1 : word1CharArray){
            word1Set.add(char1);
        }
        for (char char2 : word2CharArray){
            word2Set.add(char2);
        }
        if (word1Set.size() != word2Set.size()){
            return false;
        }

        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char char1 : word1CharArray){
            word1Map.put(char1, word1Map.getOrDefault(char1, 0) + 1);
        }
        for (char char2 : word2CharArray){
            word2Map.put(char2, word2Map.getOrDefault(char2, 0) + 1);
        }
        // then I need double check, not only for size but also for key's value check
        // key contain && value is equal
        boolean result = false;

//        Set<Integer> checkSet1 = new HashSet<>();
//        for (int ele : word1Map.values()){
//            checkSet1.add(ele);
//        }
//
//        Set<Integer> checkSet2 = new HashSet<>();
//        for (int ele : word2Map.values()){
//            checkSet2.add(ele);
//        }
//
//        for (char ele : word1Map.keySet()){
//            if (word1Map.containsKey(ele) && word2Map.containsKey(ele) && checkSet1.equals(checkSet2)) {
//                result = true;
//            }
//        }
        List<Integer> word1List = new ArrayList<>();
        word1List.addAll(word1Map.values());
        List<Integer> word2List = new ArrayList<>();
        word2List.addAll(word2Map.values());

        Collections.sort(word1List);
        Collections.sort(word2List);

        if (word1List.equals(word2List)){
            result = true;

        }

        if (!word1Map.keySet().equals(word2Map.keySet())){
            result = false;
        }


        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
