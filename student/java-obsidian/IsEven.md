```java
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(IsEven.isEven(2));
        System.out.println(IsEven.isEven(26));
        System.out.println(IsEven.isEven(57));
    }
}
public class IsEven {
    public static boolean isEven(int a) {
        return a % 2 == 0;
    }
}
```
