public class Sorcerer extends Character implements Healer{

    private final int healCapacity;

    public Sorcerer(String name, int maxHealth, int x){
        super(name, maxHealth);
        this.healCapacity = x;
    }

    @Override
    public void heal(Character c){
        if((c.getCurrentHealth() + this.healCapacity)>= c.getMaxHealth()){
            c.setCurrentHealth(c.getMaxHealth());
            return;
        }
        c.setCurrentHealth(c.getCurrentHealth() + this.healCapacity);
    }
    @Override
    public int getHealCapacity(){
        return this.healCapacity;
    }

    @Override
    public String toString(){
        if(super.getCurrentHealth() == 0){
            return String.format("%s is a dead sorcerer. So bad, it could heal %s HP.", super.getName(), this.healCapacity);
        }
        
        return String.format("%s is a sorcerer with %s HP. It can heal %s HP.", super.getName(), super.getCurrentHealth(), this.healCapacity );

    }
}