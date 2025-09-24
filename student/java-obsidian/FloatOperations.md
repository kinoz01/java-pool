```java
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println("Add");
        System.out.println(FloatOperations.addTwoFloats(1.0f, 2.5f));
        System.out.println(FloatOperations.addTwoFloats(15.18f, 68.28347f));

        System.out.println("Divide");
        System.out.println(FloatOperations.divideTwoFloats(7.0f, 2.0f));
        System.out.println(FloatOperations.divideTwoFloats(5.6f, 6.9f));
    }
}

public class FloatOperations {
    public static float addTwoFloats(float a, float b) {
        return a + b;
    }

    public static float divideTwoFloats(float a, float b) {
        return a / b;
    }
}
```

## **Primitive Type `float`**

-   `float` is a **32-bit primitive type** in Java.
    
-   Used for decimal numbers, but with **less precision** than `double` (64-bit).
    
-   Literals must have the suffix **`f`** (or `F`) to be treated as floats:
    
    ```java
    1.0f   // float
    2.5F   // float
    1.0    // double (by default)
    ```
    

If you leave off the `f`, the compiler interprets the literal as a `double` and will complain unless you cast it.

---

##  **Arithmetic with Floats**

-   `a + b` → addition
    
-   `a / b` → division
    

When dividing floats:

-   If `b` is **zero**, you don’t get an exception (like with `int`); instead, you get special IEEE-754 values:
    
    -   `Infinity` (positive or negative)
        
    -   `NaN` (“Not a Number”)
        

Example:

```java
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(5.0f / 0);   // Infinity
		System.out.println(-5.0f / 0);  // -Infinity
		System.out.println(0.0f / 0);   // NaN
    }
}
```

This is different from integer division, which throws `ArithmeticException` on division by zero.

---

## **Precision Limitations**

-   Floats cannot exactly represent all decimal values.
    
-   This is because floats use **binary approximations** of decimals.
    
-   If precision is important (e.g. money), use **`double`** or even **`BigDecimal`**.