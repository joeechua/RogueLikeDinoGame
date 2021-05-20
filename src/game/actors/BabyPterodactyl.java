package game.actors;

import edu.monash.fit2099.engine.Actor;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A flying based baby carnivorous dinosaur (Baby Pterodactyl).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see BabyDinosaur
 */

public class BabyPterodactyl extends BabyDinosaur{

    /**
     * Constructor.
     * All Baby Pterodactyl are represented by a 'p' and have 100 hit points.
     * has dinosaur capabilities fly and carnivore
     * @param inputGender the gender of baby pterodactyl
     * @see Dinosaur
     * @see Actor
     * @see Species
     * @see Gender
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyPterodactyl(Gender inputGender){
        super("Baby " + Species.P.name(),'p',100, inputGender);
        this.addCapability(DinosaurCapabilities.FLY);
        this.addCapability(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
        setFoodLevel(10);
        setMinFoodLevel(90);
        setMaxFoodLevel(100);
        setMaxWaterLevel(100);
        setMaxUnconsciousTime(15);
        setRotTime(20);
        squares = 30;
        isFlying = true;
    }
}
