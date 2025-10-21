
# Sweep Line Algorithm: Progressive Guide

## 1. What is a Sweep Line Algorithm?
A sweep line algorithm is a technique used to efficiently process intervals or events that occur over a timeline. The core idea is to:

- Convert each interval into two events: a start and an end.
- Sort all events by their time value.
- Sweep through the sorted events, maintaining a counter for active intervals.
- At each event, update the counter and check if the number of overlaps meets your criteria (e.g., exceeds a threshold).

This approach is widely used for problems like finding the maximum number of overlapping intervals, merging intervals, or detecting conflicts.

---

## 2. Example Use Case: Network Device/Process Concurrency
Suppose you want to find intervals where the number of concurrent devices (or processes) on the network that equals or exceeds a given maximum. You would:
- Flatten all intervals into start and end events.
- Sort events by time (and incase of duplicate starts, use a secondary sort on earliest end time).
- Sweep through events, incrementing a counter for starts and decrementing for ends.
- Whenever the counter exceeds the maximum, record the interval when max was exceeded.

### Example: 
```
Input Intervals:    [[1,5],[2,7],[3,8],[4,9],[6,10]]
Overlaps > 3:       [[4,5],[6,7]]

Visual Mapping of Overlaps
Time	1	2	3	4	5	6	7	8	9	10
[1, 5]	█	█	█	█						
[2, 7]		█	█	█	█	█				
[3, 8]			█	█	█	█	█			
[4, 9]				█	█	█	█	█		
[6, 10]						█	█	█	█	
Count	1	2	3	4	3	4	3	2	1	0
Result			    ✗      ✗
```
---

## 3. Core Sweep Line Algorithm (Java Example)

NOTE: **The full example code is [here](src/main/java/com/example/App.java)**. Below is the core code snippet for the sweep-line algorithm that finds periods where concurrency exceeds a given maximum:

```java
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
```

### Step-by-step Explanation
- **Purpose:** Processes all start and end events (sorted by time) to find intervals where the number of concurrent devices/processes exceeds `max`.
- **Variables:**
	- `event[0]`: The time of the event (either start or end of an interval).
	- `event[1]`: The type of event (`1` for start, `-1` for end).
	- `count`: Tracks the current number of active intervals (devices/processes).
	- `overlapStart`: Remembers the start time of an interval where concurrency exceeds `max`.
- **Logic:**
	1. Update count: If it's a start event (`1`), increment the count. If it's an end event (`-1`), decrement the count.
	2. Detect start of overlap: If the count just exceeded `max` and we are not already tracking an overlap, record the start time.
	3. Detect end of overlap: If the count drops back to `max` or below and we are tracking an overlap, record the end time and save the interval `[overlapStart, event[0]]` to the result. Reset `overlapStart` to `null` to be ready for the next overlap.
- **Result:** The `result` list will contain all intervals `[start, end]` where the number of concurrent devices/processes was greater than `max`.

---

## 4. Big O Analysis
- **Converting intervals to events:** $O(n)$, where $n$ is the number of intervals.
- **Sorting events:** $O(n \log n)$, since there are $2n$ events.
- **Sweeping through events:** $O(n)$, as you process each event once.

**Total time complexity:** $O(n \log n)$  
**Space complexity:** $O(n)$ (for storing events and results)

This is much more efficient than brute-force approaches, which can be $O(n^2)$ if you check every pair of intervals.

---

## 5. References
- ["Algorithms" by Robert Sedgewick and Kevin Wayne](https://algs4.cs.princeton.edu/30searching/)
- [Sweep-Line Algorithm - Wikipedia](https://en.wikipedia.org/wiki/Sweep_line_algorithm)
- [Interval Overlap Problems - GeeksforGeeks](https://www.geeksforgeeks.org/maximum-number-of-overlapping-intervals/)
