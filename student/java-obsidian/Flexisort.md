```java
// Sorter.java
public abstract class Sorter {
    private int[] array;

    public int[] getArray() {
        return array
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    public abstract void sort();
}

// InsertionSort.java
public class InsertionSort extends Sorter {
    @Override public void sort() {
        int[] a = getArray();
        if (a == null) return;
        for (int i=1; i<a.length; i++) {
            for (int j=i; j> 0 && a[j-1] > a[j]; j--) {
                int t = a[j];
                a[j] = a[j-1];
                a[j-1] = t;
            }
        }
    }
}

public class BubbleSort extends Sorter {
	@Override
	public void sort() {
		int[] a = getArray();
		if (a == null) return;
		for (int i = a.length-1; i > 0; i--){
			boolean s = false;
			for (int j = 0; j < i; j++)
				if (a[j] > a[j+1]) {
					int t = a[j];
					a[j] = a[j+1];
					a[j+1] = t;
					s = true;
				}
			if (!s) break;
		}
	}
}
```

## 1. Bubble Sort

### **1\. Theory Behind Bubble Sort**

**Bubble Sort** is a simple comparison-based sorting algorithm. It works by repeatedly stepping through the list, comparing adjacent elements, and swapping them if they are in the wrong order. This process repeats until the list is sorted.

**How it works:**

-   Compare each pair of adjacent elements.
    
-   If the left element is greater than the right one, swap them.
    
-   At the end of each pass, the largest unsorted element is "bubbled" to its correct position.
    
-   Repeat the process for the remaining unsorted part of the array.
    

**Time Complexity:**

-   Worst-case: O(n²)
    
-   Best-case (when already sorted): O(n) — if optimized with a swapped flag
    
-   Space complexity: O(1) — in-place sorting
    

---

### **2\. Code Walkthrough**

```java
public class BubbleSort extends Sorter {
```

This class extends `Sorter`, which is expected to have a method `getArray()` returning an `int[]`.

```java
@Override
public void sort() {
```

Overrides the `sort()` method defined in `Sorter`.

```java
int[] a = getArray();
    if (a == null) return;
```

Retrieves the array to sort. If the array is `null`, it exits.

```java
for (int i = a.length-1; i > 0; i--) {
```

This loop defines how many passes will be made over the array. After each pass, the last `i` elements are sorted, so we reduce the range each time.

```java
boolean s = false;
```

A **flag** to track if any swaps were made during this pass. If no swaps happen, the array is already sorted and we can exit early — an optimization.

```java
for (int j = 0; j < i; j++)
            if (a[j] > a[j+1]) {
                int t = a[j];
                a[j] = a[j+1];
                a[j+1] = t;
                s = true;
            }
```

Inner loop:

-   Compares adjacent elements `a[j]` and `a[j+1]`.
    
-   Swaps them if they're in the wrong order (greater than).
    
-   Sets the `s` flag to `true` if a swap happens.
    

```java
if (!s) break;
```

If no swaps occurred during the inner loop, the array is already sorted, so the loop exits early — saving time in best-case scenarios.

---

### **3\. Example**

Input array: `[5, 3, 2, 4, 1]`

**Pass 1:**

-   Swap 5 and 3 → `[3, 5, 2, 4, 1]`
    
-   Swap 5 and 2 → `[3, 2, 5, 4, 1]`
    
-   Swap 5 and 4 → `[3, 2, 4, 5, 1]`
    
-   Swap 5 and 1 → `[3, 2, 4, 1, 5]`
    

5 is now in the correct position.

**Next Passes:**

-   Continue until the full array is sorted.
    
-   If during a pass no swaps are made, the sort exits early.

> In bubble sort we start with the whole array, iterate over it, taking couplet and swapping, this guarantee that the biggest number will *bubble up* to the end. After that we remove that end by now taking the array **minus** the last element (case). This will continue until we either don't swap in the iteration or we continue iterating, swapping, bubbling and shrinking our array until there is no more to consume from it.

https://www.youtube.com/watch?v=xli_FI7CuzA

## 2. Insertion Sort

### **1\. Theory Behind Insertion Sort**

**Insertion Sort** is a simple, comparison-based sorting algorithm. It's often used for small or mostly sorted datasets due to its simplicity and efficiency in those cases.

#### **Concept:**

-   Imagine sorting a hand of playing cards.
    
-   Start with the second card, compare it to the cards before it.
    
-   Insert it into the correct position by shifting larger cards forward.
    
-   Repeat this for each card (element) in the hand (array).
    

#### **Characteristics:**

-   **In-place**: Uses no extra memory.
    
-   **Stable**: Maintains the order of equal elements.
    
-   **Adaptive**: Fast for nearly-sorted data.
    

#### **Time Complexity:**

-   Best case (already sorted): **O(n)**
    
-   Average and worst case: **O(n²)**
    
-   Space complexity: **O(1)**
    

---

### **2\. Code Breakdown**

```java
public class InsertionSort extends Sorter {
```

This class extends `Sorter`, which provides the method `getArray()` returning an `int[]`.

```java
@Override
public void sort() {
```

Overrides the `sort()` method from `Sorter`.

```java
int[] a = getArray();
    if (a == null) return;
```

Gets the array to sort. If it’s `null`, exit early.

```java
for (int i = 1; i < a.length; i++) {
```

Starts from index 1, assuming the first element (index 0) is already "sorted".

```java
for (int j = i; j > 0 && a[j - 1] > a[j]; j--) {
```

Inner loop that:

-   Moves backwards from index `i` to `0`
    
-   Continues as long as the previous element `a[j - 1]` is greater than the current `a[j]`
    
-   This is where the "insertion" happens — shifting larger elements to the right
    

```java
int t = a[j];
            a[j] = a[j - 1];
            a[j - 1] = t;
```

Swaps the elements `a[j]` and `a[j - 1]` to move the smaller element into its correct position.

This loop continues shifting until it finds the correct insertion point or reaches the beginning of the array.

---

### **3\. Example**

Input: `[5, 3, 4, 1]`

**Pass 1 (i=1):**

-   `a[0]=5`, `a[1]=3` → 5 > 3 → swap → `[3, 5, 4, 1]`
    

**Pass 2 (i=2):**

-   `a[1]=5`, `a[2]=4` → 5 > 4 → swap → `[3, 4, 5, 1]`
    

**Pass 3 (i=3):**

-   `a[2]=5`, `a[3]=1` → swap → `[3, 4, 1, 5]`
    
-   `a[1]=4`, `a[2]=1` → swap → `[3, 1, 4, 5]`
    
-   `a[0]=3`, `a[1]=1` → swap → `[1, 3, 4, 5]`
    

Now the array is sorted.

> Unlike bubble sort, in the insertion sort we take start by the minimal left array (two cases), we sort elements by comparing the element where we at to it's left elements, and *inserting* it in it's correct position by continuous swaps.


https://www.youtube.com/watch?v=JU767SDMDvA

[Bubble sort vs Insertion sort](https://www.youtube.com/watch?v=TZRWRjq2CAg)