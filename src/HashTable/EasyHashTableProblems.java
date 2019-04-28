package HashTable;

import java.util.HashSet;

public class EasyHashTableProblems {
    public static void main(String[] args) {
        //System.out.println(isHappy(20));
        System.out.println(countPrimes(499979));//499979
    }
    public static int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }

        return count;
    }
    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            if (!set.add(n))
                return false;
            int result = 0;
            while (n > 0) {
                result += (n%10) * (n%10);
                n /= 10;
            }
            n = result;
        }
        return true;
    }
}
