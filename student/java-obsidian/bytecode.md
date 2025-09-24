**"When I compile my Java code, why do I see readable Java-like code instead of bytecode when I open `.class` files, even though I thought `.class` files contain bytecode? How is that possible?"**

Here’s the clear explanation:

---

### What You’re Seeing

You're using **IntelliJ IDEA**, and it includes a tool called the **FernFlower decompiler**. This tool **automatically decompiles** `.class` files (which are normally in bytecode format) **back into human-readable Java code** inside the IDE.

So, when you open a `.class` file in IntelliJ, you're not seeing the actual bytecode. You're seeing the **decompiled version** of it, which **approximates** what the original `.java` code looked like.

---

### Why `.class` Files Don't Look Like Bytecode in IntelliJ

-   `.class` files **do contain bytecode** — a low-level, binary representation of your program.
    
-   But IntelliJ **detects** that you're opening a `.class` file and **automatically runs FernFlower** to show you a friendly Java-like view.
    
-   This is for **developer convenience**, to let you inspect `.class` files more easily without reading raw bytecode.
    

---

### What Is Bytecode Then?

Actual Java bytecode looks like this when viewed in a disassembler (e.g., `javap -c HelloWorld.class`):

```java
Compiled from "HelloWorld.java"
public class HelloWorld {
  public HelloWorld();
    Code:
       0: aload_0
       1: invokespecial #1
       4: return

  public static java.lang.String helloWorld();
    Code:
       0: ldc           #2       // String Hello Wrld !
       2: areturn
}
```

This is the **real structure** executed by the **JVM**, and it's what `javac` produces.

---

### How to See Actual Bytecode

To see the real bytecode, do one of the following:

1.  **Use `javap` (Java Disassembler)**:
    
    ```bash
    javap -c HelloWorld.class
    ```
    
2.  **Open `.class` file with a hex editor**: You’ll see binary content that’s not human-readable.
    

---

### Summary

-   You’re seeing decompiled code because **IntelliJ is showing a reverse-engineered version** using FernFlower.
    
-   The real `.class` files still contain bytecode.
    
-   To view actual bytecode, use tools like `javap`.

***
# Another Question

**"Why do a decompiler and a disassembler give different code outputs? What's the reason behind the difference?"**

Great question. Let's clarify what each tool does and **why they produce very different results**, even though they work from the same `.class` file.

---

## **1\. Key Difference: Decompiler vs Disassembler**

| Tool                                      | What it does                                        | Output format                                                      | Level of abstraction |
| ----------------------------------------- | --------------------------------------------------- | ------------------------------------------------------------------ | -------------------- |
| **Disassembler** (`javap -c`)             | Translates **bytecode** into **JVM instructions**   | JVM bytecode mnemonics (e.g., `getstatic`, `ldc`, `invokevirtual`) | **Low-level**        |
| **Decompiler** (e.g., FernFlower, JD-GUI) | Reconstructs **Java source code** from **bytecode** | Java-like source code (e.g., `System.out.println("Hello")`)        | **High-level**       |

---

## **2\. Why the Outputs Are Different**

### ► **Disassembler** (`javap -c`)

-   Reads `.class` file bytecode and prints the **exact JVM instructions**.
    
-   It doesn’t try to turn it into Java source code.
    
-   It reflects the **real operations** the JVM will execute.
    

**Example disassembled:**

```text
0: getstatic     #2 // Field java/lang/System.out
3: ldc           #3 // String "Hello"
6: invokevirtual #4 // Method println
```

This is **not meant to be readable Java code**, but it’s exact and precise — a 1:1 map from bytecode.

---

### ► **Decompiler** (e.g., FernFlower in IntelliJ)

-   Analyzes the `.class` file bytecode and **attempts to reconstruct** the **original Java source code**.
    
-   It's an **approximation** — bytecode lacks some high-level information (e.g., comments, variable names, some types).
    
-   The result is **not always 100% accurate**, but it gives readable Java-like code.
    

**Example decompiled:**

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
```

This looks like the original `.java` file — **even though the actual `.class` file only contains bytecode**.

---

## **3\. Why the Decompiler Can Only Approximate**

-   Java bytecode **does not preserve**:
    
    -   Original variable names (they’re replaced with `var0`, `var1`, etc.)
        
    -   Comments
        
    -   Some generic type information
        
    -   Some high-level control flow (like switch-case labels)
        
-   A decompiler tries to **guess and rebuild** the original structure.
    

That’s why the output:

-   **Looks like Java**, but
    
-   **May not match the exact original code**
    

Meanwhile, the disassembler shows the **true, exact JVM instructions**, not a guess or reconstruction.

---

## **4\. Summary**

-   **Disassembler** (`javap -c`): Shows **actual JVM bytecode** (low-level instructions).
    
-   **Decompiler** (e.g., FernFlower): Tries to **reverse-engineer the original Java source** (high-level code).
    
-   They differ because one shows **what the JVM executes**, the other **what the developer may have written**.