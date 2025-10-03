import java.util.*;

public class SortList {
    public static List<Integer> sort(List<Integer> list) {
        if (list == null) return null;
        var a = new ArrayList<>(list);
        a.sort(null); // ascending
        return a;
    }

    public static List<Integer> sortReverse(List<Integer> list) {
        if (list == null) return null;
        var a = new ArrayList<>(list);
        a.sort(Collections.reverseOrder()); // descending
        return a;
    }
}
