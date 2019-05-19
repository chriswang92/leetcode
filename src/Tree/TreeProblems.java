package Tree;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
public class TreeProblems {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(3);
        node.left = new TreeNode(9);
        node.right = new TreeNode(20);
        node.right.left = new TreeNode(15);
        node.right.right = new TreeNode(7);
        //maxDepth(node)
        //sortedArrayToBST(new int[]{0,1,2,3,4,5}));//-10,-3,0,5,9

        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(2);
        node2.right = new TreeNode(2);
        node2.left.left = new TreeNode(3); //null; //
        node2.left.right = new TreeNode(4);
        node2.right.left = new TreeNode(4); //null; //
        node2.right.right = new TreeNode(3);
        TreeNode node3 = new TreeNode(1);
        node3.left = new TreeNode(2);
        node3.right = new TreeNode(2);
        node3.left.right = new TreeNode(4);
        node3.right.right = new TreeNode(4);
        System.out.println(isSymmetric(node3));
    }
    //101. Symmetric Tree
    public static boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
            return true;
        return isMirror(root.left, root.right);
    }
    /*
    The idea is to write a recursive function isMirror() that takes two trees as an argument
    and returns true if trees are the mirror and false if trees are not mirror.
    The isMirror() function recursively checks two roots and subtrees under the root.
     */
    static boolean isMirror(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if ((node1 == null && node2 != null) || (node1 != null && node2 == null))
            return false;
        return node1.val == node2.val && isMirror(node1.left, node2.right) && isMirror(node1.right, node2.left);
    }
    //108. Convert Sorted Array to Binary Search Tree
    // My solution of sortedArrayToBST
    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
    public static TreeNode helper(int[] nums,int low,int high) {
        if (high < low)
            return null;
        int mid = (low+high)/2;
        // create root
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, low ,mid-1);
        root.right = helper(nums,mid+1,high);

        return root;
    }

    //104. Maximum Depth of Binary Tree
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        //else if (root.left == null) { return maxDepth(root.right) + 1; }
        //else if (root.right == null) { return maxDepth(root.left) + 1; }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
