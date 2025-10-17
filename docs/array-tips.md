# Java Arrays and Lists Cheat Sheet

## Summary
- Use Arrays for static utility methods.
- Use List for flexible, object-oriented array-like structures.
- Use System.arraycopy for fast, manual copying of array segments.

## 1. Arrays
- Fixed size, fast, but limited methods.
- Declaration: `int[] arr = {1, 2, 3};`
- Access: `arr[0]` (first element)
- Common static methods (from `java.util.Arrays`):
  - `Arrays.sort(arr);`
  - `Arrays.copyOf(arr, newLength);`
  - `Arrays.copyOfRange(arr, start, end);`
  - `Arrays.toString(arr);`

## 2. Lists (`List<Integer>`)
- Flexible size, many methods, slower than arrays for some operations.
- Declaration: `List<Integer> list = new ArrayList<>();`
- Initialization: `List<Integer> list = Arrays.asList(1, 2, 3);`
- Access: `list.get(0)`
- Common instance methods:
  - `list.add(value);`
  - `list.remove(index);` or `list.remove(value);`
  - `list.size();`
  - `list.subList(start, end);` (returns a view)

## 3. System.arraycopy
- Low-level, fast, for copying array segments.
- Usage: `System.arraycopy(src, srcPos, dest, destPos, length);`
- Example:
  ```java
  int[] src = {1, 2, 3};
  int[] dest = new int[2];
  System.arraycopy(src, 1, dest, 0, 2); // dest = {2, 3}
  ```

## Quick Tips
- Use `Arrays.copyOfRange(arr, start, end)` for slicing arrays.
- Use `list.subList(start, end)` for slicing lists.
- To remove a range from a list: `list.subList(start, end).clear();`
- To print arrays: `System.out.println(Arrays.toString(arr));`
- To print lists: `System.out.println(list);`

---
For more, see the official Java docs or ask for a specific example!