public class Monster extends Character {

    public String monsterName;

    public Monster(){
        monsterName = "Monster";
        strength = (int)(Math.random() * 20) + 30;
        health = 150;
        attack = 1;
        defense = 20;
    }

    public String entrance(){
        return monsterName;
    }
}	