package Tree;

import java.util.*;

/**
 * Definition for a binary tree node.
 */
public class TreeNode {
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

    static void setChildren(TreeNode curr, int i, Integer[] arr, int currentLevel, int maxLevel) {
        if (curr == null) return;

        int leftIndex = 2 * i + 1, rightIndex = 2 * i + 2;

        /* If current node is not in the max level and its left and right are out of bound, it means there's null on its level or its parent levels,
        then then we shift back to get nearest available indexes.
         */
        if (currentLevel < maxLevel) {
            while (leftIndex > arr.length - 1) {
                leftIndex -= 2;
            }
            while (rightIndex > arr.length - 1) {
                rightIndex -= 2;
            }
        }

        if (leftIndex < arr.length && arr[leftIndex] != null) {
            System.out.printf("\n leftIndex %d - %d, node=%d\n", leftIndex, arr[leftIndex], curr.val);
            curr.left = new TreeNode(arr[leftIndex]);
            setChildren(curr.left, leftIndex, arr, currentLevel + 1, maxLevel);
        }

        if (rightIndex < arr.length && arr[rightIndex] != null) {
            System.out.printf("\n rightIndex %d - %d, node=%d\n", rightIndex, arr[rightIndex], curr.val);
            curr.right = new TreeNode(arr[rightIndex]);
            setChildren(curr.right, rightIndex, arr, currentLevel + 1, maxLevel);
        }


    }

    static int getMaxLevel(Integer[] arr) {
        double log = Math.log(arr.length);
        double res = Math.ceil(log) + 1;
        return (int) res;
    }

    // Make a binary tree from array. For node with index i in array, its left child = 2i+1, right child = 2i+2
    public static TreeNode convertArrayToTree(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);
        int maxLevel = getMaxLevel(arr);

        setChildren(root, 0, arr, 1, maxLevel);

        return root;
    }

    // Add missing nulls as children of non-leaf null nodes in the given array
    public static List<Integer> addNullsToArr(Integer[] arr, List<Integer> list) {
        List<Integer> newList = new ArrayList<>(list);
        int i = 0;
        // need traverse each node in arr
        while (i < arr.length) {
            int left = 2 * i + 1, right = 2 * i + 2;
            if (left > arr.length - 1) {
                i++;
                continue;
            }
            if (arr[i] == null) {
                List<Integer> currList = new ArrayList<>();
                List<Integer> beforeLeft = newList.subList(0, left);
                List<Integer> afterLeft = newList.subList(left, list.size() - 1);
                currList.addAll(beforeLeft);
                currList.add(null);
                currList.add(null);
                currList.addAll(afterLeft);
                newList = currList;
            }
            i++;
        }
        return newList;
    }

    public static void setChildren2(List<Integer> list, TreeNode node) {
        if (node == null) {
            return;
        }

        Integer left = list.isEmpty() ? null : list.remove(0);
        node.left = left == null ? null : new TreeNode(left);
        Integer right = list.isEmpty() ? null : list.remove(0);
        node.right = right == null ? null : new TreeNode(right);

        setChildren2(list, node.left);
        setChildren2(list, node.right);
    }

    public static TreeNode convertArrayToTree2(Integer[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(arr[0]);

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(arr));

        setChildren2(list.subList(1, list.size()), root);

        return root;
    }
}
