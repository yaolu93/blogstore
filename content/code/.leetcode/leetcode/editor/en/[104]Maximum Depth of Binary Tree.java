//Given the root of a binary tree, return its maximum depth. 
//
// A binary tree's maximum depth is the number of nodes along the longest path 
//from the root node down to the farthest leaf node. 
//
// 
// Example 1: 
// 
// 
//Input: root = [3,9,20,null,null,15,7]
//Output: 3
// 
//
// Example 2: 
//
// 
//Input: root = [1,null,2]
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// The number of nodes in the tree is in the range [0, 10⁴]. 
// -100 <= Node.val <= 100 
// 
//
// Related Topics Tree Depth-First Search Breadth-First Search Binary Tree 👍 12
//720 👎 226


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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        System.out.print(" -val:"+root.val);

        int lDepth = maxDepth(root.left);
        System.out.print(" -L.val:"+root.val);
        System.out.print(" left:"+lDepth);

        int rDepth = maxDepth(root.right);
        System.out.print(" -R.val:"+root.val);
        System.out.print(" right:"+rDepth);

        int max = Math.max(lDepth, rDepth) + 1;
        System.out.println(" max:"+max);
        return max;

    }
}
//leetcode submit region end(Prohibit modification and deletion)
