
import creature.Monster;
import creature.character.Hero;
import creature.character.SuperHero;
import creature.character.Thief;
import creature.character.Wizard;
import creature.monster.Matango;
import creature.monster.Goblin;
import creature.monster.Slime;
import creature.Character;
import java.util.ArrayList;

import weapon.Dagger;
import weapon.Sword;
import weapon.Wand;

public class GameMaster {
    public static void main(String[] args) {

        ArrayList<Character> party = new ArrayList<Character>();
        Hero hero = new Hero("勇者", 100,new Sword());
        Wizard wizard = new Wizard("魔法使い", 60, 20,new Wand());
        Thief thief = new Thief("盗賊", 70,new Dagger());
        party.add(hero);
        party.add(wizard);
        party.add(thief);
        System.out.println("---味方パーティ---");
        for (Character member : party) {
            member.showStatus();
        }
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        for (int i = 0; i<5; i++){

        }
        Matango matango = new Matango('A', 45);
        Goblin goblin = new Goblin('A', 50);
        Slime slime = new Slime('A', 40);
        monsters.add(matango);
        monsters.add(goblin);
        monsters.add(slime);
        System.out.println("---敵グループ---");
        for (Monster member : monsters) {
            member.showStatus();
        }
        System.out.print("\n");
        System.out.println("味方の総攻撃!");
        for (Character member : party) {
            for (Monster m : monsters) {
                member.attack(m);
            }
        }
        System.out.print("\n");
        System.out.println("敵の総攻撃!");
        for (Monster member : monsters) {
            for (Character m : party) {
                member.attack(m);
            }
        }
        SuperHero superHero = new SuperHero(hero);
        party.set(0, superHero);
        System.out.print("\n");

        System.out.println("ダメージを受けた勇者が突然光だした!");
        System.out.println("勇者はスーパーヒーローに進化した!");
        for (Character member : party) {
            if (member.equals(superHero)) {
                for (Monster m : monsters) {
                    member.attack(m);
                }
            }
        }
        System.out.print("\n");

        System.out.println("---味方パーティ最終ステータス---");
        for (Character member : party) {
            member.showStatus();
            if (member.isAlive() == false){
                System.out.println("生存状況：戦闘不能");
            }else {
                System.out.println("生存状況：生存");
            }
        }
        System.out.print("\n");
        System.out.println("---敵グループ最終ステータス---");
        for (Monster member : monsters) {
            member.showStatus();
            if (member.isAlive() == false){
                System.out.println("生存状況：討伐済み");
            }else {
                System.out.println("生存状況：生存");
            }
        }
    }
}
