package creature.character;
import creature.Character;

import creature.Creature;
import weapon.Weapon;

public class Hero extends Character {
    public Hero(String name, int hp, Weapon weapon){
        super(name,hp,weapon);
    }

    public void attack(Creature target) {
        System.out.println(this.getName() + "は" + weapon+ "で攻撃!" + target.getName() + "に10のダメージを与えた!");
        target.setHp(target.getHp() - weapon.damage);

    }
    attackMessage();
}
