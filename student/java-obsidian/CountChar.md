```java
public class CountChar {
    public static int count(String s, char c) {
        return s == null ? 0 : (int) s.chars().filter(ch -> ch == c).count();
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(CountChar.count("Hello World !", 'l'));
    }
}
```

### What is `(int)`?

```java
(int) s.chars().filter(ch -> ch == c).count();
```

-   The `count()` method returns a `long` value.
    
-   `(int)` is a **type cast**: it converts the `long` to an `int`.
    
-   This is necessary because the method declares its return type as `int`, but `count()` produces a `long`.

### Casting examples

```java
public class CastingExamples {
    public static void main(String[] args) {
        
        // 1. Implicit Casting (Widening)
        int i = 100;
        long l = i; // int to long
        float f = l; // long to float
        double d = f; // float to double

        System.out.println("Implicit Casting:");
        System.out.println("int to long: " + l);
        System.out.println("long to float: " + f);
        System.out.println("float to double: " + d);

        // 2. Explicit Casting (Narrowing)
        double d2 = 123.456;
        float f2 = (float) d2; // double to float
        long l2 = (long) f2;   // float to long
        int i2 = (int) l2;     // long to int

        System.out.println("\nExplicit Casting:");
        System.out.println("double to float: " + f2);
        System.out.println("float to long: " + l2);
        System.out.println("long to int: " + i2);

        // 3. Char and int casting
        char c = 'A'; // Unicode value 65
        int charToInt = c; // Implicit
        char intToChar = (char) (charToInt + 1); // Explicit

        System.out.println("\nChar and Int Casting:");
        System.out.println("char to int: " + charToInt);
        System.out.println("int to char: " + intToChar);

        // 4. Byte and short casting
        byte b = 10;
        short s = b; // Implicit
        byte b2 = (byte) s; // Explicit

        System.out.println("\nByte and Short Casting:");
        System.out.println("byte to short: " + s);
        System.out.println("short to byte (cast): " + b2);

        // 5. Overflow Example in Casting
        int bigInt = 130;
        byte overflowByte = (byte) bigInt; // Will overflow (130 > Byte.MAX_VALUE = 127)

        System.out.println("\nOverflow Casting:");
        System.out.println("int 130 to byte (overflow): " + overflowByte);

        // 6. Object Casting
        Object obj = "Hello";
        String str = (String) obj; // Downcasting Object to String

        System.out.println("\nObject Casting:");
        System.out.println("Object to String: " + str);

        // 7. Invalid Casting (will throw exception at runtime)
        try {
            Object number = Integer.valueOf(42);
            String wrongCast = (String) number; // Will throw ClassCastException
            System.out.println("Invalid cast: " + wrongCast);
        } catch (ClassCastException e) {
            System.out.println("\nInvalid Casting:");
            System.out.println("Caught exception: " + e);
        }
    }
}
```


```java
    String wrongCast = (String) number; // ❌ Invalid cast
    ```
    
❌ Why This Fails:

-   The actual object is an `Integer`, **not** a `String`.
    
-   Java allows you to **compile** this cast, because `Object` can be cast to any reference type.
    
-   But at **runtime**, Java checks the actual type of the object (`Integer`) and sees it's not a `String`.
    
-   So it throws

### ✅ Difference Between **Implicit** and **Explicit** Casting in Java

| Feature | **Implicit Casting (Widening)** | **Explicit Casting (Narrowing)** |
| --- | --- | --- |
| **Definition** | Automatic conversion by the compiler. | Manual conversion using a cast operator `(type)`. |
| **Also Known As** | Widening conversion | Narrowing conversion |
| **Syntax** | Done automatically — no cast needed | Must use cast — e.g., `(int) someDouble` |
| **Safe?** | Always safe — no data loss | Risky — may cause data loss or overflow |
| **When Used** | When converting smaller to larger data types | When converting larger to smaller data types |
| **Example** | `int i = 10; long l = i;` | `double d = 10.5; int i = (int) d;` |
| **Possible Issues** | None | Data truncation, overflow, or `ClassCastException` (objects) |
