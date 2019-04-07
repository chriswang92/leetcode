package HashTable;

import java.util.HashSet;

public class HashTableProblems {
    public static void main(String[] args) {
        System.out.println(isHappy(20));
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
