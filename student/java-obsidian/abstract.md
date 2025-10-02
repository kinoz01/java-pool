If a class inherits from an abstract class (or implements an interface) and that class itself is **not** marked abstract, it is **required** to provide actual code (a body) for all the abstract methods it inherited.

In other words, if a class is “concrete” (meaning you can create objects from it), it cannot leave any inherited abstract methods unimplemented — otherwise the compiler will complain, because the class would still be “incomplete.”

Example:

```java
abstract class Animal {
    abstract void makeSound(); // no body
}

class Dog extends Animal {
    // This class is NOT abstract, so it MUST implement makeSound
    void makeSound() {
        System.out.println("Bark!");
    }
}
```

Here `Dog` is a normal class (not marked abstract), so it **has to** provide a body for `makeSound()`.

But if you make `Dog` abstract too:

```java
abstract class Dog extends Animal {
    // no implementation for makeSound()
}
```

This is allowed because `Dog` is also abstract — it doesn’t have to implement the abstract method yet. But then you still can’t make an object of `Dog` directly until some subclass finally implements the method.