package game.actors;

import edu.monash.fit2099.engine.Actor;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A baby herbivorous and long neck dinosaur (Baby Brachiosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see BabyDinosaur
 */

public class BabyBrachiosaur extends BabyDinosaur{
    /**
     * Constructor.
     * All Brachiosaur are represented by a 'b' and have 100 hit points.
     * has dinosaur capabilities herbivore and long neck
     * @param g the gender of baby brachiosaur
     * @see Dinosaur
     * @see Actor
     * @see Species
     * @see Gender
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyBrachiosaur(Gender g) {
        super("Baby " + Species.B.name(), 'b', 1000,g);
        this.addCapability(DinosaurCapabilities.HERBIVORE);
        this.addCapability(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
        gender = g;
        setFoodLevel(10);
        setMinFoodLevel(140);
        setMaxFoodLevel(160);
        setMaxUnconsciousTimeFL(15);
        setRotTime(40);
        setMaxWaterLevel(200);
    }
}
