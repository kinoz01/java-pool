**non-static methods cannot be called without creating an object of the class.** This is because non-static methods are tied to a **specific instance** of a class. They usually operate on the **instance variables** of that object.

---

## 1\. **Non-Static Method Requires an Object**

### Example:

```java
class Greeter {
    public void sayHello() {
        System.out.println("Hello!");
    }
}

public class Main {
    public static void main(String[] args) {
        Greeter g = new Greeter(); // Creating an object
        g.sayHello();              // Calling non-static method
    }
}
```

Trying to call `sayHello()` directly like this:

```java
Greeter.sayHello(); // ❌ Compile-time error
```

...will result in a **compile-time error**, because `sayHello()` is **non-static**.

---

## 2\. **Why the Restriction Exists**

-   A non-static method can access both **instance** and **static** variables.
    
-   But without an object, **instance variables don't exist**, so the method cannot run.
    

>Explanation:

When you define a **non-static method** in a class, that method is meant to work on the **data specific to an object** — this means variables that belong to an individual instance of the class. These variables are called **instance variables** because each object has its own copy of them.

Now, before you can call a non-static method, **you must create an object of the class**, because otherwise there is **no instance data** for that method to work with. If you try to call the method without creating an object, the program has no context — no "object" — to get the values of the instance variables from. That’s why the method can’t run: it relies on data that doesn’t exist yet.

In contrast, **static methods** belong to the **class itself**, not to any one object. Since they don’t depend on any instance-specific data, they can be called even before any objects are created.

Let’s break it down with an example.

```java
class Person {
    String name; // instance variable

    public void sayName() {
        System.out.println("My name is " + name); // depends on instance variable
    }

    public static void greet() {
        System.out.println("Hello!"); // does not depend on instance
    }
}
```

Now look at how these methods are used:

```java
Person.greet();      // This works fine — greet() is static
Person.sayName();    // This gives a compile-time error — no object exists
```

To call `sayName()`, you need to first create a `Person` object:

```java
Person p = new Person();
p.name = "John";
p.sayName();         // Now it works, because the object and its data exist
```

So when I said *"without an object, instance variables don't exist"*, I meant that unless you've created a specific object from the class, the non-static parts (like `name`, in the example) simply have not been created in memory — and so the method that depends on them cannot be used.

## 3\. **Static vs Non-Static Method Comparison**

| Feature | Static Method | Non-Static Method |
| --- | --- | --- |
| Belongs to | Class | Object (instance) |
| Accessed via | Class name | Object reference |
| Can access instance data | ❌ No | ✅ Yes |
| Needs object to call | ❌ No | ✅ Yes |