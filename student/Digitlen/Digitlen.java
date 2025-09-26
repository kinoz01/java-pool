public class Digitlen {
    public static int digitlen(long n) {
        int len = Long.toString(n).length();
        return n < 0 ? len - 1 : len;
    }
}