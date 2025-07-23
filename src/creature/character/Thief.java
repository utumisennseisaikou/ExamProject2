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
        System.out.println(getName() + "は素早く2回攻撃した!" + target.getName() + "に5のダメージを与えた!");
        target.setHp(target.getHp() - (getWeapon().getDamage()*2));
        System.out.println(getWeapon().getName()+getWeapon().attackMessage());
    }
    public void guard() {
        guard = true;
    }
    public void setHp(int hp ){
        if(true == guard) {
            System.out.println("しかし、" + getName() + "は攻撃を回避し、ダメージが入らなかった");
            guard = false;
        }else{
            super.setHp(hp);
        }
    }
}
