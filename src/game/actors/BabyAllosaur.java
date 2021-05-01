package game.actors;

import game.enums.DinosaurCapabilities;

public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyAllosaur() {
        super("Baby Allosaur",'a',1000);
        dinoChar = 'a';
        capabilities.add(DinosaurCapabilities.CARNIVORE);
    }

    public boolean isVegetarian(){return false;}
}
