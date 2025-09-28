Starting from Java 8, interfaces are no longer limited to just abstract methods. Java added more flexibility by allowing **default**, **static**, and later in Java 9, **private** methods in interfaces. Here's a breakdown of each:

---

## 1\. **Default Methods** (Java 8+)

### **What is a default method?**

A **default method** is a method in an interface that has a **method body**. It allows interface designers to **add new methods to interfaces** without breaking the existing implementations of those interfaces.

### **Syntax:**

```java
interface MyInterface {
    default void show() {
        System.out.println("Default show method");
    }
}
```

### **Key Points:**

-   Must use the `default` keyword.
    
-   Has a body (i.e., it’s not abstract).
    
-   Can be **inherited** by implementing classes.
    
-   Can be **overridden** by the implementing class.
    

### **Use Case:**

Adding new functionality to interfaces **without breaking** existing code.

### **Example:**

```java
interface Animal {
    default void breathe() {
        System.out.println("Breathing...");
    }
}

class Dog implements Animal {
    // Inherits breathe() by default
}
```

---

## 2\. **Static Methods** (Java 8+)

### **What is a static method in an interface?**

A **static method** in an interface is associated with the **interface itself**, not with its instances. It is like a utility method that can be used without implementing the interface.

### **Syntax:**

```java
interface Utility {
    static int square(int x) {
        return x * x;
    }
}
```

### **Key Points:**

-   Must use the `static` keyword.
    
-   Can be called using the **interface name**, not via the implementing class or object.
    
-   Not inherited by implementing classes.
    

### **Use Case:**

Defining **helper** or **utility methods** related to the interface.

### **Example:**

```java
int result = Utility.square(5); // Output: 25
```

---

## 3\. **Private Methods** (Java 9+)

### **What is a private method in an interface?**

A **private method** in an interface is used to **share code between default or static methods** inside the same interface. It’s not visible outside the interface.

### **Syntax:**

```java
interface Logger {
    default void logInfo(String msg) {
        log(msg, "INFO");
    }

    default void logError(String msg) {
        log(msg, "ERROR");
    }

    private void log(String msg, String level) {
        System.out.println(level + ": " + msg);
    }
}
```

### **Key Points:**

-   Must use the `private` keyword.
    
-   Cannot be accessed by classes that implement the interface.
    
-   Cannot be abstract.
    
-   Meant for **internal code reuse** within the interface.
    

### **Use Case:**

Reducing code duplication among default methods or static methods in the same interface.

---

## Summary Table

| Method Type | Available Since | Inheritable | Requires Implementation in Class | Use Case |
| --- | --- | --- | --- | --- |
| Abstract | Java 1.0 | Yes | Yes | Define method contracts |
| Default | Java 8 | Yes | No (optional override) | Add new methods without breaking code |
| Static | Java 8 | No | Not applicable | Utility/helper methods |
| Private | Java 9 | No | Not applicable | Code reuse within the interface |
