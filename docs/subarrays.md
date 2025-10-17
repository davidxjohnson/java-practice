Here's the complete implementation of the hash map approach for you to study:

public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
    long count = 0;
    int n = nums.size();
    int start = 0;
    while (start < n) {
        // Skip elements > M
        while (start < n && nums.get(start) > M) {
            start++;
        }
        
        if (start >= n) break;
        
        // Find the end of current valid segment
        int end = start;
        while (end < n && nums.get(end) <= M) {
            end++;
        }// Process this valid segment using prefix sum technique
        Map<Long, Integer> prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1); // Empty subarray has sum 0
        long currentSum = 0;
        
        for (int i = start; i < end; i++) {
            currentSum += nums.get(i);// Check if there's a prefix sum that gives us the target sum k
            // If currentSum - k exists in our map, it means there's a subarray with sum k
            count += prefixSumCount.getOrDefault(currentSum - k, 0);
            
            // Update the prefix sum count
            prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
        }
        
        // Move to next segment
        start = end;
    }
    
    return count;
}
Copy
Let me explain the key parts:

Segment Processing: We split the array into segments where all elements are ≤ M```java while (start < n) { // Skip elements > M while (start < n && nums.get(start) > M) { start++; }

if (start >= n) break;

// Find the end of current valid segment
int end = start;
while (end < n && nums.get(end) <= M) {
    end++;
}

// Process segment...
// Move to next segment
start = end;
}

Prefix Sum Logic: For each segment, we use the hash map technique

Map<Long, Integer> prefixSumCount = new HashMap<>();
prefixSumCount.put(0L, 1); // Empty subarray has sum 0
long currentSum = 0;

for (int i = start; i < end; i++) {
    currentSum += nums.get(i);
    
    // If currentSum - k exists in our map, it means there's a subarray with sum k
    count += prefixSumCount.getOrDefault(currentSum - k, 0);
    
    // Update the prefix sum count
    prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);
}
Copy
The key insight is that if we have two prefix sums prefixSum1 and prefixSum2 where:

prefixSum1 is the sum of elements from index 0 to i
prefixSum2 is the sum of elements from index 0 to j (where j > i)
Then the sum of elements from index i+1 to j is prefixSum2 - prefixSum1.

So if we want a subarray with sum k, we need to find positions where currentSum - previousSum = k, which is equivalent to finding previousSum = currentSum - k.