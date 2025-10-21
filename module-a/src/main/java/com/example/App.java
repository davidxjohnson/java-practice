package com.example;

import java.util.HashSet;
import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // String test = "abcde*abfghij*klmno*pqrstu*vwxyz*"; 
        String test = "abcdaef";//*abbbccc*dddddeeefff*ghij*klmno*ppqqrrss*"; 
        int result = maxDistinctSubstringLengthInSessions(test);
        System.out.println("Max distinct substring length in sessions: " + result);
    }

    public static int maxDistinctSubstringLengthInSessions(String test) {
        HashSet<Character> charSet = new HashSet<>();
        Stack<Integer> count = new Stack<>();
        int max = 0, c = 0, n = test.length();

        for (int i = 0; i < n; i++) {
            if (test.charAt(i) == 42) {     // handles delimeter '*'
                count.push(charSet.size()); // handles count
                charSet.clear();
                continue;
            }
            charSet.add(test.charAt(i));    // handles duplicates
        }
        count.push(charSet.size());         // for the last subString
        
        while (!count.isEmpty()) {
            c = count.pop();
            max = (c > max) ? c : max;
        }
        return max;
    }
}
