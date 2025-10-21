
/*
 * Usage Instructions:
 *
 * Build:
 *   mvn clean install -pl maxConcurant
 *
 * Run with default parameters:
 *   mvn -pl maxConcurant exec:java \
 *     -Dexec.mainClass="com.example.App"
 *
 * Run with custom intervals and max:
 *   mvn -pl maxConcurant exec:java \
 *     -Dexec.mainClass="com.example.App" \
 *     -Dexec.args="--intervals=[[1,11],[2,7],[3,15],[4,9],[6,20]] --max=3"
 *
 * Example:
 *   mvn -pl maxConcurant exec:java \
 *     -Dexec.mainClass="com.example.App" \
 *     -Dexec.args="--intervals=[[1,5],[2,7],[3,8],[4,9],[6,10]] --max=3"
 */
package com.example;
import java.util.ArrayList;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.Arrays;
import java.util.List;

@Command(name = "maxConcurant", mixinStandardHelpOptions = true, description = "Find intervals where concurrency exceeds max.")
public class App implements Runnable {
    @Option(names = {"--intervals"}, description = "Intervals in format [[1,5],[2,7],[3,8]]", required = false)
    String intervalsInput = "[[1,5],[2,7],[3,8],[4,9],[6,10]]";

    @Option(names = {"--max"}, description = "Maximum allowed concurrency", required = false)
    int max = 3;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        int[][] intervals = parseIntervals(intervalsInput);
        int[][] overlaps = maxConcurant(intervals, max);
        System.out.println("Max Concurrency:\t\t" + max);
        System.out.println("Device intervals:\t" + Arrays.deepToString(intervals));
        System.out.println("Overlaps exceed max:\t" + Arrays.deepToString(overlaps));
    }

    private int[][] parseIntervals(String input) {
        // Remove brackets and spaces, split into pairs
        input = input.replaceAll("\\s", "").replaceAll("\\[\\[", "").replaceAll("\\]\\]", "");
        String[] pairs = input.split("\\],\\[");
        int[][] intervals = new int[pairs.length][2];
        for (int i = 0; i < pairs.length; i++) {
            String[] nums = pairs[i].replaceAll("\\[|\\]", "").split(",");
            intervals[i][0] = Integer.parseInt(nums[0]);
            intervals[i][1] = Integer.parseInt(nums[1]);
        }
        return intervals;
    }
    private static int[][] maxConcurant(int[][] intervals, int max) {
        // Create events for all start and end times
        List<int[]> events = new ArrayList<>();
        for (int[] interval : intervals) {
            events.add(new int[]{interval[0], 1});  // Start event
            events.add(new int[]{interval[1], -1}); // End event
        }

        // Sort events by time, prioritizing start events over end events at the same time
        events.sort((a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]); 

        List<int[]> result = new ArrayList<>();
        int count = 0;
        Integer overlapStart = null;

        for (int[] event : events) {
            count += event[1];
            if (count > max && overlapStart == null) {
                overlapStart = event[0];
            }
            if (count <= max && overlapStart != null) {
                result.add(new int[]{overlapStart, event[0]});
                overlapStart = null;
            }
        }
        
        // Convert List<int[]> to int[][]
        int[][] resultArr = new int[result.size()][2];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    // private static class Event {
    //     public final int time;
    //     public final int type; // 1 for start, -1 for end
    //     public Event(int time, int type) {
    //         this.time = time;
    //         this.type = type;
    //     }
    // }
}


