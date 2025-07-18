package weapon;

public abstract class Weapon {
    private String name;
    private int damage;
    private int cost =0;
    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }
     public abstract String attackMessage();
    String getName(){
        return name;
    }
    int getDamage(){
        return damage;
    }
    void setDamage(int damage){
        this.damage = damage;
    }
    int getCost(){
        return cost;
    }
    void setCost(int cost){
        this.cost = cost;

    }
}
