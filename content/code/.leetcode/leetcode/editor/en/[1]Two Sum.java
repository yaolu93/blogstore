/**
Given an array of integers nums and an integer target, return indices of the 
two numbers such that they add up to target. 

 You may assume that each input would have exactly one solution, and you may 
not use the same element twice. 

 You can return the answer in any order. 

 
 Example 1: 

 
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
 

 Example 2: 

 
Input: nums = [3,2,4], target = 6
Output: [1,2]
 

 Example 3: 

 
Input: nums = [3,3], target = 6
Output: [0,1]
 

 
 Constraints: 

 
 2 <= nums.length <= 10⁴ 
 -10⁹ <= nums[i] <= 10⁹ 
 -10⁹ <= target <= 10⁹ 
 Only one valid answer exists. 
 

 
Follow-up: Can you come up with an algorithm that is less than 
O(n²)
 time complexity?

 Related Topics Array Hash Table 👍 55977 👎 1930

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // need to have a temp length, because we need second loop to added
        // just 33%
        int n = nums.length;
        for (int i = 0; i <= n-1; i++) {
            for(int j = i + 1; j <= n - 1 ; j++){
                if(target == nums[i] + nums[j]){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
//leetcode submit region end(Prohibit modification and deletion)


//public int[] twoSum(int[] nums, int target) {
//    // 66%
//    //should have a length
//    //use Hashmap, nMap.get(complement) - actually return the index
//    //return int array
//    int n = nums.length;
//    Map<Integer, Integer> nMap = new HashMap<>();
//    for(int i = 0; i < n; i++){
//        nMap.put(nums[i], i);
//    }
//
//    for(int i = 0; i < n; i++ ){
//        int complement = target - nums[i];
//        if(nMap.containsKey(complement) && nMap.get(complement) != i){
//            return new int[]{nMap.get(complement), i};
//        }
//    }
//
//    return new int[]{};
//}

//class Solution {
//    public int[] twoSum(int[] nums, int target) {
//        // 99%
//        //just one for loop
//        int n = nums.length;
//        Map<Integer, Integer> numMap = new HashMap<>();
//
//        for(int i = 0; i < n; i++){
//            int complement = target - nums[i];
//            if(numMap.containsKey(complement)){
//                return new int[]{numMap.get(complement), i};
//            }
//            numMap.put(nums[i], i);
//        }
//
//        return new int[]{};
//    }
//}