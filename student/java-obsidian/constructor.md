In Java, a **constructor must have exactly the same name as the class** in which it is declared. This is how the compiler recognizes that a method-like declaration is in fact a constructor and not a regular method.

A constructor also **has no return type**, not even `void`. If you add a return type, it becomes a normal method, not a constructor.

---

### Example (Correct)

```java
public class CelestialObject {
    public String name;

    // Constructor (same name as class, no return type)
    public CelestialObject(String name) {
        this.name = name;
    }
}
```

Usage:

```java
CelestialObject sun = new CelestialObject("Soleil");
System.out.println(sun.name); // Soleil
```

---

### Example (Incorrect — Different Name)

```java
public class CelestialObject {
    public String name;

    // Wrong: does not match class name
    public void CelestialObject(String name) {
        this.name = name;
    }
}
```

This is **not** a constructor. It’s just a normal method called `CelestialObject` that returns `void`.  
If you write `new CelestialObject("Soleil");`, the compiler will fail because no constructor matching `(String)` exists.

---

### Summary

-   Constructor name = Class name (must match exactly, including case).
    
-   Constructors have **no return type**.
    
-   If you don’t declare any constructor, Java provides a **default no-argument constructor**.