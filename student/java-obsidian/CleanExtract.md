```java
public class CleanExtract {
    public static String extract(String s) {
        return s == null ? ""
                : java.util.Arrays.stream(s.split("\\|"))
                        .map(t -> {
                            int f = t.indexOf('.'), l = t.lastIndexOf('.');
                            t = (f >= 0 && l > f) ? t.substring(f + 1, l) : (f >= 0 ? t.substring(f + 1) : t);
                            return t.trim();
                        })
                        .filter(t -> !t.isEmpty())
                        .collect(java.util.stream.Collectors.joining(" "));
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(CleanExtract.extract("The |||. quick brown. | what do you ..| .fox .|. Jumps over the lazy dog. ."));
        System.out.println(CleanExtract.extract("  | Who am .I  | .love coding,  |  |.  Coding is fun . | ...  "));
    }
}
```

1.  **Null guard**
    

```java
s == null ? "" : ...
```

If `s` is `null`, return the empty string immediately. Otherwise continue with the pipeline.

2.  **Split on pipe `|`**
    

```java
s.split("\\|")
```

-   `split` takes a **regex**; `|` means “OR” in regex, so it must be escaped.
    
-   In Java strings, backslash itself is escaped, hence `"\\|"`.
    
-   Result: an array of segments between the pipes. Adjacent pipes or leading/trailing pipes yield empty strings.
    

3.  **Turn array into a Stream**
    

```java
java.util.Arrays.stream(...)
```

Gives a `Stream<String>` so we can apply functional operations (`filter`, `map`, `collect`).


4.  **Map (transform) each token**
    

```java
.map(t -> {
    int f = t.indexOf('.'), l = t.lastIndexOf('.');
    t = (f >= 0 && l > f) ? t.substring(f + 1, l)
        : (f >= 0 ? t.substring(f + 1) : t);
    return t.trim();
})
```

Inside this lambda:

-   `f` = index of the **first** dot; `-1` if none.
    
-   `l` = index of the **last** dot.
    
-   Cases:
    
    -   **Two or more dots** (`f >= 0 && l > f`): take the substring **between** the first and last dot: `t.substring(f+1, l)`.
        
    -   **Exactly one dot** (`f >= 0` but `l == f`): take the substring **after** the first (and only) dot: `t.substring(f+1)`.
        
    -   **No dots** (`f < 0`): keep the token as-is returning t.
        
-   Finally, `trim()` removes leading/trailing whitespace (including spaces produced by those substrings).
    

5.  **Filter: drop tokens that became empty after trimming/extraction**
    

```java
.filter(t -> !t.isEmpty())
```

This catches cases like `"  "` or content that collapses to `""` after the substring logic (e.g., only dots).

6.  **Join with single spaces**
    

```java
.collect(java.util.stream.Collectors.joining(" "))
```

Concatenate the remaining tokens with exactly one space between them.

---

### Step-by-step on your inputs

#### Input 1

```arduino
"The|. quick brown. | what do you ..| .fox .|. Jumps over the lazy dog. ."
```

Split by `|` → tokens:

1.  `"The"`
    
2.  `". quick brown. "`
    
3.  `" what do you .."`
    
4.  `" .fox ."`
    
5.  `". Jumps over the lazy dog. ."`
    

Transform each:

1.  `"The"` → no dots → `"The"` → trim → `"The"`
    
2.  `". quick brown. "` → first dot at start, last dot before trailing space → between them → `" quick brown"` → trim → `"quick brown"`
    
3.  `" what do you .."` → first dot at penultimate position, last at last → between them is empty → `""` → filtered out
    
4.  `" .fox ."` → between first and last dot → `"fox "` → trim → `"fox"`
    
5.  `". Jumps over the lazy dog. ."` → between first and last dot → `" Jumps over the lazy dog. "` → trim → `"Jumps over the lazy dog."`
    

Join →  
**`"The quick brown fox Jumps over the lazy dog."`**

#### Input 2

```arduino
"  | Who am .I  | .love coding,  |  |.  Coding is fun . | ...  "
```

Split → tokens:

1.  `"  "`
    
2.  `" Who am .I  "`
    
3.  `" .love coding,  "`
    
4.  `"  "`
    
5.  `".  Coding is fun . "`
    
6.  `" ...  "`
    

Transform:

1.  `"  "` → no dots → trim → `""` → filtered out
    
2.  `" Who am .I  "` → one dot before `I` → take after dot → `"I  "` → trim → `"I"`
    
3.  `" .love coding,  "` → one dot near start → after dot → `"love coding,  "` → trim → `"love coding,"`
    
4.  `"  "` → trim → `""` → filtered out
    
5.  `".  Coding is fun . "` → two dots → between them → `"  Coding is fun "` → trim → `"Coding is fun"`
    
6.  `" ...  "` → first dot at index 1, last at index 3 → between them → `"."` → trim → `"."`
    

Join →  
**`"I love coding, Coding is fun ."`**

(Note: the final period has a leading space because it came from its own token. If you want punctuation to attach without a space, you’d adjust the joining or post-process punctuation.)

---
### Corner cases and behavior

-   **No dot** in a token → token is used as-is (trimmed).
    
-   **Exactly one dot** → take everything **after** that dot (trimmed).
    
-   **Two or more dots** → take content **between first and last** dot (trimmed).
    
-   Tokens of only spaces or dots may become `""` and get dropped.
    
-   `trim()` uses Unicode whitespace; it cleans leading/trailing, not internal spaces.
    
-   `split("\\|")` treats `|` as a plain delimiter; adjacent pipes make empties that the filters remove.


### SubString method

The `substring` method in Java creates a new string that contains a portion of the original string. Since strings in Java are immutable, calling substring does not change the original string, it just returns a new one.

There are two main versions. 

- The first form is `substring(int beginIndex)`. It takes the starting index and returns everything from that position up to the end of the string. 
  For example, `"Hello".substring(2)` returns `"llo"`. 

- The second form is `substring(int beginIndex, int endIndex)`. This takes a start index and an end index, where the start is inclusive and the end is exclusive. 
  For example, `"Hello".substring(1, 4)` returns `"ell"` because it includes characters at indices 1, 2, and 3, but not 4.

If you pass an index outside the valid range (less than 0, greater than the string length, or a begin index larger than the end index), Java throws a `StringIndexOutOfBoundsException`.

Because indices start at zero in Java, the first character of a string is index 0, and the last character is at length() - 1.

### Stream

When you call `s.split("\\|")`, you get back a plain Java array of strings (`String[]`). Arrays are fixed-size and don’t have higher-level operations like `filter`, `map`, or `collect`. To use those fluent, functional-style operations, the code wraps the array into a `Stream` by calling `java.util.Arrays.stream(...)`.

A `Stream<String>` is a special abstraction in Java that lets you process sequences of data in a pipeline: each element of the array becomes part of the stream, and then you can apply transformations step by step. For example, `filter(t -> !t.isEmpty())` goes through each string in the stream and only keeps the ones that are not empty. The `.map(...)` stage changes each string according to the given lambda. At the end, `.collect(...)` gathers the results back into a single object (in this case, a concatenated string).

So the call to `Arrays.stream(...)` is essentially converting a low-level array into a stream, which enables writing clean, functional pipelines instead of manual loops. It doesn’t copy the strings; it just provides a view that knows how to traverse the array one element at a time.

### Collect

`.collect(java.util.stream.Collectors.joining(" "))` is the final step of the stream pipeline, and its job is to take all the elements that survived the filters and transformations and turn them into a single `String`. The method `.collect(...)` is a terminal operation on a stream, which means it ends the pipeline and gathers all results into some kind of container or value.

`Collectors.joining(" ")` is a predefined collector from the `java.util.stream.Collectors` class. It tells the stream to concatenate all the strings in the stream into one string, and between each element, put a single space `" "`. So if the stream elements were `["The", "quick", "brown"]`, the result would be `"The quick brown"`.

The space you give to `joining(" ")` is the delimiter. You could choose another character, like a comma or nothing at all, depending on the output you want. There are other overloads of `joining` that let you also specify a prefix and a suffix, for example `Collectors.joining(", ", "[", "]")` would produce something like `"[The, quick, brown]"`.

So in your code, after filtering and trimming, each valid token becomes one string element in the stream, and `collect(Collectors.joining(" "))` merges them into a clean sentence with exactly one space between words.

## Alternative Code

If you want the traditional version with a `for` loop instead of a stream pipeline, you can expand the logic step by step. Here’s how it would look:

```java
public class CleanExtract {
    public static String extract(String s) {
        if (s == null) {
            return "";
        }

        String[] parts = s.split("\\|"); // split input into tokens
        StringBuilder result = new StringBuilder(); // to build the final output

        for (String t : parts) {
            int f = t.indexOf('.');
            int l = t.lastIndexOf('.');

            if (f >= 0 && l > f) {
                t = t.substring(f + 1, l); // between first and last dot
            } else if (f >= 0) {
                t = t.substring(f + 1); // after only one dot
            }

            t = t.trim(); // remove spaces

            if (!t.isEmpty()) { // skip empties
                if (result.length() > 0) {
                    result.append(" "); // add separator
                }
                result.append(t);
            }
        }
        return result.toString();
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(CleanExtract.extract("The |||. quick brown. | what do you ..| .fox .|. Jumps over the lazy dog. ."));
        System.out.println(CleanExtract.extract("  | Who am .I  | .love coding,  |  |.  Coding is fun . | ...  "));
    }
}
```

This version is functionally equivalent to your stream-based code. Instead of mapping, filtering, and joining in a pipeline, you’re explicitly looping through each token, cleaning it, checking if it’s empty, and then appending it to the final result with proper spacing.