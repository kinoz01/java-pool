```java
public class Digitlen {
    public static int digitlen(long n) {
        int len = Long.toString(n).length();
        return n < 0 ? len - 1 : len;
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(Digitlen.digitlen(-99999999L));
    }
}
```

> Note the usage of L for long

to accept even bigger numbers you can add this method to `Digitlen` class

```java
public static int digitlen(java.math.BigInteger n) {
    return n.abs().toString().length();
}

public class ExerciseRunner {
    public static void main(String[] args) {
	    System.out.println(Digitlen.digitlen(-999999999999999999999999L)); // won't compile
	    // Use BigInteger for huge numbers
		System.out.println(Digitlen.digitlen(new java.math.BigInteger("9989899...9997")));
    }
}
```
