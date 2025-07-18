package creature.character;
import creature.Character;
import creature.Creature;
import weapon.Weapon;

public class Thief extends Character {
    private boolean guard;
    public Thief(String name, int hp, Weapon weapon) {
        super(name,hp,weapon);
    }


    public void attack(Creature target){
        System.out.println(getName() + "は素早く攻撃した!" + target.getName() + "に5のダメージを与えた!");
        target.setHp(target.getHp() - (getWeapon().getDamage()*2));
        System.out.println("素早く2回攻撃した!");
        System.out.println(getWeapon()+getWeapon().attackMessage());
    }
    public void guard(){
        setGuard(true);
    }
    public boolean getGuard() {
        return guard;
    }
    public void setGuard(boolean guard) {
        this.guard = guard;
    }

}
