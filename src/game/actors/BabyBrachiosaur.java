package game.actors;

import edu.monash.fit2099.engine.Actor;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Species;

/**
 * A baby herbivorous and long neck dinosaur (Baby Brachiosaur).
 * @see Actor
 * @see Dinosaur
 * @see DinosaurCapabilities
 * @see Food
 */

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     * All Brachiosaur are represented by a 'b' and have 100 hit points.
     * has dinosaur capabilities herbivore and long neck
     */
    public BabyBrachiosaur() {
        super("Baby " + Species.B.name(), 'b', 1000);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
    }
}
