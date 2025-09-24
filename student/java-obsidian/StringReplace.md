```java
public class StringReplace {
    public static String replace(String s, char  target, char  replacement) {
        return s.replace(target, replacement);
    }
    public static String replace(String s, String  target, String  replacement) {
        return s.replace(target, replacement);
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(StringReplace.replace("javatpoint is a very good website", 'a', 'e'));
        System.out.println(StringReplace.replace("my name is khan my name is java", "is","was"));
        System.out.println(StringReplace.replace("hey i'am java", "I'am","was"));
    }
}
```

### Two `replace` Methods

Notice there are **two methods with the same name** `replace`, but different parameter lists:

1.  `replace(String s, char target, char replacement)`
    
2.  `replace(String s, String target, String replacement)`
    

This is called **method overloading**.

-   **Overloading rule**: Methods can have the same name if their **parameter types or count are different**.
    
-   The compiler decides which one to use based on the arguments passed.

#### What They Do

Both methods internally call Java’s built-in `String.replace(...)` methods:

-   **`replace(char oldChar, char newChar)`**  
    Replaces all occurrences of a given character with another character.
    
-   **`replace(CharSequence target, CharSequence replacement)`**  
    Replaces all occurrences of a substring (like `"is"`) with another substring (like `"was"`).
    

So your methods are just **wrappers** around the built-in ones.

*******

### Recap

#### a) **Method Overloading**

-   Both methods are called `replace`.
    
-   Which one is used depends on the **argument types**.
    
-   If you pass `'a'`, `'e'` (chars), the first one is chosen.
    
-   If you pass `"is"`, `"was"` (Strings), the second one is chosen.
    

#### b) **Case Sensitivity**

-   `String.replace` is **case-sensitive**.  
    `"I"` and `"i"` are different.
    
-   That’s why `"I'am"` didn’t match `"i'am"` in the third call.
    

#### c) **Immutability**

-   Strings are immutable, so `replace(...)` **returns a new String**.
    
-   The original string is unchanged.
    
-   That’s why each method does `return s.replace(...)`.