```java
public class DoOp {
    public static String operate(String[] a) {
        if (a == null || a.length != 3)
            return "Error";
        int x = Integer.parseInt(a[0]), y = Integer.parseInt(a[2]);
        return switch (a[1]) {
            case "+" -> "" + (x + y);
            case "-" -> "" + (x - y);
            case "*" -> "" + (x * y);
            case "/" -> y == 0 ? "Error" : "" + (x / y);
            case "%" -> y == 0 ? "Error" : "" + (x % y);
            default -> "Error";
        };
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(DoOp.operate(new String[] { "1", "+", "2" }));
        System.out.println(DoOp.operate(new String[] { "1", "-", "1" }));
        System.out.println(DoOp.operate(new String[] { "1", "%", "0" }));
        System.out.println(DoOp.operate(args));
    }
}
```

The `main` method has the signature:

```java
public static void main(String[] args)
```

The `args` parameter is an array of `String`. When you run a Java program from the command line, you can pass extra words after the class name. These get placed in the `args` array.

Example:

```bash
java ExerciseRunner hello world 42
```

Inside Java:

-   `args[0] == "hello"`
    
-   `args[1] == "world"`
    
-   `args[2] == "42"`
    

So when this code calls `DoOp.operate(args)`, it’s expecting the user to provide 3 arguments: a number, an operator, and another number. For example:

```bash
java ExerciseRunner 10 * 5
```

Then `args` will be `["10", "*", "5"]`.

---

Another interesting piece is **`Integer.parseInt(...)`**. This converts a string into an integer. Since `args` is an array of `String`, `"10"` must be converted into `10` (an `int`) before doing arithmetic. If you pass something invalid like `"abc"`, `parseInt` throws a `NumberFormatException`.

---

The code also uses string concatenation with `"" + (x + y)`. This is a quick trick to turn a number into a string:

-   `(x + y)` is an `int`
    
-   `"" + (x + y)` forces Java to concatenate it with an empty string, producing a new `String`.  
    For example, if `x + y = 3`, this becomes `"3"`. A more explicit way is `String.valueOf(x + y)`.
    