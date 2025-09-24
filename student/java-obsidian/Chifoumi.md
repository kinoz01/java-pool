```java
public class Chifoumi {
    public enum ChifoumiAction {
        ROCK, PAPER, SCISSOR
    }

    public static ChifoumiAction getActionBeatenBy(ChifoumiAction chifoumiAction) {
        switch (chifoumiAction) {
            case ROCK:    return ChifoumiAction.SCISSOR;
            case PAPER:   return ChifoumiAction.ROCK;
            case SCISSOR: return ChifoumiAction.PAPER;
            default: throw new IllegalArgumentException("Unknown action: " + chifoumiAction);
        }
    }

    public static void main(String[] args) {
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.ROCK));
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.PAPER));
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.SCISSOR));
    }
}
```

# 1\. File Organization Rule in Java

### The Rule:

-   In Java, **at most one `public` top-level class (or interface, or enum) is allowed per `.java` file**.
    
-   If a class/enum is `public`, the file name must **exactly match the public type’s name**, including case, plus `.java`.
    

Examples:

-   `public class HelloWorld {}` → must be in `HelloWorld.java`.
    
-   `public enum ChifoumiAction {}` → must be in `ChifoumiAction.java`.
    

### Why this rule?

1.  **Clarity for the compiler and humans**:  
    If a public type is called `ExerciseRunner`, you instantly know it lives in `ExerciseRunner.java`.
    
2.  **Avoid ambiguity**:  
    If multiple public types lived in one file, Java wouldn’t know which to map to the file name.
    
3.  **Consistency in large projects**:  
    Tools (IDEs, build systems) rely on this predictable mapping.
    

### What about non-public (default/package-private)?

-   You *can* have multiple non-public classes/enums/interfaces in one file.
    
-   But only one of them can be `public`.
    
-   Example:
    
    ```java
    // File: Utils.java
    public class Utils {}
    class Helper {}   // package-private, allowed here
    enum Mode {}      // package-private, allowed here
    ```
    
    But you won’t see this often — usually, each top-level type gets its own file for clarity.
    

---

# 2\. The Code Explained Step by Step

## File 1: `Chifoumi.java`

```java
public class Chifoumi {
    public static ChifoumiAction getActionBeatenBy(ChifoumiAction chifoumiAction) {
        switch (chifoumiAction) {
            case ROCK:    return ChifoumiAction.SCISSOR;
            case PAPER:   return ChifoumiAction.ROCK;
            case SCISSOR: return ChifoumiAction.PAPER;
            default: throw new IllegalArgumentException("Unknown action: " + chifoumiAction);
        }
    }
}
```

### Keywords and Structure

-   `public class Chifoumi`
    
    -   `public`: accessible from anywhere.
        
    -   `class`: declares a class (blueprint of methods/fields).
        
    -   `Chifoumi`: class name, matches file name.
        

### Method

```java
public static ChifoumiAction getActionBeatenBy(ChifoumiAction chifoumiAction)
```

-   `public`: accessible anywhere.
    
-   `static`: belongs to the class, not objects → `Chifoumi.getActionBeatenBy(...)`.
    
-   `ChifoumiAction`: return type → method returns an enum value.
    
-   `getActionBeatenBy`: method name (camelCase, descriptive).
    
-   `(ChifoumiAction chifoumiAction)`: parameter of type `ChifoumiAction`.
    

### Switch Statement

```java
switch (chifoumiAction) {
    case ROCK:    return ChifoumiAction.SCISSOR;
    case PAPER:   return ChifoumiAction.ROCK;
    case SCISSOR: return ChifoumiAction.PAPER;
    default: throw new IllegalArgumentException("Unknown action: " + chifoumiAction);
}
```

-   `switch`: control flow that checks a value against **cases**.
    
-   `case ROCK`: if `chifoumiAction == ChifoumiAction.ROCK`, execute this branch.
    
-   `return`: exits the method, giving back a value.
    
-   `default`: fallback if none of the cases match.
    
    -   Here it throws an exception → defensive programming, in case future enums are added.
        

---

## File 2: `ChifoumiAction.java`

```java
public enum ChifoumiAction {
    ROCK, PAPER, SCISSOR
}
```

### Keywords

-   `public enum`:
    
    -   `enum` is a **special class** type that defines a fixed set of constants.
        
    -   Each constant (`ROCK`, `PAPER`, `SCISSOR`) is actually an instance of `ChifoumiAction`.
        
    -   Stronger than `int` constants — type-safe and self-documenting.
        

### Why enums?

-   Prevent invalid values (only ROCK, PAPER, SCISSOR are allowed).
    
-   Make code more readable (instead of `0, 1, 2`).
    

---

## File 3: `ExerciseRunner.java`

```java
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.ROCK));
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.PAPER));
        System.out.println(Chifoumi.getActionBeatenBy(ChifoumiAction.SCISSOR));
    }
}
```

### Keywords

-   `public class ExerciseRunner`: the "runner" class, containing the entry point.
    
-   `main(String[] args)`: program entry point recognized by JVM.
    
-   `System.out.println(...)`: prints to console.
    

### Calls

-   `Chifoumi.getActionBeatenBy(ChifoumiAction.ROCK)`:
    
    -   Calls the static method in `Chifoumi`, passing the enum value `ROCK`.
        
    -   The method returns which action `ROCK` beats (in this code, SCISSOR).
        
    -   Printed to console.

# 3. new vs old Switch
## 1\. Old Switch Statements

In classic `switch` **statements** (before Java 12), you almost always needed a `default`, because:

-   The compiler did **not check exhaustiveness**.
    
-   If you forgot to handle a case, execution could just "do nothing" and continue.
    
-   `default` was there as a safety net.
    

Example:

```java
switch (day) {
    case MONDAY: ...
    case TUESDAY: ...
    // forgot WEDNESDAY!
    default: System.out.println("Some other day");
}
```

---

## 2\. New Switch Expressions

In Java 12+ (final in Java 14), `switch` can be used as an **expression** that produces a value:

```java
return switch (chifoumiAction) {
    case ROCK    -> ChifoumiAction.SCISSOR;
    case PAPER   -> ChifoumiAction.ROCK;
    case SCISSOR -> ChifoumiAction.PAPER;
};
```

Here:

-   The `switch` must **always produce a value** (otherwise `return` would fail).
    
-   That means the compiler enforces **exhaustiveness**: all possible input values must be covered.
    

---

## 3\. Why `default` Is Not Needed Here

-   The type being switched on is `ChifoumiAction`, an **enum**.
    
-   That enum has exactly **three constants**: `ROCK`, `PAPER`, `SCISSOR`.
    
-   Your `switch` handles all three explicitly.
    
-   Since all enum constants are covered, the compiler knows the `switch` is exhaustive.
    
-   Therefore, **no `default` is required**.
    

If you removed one case (say, forgot `SCISSOR`), the compiler would complain:

```r
error: the switch expression does not cover all possible input values
```

---

## 4\. What If You Add a New Enum Constant?

Suppose you update the enum:

```java
public enum ChifoumiAction {
    ROCK, PAPER, SCISSOR, LIZARD
}
```

Now, your method won’t compile until you add a case for `LIZARD` or a `default`:

```r
error: the switch expression does not cover all possible input values
```

This is safer than the old `switch` statement, where the code would still compile but might behave incorrectly.