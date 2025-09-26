public class CountChar {
    public static int count(String s, char c) {
        return s == null ? 0 : (int) s.chars().filter(ch -> ch == c).count();
    }
}