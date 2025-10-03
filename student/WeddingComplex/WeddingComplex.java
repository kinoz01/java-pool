import java.util.*;

public class WeddingComplex {
    public static Map<String, String> createBestCouple(Map<String, List<String>> first,
                                                       Map<String, List<String>> second) {
        if (first == null || second == null) return Map.of();

        Map<String, Map<String, Integer>> rank = new HashMap<>();
        for (var e : second.entrySet()) {
            Map<String, Integer> r = new HashMap<>();
            List<String> prefs = e.getValue();
            for (int i = 0; i < prefs.size(); i++) r.put(prefs.get(i), i);
            rank.put(e.getKey(), r);
        }

        Map<String, String> partnerOfSecond = new HashMap<>(); // second -> first
        Map<String, Integer> next = new HashMap<>();           // next proposal index per first
        Deque<String> free = new ArrayDeque<>(first.keySet());

        while (!free.isEmpty()) {
            String f = free.poll();
            int i = next.getOrDefault(f, 0);
            String s = first.get(f).get(i);
            next.put(f, i + 1);

            String cur = partnerOfSecond.get(s);
            if (cur == null || rank.get(s).get(f) < rank.get(s).get(cur)) {
                if (cur != null) free.add(cur);
                partnerOfSecond.put(s, f);
            } else {
                free.add(f);
            }
        }

        // build first -> second map
        Map<String, String> res = new HashMap<>();
        for (var e : partnerOfSecond.entrySet()) res.put(e.getValue(), e.getKey());
        return res;
    }
}
