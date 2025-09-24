```java
public class StringLength {
    public static int getStringLength(String s) {
        return (s == null) ? 0 : s.length();
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(StringLength.getStringLength("Hello World !"));
        System.out.println(StringLength.getStringLength(""));
    }
}
```

## 1\. **The Ternary Operator `?:`**

```java
return (s == null) ? 0 : s.length();
```

-   This is called the **ternary conditional operator**.
    
-   Syntax:
    
    ```java
    condition ? valueIfTrue : valueIfFalse
    ```
    
-   Works like a compact `if-else` expression.
    

Equivalent expanded code:

```java
if (s == null) {
    return 0;
} else {
    return s.length();
}
```

Why it’s interesting:

-   Unlike `if-else` (which is a **statement**), the ternary operator is an **expression** → it produces a value directly.
    
-   It forces both branches (`0` and `s.length()`) to be type-compatible (both must be `int` here).
    

---

## 2\. **`null` Checks**

The code explicitly checks for `null`:

```java
(s == null) ? 0 : s.length();
```

-   If `s` were `null` and we just called `s.length()`, it would throw a **`NullPointerException`**.
    
-   This is a common pattern: check for null before accessing methods or fields.
    

This is essentially a **safe guard** to make the method more robust.

---

## 3\. **Empty String vs `null`**

The program tests two cases:

```java
System.out.println(StringLength.getStringLength("Hello World !")); // normal string
System.out.println(StringLength.getStringLength(""));              // empty string
```

-   `"Hello World !"` → length is 13.
    
-   `""` (empty string) → length is 0.
    
-   If we passed `null`, it would return 0 because of the ternary check.
    

Interesting point:

-   `""` (empty string) is **not** the same as `null`.
    
    -   Empty string = a valid `String` object with length 0.
        
    -   `null` = no object at all.
        

---

## 4\. **Method Design Choice**

The method returns **0 for `null`**.  
This is a design decision:

-   Some developers prefer returning 0 (treating null as "no characters").
    
-   Others prefer throwing an exception to signal that `null` is unexpected.
    

This difference highlights the importance of **API design decisions**.

---

## 5\. **String Immutability (Indirectly Relevant)**

While not directly shown here, it’s worth noting:

-   Strings in Java are [[immutable]].
    
-   So `"Hello World !".length()` is safe; it doesn’t modify the string.