import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Given an array of integers arr, return true if the number of occurrences of 
each value in the array is unique or false otherwise. 

 
 Example 1: 

 
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values 
have the same number of occurrences. 

 Example 2: 

 
Input: arr = [1,2]
Output: false
 

 Example 3: 

 
Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
 

 
 Constraints: 

 
 1 <= arr.length <= 1000 
 -1000 <= arr[i] <= 1000 
 

 Related Topics Array Hash Table 👍 4989 👎 130

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        //api: getorDefault
        //key:1, value:3    key:2, value:2    key:3, value:1
        // my personal idea is using the count to comparing the hashmap's size()

        int n = arr.length;
        int count = 0;
        Map<Integer, Integer> arrMap = new HashMap<>();
        Set<Integer> arrSet = new HashSet<>();

        for (int ele : arr){
            arrMap.put(ele, arrMap.getOrDefault(ele, 0) + 1);
            System.out.println(arrMap);
        }

        for (int ele : arrMap.values()){
            arrSet.add(ele);
            System.out.println(arrMap.keySet());

        }

        for (int ele : arrSet){
            if (arrMap.containsKey(ele)){
                count++;
            }
        }
        System.out.println(count);

        return arrMap.size() == arrSet.size();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
