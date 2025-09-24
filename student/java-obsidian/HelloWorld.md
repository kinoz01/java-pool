```java
public class HelloWorld {
    public static String helloWorld() {
        return  "Hello World !";
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(HelloWorld.helloWorld());
    }
}
```

## File/layout rules you should know

-   In Java, **each public top-level class must live in its own file** named exactly like the class (including case) plus `.java`.
    
    -   Put `HelloWorld` in `HelloWorld.java`.
        
    -   Put `ExerciseRunner` in `ExerciseRunner.java`.
        
    -   If you try to put both public classes in one file, the compiler errors. (Alternative: remove `public` from one of them.)
        
-   No `package` line is shown, so these classes are in the **default package**. In real projects you should declare a package and place files in matching folders (e.g., `package demo;` then `demo/HelloWorld.java`).
    

## [[Classes]] 1: `public class HelloWorld { ... }`

-   `public` — **access modifier**. This class is visible to any other code on the classpath. Without it (package-private), it would only be visible inside the same package.
    
-   `class` — declares a **reference type** (the blueprint for objects and static members).
    
-   `HelloWorld` — the **identifier** (class name). By convention, **PascalCase**.
    
-   `{ ... }` — the **class body** containing fields, methods, and nested types.
    

### Method: `public static String helloWorld() { ... }`

-   `public` — any code can call this method.
    
-   `static` — the method **belongs to the class**, not to instances. You can call it as `HelloWorld.helloWorld()` without `new HelloWorld()`.
    
-   `String` — the **return type**. This method must return a `java.lang.String`.
    
-   `helloWorld` — the **method name** (identifier). By convention, **camelCase**.
    
-   `()` — **parameter list**. Empty here, so the method takes no arguments.
    
-   Method body:
    
    ```java
    return "Hello World !";
    ```
    
    -   `return` — sends a value back to the caller; the value type must match the declared return type.
        
    -   `"Hello World !"` — a **string literal**. String literals are instances of `String` (interned by the compiler).
        
    -   `;` — statement terminator.
        

## Class 2: `public class ExerciseRunner { ... }`

-   Same explanations for `public`, `class`, `ExerciseRunner`, and `{ ... }` as above.
    

### Entry point: `public static void main(String[] args) { ... }`

This signature is **special**; the JVM looks for it as the program’s entry point.

-   `public` — the JVM (external to the class) must be able to call it.
    
-   `static` — the JVM calls it **without** creating an instance of `ExerciseRunner`.
    
-   `void` — **no return value**.
    
-   `main` — exact method name the JVM expects.
    
-   `(String[] args)` — a single parameter:
    
    -   `String[]` — an **array of String**. It holds command-line arguments.
        
    -   `args` — the **parameter name**. Access like `args.length`, `args[0]`, etc.
        

### Statement inside `main`:

```java
System.out.println(HelloWorld.helloWorld());
```

Breakdown:

-   `System` — a **class** in `java.lang` (that package is auto-imported).
    
-   `.` — the **member access operator**.
    
-   `out` — a **public static field** of `System`; type is `java.io.PrintStream`. It represents the standard output stream (usually your console).
    
-   `println(...)` — an **instance method** on `PrintStream` that prints its argument followed by a newline. It’s **overloaded**; here it accepts a `String`.
    
-   `HelloWorld.helloWorld()` — a **static method call**:
    
    -   `HelloWorld` — class qualifier (no object needed because the method is `static`).
        
    -   `helloWorld()` — returns the `String` `"Hello World !"`.
        
-   The returned `String` is passed to `println`, which writes it to the console and appends a newline.
    

Net effect: when `main` runs, the console shows:

```nginx
Hello World !
```


## Common variations and notes

-   You could make `helloWorld()` **non-static**, but then you would need an instance:
    
    ```java
    System.out.println(new HelloWorld().helloWorld());
    ```
    
-   You can accept and use command-line arguments:
    
    ```java
    if (args.length > 0) System.out.println("First arg: " + args[0]);
    ```
    
-   Best practice is to place classes in a **named package** and compile with `-d` so class files follow package folders:
    
    ```java
    // in file src/demo/HelloWorld.java
    package demo;
    public class HelloWorld { ... }
    
    // compile:
    javac -d out src/demo/HelloWorld.java src/demo/ExerciseRunner.java
    // run (class path set to 'out'):
    java -cp out demo.ExerciseRunner
    ```