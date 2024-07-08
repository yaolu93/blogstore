/*
 * @lc app=leetcode id=118 lang=java
 *
gg * [118] Pascal's Triangle
 *
 * https://leetcode.com/problems/pascals-triangle/description/
 *
 * algorithms
 * Easy (74.73%)
 * Likes:    12820
 * Dislikes: 449
 * Total Accepted:    1.7M
 * Total Submissions: 2.3M
 * Testcase Example:  '5'
 *
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * 
 * In Pascal's triangle, each number is the sum of the two numbers directly
 * above it as shown:
 * 
 * 
 * Example 1:
 * Input: numRows = 5
 * Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
 * Example 2:
 * Input: numRows = 1
 * Output: [[1]]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= numRows <= 30
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        // List<List<Integer>> results = new ArrayList<List<Integer>>();
        for (int i = 0; i < numRows; i++) {
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

        return results;
    }
}
// @lc code=end




// class Solution {
//     public List<List<Integer>> generate(int numRows) {
//         List<List<Integer>> results = new ArrayList<>();
//         // List<List<Integer>> results = new ArrayList<List<Integer>>();
//         for (int i = 0; i < numRows; i++) {
//             List<Integer> row = new ArrayList<>();
//             for (int j = 0; j <= i; j++) {
//                 if(j == 0 || j == i){
//                     row.add(1);
//                 } else {
//                     row.add(results.get(i - 1).get(j - 1) +results.get(i - 1).get(j));
//                 }
//             } 

//             results.add(row);
//         }

//         return results;
//     }
// }
