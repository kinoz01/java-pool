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

####  7. Execution Flow

-   `javac` converts `.java` → `.class` (Java bytecode)
    
-   `java` runs `.class` via the JVM
    
-   The JVM interprets or compiles the bytecode into machine code and executes it.