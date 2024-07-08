/*
 * @lc app=leetcode id=119 lang=java
 *
 * [119] Pascal's Triangle II
 *
 * https://leetcode.com/problems/pascals-triangle-ii/description/
 *
 * algorithms
 * Easy (64.29%)
 * Likes:    4814
 * Dislikes: 340
 * Total Accepted:    903.6K
 * Total Submissions: 1.4M
 * Testcase Example:  '3'
 *
 * Given an integer rowIndex, return the rowIndex^th (0-indexed) row of the
 * Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it as shown:
 * 
 * 
 * Example 1:
 * Input: rowIndex = 3
 * Output: [1,3,3,1]
 * Example 2:
 * Input: rowIndex = 0
 * Output: [1]
 * Example 3:
 * Input: rowIndex = 1
 * Output: [1,1]
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= rowIndex <= 33
 * 
 * 
 * 
 * Follow up: Could you optimize your algorithm to use only O(rowIndex) extra
 * space?
 * 
 */

// @lc code=start
// class Solution {
//     public List<Integer> getRow(int rowIndex) {
        
//     }

    
// }


class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<List<Integer>> results = new ArrayList<>();
        // List<List<Integer>> results = new ArrayList<List<Integer>>();
        for (int i = 0; i <= rowIndex; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i){
                    row.add(1);
                } else {
                    row.add(results.get(i - 1).get(j - 1) +results.get(i - 1).get(j));
                }
            } 

            results.add(row);
        }

        return results.get(rowIndex);
    }
}
// @lc code=end
