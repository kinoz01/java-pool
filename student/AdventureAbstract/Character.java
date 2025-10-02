import java.util.ArrayList;
import java.util.List;

public abstract class Character {
    private static final List<Character> allCharacters = new ArrayList<>();

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

    // Common helpers
    public void gainHealth(int amount) {
        if (amount <= 0)
            return;
        currentHealth = Math.min(maxHealth, currentHealth + amount);
    }

    protected void applyDamage(int dmg) {
        if (dmg <= 0)
            return;
        currentHealth = Math.max(0, currentHealth - dmg);
    }

    // Now abstract as required
    public abstract void attack(Character target);

    public abstract void takeDamage(int damage);

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
        StringBuilder sb = new StringBuilder(
                "------------------------------------------\nCharacters currently fighting : \n");
        for (Character c : allCharacters)
            sb.append(" - ").append(c.toString()).append("\n");
        sb.append("------------------------------------------\n");
        return sb.toString();
    }

    public static Character fight(Character a, Character b) {
        while (a.getCurrentHealth() > 0 && b.getCurrentHealth() > 0) {
            a.attack(b);
            if (b.getCurrentHealth() <= 0)
                return a;
            b.attack(a);
            if (a.getCurrentHealth() <= 0)
                return b;
        }
        return null;
    }
}
