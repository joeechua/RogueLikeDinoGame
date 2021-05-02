package game.actors;

import game.enums.DinosaurCapabilities;

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     */
    public BabyBrachiosaur() {
        super("Baby Brachiosaur", 'b', 1000);
        dinoChar = 'b';
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
    }
}
