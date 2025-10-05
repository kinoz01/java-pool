import java.util.*;

public class TopFrequents {
    public List<Integer> findTopKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> first = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            freq.put(x, freq.getOrDefault(x, 0) + 1);
            first.putIfAbsent(x, i);
        }
        List<Integer> keys = new ArrayList<>(freq.keySet());
        keys.sort((a, b) -> {
            int fa = freq.get(a), fb = freq.get(b);
            if (fa != fb) return fb - fa;
            return first.get(a) - first.get(b);
        });
        return keys.subList(0, Math.min(k, keys.size()));
    }
}
