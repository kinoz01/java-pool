import java.util.ArrayList;
import java.util.List;

public class Character {
    private static List<Character> allCharacters = new ArrayList<>();

    private final String name;
    private final int maxHealth;
    private int currentHealth;

    public Character(String name, int maxHealth) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        allCharacters.add(this);
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    // Safe way to increase health without exposing the field
    public void gainHealth(int amount) {
        if (amount <= 0)
            return;
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    public void takeDamage(int damage) {
        currentHealth = Math.max(0, currentHealth - damage);
    }

    public void attack(Character target) {
        target.takeDamage(9);
    }

    @Override
    public String toString() {
        if (currentHealth == 0)
            return name + " : KO";
        return name + " : " + currentHealth + "/" + maxHealth;
    }

    public static String printStatus() {
        if (allCharacters.isEmpty()) {
            return "------------------------------------------\nNobody's fighting right now !\n------------------------------------------\n";
        }
        StringBuilder status = new StringBuilder(
                "------------------------------------------\nCharacters currently fighting :\n");
        for (Character c : allCharacters) {
            status.append(" - ").append(c.toString()).append("\n");
        }
        status.append("------------------------------------------\n");
        return status.toString();
    }

    public static Character fight(Character c1, Character c2) {
        while (c1.getCurrentHealth() > 0 && c2.getCurrentHealth() > 0) {
            c1.attack(c2);
            if (c2.getCurrentHealth() <= 0)
                return c1;

            c2.attack(c1);
            if (c1.getCurrentHealth() <= 0)
                return c2;
        }
        return null;
    }
}
