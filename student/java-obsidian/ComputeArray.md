```java
public class ComputeArray {
    public static int[] computeArray(int[] a) {
        return a == null ? null
                : java.util.Arrays.stream(a)
                        .map(v -> {
                            return v % 3 == 0 ? v * 5 : v % 3 == 1 ? v + 7 : v;
                        })
                        .toArray();
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        int[] array = ComputeArray.computeArray(new int[]{9, 13, 8, 23, 1, 0, 89});
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
```

or alternatively:

```java
public class ComputeArray {
    public static int[] computeArray(int[] a) {
        if (a == null) return null;
        int[] r = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            int v = a[i], m = v % 3;
            r[i] = m == 0 ? v * 5 : m == 1 ? v + 7 : v;
        }
        return r;
    }
}

​￼public class ExerciseRunner {
    ​￼public static void main(String[] args) {
        int[] array = ComputeArray.computeArray(new int[]{9, 13, 8, 23, 1, 0, 89});
        ​￼for (int i : array) {
            System.out.print(i + " ");
        }
    }
}
```