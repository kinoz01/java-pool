import java.util.*;

public class Character {
    private final int maxHealth;
    private int currentHealth;
    private final String name;
    private static List<Character> allCharacters = new ArrayList<>();

    public Character(String name, int maxHealth){
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.name = name;
        Character.allCharacters.add(this);
    }

    public static String printStatus() {
        if (allCharacters != null && allCharacters.size() != 0) {
            String res = "------------------------------------------\n" +
                    "Characters currently fighting :\n";
            for (Character ch : allCharacters) {
                res += " - " + ch.toString() + "\n";
            }
            res += "------------------------------------------\n";
            return res;
        } else {
            return "------------------------------------------\nNobody's fighting right now !\n------------------------------------------\n";

        }
    }



    public static Character fight(Character c1, Character c2){

        while( true ){
            c1.attack(c2);
            if (c2.currentHealth == 0) {
                break;
            }
            c2.attack(c1);
            if (c1.currentHealth == 0) {
                break;
            }
        }

        return c1.currentHealth == 0 ? c2 : c1;
    }

    public int getMaxHealth(){
        return this.maxHealth;
    }

    public int getCurrentHealth(){
        return this.currentHealth;
    }

    public void setCurrentHealth(int x){
        this.currentHealth = x;
    }

    public String getName(){
        return this.name;
    }

    public void takeDamage(int damage){
        
        this.currentHealth = this.currentHealth - damage;
        if(this.currentHealth < 0){
            this.currentHealth = 0;
        }
    }

    public void attack(Character x){
        x.takeDamage(9);
    }

    @Override
    public String toString(){
        String x; 
        if(this.currentHealth <= 0){
            x = String.format("%s : KO", this.name);
        }else{
            x = String.format("%s : %d/%d", this.name, this.currentHealth, this.maxHealth);
        }

        return x;
    }
}
