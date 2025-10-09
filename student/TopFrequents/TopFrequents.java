import java.util.*;

public class TopFrequents {
    public List<Integer> findTopKFrequent(int[] nums, int k) {
        List<Integer> ns = new ArrayList<Integer>(), fr = new ArrayList<Integer>(), getNums = new ArrayList<Integer>(), res = new ArrayList<Integer>();
        for (int x : nums) {
            int index = ns.indexOf(x);
            if (index == -1) {
                ns.add(x);
                fr.add(1);
            } else {
                fr.set(index, fr.get(index) + 1);
            }
        }

        for (int i = 0; i < ns.size(); i++)
            getNums.add(i);
        getNums.sort((a, b) -> Integer.compare(fr.get(b), fr.get(a)));
        for (int i = 0; i < Math.min(k, getNums.size()); i++) {
            res.add(ns.get(getNums.get(i)));
        }
        return res;
    }
}