import java.util.Set;

public class SetEquals {
    public static boolean areSetsEqual(Set<String> set1, Set<String> set2) {
        return set1 == set2 || (set1 != null && set1.equals(set2));
    }
}
