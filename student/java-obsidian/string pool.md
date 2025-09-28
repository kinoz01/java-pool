
```java
public class Main {
    public static void main(String[] args) {
        String s1 = "HELLO";
        String s2 = "HELLO";

        // Reference comparison
        System.out.println("s1 == s2 : " + (s1 == s2));

        // Content comparison
        System.out.println("s1.equals(s2) : " + s1.equals(s2));

        // Force different objects
        String s3 = new String("HELLO");
        System.out.println("s1 == s3 : " + (s1 == s3));
        System.out.println("s1.equals(s3) : " + s1.equals(s3));
    }
}
```

> Why `s1 == s3` would print false??

### 1\. What is the String Pool?

-   The **String Pool** (also called the **intern pool**) is a special memory region inside the Java Heap where **string literals** are stored.
    
-   It ensures that every literal `"HELLO"` in your program points to the **same object**, saving memory and allowing faster equality checks with `==`.
    

Example:

```java
String a = "HELLO";
String b = "HELLO";

System.out.println(a == b); // true → both reference the same object in the pool
```

---

### 2\. Why does Java do this?

Strings are **immutable** in Java (you can’t change their characters once created).  
That means if `"HELLO"` is shared across your code, no one can accidentally modify it.  
So Java is free to reuse a single `"HELLO"` instance across multiple variables.

---

### 3\. What happens with `new String()`?

When you do this:

```java
String c = new String("HELLO");
```

-   Java creates a **new object in the heap**, outside of the pool.
    
-   Now you have:
    
    -   One `"HELLO"` in the pool.
        
    -   One `"HELLO"` in the heap (different reference).
        

So:

```java
System.out.println(a == c);    // false (different objects)
System.out.println(a.equals(c)); // true (same characters)
```

---

### 4\. Forcing Pool Membership: `intern()`

You can tell Java: “Take this string and put it in the pool (or reuse it if it’s already there).”

```java
String c = new String("HELLO");
String d = c.intern();

System.out.println(a == d); // true, because d now points to the pooled "HELLO"
```

---

### 5\. Memory Layout Mental Model

Think of it like this:

-   **String Pool (inside heap)**:  
    `"HELLO"` (one shared reference)
    
-   **Heap (normal objects)**:  
    `new String("HELLO")` (separate object, same content)
    

So:

-   `"HELLO"` literal always goes to the **pool**.
    
-   `new String("HELLO")` makes a **new heap object** every time.
    

---

### 6\. Summary Rules

-   String literals (`"text"`) → go into the pool, reused.
    
-   `new String("text")` → new object, not reused.
    
-   `==` → compares references (same object?).
    
-   `.equals()` → compares characters (same content?).
    
-   `.intern()` → ensures a string is in the pool.
    
