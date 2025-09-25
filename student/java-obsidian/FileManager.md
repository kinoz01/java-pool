```java
import java.io.IOException;
import java.nio.file.*;

public class FileManager {
    public static void createFile(String fileName, String content) throws IOException {
        Files.writeString(Path.of(fileName), content);
    }
    public static String getContentFile(String fileName) throws IOException {
        return Files.readString(Path.of(fileName));
    }
    public static void deleteFile(String fileName) throws IOException {
        Files.deleteIfExists(Path.of(fileName));
    }
}
```

**Note** the you can also use  for deleting:

```java
public static void deleteFile(String fileName) {
        File fyl = new File(fileName);
        fyl.delete();
}
```

> But you have to do an extra import:

```java
import java.io.File;
```

## ✅ Purpose of the Class

This class provides **three utility methods** to manage files:

| Method           | Purpose                           |
| ---------------- | --------------------------------- |
| `createFile`     | Creates a file and writes content |
| `getContentFile` | Reads and returns file content    |
| `deleteFile`     | Deletes a file if it exists       |

It uses the **modern NIO API** (`java.nio.file.*`) for all file operations.

---

## 🔹 Line-by-Line Breakdown

### `import java.io.IOException;`

-   Needed because methods in `java.nio.file.Files` can throw `IOException`
    
-   A **checked exception**: must be declared or caught
    

### `import java.nio.file.*;`

-   Imports:
    
    -   `Files`: static methods for file operations
        
    -   `Path`: represents file paths
        
    -   `Path.of(...)`: creates a `Path` object from a string
        

---

### Method 1: `createFile`

```java
public static void createFile(String fileName, String content) throws IOException {
    Files.writeString(Path.of(fileName), content);
}
```

-   Creates a file with name `fileName`
    
-   Writes `content` into it
    
-   If the file exists, it’s **overwritten**
    
-   Uses:
    
    -   `Path.of(fileName)` → converts string to `Path`
        
    -   `Files.writeString(...)` → writes the string to the file
        
-   `throws IOException`: if something goes wrong (e.g., permission denied, disk full)
    

---

### Method 2: `getContentFile`

```java
public static String getContentFile(String fileName) throws IOException {
    return Files.readString(Path.of(fileName));
}
```

-   Reads and returns the content of the file as a `String`
    
-   Uses:
    
    -   `Files.readString(...)` to load the entire file
        
-   Throws `IOException` if:
    
    -   File not found
        
    -   File is unreadable
        

---

### Method 3: `deleteFile`

```java
public static void deleteFile(String fileName) throws IOException {
    Files.deleteIfExists(Path.of(fileName));
}
```

-   Deletes the file if it exists
    
-   Uses:
    
    -   `Files.deleteIfExists(...)` — doesn’t throw an error if the file doesn’t exist
        
-   Throws `IOException` if:
    
    -   File exists but can't be deleted (e.g., permission issue)
        

---

## 🔸 What is `throws` in Java?

When you use:

```java
public static void createFile(...) throws IOException
```

You're telling Java:

> "This method **might throw an exception**, and I’m not handling it here. Whoever calls it must handle it."

### ✅ Benefits:

-   Keeps the method code clean
    
-   Lets higher-level code decide how to respond to errors
    

### ⚠️ Downside:

-   You must handle the exception wherever the method is called (or keep throwing it up)
    

---

## 🔸 What If We Don’t Use `throws`?

You must catch the exception **inside** the method using a `try-catch` block.

### 🔁 Rewritten with `try-catch`:

```java
public static void createFile(String fileName, String content) {
    try {
        Files.writeString(Path.of(fileName), content);
    } catch (IOException e) {
        e.printStackTrace(); // or log, or handle differently
    }
}
```

Same for the other two:

```java
public static String getContentFile(String fileName) {
    try {
        return Files.readString(Path.of(fileName));
    } catch (IOException e) {
        e.printStackTrace();
        return ""; // or null, or custom error message
    }
}

public static void deleteFile(String fileName) {
    try {
        Files.deleteIfExists(Path.of(fileName));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
```

---

## 🔁 Summary: `throws` vs `try-catch`

| Feature | `throws IOException` | `try-catch` block |
| --- | --- | --- |
| Where handled | Caller (outside this method) | Inside the method |
| Method signature | Must declare `throws IOException` | No `throws` needed |
| Code clarity | Cleaner, less cluttered | More verbose, but self-contained |
| Use case | Library methods, when caller decides handling | App-level methods, when error must be logged |

---

## ✅ When to Use Which?

-   Use `**throws**` if:
    
    -   You're writing **library or utility code**
        
    -   You want the **caller to decide** how to handle failures
        
-   Use `**try-catch**` if:
    
    -   You're writing top-level application code
        
    -   You want to **log or recover** from the error immediately


> Using `throws` in the `main` method, like `public static void main(String[] args) throws IOException`, allows you to declare that the method may throw an exception without handling it inside. This is useful for simplifying code in small programs, demos, or scripts where detailed error handling isn't necessary. If an exception occurs, the Java Virtual Machine will automatically print the error and stack trace to the console. While this approach keeps the code cleaner, it's best used when graceful recovery or user-friendly error messages aren't required.