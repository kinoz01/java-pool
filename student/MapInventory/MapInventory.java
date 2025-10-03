import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MapInventory {
    public static int getProductPrice(Map<String, Integer> inventory, String productId) {
        return inventory == null ? -1 : inventory.getOrDefault(productId, -1);
    }

    public static List<String> getProductIdsByPrice(Map<String, Integer> inventory, int price) {
        List<String> res = new ArrayList<>();
        if (inventory == null) return res;
        for (var e : inventory.entrySet())
            if (e.getValue() != null && e.getValue() == price) res.add(e.getKey());
        return res;
    }
}
