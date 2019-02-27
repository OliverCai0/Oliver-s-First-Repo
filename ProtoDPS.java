/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class ProtoDPS extends Protagonist {//Protagonist damager class, can specialize to increase attack //hw44#3

    public ProtoDPS( String name) {
        super(name);//Uses Protagonist constructor to set stats for ProtoDPS //hw44#3
    }

    public void specialize() {
        if (!isSpec) {
            System.out.println(attack *= 4);
            System.out.println(defense /= 3);
            isSpec = true;
        }
    }
    
}