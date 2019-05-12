package String;

import java.util.Stack;

public class EasyStringProblems {
    public static void main(String[] args) {
        //System.out.println(countAndSay2(6));
        //System.out.println(isValid("(([]){})"));
        System.out.println(longestCommonPrefix(new String[] {"aa","a"} ));//"flower","flow","flight"
    }
    //14. Longest Common Prefix
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        String longestCommonPrefix = "";
        int i = 0, smallestSize = strs[0].length();
        // Find the smallestLength of all words, no need to continue when index > smallestLength
        for (int j = 1;j < strs.length; j++) {
            if (strs[j].length() <= smallestSize)
                smallestSize = strs[j].length();
        }
        while (i < smallestSize) {
            char currChar = strs[0].charAt(i);
            boolean allSame = true;
            for (int j = 1;j < strs.length; j++) {
                if (strs[j].charAt(i) != currChar) {
                    allSame = false;
                }
            }
            if (!allSame)
                break;
            longestCommonPrefix += currChar;
            i ++;
        }
        return  longestCommonPrefix;
    }
    //20. Valid Parentheses
    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
    //38. Count and Say
    // 1ms solution
    public String countAndSay3(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev;
        int count = 0;
        char say;

        for (int i = 1; i < n; i++){
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);

            for (int j = 1, len = prev.length(); j < len; j++){

                if (prev.charAt(j) != say){
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                }
                else count++;
            }
            curr.append(count).append(say);
        }

        return curr.toString();
    }
    // Method2. Iteration
    public static String countAndSay2(int n) {
        String res = "1";
        while (n > 1) {
            String temp = "";
            for (int i = 0; i < res.length(); i++) {
                int repeatNum = getRepeatNum(res.substring(i));
                temp += repeatNum + "" + res.charAt(i);
                // Move to the next different number index
                i = i + repeatNum - 1;
            }
            n--;
            res = temp; // Update res
        }
        return res;
    }

    // Method1. Double recursion
    // Outer recursion
    public static String countAndSay(int n) {
        //第一行就直接输出
        if (n == 1) {
            return "1";
        }
        //得到上一行的字符串
        String last = countAndSay(n - 1);
        //输出当前行的字符串
        return getNextString(last);
    }
    // Inner recursion
    private static String getNextString(String last) {
        //长度为 0 就返回空字符串
        if (last.length() == 0) {
            return "";
        }
        //得到第 1 个字符重复的次数
        int num = getRepeatNum(last);
        // 次数 + 当前字符 + 其余的字符串的情况
        return num + "" + last.charAt(0) + getNextString(last.substring(num));
    }

    //得到字符 string[0] 的重复个数，例如 "111221" 返回 3
    private static int getRepeatNum(String string) {
        int count = 1;
        char same = string.charAt(0);
        for (int i = 1; i < string.length(); i++) {
            if (same == string.charAt(i)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
