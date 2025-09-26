```java
import java.io.*;
import java.util.Arrays;

public class RotateArray {
    public static Integer[] rotate(Integer[] arr, int rotationCount) {
        if (arr == null || arr.length == 0) return arr;
        java.util.Collections.rotate(java.util.Arrays.asList(arr), rotationCount);
        return arr;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(RotateArray.rotate(new Integer[]{4, 2, 1, 3}, 1)));
    }
}
```

This code **rotates the elements** of the array `arr` by `rotationCount` positions (to the right if positive, to the left if negative).

### Step-by-Step Explanation

#### 1\. `java.util.Arrays.asList(arr)`

-   Converts the `Integer[] arr` into a **fixed-size `List<Integer>`**.
    
-   This list is **backed by the original array**. This means the list and the array share the same underlying memory; modifying the list directly modifies the array, and vice versa. No new array or copy is created — it's just a wrapper around the original array.

#### 2\. `java.util.Collections.rotate(...)`

-   Rotates the elements of the list **in place**.
    
-   The elements are shifted:
    
    -   `rotationCount > 0`: rightward
        
    -   `rotationCount < 0`: leftward
        
-   Internally, it repositions the elements using list operations like `get()` and `set()`.
    

#### 3\. Combined Effect

-   The array `arr` is rotated **in place**, without creating a new array.
    
-   Because `asList()` provides a view backed by the array, the rotation affects the original `arr`.
    

---
### Example

```java
Integer[] arr = {1, 2, 3, 4, 5};
Collections.rotate(Arrays.asList(arr), 2);
// arr becomes: {4, 5, 1, 2, 3}
```


## Why using rotate on List not Array directly?

Here's **why `Collections.rotate()` works only on `List`**, not on arrays:

#### 1\. **`Collections` Is Designed for `Collection` Types**

The `java.util.Collections` class provides utility methods specifically for working with the **Collection Framework**, and `List` is a core interface in that framework.

-   Arrays (`int[]`, `Integer[]`, etc.) are **not** part of the Collection hierarchy.
    
-   Arrays are **native Java language structures**, not objects that implement `List`.
    

---

#### 2\. **Method Signature of `Collections.rotate()`**

```java
public static void rotate(List<?> list, int distance)
```

-   It explicitly takes a `List` parameter — not an array.
    
-   The method internally relies on the `List` interface's methods like `get()`, `set()`, `size()`, etc.
    
-   Arrays don't have those methods — they use indexed access only (`arr[i]`), not method calls.
    

---

#### 3\. **Type System Differences**

-   A `List<Integer>` is an object implementing `List` interface.
    
-   An `Integer[]` array is just a block of memory with integer references — it does not support polymorphic behavior like `List`.
    

---

#### 4\. **Design Philosophy**

-   Java separates **core language structures (like arrays)** from the **Collection Framework**.
    
-   Collections are **more flexible**, but arrays are **faster and lower-level**.
    
-   `Collections.rotate()` is part of a high-level utility class — it's not meant for low-level operations on arrays.
    
