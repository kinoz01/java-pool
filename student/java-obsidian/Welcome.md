## Introduction
### What is Java?

Java is a high-level, object-oriented programming language developed by Sun Microsystems (now owned by Oracle). It’s designed to be platform-independent via the [[JVM|Java Virtual Machine]] (**JVM**), meaning you can write Java code once and run it anywhere.

### What is javac?

`javac` is the Java compiler. It takes your human-readable `.java` source files and compiles them into bytecode, which is saved in .class files. This bytecode can be executed by the JVM, not directly by the operating system.

### How to Compile and Run Java code

**Step-by-step:**

1.  **Write Code**: Save the code in a file called for example `Main.java`.
    
2.  **Compile**:
    

```bash
javac Main.java
```

This produces `Main.class`.

3.  **Run**:
    

```bash
java Main
```

This tells the JVM to execute the `Main.class` file.

####  Execution Flow

-   `javac` converts `.java` → `.class` (Java bytecode)
    
-   `java` runs `.class` via the JVM
    
-   The JVM interprets or compiles the bytecode into machine code and executes it.

### javac -d

-   `-d` tells `javac` **where to place the generated `.class` files**.
    
-   Normally, `javac` just puts the compiled `.class` files in the same directory as the `.java` source.
    
-   With `-d <directory>`, you specify an output directory, and `javac` will also create the proper sub-directories to match the package structure.
    

Example:

```bash
javac -d out MyProgram.java
```

-   Compiles `MyProgram.java`.
    
-   If the file has `package com.example;`, then the compiler creates `out/com/example/MyProgram.class`.
    

So `-d` = **destination directory for compiled classes**.

### java -cp

The `-cp` (or `-classpath`) flag in `java` (and also in `javac`) tells the JVM **where to look for classes** when running your program.

By default, Java only looks in the current directory (`.`). If your code depends on other compiled `.class` files or external `.jar` libraries, you need to tell Java where they are.

#### Usage:

```bash
java -cp <path> MainClass
```

-   `<path>` can be:
    
    -   A directory containing `.class` files
        
    -   A `.jar` file
        
    -   Multiple entries separated by `:` on Linux/macOS, or `;` on Windows
        

#### Examples:

1.  **Run with classes in a folder**
    
    ```bash
    java -cp out Main
    ```
    
    Looks for `Main.class` inside `out/`.
    
2.  **Run with a JAR file**
    
    ```bash
    java -cp mylib.jar Main
    ```
    
    Runs `Main`, but also loads classes from `mylib.jar`.
    
3.  **Run with multiple paths**
    
    ```bash
    java -cp out:lib/mylib.jar Main
    ```
    
    (Linux/macOS) Looks in `out/` and `lib/mylib.jar`.
    
    ```bash
    java -cp out;lib\mylib.jar Main
    ```
    
    (Windows syntax with `;` instead of `:`).
    

---

So in short:  
`-cp` = **class path** → tells Java where to find your own and third-party classes/JARs.