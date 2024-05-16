//You are given a sorted array consisting of only integers where every element 
//appears exactly twice, except for one element which appears exactly once. 
//
// Return the single element that appears only once. 
//
// Your solution must run in O(log n) time and O(1) space. 
//
// 
// Example 1: 
// Input: nums = [1,1,2,3,3,4,4,8,8]
//Output: 2
// 
// Example 2: 
// Input: nums = [3,3,7,7,10,11,11]
//Output: 10
// 
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 10⁵ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics Array Binary Search 👍 10981 👎 184


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    int left = 0;
    int right = nums.length-1;

        while(left < right){
        int mid = left+(right-left)/2;
        boolean restIsEven = (right - mid)%2 == 0;
        if(nums[mid] == nums[mid+1]){
            if(restIsEven){
                left = mid + 2 ;
            }else{
                right = mid - 1;
            }

        }else if(nums[mid] == nums[mid-1]){
            if(restIsEven){
                right = mid -2;
            }else{
                left = mid + 1;
            }
        }else{
            return nums[mid];
        }
    }

        return nums[left];

}
}
//leetcode submit region end(Prohibit modification and deletion)
