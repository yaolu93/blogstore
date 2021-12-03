import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * @lc app=leetcode id=349 lang=java
 *
 * [349] Intersection of Two Arrays
 *
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * algorithms
 * Easy (67.12%)
 * Likes:    2142
 * Dislikes: 1784
 * Total Accepted:    575.6K
 * Total Submissions: 850.4K
 * Testcase Example:  '[1,2,2,1]\n[2,2]'
 *
 * Given two integer arrays nums1 and nums2, return an array of their
 * intersection. Each element in the result must be unique and you may return
 * the result in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums1 = [1,2,2,1], nums2 = [2,2]
 * Output: [2]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * Output: [9,4]
 * Explanation: [4,9] is also accepted.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        
        Set<Integer> set1 = new HashSet<Integer>();
        
        Set<Integer> set2 = new HashSet<Integer>();

        //delete the duplicate elements
        for(int num1 : nums1){
            set1.add(num1);
        }
        for (int num2 : nums2) {
            set2.add(num2);
        }
        
        //get the same elements
        return getSameElement(set1, set2);


    }

    private int[] getSameElement(Set<Integer> set1, Set<Integer> set2) {
        if(set2.size() > set1.size()){
            return getSameElement(set2, set1);
        }
        List<Integer> res = new ArrayList<Integer>();
        for (int element : set1) {
            if(set2.contains(element)){
                res.add(element);
            }
        }

        int[] result = new int[res.size()];

        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
// @lc code=end

