public class Protagonist extends Character {

    protected String name;//hw3C#1
    protected boolean isSpec;
    protected final double BASE_ATK; //hw3C#1
    protected final int BASE_DEF; //hw3C#1

    public Protagonist(String name){//hw3C#1
        this.name = name;
        strength = 35;
        health = 300;
        isSpec = false;
        attack = BASE_ATK = 1;
        defense = BASE_DEF = 30;
    }

    public String toString() {
        String tst = super.toString(); //Uses Character toString in order to print out regular stats //hw44#3
        return "Name: " + name + System.lineSeparator()
                + tst + System.lineSeparator()
                + "Base attack: " + BASE_ATK + System.lineSeparator()
                + "Base defense: " + BASE_DEF;
    }
    
    public String getName(){//hw3C#1
        return name;
    }
    
    public void specialize(){//hw3C#1
        if (!isSpec) {
            isSpec = true;
            attack *= 2;
            defense /= 2;
        }
    }
    
    public void normalize(){//hw3C#1
        isSpec = false;
        attack = BASE_ATK;
        defense = BASE_DEF;
    }
}