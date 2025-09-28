```java
public abstract class Factorial {
    public abstract long calculate(int n);
}

public class IterativeFactorial extends Factorial {
    @Override public long calculate(int n) {
        long r = 1;
        for (int i = 2; i <= n; i++) r *= i;
        return r;
    }
}

public class RecursiveFactorial extends Factorial {
    @Override public long calculate(int n) {
        return n <= 1 ? 1 : n * calculate(n - 1);
    }
}
```
