/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 *
 * https://leetcode.com/problems/spiral-matrix/description/
 *
 * algorithms
 * Medium (50.14%)
 * Likes:    14744
 * Dislikes: 1303
 * Total Accepted:    1.5M
 * Total Submissions: 2.9M
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given an m x n matrix, return all elements of the matrix in spiral order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<Integer>();
        if(matrix.length == 0){
            return res;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;

        while (top <= bottom && left <= right ) {
            // traverse right
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++;

            // traverse down
            for (int j = top; j <= bottom ; j++) {
                res.add(matrix[j][right]);
             }
            right--;

            if(top <= bottom){
                //traverse left
                for (int j = right; j >= left ; j--) {
                    res.add(matrix[bottom][j]);
                } 
            }
            bottom--;

            if(left <= right){
                // traverse up
                for (int j = bottom; j >= top ; j--) {
                    res.add(matrix[j][left]);
                } 
            }
            left++;
        }

        return res;
        
    }
}
// @lc code=end

// class Solution {
//     public List<Integer> spiralOrder(int[][] matrix) {
//         List<Integer> res = new ArrayList<Integer>();
        
//         if (matrix.length == 0) {
//             return res;
//         }
        
//         int rowBegin = 0;
//         int rowEnd = matrix.length-1;
//         int colBegin = 0;
//         int colEnd = matrix[0].length - 1;
        
//         while (rowBegin <= rowEnd && colBegin <= colEnd) {
//             // Traverse Right
//             for (int j = colBegin; j <= colEnd; j ++) {
//                 res.add(matrix[rowBegin][j]);
//             }
//             rowBegin++;
            
//             // Traverse Down
//             for (int j = rowBegin; j <= rowEnd; j ++) {
//                 res.add(matrix[j][colEnd]);
//             }
//             colEnd--;
            
//             if (rowBegin <= rowEnd) {
//                 // Traverse Left
//                 for (int j = colEnd; j >= colBegin; j --) {
//                     res.add(matrix[rowEnd][j]);
//                 }
//             }
//             rowEnd--;
            
//             if (colBegin <= colEnd) {
//                 // Traver Up
//                 for (int j = rowEnd; j >= rowBegin; j --) {
//                     res.add(matrix[j][colBegin]);
//                 }
//             }
//             colBegin ++;
//         }
        
//         return res;
//     }
// }
