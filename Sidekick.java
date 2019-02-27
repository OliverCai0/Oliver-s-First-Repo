/**
 * Sidekick can occasionally attack the main
 * character(user) by accident.
 * 
 * Usage of Polymorphism:
 * - A field named mainGuy, which stores
 * a reference to a Protagonist.
 * - Using super.attack(Character target)
 * in its special attack method.
 */

public class Sidekick extends Protagonist{

    /**
     * A Protagonist mainGuy stores a reference
     * to the main character. This provides the
     * parameter for the special attack mathod of
     * sidekick.
     */
    public Protagonist mainGuy;

    public Sidekick(String name, 
                    Protagonist pat){
        super(name);//Constructor for sidekick class uses Protagonist super constructor to set stats and name. The fields such as strength are inherited from the Protagonist super class //hw44#3
        mainGuy = pat;
    }

    /**
     * The sidekick has a 30% chance to fumble and 
     * accidentally attack the main character. If
     * the fumble pathway occurs, a message detailing
     * this action and how much damage the sidekick
     * does to the main character is printed. No
     * damage will be done to the Monster if 
     * this happens.
     **/
    public int attack(Character target){//hw3E#0
        boolean fumble = (Math.random() < .3);
        if(fumble){
            System.out.println("\n" + "Oof, " + name + 
                                " tried to drop kick, but hit " +
                                mainGuy.getName() +  " instead.");
            System.out.println("\n" + name + 
                               " dealt " + 
                               super.attack(mainGuy) + //Sidekick attack method uses super.attack to attack the user if the sidekick fumbles //hw44#3
                               " damage to " +
                               mainGuy.getName() + "!");
            return 0;
        }
        else return super.attack(target);//Sidekick attack method uses super.attack to calculate damage done to the monster //hw44#3
    }
}