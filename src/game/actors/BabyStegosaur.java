package game.actors;

import game.enums.DinosaurCapabilities;

public class BabyStegosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyStegosaur() {
        super("Baby Stegosaur",'s',100);
        dinoChar = 's';
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        attackTurns = 0;
    }

    public boolean isVegetarian(){return true;}
    @Override
    public void tick() {
        super.tick();
        if(attackTurns != 0){
            attackTurns--;
        }
    }
}
