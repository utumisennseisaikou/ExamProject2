package weapon;

public class Sword extends Weapon{
    Sword(String name,int damage){
        super("剣",10);
    }
    public String attackMessage(){
        return "で切りつけた";
    }
}
