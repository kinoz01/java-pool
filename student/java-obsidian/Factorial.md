```java
public class Factorial {
    public static Integer factorial(Integer n) {
        if (n == null || n < 0) return null;
        int r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}
```

The difference between **`int`** and **`Integer`** in Java is fundamental. Let’s break it down clearly.

## 1\. **`int` (Primitive Type)**

-   A primitive data type.
    
-   Stores the actual numeric value directly.
    
-   Default value is `0` if not initialized.
    
-   Always has a value — cannot be `null`.
    
-   More memory efficient and faster since it avoids object overhead.
    

Example:

```java
int a = 5;
int b = 10;
int sum = a + b; // works directly with numbers
```

---

## 2\. **`Integer` (Wrapper Class)**

-   A class in `java.lang` that wraps a primitive `int` inside an object.
    
-   Provides useful methods (e.g., `Integer.parseInt`, `toString`, etc.).
    
-   Can hold `null`, which is useful when you want to represent "no value" or "not defined".
    
-   Takes more memory and is slower compared to `int` because it’s an object.
    

Example:

```java
Integer x = 5;       // Autoboxing: int → Integer
Integer y = null;    // Allowed, can store null
int sum = x + 10;    // Auto-unboxing: Integer → int
```

---

## 3\. **Autoboxing and Unboxing**

Java automatically converts between `int` and `Integer` when needed:

-   **Autoboxing**: `int → Integer`
    
-   **Unboxing**: `Integer → int`
    

Example:

```java
Integer obj = 10;  // autoboxing
int num = obj;     // unboxing
```

---

## 4\. **When to Use Each**

-   Use **`int`** when you are doing calculations, loops, or performance-critical tasks.
    
-   Use **`Integer`** when:
    
    -   You need to store `null` (e.g., database values, optional inputs).
        
    -   You want to use Java collections (`List`, `Map`, etc.) — because collections can only store objects, not primitives.

# More wrappers

| Feature | `int` (primitive) | `Integer` (object) |
| --- | --- | --- |
| Type | Primitive | Wrapper class |
| Can be `null`? | No | Yes |
| Default value | `0` | `null` |
| Memory use | Smaller, faster | Larger, slower |
| Useful for | Math, loops, performance | Collections, nullable values, APIs |

---

Would you like me to also explain what happens if you pass `null` to this factorial method and why it doesn’t work if the parameter was declared as just `int`?

You: 

what are other examples of this??

ChatGPT: 

Great — let’s go beyond just `int` vs `Integer`.

In Java, **all 8 primitive types** have **wrapper classes** in `java.lang`. This is exactly the same situation as `int` vs `Integer`.

---

## 1\. Primitives and Their Wrapper Classes

In Java, all 8 primitive types have wrapper classes in java.lang. This is exactly the same situation as int vs Integer.

| Primitive | Wrapper Class |
| --- | --- |
| `byte` | `Byte` |
| `short` | `Short` |
| `int` | `Integer` |
| `long` | `Long` |
| `float` | `Float` |
| `double` | `Double` |
| `char` | `Character` |
| `boolean` | `Boolean` |

---

## 2\. Examples of Differences

### Example A: `double` vs `Double`

```java
public static Double safeDivide(Double a, Double b) {
    if (a == null || b == null || b == 0) return null;
    return a / b;
}

public static double divide(double a, double b) {
    return a / b; // cannot check for nulls
}
```

-   `double` → fast, cannot be `null`.
    
-   `Double` → can be `null`, safe to use in methods dealing with optional values.
    

---

### Example B: `boolean` vs `Boolean`

```java
Boolean flag = null; // allowed
boolean safe = false;
// boolean safe = null; // compile error

if (flag == null) {
    System.out.println("Flag not set");
}
```

-   `boolean` → only `true` or `false`.
    
-   `Boolean` → can also be `null`, useful when something is "unknown".
    

---

### Example C: Using Collections

Collections cannot store primitives directly. Only wrapper classes are allowed.

```java
import java.util.*;

public class Example {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(5);     // autoboxing: int → Integer
        nums.add(null);  // allowed
        System.out.println(nums); // [5, null]
    }
}
```

If you tried `List<int> nums = new ArrayList<>();` → it won’t compile.

---

### Example D: Autoboxing/Unboxing

```java
Integer a = 10; // autoboxing
int b = a;      // unboxing

Double x = 5.5;
double y = x;   // unboxing
```

Java automatically converts between primitive and wrapper when needed.

---

### Example E: Default Values

-   In an object (like an array field of a class):
    
    ```java
    class Example {
        int num;        // defaults to 0
        Integer ref;    // defaults to null
    }
    ```
    

---

## 3\. Why This Exists

-   **Primitives**: efficient, memory-friendly, best for heavy math and loops.
    
-   **Wrappers**: add flexibility — can be `null`, work with generics, and have helper methods like `Integer.parseInt()` or `Double.isNaN()`.