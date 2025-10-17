package com.example;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * "Between Two Sets" Problem
  
 */
@Command(name = "App", mixinStandardHelpOptions = true, description = "Between Two Sets problem")
public class App implements Callable<Integer> {
    @Option(names = {"-a", "--arrayA"}, description = "First array (comma-separated)", defaultValue = "2,4")
    private String arrayA;

    @Option(names = {"-b", "--arrayB"}, description = "Second array (comma-separated)", defaultValue = "16,32,96")
    private String arrayB;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        List<Integer> a = parseList(arrayA);
        List<Integer> b = parseList(arrayB);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        int count = getTotalX(a, b);
        System.out.println("Number of integers between the sets: " + count);
        return 0;
    }

    // Parses a comma-separated string into a List<Integer>
    private static List<Integer> parseList(String s) {
        List<Integer> result = new ArrayList<>();
        for (String part : s.split(",")) {
            try {
                result.add(Integer.parseInt(part.trim()));
            } catch (NumberFormatException e) {
                System.err.println("Invalid integer: " + part);
            }
        }
        return result;
    }

    // Finds the number of integers between the two sets
    private static int getTotalX(List<Integer> a, List<Integer> b) {
        int lcm = getLCM(a);
        int gcd = getGCDList(b);
        int count = 0;
        for (int i = lcm; i <= gcd; i += lcm) {
            if (gcd % i == 0) {
                count++;
            }
        }
        return count;
    }

    // Returns the LCM of a list of integers
    private static int getLCM(List<Integer> a) {
        return a.stream().reduce(1, (x, y) -> lcm(x, y));
    }

    // Returns the GCD of a list of integers
    private static int getGCDList(List<Integer> nums) {
        return nums.stream().reduce(nums.get(0), (x, y) -> gcd(x, y));
    }

    // Returns the LCM of two integers
    private static int lcm(int a, int b) {
        return Math.abs(a * b) / gcd(a, b);
    }

    // Returns the GCD of two integers
    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

}
  