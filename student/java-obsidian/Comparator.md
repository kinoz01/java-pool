**Question:**

> In this line:  
> `java.util.Arrays.sort(a, java.util.Comparator.comparingInt(Integer::parseInt));`  
> There is no `-` sign like in `(a, b) -> a - b`, so how does it actually compare values? Is `comparingInt` doing the comparison?

---

**Answer:**

Yes — the comparison is handled entirely by `Comparator.comparingInt(...)`.

Even though you don't see the `-` sign, Java still compares the values by converting each string to an integer and using a proper comparison method internally.

Here's what happens step by step:

1.  You pass `Integer::parseInt` as a function. This means: “convert a string like `"10"` to the number `10`.”
    
2.  `comparingInt(...)` creates a comparator that:
    
    -   Applies `Integer.parseInt` to both elements.
        
    -   Compares the resulting integers using `Integer.compare(n1, n2)` — a built-in, safe method that handles all integer comparisons.
        

This is equivalent to writing a manual comparator like:

```java
Comparator<String> comp = (s1, s2) -> {
    int n1 = Integer.parseInt(s1);
    int n2 = Integer.parseInt(s2);
    return Integer.compare(n1, n2);
};
```

So although there's no `-` sign, the comparison logic still happens — it's just handled by `Integer.compare(...)` instead, which is safer and avoids problems like integer overflow.

In summary, `Comparator.comparingInt(Integer::parseInt)` tells Java:

> “Sort the array by converting each string to an int and comparing those values using safe built-in comparison logic.”

That's why the sort works even though it looks like nothing is being subtracted directly.


## Manual Example

Here’s a **complete Java example** where we **manually define everything** without using any shortcuts like `Comparator.comparingInt` or method references. We’ll create our own `Comparator`, apply it in `Arrays.sort`, and run it in `main`.

```java
import java.util.Arrays;
import java.util.Comparator;

public class ManualCompareSortExample {
    public static void main(String[] args) {
        // Array of number strings
        String[] numbers = { "10", "2", "5", "1", "20" };

        // Custom comparator using manual comparison
        Comparator<String> numericStringComparator = new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                int n1 = Integer.parseInt(s1); // parse s1
                int n2 = Integer.parseInt(s2); // parse s2

                // Manual comparison logic (not using Integer.compare(n1, n2))
                return ManualCompareSortExample.compare(n1, n2);
            }
        };

        // Sort the array using the manual comparator
        Arrays.sort(numbers, numericStringComparator);

        // Print the sorted array
        System.out.println(String.join(" ", numbers));
    }
    
    public static int compare(int x, int y) {
	    return (x < y) ? -1 : ((x == y) ? 0 : 1);
	}
}

```
