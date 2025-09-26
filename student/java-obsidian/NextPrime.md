
```java
public class NextPrime {
    public static Integer nextPrime(Integer n) {
        int m = (n == null ? 1 : n) + 1;
        if (m < 2)
            m = 2;
        while (!p(m))
            m++;
        return m;
    }

    private static boolean p(int x) {
        if (x % 2 == 0)
            return x == 2;
        for (int i = 3; i * i <= x; i += 2)
            if (x % i == 0)
                return false;
        return true;
    }
}
```