/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class MonoVam extends Monster {//Monster Vampire class can restore health hw44#4

    public MonoVam(){
        super();//Uses Monster constructor to set stats for MonoVam. It inherits stats from the Character class. //hw44#4
        monsterName = "Vampire";
    }

    public int attack(Character target){
        int damage = (int)((strength * attack) - target.getDefense());
        target.lowerHP(damage);
        lowerHP(-damage);
        return damage;
    }

    public String entrance(){
        return super.entrance() + " --- he likes to volunteer at the blood shelter";//Uses Monster entrance() in order to print out name and description //hw44#3
    }
    
}