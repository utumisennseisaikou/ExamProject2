
import creature.*;
import creature.character.*;
import creature.monster.*;
import creature.Character;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import weapon.*;

public class GameMaster {
    public static void main(String[] args) {

        ArrayList<Character> party = new ArrayList<Character>();
        Hero hero = new Hero("勇者", 100, new Sword());
        Wizard wizard = new Wizard("魔法使い", 60, 20, new Wand());
        Thief thief = new Thief("盗賊", 70, new Dagger());
        party.add(hero);
        party.add(wizard);
        party.add(thief);

        ArrayList<Monster> monsters = new ArrayList<Monster>();
        Random rand = new Random();
        int goblinCount = 0;
        int matangoCount = 0;
        int slimeCount = 0;

        for (int i = 0; i < 5; i++) {
            int monsterType = rand.nextInt(3);
            switch (monsterType) {
                case 0:
                    monsters.add(new Goblin((char) ('A' + goblinCount++), 50));
                    break;
                case 1:
                    monsters.add(new Matango((char) ('A' + matangoCount++), 45));
                    break;
                case 2:
                    monsters.add(new Slime((char) ('A' + slimeCount++), 40));
                    break;

            }
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!party.isEmpty() && !monsters.isEmpty()) {
                // ステータス表示
                System.out.println("\n【味方ステータス】");
                for (Character c : party) {
                    c.showStatus();
                }
                System.out.println("\n【敵ステータス】");
                for (Monster m : monsters) {
                    m.showStatus();
                }

                // 味方のターン
                Iterator<Character> it = party.iterator();
                int choice = -1;
                while (it.hasNext()) { //次の要素があるかを見ている
                    Character c = it.next(); //次のデータを取り込んでいる
                    if (monsters.isEmpty()) break;
                    System.out.println("\n" + c.getName() + "の行動を選択してください：");
                    if (c instanceof SuperHero){
                        System.out.println("スーパーヒーローは攻撃するしかない!");
                        System.out.print("1. 攻撃\n>");


                    }else if (c instanceof Hero) {
                            System.out.print("1. 攻撃 2. SuperHeroになる \n>");

                    } else if (c instanceof Wizard) {
                        System.out.print("1. 攻撃 2. 魔法攻撃 \n>");
                    } else if (c instanceof Thief) {
                        System.out.print("1. 攻撃 2. 守り \n>");
                    }
                    while (true) {
                        String input = br.readLine();
                        try {
                            choice = Integer.parseInt(input);
                            if (c instanceof SuperHero) break;
                            if (choice == 1 || choice == 2) break;
                            else System.out.println("1か2を選んでください。");
                        } catch (NumberFormatException | NullPointerException e) {
                            System.out.println("入力エラー：数字を入力してください。");
                        }
                    }

                    if (choice == 2) {
                        if (c instanceof Hero) {
                            SuperHero sh = new SuperHero((Hero) c);
                            System.out.println("勇者はスーパーヒーローに進化した!");
                            System.out.println("勇者は力を解放する代償として30のダメージを受けた!");
                            sh.setHp(sh.getHp() - 30);
                            if (sh.getHp() > 0) {
                                party.set(party.indexOf(c), sh);

                                continue;
                            } else {
                                c.die();
                                it.remove();
                                continue;
                            }

                        } else if (c instanceof Thief) {
                            System.out.println("盗賊は身を守っている！");
                            ((Thief) c).guard();
                            continue;
                        }
                    }

                    // 攻撃対象選択
                    System.out.println("攻撃するモンスターを選んでください：");
                    for (int i = 0; i < monsters.size(); i++) {
                        System.out.println(i + ": " + monsters.get(i).getName()+monsters.get(i).getSuffix());
                    }

                    int targetIndex = -1;
                    while (true) {
                        String input = br.readLine();
                        try {
                            targetIndex = Integer.parseInt(input);
                            if (targetIndex >= 0 && targetIndex < monsters.size())
                                break;
                            else System.out.println("正しい番号を選んでください。");
                        } catch (NumberFormatException | NullPointerException e) {
                            System.out.println("入力エラー：数字を入力してください。");
                        }
                    }

                    Monster target = monsters.get(targetIndex);
                    if(choice == 1) {
                        c.attack(target);
                        System.out.println("\n【敵ステータス】");
                        for (Monster m : monsters) {
                            m.showStatus();
                        }
                    }else if (choice == 2) {
                        ((Wizard) c).magicattack(target);
                        System.out.println("\n【敵ステータス】");
                        for (Monster m : monsters) {
                            m.showStatus();
                        }
                    }

                    if (target.getHp() <= 5) {
                        target.run();
                        monsters.remove(target);
                    } else if (target.getHp() <= 0) {
                        target.die();
                        monsters.remove(target);
                    }
                }

                // 敵のターン（ランダムに味方を攻撃）
                Iterator<Monster> randomattack = monsters.iterator();
                while (randomattack.hasNext()) {
                    Monster m = randomattack.next();
                    if (party.isEmpty()) break;

                    int targetIndex = rand.nextInt(party.size());
                    Character target = party.get(targetIndex);
                    m.attack(target);
                    System.out.println("\n【味方ステータス】");
                    for (Character c : party) {
                        c.showStatus();
                    }

                    if (target.getHp() <= 0) {
                        target.die();
                        party.remove(targetIndex);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("入出力エラー：" + e.getMessage());
        }

        // 勝敗判定
        if (monsters.isEmpty()) {
            System.out.println("敵を全て倒した！" + party.get(0).getName() + "達は勝利した！");
        } else {
            System.out.println("味方パーティは全滅してしまった…");
        }
    }
}