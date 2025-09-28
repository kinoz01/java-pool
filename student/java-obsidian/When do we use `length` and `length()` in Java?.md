In Java, whether you use `length` or `length()` depends on the type of object you are working with:

### 1\. `length` (without parentheses)

-   Used with **arrays**
    
-   It is a **field**, not a method
    
-   Gives the number of elements in the array
    

**Example:**

```java
int[] numbers = {1, 2, 3};
int size = numbers.length;
```

### 2\. `length()` (with parentheses)

-   Used with **`String`**
    
-   It is a **method** of the `String` class
    
-   Returns the number of characters in the string
    

**Example:**

```java
String name = "Java";
int size = name.length();
```

### Summary:

| Type | Use | Example |
| --- | --- | --- |
| Array | `.length` | `array.length` |
| String | `.length()` | `string.length()` |