package TwoPointers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyTwoPointersProblems {
    public static void main(String[] args) {
        //char[] s = new char[]{'H','e','l','l','o'};
        //reverseString(s);
        //System.out.println("".length());
        //System.out.println(strStr("hello", "ll"));
        System.out.println(isPalindrome("race,#$ a car"));
    }
    /* 125. Valid Palindrome */
    public static boolean isPalindrome(String s) {
        /*
        boolean res = false;
        List<Character> l = new ArrayList<>();
        s = s.toLowerCase().replaceAll("[^A-Za-z0-9]", ""); // [^A-Za-z0-9]

        return isPalindromeString(s);
        */
        int start = 0, end = s.length() - 1;
        while (start < end) {
            char startChar = s.charAt(start), endChar = s.charAt(end);
            if (!Character.isLetterOrDigit(startChar)) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(endChar)) {
                end --;
                continue;
            }
            if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    public static boolean isPalindromeString(String s) {
        char[] chars = s.toCharArray();
        for (int i=0, j = chars.length - 1;i < j; i++,j--) {
            if (chars[i] != chars[j])
                return false;
        }
        return true;
    }
    /* 28. Implement strStr() */
    public static int strStr(String haystack, String needle) {
        if (needle == "" || needle.length() == 0) {
            return 0;
        }
        for (int i=0;i<haystack.length();i++) {
            if (haystack.charAt(i) == needle.charAt(0) && i+needle.length() <= haystack.length()) {
                if (haystack.substring(i, i+needle.length()).equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }
    /* 344. Reverse String */
    public static void reverseString(char[] s) {
        for (int i=0, j = s.length - 1;i < j; i++,j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }
}
