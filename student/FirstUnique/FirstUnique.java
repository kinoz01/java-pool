import java.util.*;

public class FirstUnique {
    public char findFirstUnique(String s) {
        Map<Character,Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < s.length(); i++)
            if (countMap.get(s.charAt(i)) == 1) return s.charAt(i);
        return '_';
    }
}
