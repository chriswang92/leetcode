package Graph;

import Tree.TreeNode;

import java.util.*;

public class DFSQuestions {
    // Amazon OA q1: find distinct island
    static boolean isSafe(List<List<Integer>> grid, int row, int col, int rows, int columns) {
        /**
         * row and col are both in range, and the position is a building(1) and not visited
         */
        return (row >= 0) && (row < rows) && col >= 0 && col < columns
                && grid.get(row).get(col) == 1; //&& !visited[row][col];
    }
    static void DFS(List<List<Integer>> grid, int row, int col, int rows, int columns
//            ,boolean[][] visited
    ) {
        System.out.printf("\nDFS on (%d, %d)",row,col);
//        visited[row][col] = true; // use another structure to save visited positions
        grid.get(row).set(col,-1); // or mark visited by this, but will modify original grid
        /*
        Each DFS direction based on follow directions
         */
        int[] rowNbr1 = new int[]{-1,0,1,0}; // up, right, down, left
        int [] colNbr1 = new int[]{0,1,0,-1};

        int[] rowNbr2 = new int[]{-1,0,0,1}; // up, right, left, down
        int [] colNbr2 = new int[]{0,1,-1,0};

        int[] rowNbr = new int[]{0,0,-1,1}; // left, right, up ,down
        int [] colNbr = new int[]{-1,1,0,0};

        for (int k=0;k<4;++k) {
            if (isSafe(grid, row+ rowNbr[k], col + colNbr[k], rows, columns)) {
                DFS(grid, row + rowNbr[k], col + colNbr[k], rows, columns);
            }
        }
    }
    /*
    https://www.geeksforgeeks.org/find-number-of-islands/
    https://leetcode.com/problems/number-of-distinct-islands/
     */
    static int numberAmazonGoStores(int rows, int columns, List<List<Integer>> grid)
    {
        int count = 0;
//        boolean[][] visited = new boolean[rows][columns];

        for (int i=0;i<rows;++i) {
            for (int j=0;j<columns;++j) {
                if (grid.get(i).get(j) == 1
//                && !visited[i][j]
                ) {
                    /*
                    If a cell with value 1 is not visited yet, then new island found,
                     Visit all cells in this island and increment island count
                     */
//                    DFS(grid, i, j, visited, rows, columns);
                    DFS(grid, i, j, rows, columns);
                    ++count; // count = how many DFS initialized
                }
            }
        }

        return count;
    }
    /*
    Amazon phone interview question Advanced: Get distinct islands with different area
    https://leetcode.com/discuss/interview-question/316156/Amazon-phone-screen-Number-of-distinct-islands/295316
     */
    static int getDistinctCount(int[][] grid) {
        int size = 0;
        Set<Integer> set = new HashSet<>();
        for (int r=0;r<grid.length;r++) {
            for (int c=0;c<grid[0].length;c++) {
                if (grid[r][c] == 1) {
                    set.add(getArea(r,c,grid)); // set size = how many DFS initialized
                }
            }
        }
        return set.size();
    }
    /*
    Use DFS to getArea
     */
    static int getArea(int r, int c, int[][] grid) {
        if (r<0 || r>grid.length-1 || c<0 || c>grid[0].length-1 || grid[r][c] != 1) {
            return 0;
        }
        grid[r][c] = -1;
        return 1 + getArea(r+1, c, grid) + getArea(r-1, c, grid) + getArea(r, c-1, grid) + getArea(r, c+1, grid);
    }

    /* leetcode q#1302. Deepest Leaves Sum:
    Given a binary tree, return the sum of values of its deepest leaves.
    ex:
    Input: root = [1,2,3,4,5,null,6,7,null,null,null,null,8]
    Output: 15
    Constraints:

The number of nodes in the tree is between 1 and 10^4.
The value of nodes is between 1 and 100.
    */
    static int maxLevel(TreeNode node) {
        if(node == null) return 0;
        return 1 + Math.max(maxLevel(node.left), maxLevel(node.right));
    }
    static int DFS(TreeNode node, int currentHeight, int deepestHeight) {
        if (node == null) return 0;
        currentHeight++;
        System.out.printf("\n%d currentHeight=%d deepestHeight=%d", node.val, currentHeight, deepestHeight);
        if (currentHeight == deepestHeight && node.left == null && node.right == null) {
            return node.val;
        }
        return DFS(node.left, currentHeight, deepestHeight) + DFS(node.right, currentHeight, deepestHeight);
    }
    static int deepestLeavesSum(TreeNode root) {
        int deepestHeight = maxLevel(root);
//        Map<Integer, Integer> map = new HashMap<>(); // key = height, value = sum
        return DFS(root, 0, deepestHeight);
    }
    /*
    leetcode 1315. Sum of Nodes with Even-Valued Grandparent

     */
    static List<TreeNode> getGrandChild(TreeNode node) {
        List<TreeNode> possibleGrandChildren = new ArrayList<>();
        for (TreeNode child : Arrays.asList(node.left,node.right)) {
            if (child != null)
                possibleGrandChildren.addAll(Arrays.asList(child.left, child.right));
        }
        return possibleGrandChildren;
    }
    static int DFS(TreeNode node, LinkedList<Integer> visited) {
        if (node == null) return 0;

        int sumOfChildren = 0;
        // check if current node has a even grandparent, if so sum the value
        if (visited.size() > 1 && visited.get(visited.size() - 2) % 2 == 0) {
            sumOfChildren = node.val;
        }
        // update visited by add current node(Order traversed in DFS)
        visited.add(node.val);
//        System.out.printf("\nAdded: %d", node.val);

        // continue to current node's children
        sumOfChildren += DFS(node.left, visited) + DFS(node.right, visited);

        // when current node is processed remove it from visited(Order go back to parent in DFS)
        visited.removeLast();
//        System.out.printf("\nRemoved: %d", removed);

        return sumOfChildren;
    }
    public static int sumEvenGrandparent(TreeNode root) {
        LinkedList<Integer> visited = new LinkedList<>();
        return DFS(root, visited);
    }

    public static int sumEvenGrandparent2(TreeNode root) {
        LinkedList<Integer> path = new LinkedList();

        return dfs(root, path);
    }

    private static int dfs(TreeNode node, LinkedList<Integer> path){
        if (node == null) return 0;

        int subSum = 0;
        if (path.size() > 1 && path.get(path.size() - 2) % 2 == 0){
            subSum = node.val;
        }
        path.add(node.val);
        subSum += dfs(node.left, path) + dfs(node.right, path);
        path.removeLast();

        return subSum;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(6);
        root.left=new TreeNode(7);
        root.right=new TreeNode(8);
        root.left.left=new TreeNode(2);
        root.left.right=new TreeNode(7);
        root.right.left=new TreeNode(1);
        root.right.right=new TreeNode(3);
        root.left.left.left=new TreeNode(9);
        root.left.right.left=new TreeNode(1);
        root.left.right.right=new TreeNode(4);
        root.right.right.right=new TreeNode(5);
        System.out.printf("\n%d",sumEvenGrandparent(root));
//        TreeNode root = new TreeNode(1);
//        root.left=new TreeNode(2);
//        root.right=new TreeNode(3);
//        root.left.left=new TreeNode(4);
//        root.left.right=new TreeNode(5);
//        root.left.left.left=new TreeNode(7);
//        root.right.right=new TreeNode(6);
//        root.right.right.right=new TreeNode(8);
//        System.out.printf("\ndeepestLeavesSum = %d\n",deepestLeavesSum(root));
//        List<List<Integer>> grid = new ArrayList<>();
//        grid.add(Arrays.asList(new Integer[]{1,1,0,0}));
//        grid.add(Arrays.asList(new Integer[]{0,0,1,0}));
//        grid.add(Arrays.asList(new Integer[]{0,0,0,0}));
//        grid.add(Arrays.asList(new Integer[]{1,0,1,1}));
//        grid.add(Arrays.asList(new Integer[]{1,1,1,1}));
//        System.out.printf("\n%d", numberAmazonGoStores(5,4, grid));
//        int[][] grid2 = new int[5][4];
//        grid2[0] = new int[]{1,1,0,0};
//        grid2[1] = new int[]{0,0,1,0};
//        grid2[2] = new int[]{0,0,0,0};
//        grid2[3] = new int[]{1,0,1,1};
//        grid2[4] = new int[]{1,1,1,1};
//        System.out.printf("\n getDistinctCount: %d", getDistinctCount(grid2));

//        System.out.println(generalizedGCD(5, new int[]{2,4,6,8,10}));
//        System.out.println(cellCompete(new int[]{1,0,0,0,0,1,0,0},1));
//        System.out.println(cellCompete(new int[]{1,1,1,0,1,1,1,1},2));
    }
}
