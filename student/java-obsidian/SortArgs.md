```java
public class SortArgs {
    public static void sort(String[] a) {
        java.util.Arrays.sort(a, java.util.Comparator.comparingInt(Integer::parseInt));
        System.out.println(String.join(" ", a));
    }
}

import java.io.*;

public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);

        var defaultOut = System.out;

        System.setOut(printStream);
        SortArgs.sort(new String[] { "4", "2", "1", "3" });
        System.setOut(defaultOut);

        String output = outputStream.toString();
        System.out.println(output.equals("1 2 3 4\n"));
    }
}
```

We can also **simply** do:

```java
import java.util.Arrays;

public class SortArgs {
    public static void sort(String[] a) {
        Arrays.sort(a, (b, c) -> Integer.parseInt(b) - Integer.parseInt(c));     
        System.out.println(String.join(" ", a));
    }
}
```
## ✅ 1. **Class: `SortArgs`**

### 🔸 Purpose:

Defines a method that sorts a `String[]` (array of strings) **numerically** — even though the values are stored as strings.

### 🔸 Code:

```java
public class SortArgs {
    public static void sort(String[] a) {
        java.util.Arrays.sort(a, java.util.Comparator.comparingInt(Integer::parseInt));
        System.out.println(String.join(" ", a));
    }
}
```

### 🔸 Explanation:

-   `String[] a`: The input array of numbers as strings, e.g., `{ "4", "2", "1", "3" }`.
    
-   `Arrays.sort(...)`: Sorts the array.
    
-   `Comparator.comparingInt(Integer::parseInt)`: Custom comparator — **converts strings to integers** and then compares them numerically.
    
-   `Integer::parseInt`: A **method reference**. Shorthand for `(str) -> Integer.parseInt(str)`.
    
-   `System.out.println(...)`: Prints the sorted array joined with spaces.
    

---

## ✅ 2. **Method Reference: `Integer::parseInt`**

### 🔸 What is it?

A **shortcut** for writing a lambda that calls a method.

### 🔸 Equivalent:

```java
str -> Integer.parseInt(str)
```

This is passed to the comparator so that each string is **converted to an integer** before sorting.

---

## ✅ **3\. Comparator** 

A **Comparator** in Java is used to define **custom sorting logic** — meaning you control how two elements are compared during sorting.

### 🔹 What is a Comparator?

Java normally sorts things like numbers or strings using **natural order**:

```java
String[] words = {"apple", "banana", "zebra"};
Arrays.sort(words);  // Sorted alphabetically
```

But when default sorting isn’t what you want — for example:

-   Sorting strings **as numbers**
    
-   Sorting objects by **custom fields**
    
-   Sorting in **reverse order**
    

👉 You use a **Comparator**.

### 🔹 Comparator Interface

A **Comparator** is a **functional interface** — meaning it has exactly **one method**:

```java
int compare(T o1, T o2);
```

This method must:

-   Return `< 0` if `o1` should come before `o2`
    
-   Return `0` if they’re equal
    
-   Return `> 0` if `o1` should come after `o2`
    

### 🔹 Using Comparator in Sorting

Here’s a traditional way to use a [[Comparator]]:

```java
Arrays.sort(a, new Comparator<String>() {
    public int compare(String s1, String s2) {
        return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
});
```

-   Converts both strings to integers.
    
-   Sorts them numerically.
    

This is long and verbose — so Java 8+ introduced **lambdas** and **method references**.

### 🔹 `Comparator.comparingInt(...)`

This is a **shortcut utility method** that creates a comparator for you.

```java
Comparator.comparingInt(func)
```

Means:

> "Compare objects by converting them to an `int` using the given function."

---

## ✅ 4. **Lambda Expressions in Java**

### 🔸 Syntax:

```java
(parameters) -> expression
```

### 🔸 Example:

```java
(s1, s2) -> Integer.parseInt(s1) - Integer.parseInt(s2)
```

-   Anonymous function (doesn’t need a name).
    
-   Used to pass behavior (like comparison logic).
    
-   Similar to **arrow functions in JavaScript**: `(a, b) => a - b`.
    

---

## ✅ 5. **JavaScript Comparison (Closures)**

### 🔸 In JavaScript:

```javascript
let numbers = ["4", "2", "1", "3"];
numbers.sort((a, b) => a - b);
```

### 🔸 In Java:

```java
Arrays.sort(a, (a, b) -> Integer.parseInt(a) - Integer.parseInt(b));
```

### 🔸 Closures:

In JavaScript, closures can access any outer variable.  
In Java, lambdas can **only** access `final` or **effectively final** variables.

---

## ✅ 6. **Class: `ExerciseRunner`**

### 🔸 Purpose:

-   Redirects `System.out` to capture printed output.
    
-   Compares the result to a known correct answer.
    

### 🔸 Key Code:

```java
ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
PrintStream printStream = new PrintStream(outputStream);
System.setOut(printStream);
SortArgs.sort(new String[] { "4", "2", "1", "3" });
System.setOut(defaultOut);
String output = outputStream.toString();
System.out.println(output.equals("1 2 3 4\n"));
```

---

## ✅ 7. **Why `System.setOut(...)` is Used**

-   Temporarily changes where `System.out.println()` writes to.
    
-   Instead of printing to the console, output goes into `outputStream`.
    

---

## ✅ 8. **`ByteArrayOutputStream` & `PrintStream`**

### 🔸 `ByteArrayOutputStream`

-   Stores output in memory as raw bytes.
    
-   Think of it like a notepad that you write into.
    

### 🔸 `PrintStream`

-   A stream that prints data (like `System.out`).
    
-   Can wrap `ByteArrayOutputStream`.
    

---

## ✅ 9. **Why `.toString()` is Used**

```java
String output = outputStream.toString();
```

-   Converts the **captured bytes** into a readable `String`.
    
-   Needed because `outputStream` stores raw bytes, not actual strings.
    

---

## ✅ 10. **`String.join(" ", a)`**

-   Joins elements of the array `a` with spaces.
    
-   Example:
    
    ```java
    String[] a = { "1", "2", "3" };
    String result = String.join(" ", a); // "1 2 3"
    ```
    

---

## ✅ 11. **Final Output Comparison**

```java
System.out.println(output.equals("1 2 3 4\n"));
```

-   Compares the captured output to the expected string.
    
-   Returns `true` if the result is correct.
