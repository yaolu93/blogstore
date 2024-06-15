/**
You are given the root of a binary tree. 

 A ZigZag path for a binary tree is defined as follow: 

 
 Choose any node in the binary tree and a direction (right or left). 
 If the current direction is right, move to the right child of the current node;
 otherwise, move to the left child. 
 Change the direction from right to left or from left to right. 
 Repeat the second and third steps until you can't move in the tree. 
 

 Zigzag length is defined as the number of nodes visited - 1. (A single node 
has a length of 0). 

 Return the longest ZigZag path contained in that tree. 

 
 Example 1: 
 
 
Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
 

 Example 2: 
 
 
Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).

 

 Example 3: 

 
Input: root = [1]
Output: 0
 

 
 Constraints: 

 
 The number of nodes in the tree is in the range [1, 5 * 10⁴]. 
 1 <= Node.val <= 100 
 

 Related Topics Dynamic Programming Tree Depth-First Search Binary Tree 👍 3402 
👎 72

*/

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private int max = 0;

    public int longestZigZag(TreeNode root) {
        visit(root, 0, 0);
        return max;
    }

    private void visit(TreeNode root, int leftMax, int rightMax) {
        max = Math.max(max, Math.max(leftMax, rightMax));

        if (root == null) {
            return;
        }

        if (root.left != null) {
            visit(root.left, rightMax + 1, 0);
        }

        if (root.right != null) {
            visit(root.right, 0, leftMax + 1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
