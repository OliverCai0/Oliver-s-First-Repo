/**
 * The Strong Sidekick will 
 * heal the main protagonist
 * if he/she accidentally
 * attacks the main protagonist.
 */

public class StrongSidekick extends Sidekick{

    public StrongSidekick(String name, 
                          Protagonist pat){
            super(name, pat);
            strength = 70;
                    }

    /**
     * An appropriate message is printed out
     * if the sidekick hits the main protagonist
     */
    public int attack(Character target){
        int damage = super.attack(target);
        if(damage == 0){
            System.out.println("\n" + name + " whips out his backpack"
                                + " and throws you some bandages.\n"
                                + mainGuy.getName() + " heals for 20");
            mainGuy.lowerHP(-20);
                }
        return damage;
    }
}