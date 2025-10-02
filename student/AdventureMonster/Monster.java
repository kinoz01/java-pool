public class Monster extends Character {

    // Constructor calling the superclass constructor
    public Monster(String name, int maxHealth) {
        super(name, maxHealth);
    }

    // Override toString method for the monster-specific format
    @Override
    public String toString() {
        if (getCurrentHealth() == 0) {
            return getName() + " is a monster and is dead";
        }
        return getName() + " is a monster with " + getCurrentHealth() + " HP";
    }
}
