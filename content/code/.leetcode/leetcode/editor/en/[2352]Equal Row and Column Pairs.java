import java.util.*;

/**
Given a 0-indexed n x n integer matrix grid, return the number of pairs (ri, cj)
 such that row ri and column cj are equal. 

 A row and column pair is considered equal if they contain the same elements in 
the same order (i.e., an equal array). 

 
 Example 1: 
 
 
Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
 

 Example 2: 
 
 
Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
 

 
 Constraints: 

 
 n == grid.length == grid[i].length 
 1 <= n <= 200 
 1 <= grid[i][j] <= 10⁵ 
 

 Related Topics Array Hash Table Matrix Simulation 👍 2143 👎 144

*/

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int equalPairs(int[][] grid) {
//        使用 foreach 循环 for (int[] row : grid) 遍历二维数组 grid，实际上是遍历每一行而不是每一列。
        int length = grid.length;
        Map<Integer, int[]> rowMap = new HashMap<>();
        for (int row = 0; row < length; row ++){
            rowMap.put(row, grid[row]);
            System.out.println(rowMap.get(row));
            for (int column : rowMap.get(row)){
                System.out.println(column+"column");
            }
        }

        Map<List<Integer>, Integer> rowHashMap = new HashMap<>();
        for (int[] row: grid){
            List<Integer> rowKey = new ArrayList<>();
            for (int column : row){
                rowKey.add(column);
            }
            rowHashMap.put(rowKey, rowHashMap.getOrDefault(rowKey, 0)+1);
        }

        //traversal column
        int count = 0;
        for (int column = 0; column < length; column++) {
            List<Integer> columnKey = new ArrayList<>();
            for (int row = 0; row < length; row++) {
                columnKey.add(grid[row][column]);
            }
            if (rowHashMap.containsKey(columnKey)){
                count = count + rowHashMap.get(columnKey);
            }
        }

        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
