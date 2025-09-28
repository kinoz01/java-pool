```java
public class CelestialObject {
    public double x, y, z;
    public String name;
}

public class ExerciseRunner {
    public static void main(String[] args) {
        CelestialObject celestialObject = new CelestialObject();
        System.out.println(celestialObject.x);
        System.out.println(celestialObject.y);
        System.out.println(celestialObject.z);
        System.out.println(celestialObject.name);
    }
}
```

You define two classes. `CelestialObject` is a simple data holder with four **instance fields**: three `double`s (`x`, `y`, `z`) and one `String` (`name`). They are declared `public`, so any other class can access them directly. `ExerciseRunner` contains `main`, the program’s entry point.

When the JVM starts `main`, it executes `new CelestialObject()`. Because you didn’t declare any constructor, the compiler provides a **default no-arg constructor** that allocates a new object on the **heap** and **default-initializes** its fields:

-   Primitive numeric fields default to zero. For `double`, that is `0.0`.
    
-   Reference fields default to `null`. For `String name`, that is `null`.
    

So immediately after construction:

-   `celestialObject.x == 0.0`
    
-   `celestialObject.y == 0.0`
    
-   `celestialObject.z == 0.0`
    
-   `celestialObject.name == null`
    

`celestialObject` itself is a **reference variable** (stored on the stack frame for `main`) that points to the heap object. The four `System.out.println(...)` calls read those fields and print their values to standard output. Printing `0.0` three times is straightforward; printing a `null` reference is also safe—`println` will literally print `null`. There is no `NullPointerException` here because you are not calling a method on `name`; you are only passing the `null` reference to `println`.

If these four variables were **local variables** instead of fields (e.g., declared inside `main`), Java would require you to explicitly initialize them before use. The reason your code compiles and runs without explicit assignments is that **instance fields** are always default-initialized by the JVM as part of object construction.

A few practical and stylistic notes that follow from what the code is doing:

-   In Java, each **public** top-level class must live in its own file named after the class. This means you should have:
    
    -   `CelestialObject.java` containing `public class CelestialObject { ... }`
        
    -   `ExerciseRunner.java` containing `public class ExerciseRunner { ... }`  
        Putting both public classes in one source file will not compile.
        
-   Making fields `public` couples external code to your internal representation. A common practice is to use `private` fields with a constructor and accessors:
    
    ```java
    public class CelestialObject {
        private final double x, y, z;
        private final String name;
    
        public CelestialObject(double x, double y, double z, String name) {
            this.x = x; this.y = y; this.z = z; this.name = name;
        }
    
        public double getX() { return x; }
        public double getY() { return y; }
        public double getZ() { return z; }
        public String getName() { return name; }
    }
    ```
    
    This avoids exposing mutable state and prevents accidental use of default values.
    
-   If you want non-default values without writing a constructor, you could assign them directly at the field declaration site:
    
    ```java
    public double x = 1.0, y = 2.0, z = 3.0;
    public String name = "Sun";
    ```
    
    Then printing would show `1.0`, `2.0`, `3.0`, and `Sun`.
    
-   Printing `name.length()` at this point would throw a `NullPointerException`, because `name` is `null`. Always check for `null` or initialize the field before dereferencing:
    
    ```java
    if (celestialObject.name != null) {
        System.out.println(celestialObject.name.length());
    }
    ```