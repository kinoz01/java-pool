**Polymorphism** means **"many forms"**. In Java, it allows objects to take on many forms depending on the context, primarily via:

-   **Compile-time (Static) Polymorphism** – Achieved by **method overloading**
    
-   **Runtime (Dynamic) Polymorphism** – Achieved by **method overriding**
    

Polymorphism allows one interface to be used for a general class of actions.

---

## 1. Static Polymorphism (Method Overloading)

**Method Overloading** occurs when multiple methods in the same class have the **same name** but **different parameters**.

```java
class MathUtils {
    int add(int a, int b) {
        return a + b;
    }

    double add(double a, double b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

> All `add()` methods are overloaded based on parameter types or count.

---

## 2. Dynamic Polymorphism (Method Overriding)

**Method Overriding** happens when a subclass provides a specific implementation of a method already defined in its superclass.

### Example:

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Dog barks");
    }
}
```

```java
Animal obj = new Dog();
obj.sound(); // Outputs: Dog barks
```

> This is **runtime polymorphism** — the JVM decides which method to run at runtime.

---

##  3. The `@Override` Annotation

This annotation ensures that you're correctly overriding a method.

```java
@Override
void sound() {
    System.out.println("Dog barks");
}
```

-   Helps catch errors if the method name or signature is incorrect.
    
-   Not mandatory, but highly recommended.
    

---

## 4. Abstract Classes and Methods

### What is an Abstract Class?

An abstract class in Java:

-   Cannot be instantiated [[abstract classes|🔗]].
    
-   May contain **abstract methods** (without implementation) and **concrete methods** (with implementation).
    

```java
abstract class Animal {
    abstract void sound();

    void eat() {
        System.out.println("This animal eats food.");
    }
}
```

### Subclassing an Abstract Class

```java
class Cat extends Animal {
    @Override
    void sound() {
        System.out.println("Cat meows");
    }
}
```

> Abstract methods **must** be implemented by non-abstract subclasses [[abstract|🔗]].

---
## 5. Interfaces and Implementation

An **interface** defines a contract — a set of abstract methods that implementing classes must define.

```java
interface Vehicle {
    void start();
    void stop();
}
```

### Implementing an Interface

```java
class Car implements Vehicle {
    public void start() {
        System.out.println("Car started");
    }

    public void stop() {
        System.out.println("Car stopped");
    }
}
```

> You must implement **all** methods of the interface unless the class is also abstract.

---

## Related Concepts

| Concept | Description |
| --- | --- |
| **Inheritance** | Enables subclass to inherit methods from superclass. Required for method overriding. |
| **Encapsulation** | Bundling data with methods, usually via private fields and public getters/setters. |
| **Interface vs Abstract** | Interface: 100% abstract (pre-Java 8), multiple inheritance allowed. Abstract: can have some implemented methods. |
| **Upcasting** | Treating a subclass object as an instance of a superclass or interface (enables polymorphism). |
| **Dynamic Method Dispatch** | JVM mechanism to select the overridden method at runtime based on the object. |

---

## Quick Polymorphism Demo

```java
class Shape {
    void draw() {
        System.out.println("Drawing shape");
    }
}

class Circle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing circle");
    }
}

class Square extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing square");
    }
}

public class Test {
    public static void main(String[] args) {
        Shape s1 = new Circle();
        Shape s2 = new Square();
        s1.draw(); // Outputs: Drawing circle
        s2.draw(); // Outputs: Drawing square
    }
}
```
