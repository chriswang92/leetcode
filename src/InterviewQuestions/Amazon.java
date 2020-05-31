package InterviewQuestions;

import java.util.*;

public class Amazon {

    // Amazon Online Assessment demo Q2.
    /*
    https://www.geeksforgeeks.org/c-program-find-gcd-hcf-two-numbers/
    GCD (Greatest Common Divisor) or HCF (Highest Common Factor) of two numbers is the largest number that divides both of them.
    A simple solution is to find all prime factors of both numbers, then find intersection of all factors present in both numbers.
    Finally return product of elements in the intersection.

    An efficient solution is to use Euclidean algorithm which is the main algorithm used for this purpose.
    The idea is, GCD of two numbers doesn’t change if smaller number is subtracted from a bigger number.

    https://www.geeksforgeeks.org/gcd-two-array-numbers/
    GCD of more than two (or array) numbers
    Given an array of numbers, find GCD of the array elements
    The GCD of three or more numbers equals the product of the prime factors common to all the numbers,
    but it can also be calculated by repeatedly taking the GCDs of pairs of numbers.'
    For an array of elements, we do the following.
    We will also check for the result if the result at any step becomes 1 we will just return the 1 as gcd(1,x)=1.
     */
    static int gcd(int a, int b)
    {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }
    static int findGCD(int n, int[] arr) {
        int result = arr[0];
        for (int i = 1; i < n; i++){
            result = gcd(arr[i], result);

            if(result == 1)
            {
                return 1;
            }
        }

        return result;
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static int generalizedGCD(int num, int[] arr)
    {
        return findGCD(num, arr);
    }
    // Amazon Online Assessment demo Q1.
    public static int[] compute(int[] states, int daysLeft) {
        if (daysLeft == 0){
            return states;
        }
        int[] newStates = new int[states.length];
        int left=-1,right=-1,newCurr=-1;
        for(int i=0;i<states.length;i++) {
            int curr = states[i];

            left= i==0? 0:states[i-1];
            right=i==states.length-1?0:states[i+1];
            newCurr= left == right? 0:1;
            newStates[i] = newCurr;
        }
        return compute(newStates,daysLeft-1);
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public static List<Integer> cellCompete(int[] states, int days) {
        // WRITE YOUR CODE HERE
        int[] arr = compute(states,days);
        List<Integer> res = new ArrayList<>();
        for (int i=0;i<arr.length;i++) {
            res.add(arr[i]);
        }
        // System.out.println(arr,res);
        return res;
    }

    /*
    Amazon official OA q1
     */

    // get all building positions
//    static Map<Integer, List<Integer>> getAllBuildings(int row, int col, List<List<Integer>> grid) {
//        // key is row, value is list of columns
//        Map<Integer, List<Integer>> buidlings = new HashMap<>();
//        for (int r=0;r<row;r++) {
//            for (int c=0;c<col;c++) {
//                if (grid.get(r).get(c) == 1) {
//                    if (buidlings.get(r) == null) {
//                        buidlings.put(r, new ArrayList<>());
//                    }
//                    buidlings.get(r).add(c);
//                }
//            }
//        }
//        return buidlings;
//    }
//
//    // get all adjacent buildings for each building position
//    static Map<Integer[], List<Integer[]>> getBuildingsWithAdjacents(int row, int col, List<List<Integer>> grid) {
//        // key is row, value is list of columns
//        Map<Integer[], List<Integer[]>> map = new HashMap<>();
//        for (int r=0;r<row;r++) {
//            for (int c=0;c<col;c++) {
//                if (grid.get(r).get(c) == 1) {
//                    Integer[] position = new Integer[]{r,c};
//                    if (map.get(position) == null) {
//                        map.put(position, new ArrayList<>());
//                    }
//                    Integer[] pos = null;
//                    // Add adjacents of the position
//                    if (c-1>0 && grid.get(r).get(c-1) == 1) {
//                        pos = new Integer[]{r,c-1};
//                        map.get(position).add(pos);
//                    }
//                    if (c+1<col && grid.get(r).get(c+1) == 1) {
//                        pos = new Integer[]{r,c+1};
//                        map.get(position).add(pos);
//                    }
//                    if (r-1>0 && grid.get(r-1).get(c) == 1) {
//                        pos = new Integer[]{r-1,c};
//                        map.get(position).add(pos);
//                    }
//                    if (r+1 < row && grid.get(r+1).get(c) == 1) {
//                        pos = new Integer[]{r+1,c};
//                        map.get(position).add(pos);
//                    }
//                }
//            }
//        }
//        return map;
//    }
//    static List<Integer[]> getAdjacentsByPosition(int row, int col, Map<Integer[], List<Integer[]>> buildingsWithAdjacents) {
////        List<Integer[]> adjacents = new ArrayList<>();
//        for (Map.Entry<Integer[], List<Integer[]>> entry : buildingsWithAdjacents.entrySet()) {
//            Integer[] pos = entry.getKey();
//            if (pos[0] == row && pos[1] == col) {
//                return entry.getValue();
//            }
//        }
//        return null;
//    }
//
//    static int visit(Integer[] pos, int rows, int columns, Map<Integer[], List<Integer[]>> buildingsWithAdjacents, List<List<Integer>> visited) {
//
//        int row = pos[0], col = pos[1];
//
//        if (row < 0 || row > rows - 1 || col < 0 || col > columns - 1) {
//            return 0;
//        }
//        int res = 0;
//        if (!visited.get(row).contains(col)) {
//            visited.get(row).add(col);
//            res++;
//        }
//        List<Integer[]> adjacents = getAdjacentsByPosition(row, col, buildingsWithAdjacents);
//
//        for (Integer[] adjacentPos : adjacents) {
//            res += visit(adjacentPos, rows, columns, buildingsWithAdjacents, visited);
//        }
//        return res;
//    }
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
    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(new Integer[]{1,1,0,0}));
        grid.add(Arrays.asList(new Integer[]{0,0,1,0}));
        grid.add(Arrays.asList(new Integer[]{0,0,0,0}));
        grid.add(Arrays.asList(new Integer[]{1,0,1,1}));
        grid.add(Arrays.asList(new Integer[]{1,1,1,1}));
//        System.out.printf("\n%d", numberAmazonGoStores(5,4, grid));
        int[][] grid2 = new int[5][4];
        grid2[0] = new int[]{1,1,0,0};
        grid2[1] = new int[]{0,0,1,0};
        grid2[2] = new int[]{0,0,0,0};
        grid2[3] = new int[]{1,0,1,1};
        grid2[4] = new int[]{1,1,1,1};
        System.out.printf("\n getDistinctCount: %d", getDistinctCount(grid2));

//        System.out.println(generalizedGCD(5, new int[]{2,4,6,8,10}));
//        System.out.println(cellCompete(new int[]{1,0,0,0,0,1,0,0},1));
//        System.out.println(cellCompete(new int[]{1,1,1,0,1,1,1,1},2));
    }
}
