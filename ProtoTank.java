/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class ProtoTank extends Protagonist {//Protagonist tank class can increase defense after specialization //hw44#3

    public ProtoTank( String name) {
        super(name);//Uses Protagonist constructor to set stats for ProtoTank. Its stats are inherited from the Character super class. //hw44#3
    }
    
    public void specialize(){
        if (!isSpec) {
            System.out.println(attack /= 4);
            System.out.println(defense *= 4);
            isSpec = true;
        }
    }

}