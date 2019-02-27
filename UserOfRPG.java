/**
 * RPG Assignment by Oliver Cai
 * 
 * The features added:
 * -Multiple Monster and Protagonist
 * classes.
 * -Information about the monster visible
 * after each round.
 * -Information about the protagonist visble
 * after being picked.
 * 
 * -A sidekick that will help the user
 * defeat monsters (and occassionally
 * hit the user accidentally).
 *  -Multiple sidekick subclasses that each
 * have unique properties for the attack method.
 * (super class variable holding a reference to 
 * a single instance
 * of a sub-class)
 * (the compiler checks if method exists in 
 * variable type)
 * (the JVM runs the method according to 
 * its object type class)
 * 
 * -A record of every defeated monster
 * that will be printed at the 
 * end of a game
 * (A collection of super class variables)
 * 
 * Monster and protagonist classes
 * are borrowed from Piazza post 281.
 * They are slightly modified to include
 * their types as a field, so that their
 * names can be printed out using an
 * overloaded toString method. The
 * character class is also modified to
 * to include methods to lower attack
 * and lower defense.
 * 
 * More information about the additions
 * are put below and in the Sidekick classes.
 */

// shorthand, used to read from CLI
import java.io.*;
import java.util.*;

public class UserOfRPG
{
    //change this constant to set number of encounters in a game
    public final static int MAX_ENCOUNTERS = 5;

    //each round, a Protagonist and a Monster will be instantiated...
    private Protagonist pat;   //Is it man or woman?
    private Monster smaug; //Friendly generic monster name?

    /**
     * Your trusty sidekick.
     */
    private Protagonist sidekick;

    /**
     * record records a list of the monsters that
     * the protagonist and the sidekick have slain
     * together. The contents are printed out
     * at the end of the game.
     * 
     * Its length is intially set at 100,
     * because there is no way to expand the array
     * after each monster kill. In order to remedy
     * the possible problems that might occur, I created
     * a for loop that will only print out the information
     * at an index if it contains something other than
     * null. I also created an auto game ender if the list
     * of monsters is filled completely, but I really doubt
     * someone surviving through 100 monsters with the 
     * classes that I used for this assignment.
     */
    private static Monster[] record = new Monster[100];

    private int moveCount;
    private boolean gameOver;
    private static int difficulty;

    private InputStreamReader isr;
    private BufferedReader in;


    public UserOfRPG() {
        moveCount = 0;
        gameOver = false;
        isr = new InputStreamReader( System.in );
        in = new BufferedReader( isr );
        newGame();
    }


    /*=============================================
    void newGame() -- gathers info to begin a new game
    precondition:
    post: according to user input, modifies instance var for difficulty
    and instantiates a Protagonist
    =============================================*/
    public void newGame() {
        String s;
        String name = "";
        s = "~~~ Welcome to Ye Olde RPG! ~~~\n";

        /**
         * I added a 4th difficulty in order to get rid
         * of the limited monster rule. It helps the testing of
         * new mechanics added to the game.
         */
        s += "\nChoose your difficulty: \n";
        s += "\t1: Easy\n";
        s += "\t2: Not so easy\n";
        s += "\t3: Beowulf hath nothing on me. Bring it on.\n";
        s += "\t4: Survival Mode: Death is the only way to end the game.\n";
        s += "Selection: ";
        System.out.print( s );

        try {
            difficulty = Integer.parseInt( in.readLine() );
        } catch ( IOException e ) { }

        s = "Intrepid protagonist, what doth thy call thyself? (State your name): ";
        System.out.print( s );

        try {
            name = in.readLine();
        } catch ( IOException e ) { }

        /**
         * This chunk of code that determines a player's
         * class was borrowed from the Piazza Post 281.
         * The classes were slightly modifed. The Attacker
         * class is recommended because it is the only one 
         * that can actually destroy monsters and survive more 
         * than 2 rounds to see the game print out
         * the defeated monsters at the end.
         */
        s = "What speciality doth thy prefer?\n";
        s += "\n\t1: Attacker --- recommended" + System.lineSeparator();
        s += "\n\t2: Tank" + System.lineSeparator();
        s += "\n\t3: Vampire" + System.lineSeparator();
        System.out.println( s );
        int protype = 0;
        try {
            protype = Integer.parseInt( in.readLine() ); //hw44#6
        } catch( IOException e) {}

        //instantiate the player's character
        if (protype == 1) pat = new ProtoDPS( name );
        else if (protype == 2) pat = new ProtoTank( name );
        else if (protype == 3) pat = new ProtoVam( name );
        else pat = new Protagonist( name );

        System.out.println(pat);//hw46#1

        /**
         * Instantiates the player's sidekick name
         */
        s = "\nAnd what is the name of your trusty sidekick?\n";
        System.out.println(s);

        String sidekickName = "";
        try{
            sidekickName = in.readLine();
        }catch(IOException e) {}

        /**
         * Instantiates the sidekick class
         */

         s = "\n And what does your sidekick do in his free time?\n";
         s += "\t1: Lift weights and do first aid\n";
         s += "\t2: Study monster anatomy\n";
         s += "\t3: Nothing\n";
         System.out.println(s);

         try{
            int sidekickType = Integer.parseInt(in.readLine());
            if (sidekickType == 1) 
                sidekick = new StrongSidekick(sidekickName, pat);
            else if(sidekickType == 2) 
                sidekick = new UsefulSidekick(sidekickName, pat);
            else 
                sidekick = new Sidekick(sidekickName, pat);
            System.out.println(System.lineSeparator() + sidekick);

         }catch(IOException e) {}

    }//end newGame()


    /*=============================================
    boolean playTurn -- simulates a round of combat
    pre:  Protagonist pat has been initialized
    @return the boolean value of
        "the player survives"
    =============================================*/
    public boolean playTurn(int encounters) {
        int i = 1;
        int d1, d2, d3;

        if ( Math.random() >= ( difficulty / 3.0 ) )
            System.out.println( "\nNothing to see here. Move along!" );
        else {
            System.out.println( "\nLo, yonder monster approacheth!" );

            /**
             * Instantiates the monster randomly.
             * The three classes were borrowed from
             * Piazza Post 281 and were slightly modified.
             */
            int monsterSpawn = (int)(Math.random() * 3 + 1);
            if(monsterSpawn == 1) smaug = new MonoDPS();
            if(monsterSpawn == 2) smaug = new MonoTank();
            if(monsterSpawn == 3) smaug = new MonoVam();
            System.out.println(smaug.entrance());

            while( smaug.isAlive() && pat.isAlive() ) {

                System.out.println("\nMonster Stats:\n" + smaug);//hw46#2

                // Give user the option of using a special attack:
                // If you land a hit, you incur greater damage,
                // ...but if you get hit, you take more damage.
                try {
                    System.out.println( "\nDo you feel lucky?" );
                    System.out.println( "\t1: Nay.\n\t2: Aye!" );
                    i = Integer.parseInt( in.readLine() );
                }
                catch ( IOException e ) { }

                if ( i == 2 )
                    pat.specialize();
                else
                    pat.normalize();

                /**
                 * Determines the sidekick action.
                 * If the sidekick is dead, there
                 * will be nothing printed out.
                 * There is a 30% chance that the
                 * sidekick will attack the
                 * main protagonist.
                 * 
                 * If the sidekick accidently attacks
                 * the main protagonist, the attack 
                 * method from the Sidekick class
                 * will print out the appropriate
                 * message and deal 0 damage to the
                 * monster.
                 * 
                 */
                if(sidekick.isAlive()){//sidekick subclass inherits isAlive() from Character superclass //hw44#3
                    /**
                     * The sidekick.attack(target) method will
                     * print out a message according to the object
                     * class. Different messages will appear for
                     * the different sidekick subclasses
                     */
                    d3 = sidekick.attack(smaug);//A Monster subclass parameter is used //hw3E#0 //The attack method is overided by the instructions in the sidekick class. //hw46
                    System.out.println("\n" + sidekick.getName() + " dealt " + d3
                                    + " points of damage to the monster. ");
                }

                //Main protagonist action
                d1 = pat.attack( smaug ); //A Monster class

                System.out.println( "\n" + pat.getName() + " dealt "
                                  + d1 + " points of damage.");

                /**
                 * Instantiates the target of the Monster.
                 * If the Sidekick is no longer alive, the Monster cannot
                 * attack the sidekick.
                 */
                Protagonist smaugChoice;
                if (sidekick.isAlive() 
                    && Math.random() > .5) {
                    d2 = smaug.attack( sidekick);//Monster class inherits attack method from Character superclass //hw44#4
                    smaugChoice = sidekick;
                    System.out.println( "\n" + "Ye Olde Monster smacked "
                                  + smaugChoice.getName() + " for "
                                  + d2 + " points of damage.");
                    if( !sidekick.isAlive()){
                        //Message when the sidekick is down.
                        System.out.println("The monster struck " 
                                            + sidekick.getName() + 
                                            " down!");
                    }
                }
                else {
                    d2 = smaug.attack( pat );
                    smaugChoice = pat;
                    System.out.println( "\n" 
                                    + "Ye Olde Monster smacked "
                                    + smaugChoice.getName() + " for "
                                    + d2 + " points of damage.");
                }
            }//end while

            //option 1: you & the monster perish
            if ( !smaug.isAlive() && !pat.isAlive() ) {
                System.out.println( "'Twas an epic battle, to be sure... " +
                "You cut ye olde monster down, but " +
                "with its dying breath ye olde monster. " +
                "laid a fatal blow upon thy skull." );
                record[encounters] = smaug;
                return false;
            }
            //option 2: you slay the beast
            else if ( !smaug.isAlive() ) {
                System.out.println( "HuzzaaH! Ye olde monster hath been slain!" );
                record[encounters] = smaug;
                return true;
            }
            //option 3: the beast slays you
            else if ( !pat.isAlive() ) {
                System.out.println( "Ye olde self hath expired. You got dead." );
                return false;
            }

            /**
             * option 4: the record of monsters
             * is filled to the brim.
             */
            else if(record[99] != null){
                System.out.println("You've defeated 100 Monsters!" +
                                    "\n You've found a" +  
                                    "loophole so game over");
                return false;
            }
        } //end else

        return true;
    }//end playTurn()


    public static void main( String[] args ) {
        // As usual, uncomment progressively in tiny steps

        //loading...
        UserOfRPG game = new UserOfRPG();

        int encounters = 0;

        /**
         * Survival Gamemode
         * I added a 4th gamemode, in which there is
         * no limit to the amount of monsters that 
         * appear. This makes the record list of
         * monsters more relevant, and I feel as
         * if this is a better test for any 
         * mechanics I added to the game.
         */
        if(difficulty == 4){
            while(game.playTurn(encounters) ) {
            encounters++;
            System.out.println();
            }
        }
        else{ 
            while(    encounters < MAX_ENCOUNTERS
               && game.playTurn(encounters) ) {
            encounters++;
            System.out.println();
            }
         }

        System.out.println( "Thy game doth be over." );

        /**
         * This chunk of code prints out the names of each
         * defeated Monster in the record and the total 
         * number of monsters defeated. It does so using a 
         * for loop that will only print out the information
         * at an index if it contains something other than
         * null for reasons I specified in the comment 
         * sections above.
         */
        System.out.println("\nYour enemies obliterated: \n");

        int score = 0;
        for(int index = 0; index < record.length; index++){
            if (record[index] != null) {
                System.out.println(record[index].entrance());
                score ++;
            }
        }
        
        System.out.println("\nScore: " + score);
        System.out.println("Best Score Possible " + 100);
    }//end main

}//end class UserOfRPG
