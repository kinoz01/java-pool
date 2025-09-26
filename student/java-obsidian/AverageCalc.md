```java
public class AverageCalc {
    public static int average(int start, int end, int step) {
        if (step == 0 || (step > 0 ? start > end : start < end))
            return 0;
        int last = start + ((end - start) / step) * step;
        return (start + last) / 2;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(AverageCalc.average(1,5,1));
    }
}
```

## 1\. **Purpose of the Code**

The `average` method calculates the arithmetic mean (average) of a sequence of numbers that starts at `start`, ends at or before `end`, and increments or decrements by `step`.

The average is calculated using the formula:

```java
(start + last) / 2
```

Where `last` is the final valid number in the sequence that does not overshoot the `end`.

---

## 2\. **Sequence Behavior**

The method builds a virtual arithmetic sequence:

-   Starts from `start`
    
-   Increases or decreases by `step`
    
-   Stops at the last value that does not go past `end`
    

Examples of sequences:

-   `average(1, 5, 1)` → sequence: 1, 2, 3, 4, 5
    
-   `average(10, 2, -2)` → sequence: 10, 8, 6, 4, 2
    

---

## 3\. **Early Exit Condition**

```java
if (step == 0 || (step > 0 ? start > end : start < end))
    return 0;
```

This condition checks for:

-   Invalid step (zero)
    
-   Impossible ranges (e.g., counting up when start > end)
    

If any of these are true, the method returns `0` immediately.

---

## 4\. **How the `last` Value is Calculated**

```java
int last = start + ((end - start) / step) * step;
```

At first glance, this might look like a strange math trick, but it's actually a clean way to compute the **last valid number** in a sequence that starts at `start`, increases (or decreases) by `step`, and must not go beyond `end`.

Let's say we have a sequence: start at 2, go up by 3, and stop before or at 10.

So you get:  
`2, 5, 8` (next would be 11, which is beyond 10, so it's excluded).

We want to calculate that 8 directly, without building the whole sequence.

Here's how the formula does that:

1.  First, we do: `(end - start) / step`.  
    This tells us **how many full steps** of size `step` we can take from `start` without going past `end`.
    
    For example:  
    `end = 10`, `start = 2`, `step = 3`  
    → `(10 - 2) / 3 = 8 / 3 = 2` (integer division)
    
    That means we can take **2 full steps** of size 3.
    
2.  Now multiply that step count by the actual `step`:  
    `2 * 3 = 6`  
    So the total "distance" we can travel from the start without passing `end` is 6.
    
3.  Add that to the `start`:  
    `2 + 6 = 8`  
    That gives you the last valid number in the sequence.
    

So now `last` is 8 — the biggest number in the sequence that doesn't go past 10, while following the step size.

This works the same with negative steps.  
If `start = 10`, `end = 2`, `step = -3`, then it calculates how many steps of -3 we can take before dropping below 2. The formula adjusts correctly, because dividing and multiplying negative numbers still works in the same logical way.

---

## 5\. **Why `(start + last) / 2` is Used**

This is a standard arithmetic mean formula for a uniformly spaced (arithmetic) sequence:

-   It gives the **average** of the sequence
    
-   It's mathematically equivalent to summing all terms and dividing by the count, but much faster
    

In such sequences, the average of all values equals:

```java
(first term + last term) / 2
```

This value represents the **center** of the sequence, even if it's not an actual element in the list.

---

## 6\. **Examples**

| Input | Sequence | Result |
| --- | --- | --- |
| `average(1, 5, 1)` | 1, 2, 3, 4, 5 | 3 |
| `average(2, 10, 2)` | 2, 4, 6, 8, 10 | 6 |
| `average(10, 2, -2)` | 10, 8, 6, 4, 2 | 6 |
| `average(10, 3, -3)` | 10, 7, 4 | 7 |
| `average(5, 1, 1)` | Invalid range | 0 |
| `average(1, 5, -1)` | Invalid range | 0 |
| `average(3, 9, 0)` | Invalid step | 0 |

---

## 7\. **Alternative Implementation **

A loop-based version calculates the true mean by summing all valid values and dividing by their count:

```java
public static int average(int start, int end, int step) {
    if (step == 0 || (step > 0 ? start > end : start < end)) return 0;
    int sum = 0, count = 0;
    for (int i = start; step > 0 ? i <= end : i >= end; i += step) {
        sum += i;
        count++;
    }
    return count == 0 ? 0 : sum / count;
}
```

This is more intuitive but less efficient for large ranges.