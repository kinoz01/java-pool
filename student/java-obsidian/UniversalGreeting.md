```java
public class UniversalGreeting {
    public static String greeting(String lang) {
        return switch (lang) {
            case "FR" -> "Bonjour comment allez-vous?";
            case "EN" -> "Hello, How are you?";
            case "ES" -> "Hola, cómo estás?";
            default   -> "";
        };
    }
}
public class ExerciseRunner {
    public static void main(String[] args) {
        System.out.println(UniversalGreeting.greeting("EN"));
    }
}
```

or 

```java
public class UniversalGreeting {
    public static String greeting(String message) {
        switch (message) {
            case "FR": return "Bonjour comment allez-vous?";
            case "EN": return "Hello, How are you?";
            case "ES": return "Hola, cómo estás?";
            default:   return "";
        }
    }
}
public class UniversalGreeting {
    ​￼public static String greeting(String message) {
        ​￼switch (message) {
            case "FR": return "Bonjour comment allez-vous?";
            case "EN": return "Hello, How are you?";
            case "ES": return "Hola, cómo estás?";
            default:   return "";
        }
    }
}
```