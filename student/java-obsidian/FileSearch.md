```java
import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class FileSearch {
    public static String searchFile(String fileName) {
        Path start = Path.of("documents");
        try (Stream<Path> w = Files.walk(start)) {
            return w.filter(Files::isRegularFile)
                    .filter(p -> p.getFileName().toString().equals(fileName))
                    .map(p -> start.getFileName() + "/"
                            + start.relativize(p).toString().replace(File.separatorChar, '/'))
                    .findFirst().orElse(null);
        } catch (IOException e) {
            return null;
        }
    }
}
```


## 🔹 Goal of the `searchFile` Method

You're trying to find a file by name (like `"notes.txt"`) **inside the `documents/` folder**, including **all its subdirectories**.

If the file is found, return its **relative path as a `String`**, like:

```bash
documents/subfolder1/notes.txt
```

If not found, return `null`.

---

## ✅ Step-by-Step Breakdown

---

### **Step 1: Set the starting folder**

```java
Path start = Path.of("documents");
```

-   `Path.of(...)` converts the string `"documents"` into a `Path` object.
    
-   This `start` path is the **root directory** for your search.
    
-   All searching begins from here — like saying, “Start looking from this folder.”
    

---

### **Step 2: Recursively walk through all files and folders**

```java
try (Stream<Path> w = Files.walk(start)) {
```

-   `Files.walk(start)` returns a **Stream<Path>** — this is a pipeline of files and folders, found **recursively**.
    
-   It internally does the **recursion** — going deep into subdirectories, automatically.
    
-   `w` is just the name of this stream (you could call it anything).
    

Example: if your folder looks like:

```css
documents/
├── a.txt
├── sub/
│   └── b.txt
```

Then `Files.walk(start)` gives:

```bash
documents/
documents/a.txt
documents/sub/
documents/sub/b.txt
```

> This is where **recursion happens internally**, but you don’t write it manually.

---

### **Step 3: Filter the stream to match the file name**

```java
.filter(p -> p.getFileName().toString().equals(fileName))
```

-   This keeps only the files whose **file name matches** the given `fileName`.
    
-   `p.getFileName()` → gets just the file name part (like `a.txt`)
    
-   `.toString().equals(fileName)` → checks for an exact match
    

So if `fileName = "b.txt"`, only one `Path` in the stream will pass the filter:

```bash
documents/sub/b.txt
```

---

### **Step 4: Map the path to a formatted relative string**

```java
.map(p -> start.getFileName() + "/" +
          start.relativize(p).toString().replace(File.separatorChar, '/'))
```

This transforms the matching `Path` into a **string** that looks like:

```bash
documents/sub/b.txt
```

Let’s break it down:

-   `start.getFileName()` = `"documents"`
    
-   `start.relativize(p)` = removes the common prefix from `p`
    
    Example:
    
    ```java
    start: documents
    p:     documents/sub/b.txt
    result: sub/b.txt
    ```
    
-   `.replace(File.separatorChar, '/')` = ensures all path separators are `/`, even on Windows.
    

Final result:

```java
"documents" + "/" + "sub/b.txt" → "documents/sub/b.txt"
```

---

### **Step 5: Get the first result or return null**

```java
.findFirst().orElse(null);
```

-   `findFirst()` = stop and return the **first matching file**
    
-   `orElse(null)` = if nothing was found, return `null`
    

---

### Final Returned Value:

If the file is found, you get a string like:

```bash
documents/somefolder/target.txt
```

If not found:

```csharp
null
```

## Alternative simple code would be:

```java
import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class FileSearch {
    public static String searchFile(String fileName) throws IOException {
        Path start = Path.of("documents");
        try (var w = Files.walk(start)) {
            return w.filter(p -> p.getFileName().toString().equals(fileName))
                    .map(p -> "documents/" + start.relativize(p).toString()).findFirst().orElse(null);
        }
    }
}
```