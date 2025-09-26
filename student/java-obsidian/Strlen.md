```java
public class Strlen {
    public static int strlen(String s) {
        return s == null ? 0 : s.length();
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(Strlen.strlen("Hello World !"));
    }
}
```
