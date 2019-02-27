/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class MonoTank extends Monster{//Monster tank class that has high defense but low attack //hw44#4

    public MonoTank() {
        super();//Uses Monster constructor to set stats for MonoTank. Its stats are inherited from the Character superclass //hw44#4
        monsterName = "Boomer";
        attack /= 3;
        defense *= 4;
    }

    public String entrance(){
        return super.entrance() + " --- an absolute unit";//Uses Monster entrance() in order to print out name and description //hw44#3
    }

}