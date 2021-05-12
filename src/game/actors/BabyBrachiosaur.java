package game.actors;

import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A baby herbivorous and long neck dinosaur (Baby Brachiosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see BabyDinosaur
 */

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     * All Brachiosaur are represented by a 'b' and have 100 hit points.
     * has dinosaur capabilities herbivore and long neck
     * @param g the gender of baby brachiosaur
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyBrachiosaur(Gender g) {
        super("Baby " + Species.B.name(), 'b', 1000,g);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
        setRotTime(40);
        setFoodLevel(10);
    }
}
