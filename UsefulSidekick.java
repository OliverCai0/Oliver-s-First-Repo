/**
 * The Useful Sidekick
 * will lower the attack and
 * defense of the monster if
 * he/she hits the monster.
 */
public class UsefulSidekick extends Sidekick{
    public UsefulSidekick(String name, 
                          Protagonist pat){
        super(name, pat);
    }

    /**
     * An appropriate message is printed out
     * once the sidekick hits the monster
     */
    public int attack(Character target){
        int damage = super.attack(target);
        if (damage > 0){
            target.lowerDefense(10);
            target.lowerAttack(.2);
            System.out.println("\nThe Monster has been weakened!\n");
        }
        return damage;
    }
}