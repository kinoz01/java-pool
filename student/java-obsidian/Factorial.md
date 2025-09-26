```java
public class Factorial {
    public static Integer factorial(Integer n) {
        if (n == null || n < 0) return null;
        int r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}
```