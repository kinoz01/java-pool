```java
import java.io.*;
import java.nio.file.*;

public class Capitalize {
    public static void capitalize(String[] args) throws IOException {
        if (args == null || args.length != 2)
            return;
        Path in = Paths.get(args[0]), out = Paths.get(args[1]);
        Files.writeString(out, capitalize(Files.readString(in)));
    }

    public static String capitalize(String s) {
        if (s == null)
            return "";
        StringBuilder ret = new StringBuilder();
        for (String w : s.trim().split(" +")) { // this is called enhanced loop
            if (!w.isEmpty())
                ret.append(Character.toUpperCase(w.charAt(0)))
                        .append(w.substring(1).toLowerCase())
                        .append(' ');
        }
        return ret.toString().trim();
    }
}
```

### `import java.io.*;`

This import includes all classes in the `java.io` package. In **this code**, it is not strictly necessary. Nothing from `java.io.*` is explicitly used. All file handling here is done using the `java.nio.file` package.

**Conclusion:** This import is **redundant** and can be removed without affecting functionality.

---

### `import java.nio.file.*;`

This import is used and is **essential**. It provides:

-   `Path` and `Paths` for representing file paths.
    
-   `Files` class for reading and writing to files.
    

**Where used:**

-   `Paths.get(...)`: Converts string file names to `Path` objects.
    
-   `Files.readString(...)`: Reads all content from a file into a `String`.
    
-   `Files.writeString(...)`: Writes a `String` into a file.
    

---

### `public static void capitalize(String[] args) throws IOException`

This is the method that coordinates the process. It:

1.  Checks that two file names are passed.
    
2.  Reads from the input file.
    
3.  Capitalizes the content.
    
4.  Writes to the output file.
    

#### `if (args == null || args.length != 2) return;`

This ensures that exactly **two file paths** are provided — one for input, one for output.

-   If not, it exits silently.
    
-   Prevents `ArrayIndexOutOfBoundsException`.
    

#### `Path in = Paths.get(args[0]), out = Paths.get(args[1]);`

-   `Paths.get(...)` creates a `Path` object from a string path.
    
-   `in` is the input file path.
    
-   `out` is the output file path.
    

#### `Files.readString(in)`

-   Reads the entire content of the file at `in` as a `String`.
    

#### `capitalize(...)`

-   Calls the `capitalize(String s)` method to process the text.
    

#### `Files.writeString(out, ...)`

-   Writes the capitalized string into the file at path `out`.
    
-   If the file does not exist, it is created.
    
-   If it exists, it is overwritten.
    

---

### `public static String capitalize(String s)`

This helper method performs the **word capitalization** logic.

#### `if (s == null) return "";`

-   Handles null input safely.
    
-   Returns an empty string if the input string is null.
    

#### `StringBuilder ret = new StringBuilder();`

-   Used to build the final result efficiently.
    
-   More performant than string concatenation in a loop.
    

#### `s.trim().split(" +")`

-   `trim()` removes leading/trailing whitespace.
    
-   `split(" +")` splits the string by one or more spaces (regular expression).
    

This ensures that:

-   Multiple spaces are treated as one.
    
-   Empty words (from extra spaces) are filtered out.
    

#### Loop: `for (String w : ...)`

-   Iterates over each word in the string.
    

#### `if (!w.isEmpty())`

-   Double check against empty strings (safety measure).
    

#### `ret.append(...)`

-   Appends the capitalized word to the result string.
    

##### `Character.toUpperCase(w.charAt(0))`

-   Capitalizes the first letter of the word.
    

##### `w.substring(1).toLowerCase()`

-   Makes the rest of the word lowercase.
    

#### `ret.toString().trim();`

-   Converts the `StringBuilder` to a string.
    
-   Trims the trailing space added after the last word.
    

---

## Example

Suppose `input.txt` contains:

```css
hELLO    woRLd   FROM java
```

And you run:

```java
Capitalize.capitalize(new String[] { "input.txt", "output.txt" });
```

Then `output.txt` will contain:

```css
Hello World From Java
```