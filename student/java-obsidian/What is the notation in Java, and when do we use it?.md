The `::` operator in Java is called the **method reference operator**. It is a **shortcut for writing lambda expressions** when the lambda only calls an existing method.

This feature was introduced in **Java 8** and is used with **functional interfaces** (like `Function`, `Predicate`, `Consumer`, etc.).

It simplifies your code when you want to refer to a method **by name**, without actually calling it at that moment.

---

### General Syntax:

```java
ClassName::methodName
```

Depending on the context, it can refer to:

-   A **static method**
    
-   An **instance method** of a class
    
-   A **constructor**
    

---

### Types of Method References and Examples

#### 1\. **Reference to a Static Method**

```java
class Utils {
    public static int square(int x) {
        return x * x;
    }
}

Function<Integer, Integer> f = Utils::square;
System.out.println(f.apply(5)); // Output: 25
```

Equivalent lambda:

```java
Function<Integer, Integer> f = x -> Utils.square(x);
```

---

#### 2\. **Reference to an Instance Method of a Particular Object**

```java
class Printer {
    void print(String msg) {
        System.out.println(msg);
    }
}

Printer printer = new Printer();
Consumer<String> c = printer::print;
c.accept("Hello"); // Output: Hello
```

Equivalent lambda:

```java
Consumer<String> c = msg -> printer.print(msg);
```

---

#### 3\. **Reference to an Instance Method of a Class (Used for Streams)**

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
names.forEach(System.out::println);
```

Here, `System.out::println` is a method reference to the `println` method of the `PrintStream` class.

Equivalent lambda:

```java
names.forEach(name -> System.out.println(name));
```

---

#### 4\. **Reference to a Constructor**

```java
Supplier<List<String>> listSupplier = ArrayList::new;
List<String> list = listSupplier.get(); // Creates a new ArrayList
```

Equivalent lambda:

```java
Supplier<List<String>> listSupplier = () -> new ArrayList<>();
```

---

### When to Use `::` Method References

-   When you're **passing behavior** as a parameter (functional programming)
    
-   When a **lambda expression just calls one method**
    
-   To make the code **shorter and more readable**