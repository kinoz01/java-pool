## **1\. Overview of Java Execution Model**

Java was designed to be **portable**, **secure**, and **high-performance**. The **JVM** is central to achieving these goals.

### High-Level Process:

```csharp
Java Source Code (.java)
        ↓ compiled by javac
Java Bytecode (.class)
        ↓ loaded and run by JVM
Executed by JVM (interpreted or JIT compiled)
        ↓
Native Machine Code
```

---

## **2\. The JVM – What It Is**

The **Java Virtual Machine (JVM)** is a **software-based engine** that:

-   Loads `.class` files containing **bytecode**
    
-   Validates and executes the code
    
-   Provides memory management and runtime services
    

It is **platform-specific**, meaning there's a different JVM implementation for Windows, Linux, macOS, etc.

However, the **bytecode** is the same across all platforms — that’s where the **platform independence** comes from.

---

## **3\. Java Bytecode – The Virtual Instruction Set**

-   Bytecode is **not Java source code**, and it’s **not machine code**.
    
-   It’s an intermediate, low-level set of instructions designed for the **JVM**, not any hardware.
    
-   When you compile `.java` files using `javac`, you get `.class` files containing [[bytecode]].
    

Example Java code:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
    }
}
```

Corresponding bytecode (simplified):

```text
0: getstatic     #2 // Get System.out
3: ldc           #3 // Load "Hello, world!"
6: invokevirtual #4 // Call println
9: return
```

---

## **4\. Components of the JVM**

The JVM is made up of several **key components** that work together to load, verify, and execute bytecode:

### a) **Class Loader Subsystem**

-   **Loads** `.class` files into memory.
    
-   Supports custom class loading.
    
-   Handles dynamic loading (classes are loaded only when needed).
    

### b) **Bytecode Verifier**

-   **Checks the integrity and security** of bytecode.
    
-   Prevents illegal memory access, stack overflows, etc.
    
-   Ensures the code follows JVM rules (e.g., no illegal casts).
    

### c) **Runtime Data Areas**

The JVM uses several memory areas at runtime:

| Memory Area | Purpose |
| --- | --- |
| **Method Area** | Stores class-level data like methods, constants, static variables |
| **Heap** | Stores all objects and their instance variables |
| **Stack** | Stores method frames, local variables, return values |
| **Program Counter (PC) Register** | Keeps track of the next bytecode to execute |
| **Native Method Stack** | Used when Java code calls native (non-Java) methods |

### d) **Execution Engine**

This is the **core** of the JVM — it executes the bytecode. It has two main strategies:

#### i) **Interpreter**

-   Executes bytecode **line-by-line**.
    
-   Simple and quick startup.
    
-   Slower performance for repeated code.
    

#### ii) **JIT Compiler (Just-In-Time)**

-   Detects frequently-used bytecode ("hot code").
    
-   **Compiles it into native machine code** at runtime.
    
-   **Caches** compiled native code for faster execution later.
    
-   Balances between startup speed and long-term performance.
    

---

## **5\. Java Native Interface (JNI)**

Java can call **native code** (written in C, C++, etc.) using JNI.

This is useful for:

-   Accessing OS-level features
    
-   Using existing native libraries
    
-   Performance-critical sections
    

However, it breaks platform independence, so it's used selectively.

---

## **6\. Garbage Collection**

The JVM automatically manages memory through **Garbage Collection (GC)**.

-   **Objects are stored in the heap**.
    
-   The JVM periodically scans for unreachable objects.
    
-   Unused objects are **automatically deleted** to free memory.
    
-   This reduces memory leaks and manual memory management errors.
    

Java has multiple GC algorithms, such as:

-   Serial GC
    
-   Parallel GC
    
-   G1 GC
    
-   ZGC (for low-latency)
    

---

## **7\. Execution Flow Recap**

### Step-by-Step:

1.  **Write Java code** in a `.java` file.
    
2.  Compile it with `javac` → creates `.class` bytecode files.
    
3.  Run the program with `java`:
    
    -   JVM starts.
        
    -   Class loader loads the `.class` file.
        
    -   Bytecode verifier checks the code.
        
    -   Execution engine runs the code:
        
        -   Interpreter runs simple code.
            
        -   JIT compiler optimizes hot code into native instructions.
            
4.  **Garbage collector** runs in the background to free memory.
    

---

## **8\. Platform Independence Explained Again**

-   You write Java code once.
    
-   `javac` compiles it into **standard bytecode**.
    
-   **All platforms** have a JVM implementation that understands this bytecode.
    
-   The JVM translates bytecode into native instructions appropriate for the current platform.
    

So your Java program runs **anywhere** a JVM exists — Windows, Linux, macOS, Android, etc.