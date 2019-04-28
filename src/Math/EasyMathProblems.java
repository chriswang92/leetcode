package Math;

import java.util.*;

public class EasyMathProblems {

    public static void main(String[] args) {
        System.out.println(romanToInt("MDCXCV"));
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
