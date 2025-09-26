```java
public class MultiplicationTable {
    public static void generate(int num) {
        for (int i = 1; i <= 10; i++)
            System.out.println(num + " x " + i + " = " + (num * i));
    }
}

public class ExerciseRunner {
    public static void main(String[] args) {
        MultiplicationTable.generate(5);
    }
}
```
