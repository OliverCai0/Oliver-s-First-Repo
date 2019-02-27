/**
 * Borrowed from Piazza Post 281 by Saad and Yevgeniy
 */
public class ProtoVam extends Protagonist {//Protagonist Vampire class can restore health after attacking hw44#3

    public ProtoVam( String name) {
        super(name);//Uses Protagonist constructor to set stats for ProtoVam //hw44#3
    }

    public int attack(Character target){
        int damage = (int)((strength * attack) - target.getDefense());
        target.lowerHP(damage);
        lowerHP(-damage);
        return damage;
    }

    public String toString(){
        return "" + super.toString();
    }

}