package creature;

import weapon.Weapon;

public abstract class Character implements Creature {
    private String name;
    private int hp;
    private Weapon weapon;

    public Character(String name, int hp, Weapon weapon) {

        if (hp <0) {
            throw new IllegalArgumentException("初期設定に誤りがあるため、キャラクターを作成できませんでした");
        }
        this.hp = hp;
        this.name = name;
        this.weapon = weapon;

    }
    public final boolean isAlive(){
        if(this.hp<=0) {
            return false;
        }
        else{
            return true;
        }
    }

    public void showStatus(){
        System.out.println( this.getName() +":HP "+ this.getHp() );
    }
    public String getName(){
        return this.name;
    }
    public int getHp(){
        return this.hp;
    }
    public void setHp(int hp) {
        if (hp <= 0) {
            this.hp = 0;
        }else{
            this.hp = hp;
        }

    }
    public void die(){
        System.out.println(getName()+"は死んでしまった!");
    }


    public Weapon getWeapon() {
        return weapon;
    }
}
