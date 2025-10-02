```java
public abstract class Factorial {
    public abstract long calculate(int n);
}

public class IterativeFactorial extends Factorial {
    @Override public long calculate(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}

public class RecursiveFactorial extends Factorial {
    @Override public long calculate(int n) {
        return n <= 1 ? 1 : n * calculate(n - 1);
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        Factorial iterativeFactorial = new IterativeFactorial();
        Factorial recursiveFactorial = new RecursiveFactorial();

        // Test iterative factorial
        int number = 5;
        long iterativeResult = iterativeFactorial.calculate(number);
        System.out.println("Iterative Factorial of " + number + " is: " + iterativeResult); // Expected output: 120

        // Test recursive factorial
        long recursiveResult = recursiveFactorial.calculate(number);
        System.out.println("Recursive Factorial of " + number + " is: " + recursiveResult); // Expected output: 120
    }
}
```


-   The `Factorial` class **defines a method signature** (`calculate(int n)`) but **does not provide any implementation** (no method body).
    
-   By marking the method as `abstract`, you're saying: "Any class that extends `Factorial` must provide its own implementation of this method."
    
-   Because this class contains an abstract method, **the class itself must also be declared abstract**.
    

This design sets up a **common interface** (in the general sense, not the Java `interface`) or **template** that other classes can follow.

Then, you have two concrete classes:

```java
public class IterativeFactorial extends Factorial { ... }
public class RecursiveFactorial extends Factorial { ... }
```

Each of these subclasses **implements the abstract `calculate()` method** in its own way (one using a loop, one using recursion).

This is a real-world example of **runtime polymorphism**. You can write code that works with a general `Factorial` type, but use any subclass behind it:

```java
Factorial f = new IterativeFactorial();  // or new RecursiveFactorial();
f.calculate(5);  // Works because both subclasses implement calculate()
```


> "What does abstract mean here?"

It means that `Factorial` is a **base class that cannot be instantiated**, and it **requires** its subclasses to define the `calculate(int n)` method. It provides a unified structure, but not the actual logic — that’s left to its subclasses.