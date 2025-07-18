package creature.character;

import creature.Creature;
import creature.Character;
import weapon.Weapon;
public class Wizard extends Character {
    private int mp;

    public Wizard(String name, int hp, int mp,Weapon weapon) {
        super(name, hp, weapon);
        this.mp = mp;

    }

    public void magic(Creature target) {
        this.mp -= getWeapon().getCost();
        if (this.mp == 0) {
            System.out.println("MPが足りない");
        } else {
            target.setHp(target.getHp() - getWeapon().getDamage());
            System.out.println(getWeapon() + getWeapon().attackMessage());
        }
    }

    public void attack(Creature target) {
        System.out.println(getName() + "は石を投げた!" + target.getName() + "に3のダメージを与えた!");
        target.setHp(target.getHp() - 3);
    }
}
