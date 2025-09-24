### **In-place mutation**

When we say *in-place mutation*, we mean **modifying the existing object (or memory buffer) directly**, rather than creating a new copy.

So instead of allocating a new string and returning it, the function alters the one you already have.

Examples:

-   **Rust**
    
    ```rust
    let mut s = String::from("hi");
    s.push('!');        // modifies the same buffer in memory
    println!("{s}");    // "hi!"
    ```
    
    `push` doesn’t create a new `String`. It changes `s` in place.
    
-   **Java with StringBuilder**
    
    ```java
    StringBuilder sb = new StringBuilder("hi");
    sb.append("!");   // appends to the same buffer
    System.out.println(sb); // "hi!"
    ```
    
    Again, no new object — the same buffer grows.
    
-   **JavaScript**
    
    ```js
    let s = "hi";
    let t = s + "!"; // creates a NEW string "hi!"
    ```
    
    Here `s` is untouched; `t` is a new string. JavaScript strings cannot be changed in place.
    

---

### **Not in-place**

When the old value remains intact and you get a new one back.

-   Java `String` methods (`replace`, `trim`) always return a *new* string.
    
-   JavaScript `String` methods (`toUpperCase`, `slice`) always return a *new* string.
    
-   Rust methods like `.replace()` on `String` also return a *new String* (they don’t touch the original).
    

---

### **In-place chaining**

That’s when:

1.  The object is mutable (can be changed in place), and
    
2.  Each mutating method returns the same object (`this` / `&mut self`), so you can call another method on it immediately.
    

Example in Java:

```java
StringBuilder sb = new StringBuilder("hi");
sb.append(" there").append("!").insert(0, "Say: ");
System.out.println(sb); // "Say: hi there!"
```

This is **in-place chaining**: one object, mutated step by step, no new copies, one fluid chain.

---

So:

-   **Rust `String`** → in-place mutation, but *no chaining* by default.
    
-   **Java `StringBuilder`** → in-place mutation *and* chaining.
    
-   **JS `String`** → chaining, but *not in place* (new string each time).
    