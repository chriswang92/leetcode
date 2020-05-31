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
    static Map<Integer, List<Integer>> getAllBuildings(int row, int col, List<List<Integer>> grid) {
        // key is row, value is list of columns
        Map<Integer, List<Integer>> buidlings = new HashMap<>();
        for (int r=0;r<row;r++) {
            for (int c=0;c<col;c++) {
                if (grid.get(r).get(c) == 1) {
                    if (buidlings.get(r) == null) {
                        buidlings.put(r, new ArrayList<>());
                    }
                    buidlings.get(r).add(c);
                }
            }
        }
        return buidlings;
    }

    // get all adjacent buildings for each building position
    static Map<Integer[], List<Integer[]>> getBuildingsWithAdjacents(int row, int col, List<List<Integer>> grid) {
        // key is row, value is list of columns
        Map<Integer[], List<Integer[]>> map = new HashMap<>();
        for (int r=0;r<row;r++) {
            for (int c=0;c<col;c++) {
                if (grid.get(r).get(c) == 1) {
                    Integer[] position = new Integer[]{r,c};
                    if (map.get(position) == null) {
                        map.put(position, new ArrayList<>());
                    }
                    Integer[] pos = null;
                    // Add adjacents of the position
                    if (c-1>0 && grid.get(r).get(c-1) == 1) {
                        pos = new Integer[]{r,c-1};
                        map.get(position).add(pos);
                    }
                    if (c+1<col && grid.get(r).get(c+1) == 1) {
                        pos = new Integer[]{r,c+1};
                        map.get(position).add(pos);
                    }
                    if (r-1>0 && grid.get(r-1).get(c) == 1) {
                        pos = new Integer[]{r-1,c};
                        map.get(position).add(pos);
                    }
                    if (r+1 < row && grid.get(r+1).get(c) == 1) {
                        pos = new Integer[]{r+1,c};
                        map.get(position).add(pos);
                    }
                }
            }
        }
        return map;
    }
    static int scan(Map<Integer, List<Integer>> buildings, Map<Integer[], List<Integer[]>> buildingsWithAdjacents) {
        List<Integer[]> scanned = new ArrayList<>();
        for (Integer r: buildings.keySet()) {
            for (Integer c: buildings.get(r)) {
                Integer[] position = new Integer[]{r,c};
                if (scanned.contains(position)) {
                    continue;
                }
                List<Integer[]> adjacents = buildingsWithAdjacents.get(position);
                if (adjacents != null) {
                    for (Integer[] adjacent: adjacents) {
                        scanned.add(adjacent);
                    }
                }
                scanned.add(position);
            }
        }
        return 0;
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    static int numberAmazonGoStores(int rows, int columns, List<List<Integer>> grid)
    {
        int n = 0;
        // 1. Put all buildings in a List<Integer[]> each value is [row,column]
        Map<Integer, List<Integer>> buildings = getAllBuildings(rows,columns,grid);

        // 2. Put all buidlings in a map<Integer[], List<Integer[]>>, key is position of the building, value is positions of all adjacent buildings
        Map<Integer[], List<Integer[]>> buildingsWithAdjacents = getBuildingsWithAdjacents(rows,columns,grid);

        /* 3. Traverse through buildingsWithAdjacents.
        if the position is not scanned before, process; otherwise skip to next pos
        Add itself and all adjacents to a list of scanned positions
         */
        n = scan(buildings, buildingsWithAdjacents);
        return n;
    }
    public static void main(String[] args) {
        List<List<Integer>> grid = new ArrayList<>();
        grid.add(Arrays.asList(new Integer[]{1,1,0,0}));
        grid.add(Arrays.asList(new Integer[]{0,0,1,0}));
        grid.add(Arrays.asList(new Integer[]{0,0,0,0}));
        grid.add(Arrays.asList(new Integer[]{1,0,1,1}));
        grid.add(Arrays.asList(new Integer[]{1,1,1,1}));
        System.out.println(numberAmazonGoStores(5,4, grid));

//        System.out.println(generalizedGCD(5, new int[]{2,4,6,8,10}));
//        System.out.println(cellCompete(new int[]{1,0,0,0,0,1,0,0},1));
//        System.out.println(cellCompete(new int[]{1,1,1,0,1,1,1,1},2));
    }
}
