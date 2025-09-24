```java
public class StringConcat {
    public static String concat(String s1, String s2) {
        return s1 == null ? s2 : (s2 == null ? s1 : s1 + s2);
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(StringConcat.concat("Hello ", "étudiant !"));
        System.out.println(StringConcat.concat("", "Hello World !"));
    }
}
```
