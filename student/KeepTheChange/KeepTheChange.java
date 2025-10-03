import java.util.*;

public class KeepTheChange {
    public static List<Integer> computeChange(int amount, Set<Integer> coins) {
        if (amount < 0 || coins == null || coins.isEmpty()) return List.of();

        List<Integer> res = new ArrayList<>();
        List<Integer> cs = new ArrayList<>(coins);
        cs.sort(Comparator.reverseOrder()); // try biggest coins first

        for (int c : cs) {
            while (amount >= c) {
                res.add(c);
                amount -= c;
            }
        }
        return amount == 0 ? res : List.of(); // no exact change possible
    }
}
