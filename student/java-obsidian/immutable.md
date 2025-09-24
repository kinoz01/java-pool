## 1\. **What Does Immutable Mean?**

An **immutable object** is one whose **state cannot be changed after creation**.

For `String`:

-   Once a `String` object is created, its characters can **never be changed**.
    
-   Any operation that looks like it modifies a string actually creates a **new `String` object**.
    

Example:

```java
String s = "Hello";
s.toUpperCase(); // looks like it changes it
System.out.println(s); // prints "Hello", not "HELLO"
```

Why?

-   `toUpperCase()` creates a new string `"HELLO"`, but leaves the original unchanged.
    
-   To use the new one, you must assign it:
    

```java
s = s.toUpperCase(); // now s points to "HELLO"
```

---

## 2\. **Why Strings Are Immutable**

Java designers made `String` immutable for several reasons:

1.  **Security**  
    Strings are often used in sensitive places (e.g., file paths, database URLs, class loading, passwords).  
    If strings were mutable, malicious code could alter them after validation.
    
    Example:  
    If you passed a `"read_only.txt"` string to a method, and later code could change it to `"delete_everything.txt"`, that would be a security risk.
    

---

2.  **Caching and String Pooling**  
    Java uses a **String Pool** (a special memory region).
    
    -   When you write:
        
        ```java
        String a = "Hello";
        String b = "Hello";
        ```
        
        Both `a` and `b` point to the **same object** in the pool.
        
    -   This saves memory and improves performance.
        
    
    This only works because strings are immutable — otherwise, if `a` changed, `b` would see the change too, which would break the pool.
    

---

3.  **Thread Safety**  
    Since strings never change, they are automatically **thread-safe**.
    
    -   Multiple threads can use the same string without synchronization.
        
    -   No risk of one thread modifying the string while another reads it.
        

---

4.  **Hashing and Collections**  
    Strings are often used as **keys in hash-based collections** (like `HashMap`).
    
    -   A string’s hash code is computed from its contents.
        
    -   If the string were mutable, changing its contents would change its hash, but the map would still think it’s in the old bucket.
        
    -   That would break the data structure.
        
    
    Because strings are immutable, their hash codes never change → safe to use in `HashMap`, `HashSet`, etc.
    

---

## 3\. **Operations That Create New Strings**

Every string operation that looks like modification actually returns a **new object**:

```java
String s = "Hello";
String t = s + " World"; // new string "Hello World"
String u = s.replace("H", "J"); // new string "Jello"
```

After this:

-   `s` is still `"Hello"`.
    
-   `t` is `"Hello World"`.
    
-   `u` is `"Jello"`.
    

---

## 4\. **When You Need Mutable Strings**

If you need to **modify character data frequently**, Java provides:

-   `StringBuilder` (not thread-safe, faster)
    
-   `StringBuffer` (thread-safe, slower)
    

Example:

```java
StringBuilder sb = new StringBuilder("Hello");
sb.append(" World"); // modifies in place
System.out.println(sb.toString()); // "Hello World"
```

---

## 5\. **Summary**

-   Strings are **immutable**: once created, they never change.
    
-   Reasons:
    
    -   Security
        
    -   String pooling
        
    -   Thread safety
        
    -   Reliable hashing in collections
        
-   Modification creates **new strings**.
    
-   For performance when modifying often → use `StringBuilder` or `StringBuffer`.