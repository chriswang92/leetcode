package Tree;

/**
 * Definition for a binary tree node.
 */
public class TreeNode{
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) {
        this.val = x;
//        left = right = null;
    }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    static void setChildren(TreeNode node, int i, Integer[] arr) {
        int leftIndex = 2*i+1, rightIndex = 2*i+2;
        if (leftIndex<arr.length && arr[leftIndex] != null) {
            node.left = new TreeNode(leftIndex);
        }
        if (leftIndex<arr.length && arr[leftIndex] != null) {
            node.left = new TreeNode(leftIndex);
        }
        setChildren(node.left, leftIndex, arr);
        setChildren(node.right, rightIndex, arr);
    }

    // Make a binary tree from array. For node with index i in array, its left child = 2i+1, right child = 2i+2
    public TreeNode convertArrayToTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        setChildren(root, 0, arr);
        return root;
    }
}
