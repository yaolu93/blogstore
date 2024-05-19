//You are given an integer array nums and an integer k. 
//
// In one operation, you can pick two numbers from the array whose sum equals k 
//and remove them from the array. 
//
// Return the maximum number of operations you can perform on the array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4], k = 5
//Output: 2
//Explanation: Starting with nums = [1,2,3,4]:
//- Remove numbers 1 and 4, then nums = [2,3]
//- Remove numbers 2 and 3, then nums = []
//There are no more pairs that sum up to 5, hence a total of 2 operations. 
//
// Example 2: 
//
// 
//Input: nums = [3,1,3,4,3], k = 6
//Output: 1
//Explanation: Starting with nums = [3,1,3,4,3]:
//- Remove the first two 3's, then nums = [1,4,3]
//There are no more pairs that sum up to 6, hence a total of 1 operation. 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 1 <= nums[i] <= 10⁹ 
// 1 <= k <= 10⁹ 
// 
//
// Related Topics Array Hash Table Two Pointers Sorting 👍 3045 👎 80


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxOperations(int[] nums, int k) {
        //2,1,3,4 I like this method really
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int indexValue : nums) {
            int remaining = k - indexValue;
            if (map.containsKey(remaining)) {
                count++;
                if (map.get(remaining) == 1) {
                    map.remove(remaining); //这个是计数器只剩一个的情况下如此做法，不然就是走 else逻辑，将计数器数量减一
                } else {
                    map.put(remaining, map.get(remaining) - 1); //这个就是将匹配的值的计数器减一
                }
            } else {
                map.put(indexValue, map.getOrDefault(indexValue, 0) + 1);
                //key-value, value-count+1+1+1+1
            }
        }
        return count;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


//public int maxOperations(int[] nums, int k) {
//    Map<Integer, Integer> map = new HashMap<>();
//    int count = 0;
//    for (int i = 0; i < nums.length; i++) {
//        if (map.containsKey(k - nums[i]) && map.get(k - nums[i]) > 0) {
//            count++;
//            map.put(k - nums[i], map.get(k - nums[i]) - 1);
//        } else {
//            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
//        }
//    }
//    return count;
//}

//public int maxOperations(int[] nums, int k) {
//    //这特么的这个双指针真的简单炸了哇
//    Arrays.sort(nums);
//    int ans=0;
//    for(int i=0, y=nums.length-1 ; i<y ;){
//        if(nums[i]+nums[y]==k){
//            ans++;
//            i++;
//            y--;
//        }
//        else if(nums[i]+nums[y]<k){
//            i++;
//        }
//        else{
//            y--;
//        }
//    }
//    return ans;
//}

//public int maxOperations(int[] nums, int k) {
//    //remove them from the array, so it should use lib which can remove the elements
//    //so it could be arraylist
//    // 这一题目的基本思路是有的了，就是在性能时候出了问题
//    Arrays.sort(nums);
//
//    Map<Integer, Integer> numsMap = new HashMap<>();
//    for (int i = 0; i < nums.length; i++) {
//        numsMap.put(i, nums[i]);
//    }
//    int left = 0;
//    int right = nums.length - 1;
//    int count = 0;
//    while (left < right) {
//        if (numsMap.containsKey(left) && numsMap.containsKey(right) && numsMap.get(left) + numsMap.get(right) == k) {
//            numsMap.remove(left);
//            numsMap.remove(right);
//            count++;
//            left++;
//            right--;
//            System.out.println(numsMap.toString());
//        } else if (numsMap.containsKey(left) && numsMap.containsKey(right) && numsMap.get(left) + numsMap.get(right) > k) {
//            right--; //这个left ++ 和 right-- 的顺序不能够搞错，因为，大了就需要缩小，小了就需要变大。
//        } else if (numsMap.containsKey(left) && numsMap.containsKey(right) && numsMap.get(left) + numsMap.get(right) < k){
//            left++;
//        }
//    }
//
//    return count;
//}