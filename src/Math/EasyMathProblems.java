package Math;

import java.math.BigInteger;
import java.util.*;

public class EasyMathProblems {

    public static void main(String[] args) {
        //System.out.println(romanToInt("MDCXCV"));
        //System.out.println(titleToNumber2("BBA") == 54*26+1);
        //System.out.println(isPowerOfThree(45));
        //System.out.println(trailingZeroes(10));
        System.out.println(reverse(3));
    }
    /* 7. Reverse Integer */
    public static int reverse(int x) {
        if (x==0) return 0;
        StringBuilder sb = new StringBuilder();
        if (x < 0) {
            sb.append('-');
            x = -x;
        }
        while (x > 0) {
            int mod = x % 10;
            sb.append(mod);
            x = x / 10;
        }
        int result;
        try{
            result = Integer.parseInt(sb.toString());
            return result;
        }catch(Exception e){
            return 0;
        }
    }
    /*69. Sqrt(x) */
    public static int mySqrt(int x) {
        double y = (double)x;
        return (int) Math.sqrt(y);
    }
    /*
    172. Factorial Trailing Zeroes
     */
    public static int trailingZeroes(int n) {
        int r = 0;
        while (n > 0) {
            n /= 5;
            r += n;
        }
        return r;
    }
    /*
    326. Power of Three
    NOTE:One int that is power of int b must be mod by another int that is power of the same int b
     */
    public static boolean isPowerOfThree(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int
        int max = (int)Math.pow(3,19);
        return n>0 && (max % n==0);
    }
    public static boolean isPowerOfThree2(int n) {
        double div = n;
        while (div > 0) {
            if (div == 1.0)
                return true;
            div /= 3.0;
        }
        return false;
    }
    /*
    171. Excel Sheet Column Number
     */
    public static int titleToNumber(String s) {
        return s.chars().reduce(0, (r, ch) -> r * 26 + (ch - 'A' + 1));
    }
    public static int titleToNumber3(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); result = result * 26 + (s.charAt(i) - 'A' + 1), i++);
        return result;
    }
    public static int titleToNumber2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }

    /* 13. Roman to Integer
    Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
    Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
     */
    public static int romanToInt(String s) {
        List<Character> romanNumerals = Arrays.asList(new Character[]{'I','V','X','L','C','D','M'});
        Map<Character, Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        char[] chars = s.toCharArray();
        Character next = null, curr = null;
        int total = 0;
        for (int i=0;i<chars.length;i++) {

            Integer intValue = 0;
            curr = chars[i];
            if (i+1 < chars.length) {
                next = chars[i + 1];
            } else {
                next = 0;
            }
            // Subtraction
            if (romanNumerals.indexOf(curr) < romanNumerals.indexOf(next)) {
                intValue = map.get(next) - map.get(curr);
                i++;
            } else {
                intValue = map.get(curr);
            }
            total += intValue;
        }
        return total;
    }
}
