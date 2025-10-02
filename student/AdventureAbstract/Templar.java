public class Templar extends Character implements Healer, Tank {
    private final int healCapacity;
    private final int shield;

    public Templar(String name, int maxHealth, int healCapacity, int shield) {
        super(name, maxHealth);
        this.healCapacity = healCapacity;
        this.shield = shield;
    }

    @Override public int getHealCapacity() { return healCapacity; }
    @Override public int getShield() { return shield; }

    @Override
    public void heal(Character character) {
        if (character == null || healCapacity <= 0) return;
        character.gainHealth(healCapacity);
    }

    @Override
    public void attack(Character target) {
        heal(this);                 // heal self first
        target.takeDamage(6);       // then deal 6
    }

    @Override
    public void takeDamage(int damage) {
        int effective = Math.max(0, damage - shield);
        applyDamage(effective);
    }

    @Override
    public String toString() {
        if (getCurrentHealth() == 0)
            return getName() + " has been beaten, even with its " + shield + " shield. So bad, it could heal " + healCapacity + " HP.";
        return getName() + " is a strong Templar with " + getCurrentHealth() + " HP. It can heal " + healCapacity + " HP and has a shield of " + shield + ".";
    }
}
