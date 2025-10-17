package com.example;
// import picocli.CommandLine;
// import picocli.CommandLine.Command;
// import picocli.CommandLine.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class App {
    public static void main(String[] args) {
        List<Integer> readings = new ArrayList<>();
        List<List<Integer>> results = new ArrayList<>();

        readings = Arrays.asList(2,1,2,4,3);
        results = findNextGreaterElementsWithDistance(readings);
        System.out.println("Input: " + readings);
        System.out.println("Output:" + results);
    }

    private static List<List<Integer>> findNextGreaterElementsWithDistance(List<Integer> readings) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> defaultResult = Arrays.asList(-1, -1);
        Stack<Integer> stack = new Stack<>();
        int n = readings.size();
        for (int i = 0; i < n; i++) {
            results.add(defaultResult);
        }
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && readings.get(i) > readings.get(stack.peek())) {
                int idx = stack.pop();
                results.set(idx, Arrays.asList(readings.get(i), i - idx));
            }
            stack.push(i);
        }
        return results;
    }
}


