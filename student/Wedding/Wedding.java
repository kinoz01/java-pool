import java.util.*;

public class Wedding {
    public static Map<String, String> createCouple(Set<String> first, Set<String> second) {
        if (first == null || second == null) return Map.of();
        var a = new ArrayList<>(first);
        var b = new ArrayList<>(second);
        Collections.shuffle(a);
        Collections.shuffle(b);
        var res = new HashMap<String, String>();
        for (int i = 0, n = Math.min(a.size(), b.size()); i < n; i++) res.put(a.get(i), b.get(i));
        return res;
    }
}
