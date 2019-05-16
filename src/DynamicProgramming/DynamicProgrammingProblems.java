package DynamicProgramming;

import java.util.HashMap;

public class DynamicProgrammingProblems {
    public static void main(String[] args) {
        System.out.println(rob(new int[]{4,1,2,7,5,3,1}));
    }
    //198. House Robber. The key is to find the relation dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]).
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        // calculate dp 2 ~ nums.length - 1
        for (int i=2;i<nums.length;i++) {
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }

        return dp[nums.length - 1];
    }
    static HashMap<Integer, Integer> map = new HashMap<>();
    //70. Climbing Stairs. The key is to find the relation ways[n] = ways[n-1] + ways[n-2]
    public static int climbStairs(int n) {
        if (n == 1) {
            map.put(1,1);
            return 1;
        }
        if (n == 2) {
            map.put(2,2);
            return 2;
        }
        if (!map.containsKey(n)) {
            int value = climbStairs(n-1) + climbStairs(n - 2);
            map.put(n, value);
            return value;
        } else{
            return map.get(n);
        }
    }
}
