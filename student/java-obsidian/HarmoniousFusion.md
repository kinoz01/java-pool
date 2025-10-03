```java
import java.util.Arrays;
import java.util.stream.IntStream;

public class HarmoniousFusion {
    public int[] merge(int[] a, int[] b) {
        return IntStream
                .concat(Arrays.stream(a), Arrays.stream(b))
                .sorted()
                .toArray();
    }
}
```

> What's IntStream?

In Java, a *stream* is just a pipeline that lets you process a sequence of values step by step (think: take numbers, transform/filter/sort them, then produce a result) without writing explicit loops; **IntStream** is the version of that pipeline specialized for primitive `int` values, so it avoids creating `Integer` objects (faster, less memory). You typically create one from an `int[]` (`Arrays.stream(arr)`), from literals (`IntStream.of(1,2,3)`), or ranges (`IntStream.range(0,10)`), then chain operations like `map`, `filter`, `sorted`, and finish with a terminal operation like `sum()`, `toArray()`, or `forEach(...)`. It’s single-use (once you finish, you can’t reuse it) and can be run in parallel if needed.

> Alternative code would be:

```java
public int[] merge(int[] a, int[] b) {
    return Stream.concat(Arrays.stream(a).boxed(),
                         Arrays.stream(b).boxed())
                 .sorted()                     // sorts Integer
                 .mapToInt(Integer::intValue)  // back to primitive
                 .toArray();
}
```


Here you’re seeing two different “worlds” in Java streams:

-   **Primitive streams**: `IntStream`, `LongStream`, `DoubleStream` — work with raw primitives (`int`, `long`, `double`) and avoid boxing.
    
-   **Object streams**: `Stream<T>` — work with reference types (objects), e.g. `Stream<Integer>`.
    

Now the terms you asked about:

# What is boxing / unboxing?

-   **Boxing**: wrapping a primitive `int` into an `Integer` object.
    
    -   Example: `int x = 5; Integer y = x;` // compiler inserts boxing
        
-   **Unboxing**: extracting the primitive from an `Integer`.
    
    -   Example: `Integer y = 5; int x = y;` // compiler inserts unboxing
        

Boxing creates objects on the heap and has some overhead (CPU + GC). Primitive streams avoid that.

# `.boxed()`

-   **What it does**: converts an `IntStream` (primitives) into a `Stream<Integer>` (objects).
    
-   **Why use it**: only when you need object-stream operations or APIs that require objects (e.g., collecting into `List<Integer>`, using certain `Comparator`s, mixing with other object data).
    
-   **Example**:
    
    ```java
    IntStream s = IntStream.of(3, 1, 2);
    Stream<Integer> obj = s.boxed();  // IntStream -> Stream<Integer>
    ```
    

# `.mapToInt(Integer::intValue)`

-   **What it does**: converts a `Stream<Integer>` back to an `IntStream` by unboxing each `Integer` to `int`.
    
-   **`Integer::intValue`** is a **method reference** to the instance method `intValue()` on `Integer`. It’s shorthand for `x -> x.intValue()`.
    
-   **Example**:
    
    ```java
    Stream<Integer> obj = Stream.of(3, 1, 2);
    IntStream prim = obj.mapToInt(Integer::intValue); // Stream<Integer> -> IntStream
    ```
    

# Why/when would you use them together?

Suppose you started with `IntStream`, but needed an operation that only exists on object streams (or you want to use `Collectors.toList()`):

```java
List<Integer> list = IntStream.of(5, 2, 9)
                              .boxed()                 // IntStream -> Stream<Integer>
                              .sorted()
                              .collect(Collectors.toList());
```

Later you want a primitive `int[]` back:

```java
int[] arr = list.stream()
                .mapToInt(Integer::intValue)          // Stream<Integer> -> IntStream
                .toArray();
```

# Applying to your `merge`

Your current (best) version uses **primitive streams** only—no boxing:

```java
int[] merge(int[] a, int[] b) {
    return IntStream.concat(Arrays.stream(a), Arrays.stream(b))
                    .sorted()
                    .toArray();  // stays primitive the whole time
}
```

If you insist on “just Stream” (object stream), you’d have to box, then unbox:

```java
int[] merge(int[] a, int[] b) {
    return Stream.concat(Arrays.stream(a).boxed(),    // IntStream -> Stream<Integer>
                         Arrays.stream(b).boxed())
                 .sorted()                            // sorts Integer objects
                 .mapToInt(Integer::intValue)         // Stream<Integer> -> IntStream
                 .toArray();
}
```

This works, but it allocates `Integer` objects (boxing) and then peels them back to `int` (unboxing). It’s slower and creates more garbage than staying with `IntStream`.

# Quick cheatsheet

-   Stay with `IntStream` whenever possible (no boxing).
    
-   Use `.boxed()` only if an API forces you into `Stream<Integer>` or you need object-only operations.
    
-   Use `.mapToInt(Integer::intValue)` to come back to `IntStream` from `Stream<Integer>`.
    
-   `Integer::intValue` is just shorthand for a lambda: `x -> x.intValue()`.
    

That’s all that “boxed” and `.mapToInt(Integer::intValue)` are doing: switching between primitive and object stream representations.


## We can also do

```java
public int[] merge(int[] a, int[] b) {
    return Stream.of(a, b)
                 .flatMapToInt(Arrays::stream)
                 .sorted()
                 .toArray();
}
```

Here’s what each piece of your pipeline does and why it works.

### What it returns

An `int[]` containing all elements of `a` and `b`, sorted in ascending order.

### Step-by-step

```java
Stream.of(a, b)              // Stream<int[]>
```

-   Creates a **Stream of arrays**, i.e., a stream with two elements: the array `a` and the array `b`.
    
-   Type is `Stream<int[]>` (not `IntStream` yet).
    
-   If either `a` or `b` is `null`, you’ll later get a `NullPointerException` when trying to stream it.
    

```java
.flatMapToInt(Arrays::stream)
```

-   For each element of the upstream (`int[]`), apply `Arrays.stream(int[])` to get an `IntStream`.
    
-   Then **flatten** those two `IntStream`s into one continuous **`IntStream` of primitives** (no boxing).
    
-   After this step, your data is a single `IntStream` containing all the numbers from `a` followed by all from `b`.
    

```java
.sorted()
```

-   Sorts the primitive `IntStream` in **natural (ascending) order**.
    
-   Complexity: `O((n + m) log(n + m))` where `n=len(a)` and `m=len(b)`.
    
-   If `a` and `b` were already sorted and you wanted a linear-time merge, you’d write a two-pointer merge instead of sorting again.
    

```java
.toArray()
```

-   Terminates the stream and collects the result into a primitive `int[]`.
    

### Why `flatMapToInt` (not `flatMap`)

-   `flatMapToInt` keeps everything in **primitive form** (`IntStream`), avoiding boxing to `Integer`.
    
-   If you used `flatMap` you’d end up with `Stream<Integer>`, incurring boxing overhead and then likely unboxing later.
    

### Tiny trace example

Given `a = [3, 1]`, `b = [2, 4]`:

-   `Stream.of(a, b)` → stream of two arrays: `[3,1]`, `[2,4]`.
    
-   `flatMapToInt(Arrays::stream)` → `IntStream` of `3, 1, 2, 4`.
    
-   `sorted()` → `1, 2, 3, 4`.
    
-   `toArray()` → `[1, 2, 3, 4]`.
    

### Notes and pitfalls

-   **Null safety:** if `a` or `b` may be null, guard them (e.g., `Stream.of(Optional.ofNullable(a).orElse(new int[0]), ...)`).
    
-   **Duplicates:** nothing here removes duplicates; it just sorts everything.
    
-   **Parallelism:** you can add `.parallel()` before `sorted()` for large inputs, but parallel sorting benefits depend on data size and environment.