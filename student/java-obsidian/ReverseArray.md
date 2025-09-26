1.  Return a new reversed array:
    

```java
public class ReverseArray {
    public static Integer[] reverse(Integer[] arr) {
        if (arr == null) return null;
        Integer[] r = new Integer[arr.length];
        for (int i = 0, n = arr.length; i < n; i++) r[i] = arr[n - 1 - i];
        return r;
    }
}
```

2.  Shortest if mutation is OK:
    

```java
import java.io.*;
import java.util.Arrays;

public class ReverseArray {
    public static Integer[] reverse(Integer[] arr) {
        if (arr == null) return null;
        java.util.Collections.reverse(java.util.Arrays.asList(arr));
        return arr;
    }
}
public class ExerciseRunner {
    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(ReverseArray.reverse(new Integer[]{4, 2, 1, 3})));
    }
}
```


3.  Shortest if mutation isn't okay:

```java
public class ReverseArray {
    public static Integer[] reverse(Integer[] arr) {
        if (arr == null) return null;
        Integer[] r = arr.clone();
        java.util.Collections.reverse(java.util.Arrays.asList(r));
        return r;
    }
}
```

4.  In-place manual swap (no Collections):
    

```java
public class ReverseArray {
    public static Integer[] reverse(Integer[] arr) {
        if (arr == null) return null;
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            Integer t = arr[i]; arr[i] = arr[j]; arr[j] = t;
        }
        return arr;
    }
}
```