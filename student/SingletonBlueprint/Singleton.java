public class Singleton {
    private static Singleton instance;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    public String showMessage() {
        return "Hello, I am a singleton!";
    }
}
