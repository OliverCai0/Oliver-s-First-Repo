/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class MonoDPS extends Monster {//Damage based monster class that has high attack and low defense //hw44#4

    public MonoDPS() {
        super();//Uses Monster constructor to set stats for MonoDPS. Its stats are inherited from the Character super class //hw44#4
        monsterName = "Angry Cow";
        attack *= 2;
        defense /= 3;
    }

    public String entrance(){
        return super.entrance() + " --- she is going vegan for the first time";//Uses Monster entrance() in order to print out name and description //hw44#3
    }
}