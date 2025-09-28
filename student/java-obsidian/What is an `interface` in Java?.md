In Java, an **interface** is a reference type that defines a set of **abstract methods** (i.e., method signatures without implementations). It acts as a **contract** that any implementing class must follow.

---

## 1\. Purpose of an Interface

An interface is used to:

-   Define **what** a class should do (not **how**)
    
-   Enable **polymorphism**
    
-   Support **multiple inheritance** of type
    
-   Allow code **flexibility** and **reusability**
    

---

## 2\. Syntax Example

```java
interface Animal {
    void eat();        // abstract method
    void sleep();
}

class Dog implements Animal {
    public void eat() {
        System.out.println("Dog eats");
    }
    public void sleep() {
        System.out.println("Dog sleeps");
    }
}
```

In this example:

-   `Animal` is an interface.
    
-   `Dog` implements the interface and provides method bodies.
    

---

## 3\. Key Characteristics

| Feature | Description |
| --- | --- |
| Methods | Abstract by default (in Java 7 and below) |
| Fields | `public static final` by default |
| Multiple Inheritance | A class can implement **multiple interfaces** |
| Cannot be instantiated | You cannot create an object of an interface |

---

## 4\. Java 8 and Beyond

Since Java 8, interfaces can also have:

-   **Default methods** (with a body)
    
-   **Static methods**
    
-   **Private methods** (Java 9+)
    

**Example:**

```java
interface Vehicle {
    default void start() {
        System.out.println("Vehicle starting");
    }
}
```

---

## 5\. Common Interfaces in Java

| Interface | Purpose |
| --- | --- |
| `List` | Ordered collection |
| `Set` | Unique elements collection |
| `Map.Entry` | Represents a key-value pair |
| `Comparable` | Allows natural ordering |
| `Runnable` | Used for threads |

---

**In summary:**  
An interface in Java is a way to define **abstract behavior** that can be implemented by multiple classes. It is central to achieving **abstraction**, **flexibility**, and **polymorphism** in Java applications.