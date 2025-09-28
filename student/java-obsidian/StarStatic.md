```java
public class CelestialObject {
    private double x, y, z;
    private String name;
    public static final double KM_IN_ONE_AU = 1.5e8;

    public CelestialObject() {
        this("Soleil", 0, 0, 0);
    }

    public CelestialObject(String name, double x, double y, double z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static double getDistanceBetween(CelestialObject a, CelestialObject b) {
        return Math.hypot(Math.hypot(a.x - b.x, a.y - b.y), a.z - b.z);
    }

    public static double getDistanceBetweenInKm(CelestialObject a, CelestialObject b) {
        return getDistanceBetween(a, b) * KM_IN_ONE_AU;
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        CelestialObject defaultStar = new CelestialObject();
        CelestialObject earth = new CelestialObject("Terre", 1.0, 2.0, 2.0);

        System.out.println(CelestialObject.getDistanceBetween(defaultStar, earth));
        System.out.println(CelestialObject.getDistanceBetweenInKm(defaultStar, earth));
        System.out.println(CelestialObject.KM_IN_ONE_AU);
    }
}
```

## 1. Code explained

This class models a **celestial object** in 3D space. It stores its position using Cartesian coordinates `(x, y, z)` and has a `name`. It also defines some utility methods for computing distances between two celestial objects.

The private fields

```java
private double x, y, z;
private String name;
```

Each object has its own copy of these fields. They are private, so they cannot be accessed directly outside the class. Access is controlled through getters and setters.

The constant

```java
public static final double KM_IN_ONE_AU = 1.5e8;
```

This is a class-level constant (`static final`). It represents the number of kilometers in one astronomical unit (1 AU ≈ 150 million km). Because it’s `static`, all instances of `CelestialObject` share the same constant value. Because it’s `final`, it cannot change.

The constructors

```java
public CelestialObject() {
    this("Soleil", 0, 0, 0);
}
```

This no-argument constructor creates a default celestial object positioned at the origin with the name `"Soleil"`. It delegates to the parameterized constructor:

```java
public CelestialObject(String name, double x, double y, double z) {
    this.name = name;
    this.x = x;
    this.y = y;
    this.z = z;
}
```

This constructor lets you specify the name and coordinates explicitly.

The getters and setters  
These provide controlled access to the private fields. For example:

```java
public double getX() { return x; }
public void setX(double x) { this.x = x; }
```

`getX()` retrieves the x-coordinate. `setX()` updates it. The pattern is repeated for `y`, `z`, and `name`.

The static methods for distance

```java
public static double getDistanceBetween(CelestialObject a, CelestialObject b) {
    return Math.hypot(Math.hypot(a.x - b.x, a.y - b.y), a.z - b.z);
}
```

This computes the **Euclidean distance** between two celestial objects `a` and `b` in 3D space:

$$
d = \sqrt{ (x_a - x_b)^2 + (y_a - y_b)^2 + (z_a - z_b)^2 }
$$

The **3D Euclidean distance formula** between points $A(x₁, y₁, z₁)$ and $B(x₂, y₂, z₂)$ is:

$$
d = \sqrt{(x₁ - x₂)^2 + (y₁ - y₂)^2 + (z₁ - z₂)^2}
$$

---

Java’s `Math.hypot(p, q)` computes:

$$
\sqrt{p^2 + q^2}
$$

So it’s a safe way of writing `sqrt(p*p + q*q)` (with less risk of overflow/underflow).

---

Now break down the code:

```java
Math.hypot(a.x - b.x, a.y - b.y)
```

This computes:

$$
\sqrt{(x₁ - x₂)^2 + (y₁ - y₂)^2}
$$

That’s the **distance in the XY-plane**. Let’s call this result `dXY`.

Then the outer call:

```java
Math.hypot(dXY, a.z - b.z)
```

expands to:

$$
\sqrt{ dXY^2 + (z₁ - z₂)^2 }
$$

Substitute `dXY`:

$$
= \sqrt{ \left( \sqrt{(x₁ - x₂)^2 + (y₁ - y₂)^2} \right)^2 + (z₁ - z₂)^2 }
$$

The square root and square cancel out:

$$
= \sqrt{ (x₁ - x₂)^2 + (y₁ - y₂)^2 + (z₁ - z₂)^2 }
$$

Which is exactly the 3D Euclidean distance formula.


```java
public static double getDistanceBetweenInKm(CelestialObject a, CelestialObject b) {
    return getDistanceBetween(a, b) * KM_IN_ONE_AU;
}
```

This method takes the AU distance and multiplies it by `KM_IN_ONE_AU` to return the distance in kilometers.

Both methods are `static` because they don’t operate on a single object’s state (`this`). Instead, they take two `CelestialObject` instances as arguments and compute something external to them.

Example usage:

```java
CelestialObject sun = new CelestialObject("Soleil", 0, 0, 0);
CelestialObject earth = new CelestialObject("Terre", 1, 0, 0);

System.out.println(CelestialObject.getDistanceBetween(sun, earth));       // 1.0 (AU)
System.out.println(CelestialObject.getDistanceBetweenInKm(sun, earth));  // 150000000.0 (km)
```

So, the class provides:

-   Data encapsulation (private fields + getters/setters)
    
-   Constructors with defaults and parameters
    
-   A physical constant (`KM_IN_ONE_AU`)
    
-   Utility methods for computing distances in AU and kilometers

## 2. `final` keyword

The keyword `final` in Java has slightly different meanings depending on where you use it, but the common theme is **“cannot be changed once set.”**

-   **Final variable**:  
    Once a `final` variable has been assigned a value, it cannot be reassigned.
    
    ```java
    final int x = 10;
    x = 20; // ❌ compile-time error
    ```
    
    For **primitive types** (`int`, `double`, etc.), the value itself can’t change.  
    For **object references**, the reference can’t change, but the object’s internal state can still be modified:
    
    ```java
    final StringBuilder sb = new StringBuilder("Hello");
    sb.append(" World"); // ✅ allowed, object changed
    sb = new StringBuilder("Hi"); // ❌ not allowed, reference reassignment
    ```
    
-   **Final field**:  
    Usually used for constants, often together with `static`.
    
    ```java
    public static final double PI = 3.14159;
    ```
    
-   **Final method**:  
    A method declared `final` cannot be overridden in subclasses.
    
    ```java
    class A {
        public final void show() {}
    }
    class B extends A {
        public void show() {} // ❌ compile-time error
    }
    ```
    
-   **Final class**:  
    A class declared `final` cannot be subclassed.
    
    ```java
    public final class String {}
    class MyString extends String {} // ❌ compile-time error
    ```
    

So yes, `final` means “unchangeable,” but what exactly can’t change depends on whether it’s applied to a variable, a method, or a class.

## 3. [[Static vs Instance Methods]]

## 4. Q&A

> Why do we add `static` for the variable `public static final double KM_IN_ONE_AU = 1.5e8;`? It’s just a variable, not a method.

The reason `static` is used here is because this variable is a **class-level constant**, not something that belongs to individual objects.

If you wrote it without `static`:

```java
public final double KM_IN_ONE_AU = 1.5e8;
```

then every `CelestialObject` you create would have its own copy of `KM_IN_ONE_AU` stored in memory, even though the value is the same for all objects. That wastes memory and doesn’t make sense conceptually: the definition of 1 Astronomical Unit (AU) is universal, not tied to a single star or planet object.

By adding `static`, you make it belong to the class itself. There’s only **one copy of it** in memory, shared by all objects.

That’s why you access it like this:

```java
double km = CelestialObject.KM_IN_ONE_AU;
```

instead of needing an object.

So the keywords have distinct roles here:

-   `static` → belongs to the class, not an object.
    
-   `final` → value cannot be reassigned once set.
    
-   Together, `static final` → a constant.
    

This is why Java constants are almost always declared as `public static final`.