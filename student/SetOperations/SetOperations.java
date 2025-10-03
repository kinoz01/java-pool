import java.util.HashSet;
import java.util.Set;

public class SetOperations {
    public static Set<Integer> union(Set<Integer> set1, Set<Integer> set2) {
        var r = new HashSet<Integer>();
        if (set1 != null) r.addAll(set1);
        if (set2 != null) r.addAll(set2);
        return r;
        }

    public static Set<Integer> intersection(Set<Integer> set1, Set<Integer> set2) {
        var r = new HashSet<Integer>();
        if (set1 == null || set2 == null) return r;
        r.addAll(set1);
        r.retainAll(set2);
        return r;
    }
}
