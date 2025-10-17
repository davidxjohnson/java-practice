# Between Two Sets: Problem Statement & Usefulness

## Practical Usefulness & Significance

The "between two sets" algorithm is primarily used as a programming exercise to teach concepts like LCM (Least Common Multiple), GCD (Greatest Common Divisor), and divisibility. It is popular in coding interviews and platforms like HackerRank because:

- It demonstrates how to work with multiples, factors, and number theory.
- It requires efficient use of LCM and GCD formulas.
- It helps practice array iteration and logical reasoning.

In practical terms, the direct problem is rare in real-world applications. However, the underlying concepts (LCM, GCD, divisibility) are fundamental in areas like cryptography, scheduling, signal processing, and computer science theory.

So, while the problem itself is mainly educational and fun, the math behind it is very useful and widely applicable!

Personally, I enjoyed this particular type of problem because the core of the solution is based math theory rather than the nested itterative probing approach. You can check on [Examples.com](https://www.examples.com/maths/least-common-multiple-lcm.html) for other approaches to this problem.

---

## HackerRank Problem Statement

See also [HackerRank](https://www.hackerrank.com/challenges/between-two-sets/problem?isFullScreen=true)

There will be two arrays of integers. Determine all integers that satisfy the following two conditions:

1. The elements of the first array are all factors of the integer being considered.
2. The integer being considered is a factor of all elements of the second array.

These numbers are referred to as being between the two arrays. Determine how many such numbers exist.

### Example

Let $a = [2, 4]$ and $b = [16, 32, 96]$.

There are three numbers between the arrays: $4$, $8$, and $16$.

- $2$ and $4$ are factors of $4$, $8$, $12$, and $16$.
- $4$, $8$, and $16$ are factors of $16$, $32$, and $96$.

Return $3$.

---

### Function Description

Complete the `getTotalX` function in the editor below. It should return the number of integers that are between the sets.

`getTotalX` has the following parameter(s):

- `int a[n]`: an array of integers
- `int b[m]`: an array of integers

#### Returns

- `int`: the number of integers that are between the sets

#### Input Format

The first line contains two space-separated integers, $n$ and $m$, the number of elements in arrays $a$ and $b$.

The second line contains $n$ distinct space-separated integers $a[i]$ where $0 \leq i \lt n$.

The third line contains $m$ distinct space-separated integers $b[j]$ where $0 \leq j \lt m$.

#### Constraints

- $1 \leq n, m \leq 10$
- $1 \leq a[ i ] \leq 100$
- $1 \leq b[ j ] \leq 100$

#### Sample Input
```
2 3
2 4
16 32 96
```

#### Sample Output
```
3
```

#### Explanation

2 and 4 divide evenly into 4, 8, 12 and 16.<br>
4, 8 and 16 divide evenly into 16, 32, 96.

4, 8 and 16 are the only three numbers for which each element of a is a factor and each is a factor of all elements of b.

## Running the code

```
mvn clean install -pl between-two-sets
mvn -pl between-two-sets exec:java \
    -Dexec.mainClass="com.example.App" \
    -Dexec.args="-a 2,4 -b 16,32,96"
```
As you see above, there are two flags `-a` and `-b` that identify the two sets to process. The flags are optional and default to known working sets.