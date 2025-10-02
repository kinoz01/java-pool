import java.util.*;

public class SteadySequence {
    public int longestSequence(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        Set<Integer> s = new HashSet<>();
        for (int x : nums)
            s.add(x);
        int best = 0;
        for (int x : s)
            if (!s.contains(x - 1)) {
                int y = x;
                while (s.contains(y))
                    y++;
                best = Math.max(best, y - x);
            }
        return best;
    }
}