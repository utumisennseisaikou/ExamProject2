package creature.monster;

import creature.Creature;
import creature.Monster;
public class Goblin extends Monster {
    public Goblin(char suffix, int hp) {
        super("ゴブリン", suffix, hp);
    }

    public void attack(Creature target) {
        System.out.println(getName()+ getSuffix() + "は体当たり攻撃！" + target.getName() + "に8のダメージを与えた！");
        target.setHp(target.getHp() - 8);
    }


}
