public class Character {
    
    protected int health; // hw3C#1 hw3C#2
    protected int strength; //hw3C#1 hw3C#2
    protected double attack;// hw3C#1 hw3C#2
    protected int defense; // hw3C#1 hw3C#2
    
    public String toString() {
        return "Health: " + health + System.lineSeparator()
                + "Strength: " + strength + System.lineSeparator()
                + "Attack: " + attack + System.lineSeparator()
                + "Defense: " + defense;
    }

    public boolean isAlive(){ //hw3C#1 hw3C#2
        return health > 0;
    }

    public void lowerAttack(double value){
        attack = attack - value;
    }

    public void lowerDefense(int value){
        defense = defense - value;
    }

    protected int getDefense(){ //hw3C#1 hw3C#2
        return defense;
    }

    protected void lowerHP(int dmg){//hw3C#1 hw3C#2
        health -= dmg;
    }

    public int attack(Character target){//hw3C#1 hw3C#2 hw3E#0
        int damage = (int)((strength * attack) - target.getDefense());
        if (damage < 0) damage = 0;
        target.lowerHP(damage);
        return damage;
    }

    
}