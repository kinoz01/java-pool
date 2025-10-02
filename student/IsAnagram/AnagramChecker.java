
import java.util.Arrays;

public class AnagramChecker {
    public boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        char[] a = str1.toLowerCase().toCharArray(), b = str2.toLowerCase().toCharArray();
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }
}
