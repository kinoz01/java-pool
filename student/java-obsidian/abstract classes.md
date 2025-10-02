When we say an abstract class "cannot be instantiated," we mean that you **cannot create an object directly** from that class using the `new` keyword.

For example:

```java
abstract class Animal {
    abstract void sound();
}

// This will cause a compilation error
Animal a = new Animal(); // ❌ Not allowed
```

This is because abstract classes are meant to serve as **blueprints**. They may contain **unfinished methods** (i.e. methods without a body), which don’t make sense to run directly. Instead, you create objects from **subclasses** that provide complete implementations of those abstract methods.

Now, more on abstract classes in Java:

An abstract class is declared using the `abstract` keyword. It can include both **abstract methods** (which don't have a body) and **concrete methods** (which do). This gives it flexibility. You can provide default behavior in the abstract class that all subclasses inherit, while still forcing subclasses to implement certain methods.

Example:

```java
abstract class Animal {
    abstract void makeSound();

    void breathe() {
        System.out.println("Breathing...");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Bark!");
    }
}
```

In this case:

-   `makeSound()` is abstract and must be implemented by subclasses.
    
-   `breathe()` is a normal method inherited by subclasses like `Dog`.
    

You can use abstract classes when you want to **define a common base** with some shared code but leave some method definitions up to the subclasses. This is different from an interface, where you typically don’t provide any implementation (except default/static methods in Java 8+).

Also, abstract classes can have **constructors**, fields, and access modifiers (public, protected, private), while interfaces are more restricted in this regard.