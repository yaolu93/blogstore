/*
 * @lc app=leetcode id=59 lang=java
 *
 * [59] Spiral Matrix II
 */

// @lc code=start
class Solution {
    public int[][] generateMatrix(int n) {

        int [][] matrix = new int[n][n];
        // List<Integer> res = new ArrayList<Integer>();
        if(matrix.length == 0){
            return matrix;
        }

        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        int num = 1;// change

        while (top <= bottom && left <= right ) {
            
            // traverse rightp
            for (int j = left; j <= right; j++) {
                // res.add(matrix[top][j]); // change
                matrix[top][j] = num++;
            }
            top++;

            // traverse down
            for (int j = top; j <= bottom ; j++) {
                // res.add(matrix[j][right]);
                matrix[j][right] = num++;
            }
            right--;

            if(top <= bottom){
                //traverse left
                for (int j = right; j >= left ; j--) {
                    // res.add(matrix[bottom][j]);
                    matrix[bottom][j] = num++;
                } 
            }
            bottom--;

            if(left <= right){
                // traverse up
                for (int j = bottom; j >= top ; j--) {
                    // res.add(matrix[j][left]);
                    matrix[j][left] = num++;
                } 
            }
            left++;
        }

        return matrix;
        
    }
}
// @lc code=end
