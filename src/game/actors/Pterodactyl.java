package game.actors;

import edu.monash.fit2099.engine.Actor;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A flying based carnivorous dinosaur (Pterodactyl).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see Dinosaur
 */

public class Pterodactyl extends Dinosaur{

//    /**
//     * Constructor.
//     * All Pterodactyl are represented by a 'P' and have 100 hit points.
//     * has dinosaur capabilities fly and carnivore
//     * @see Dinosaur
//     * @see Species
//     * @see DinosaurCapabilities
//     * @see Food
//     */
//    public Pterodactyl() {
//        super(Species.P.name(), 'P', 100);
//        capabilities.add(DinosaurCapabilities.FLY);
//        capabilities.add(DinosaurCapabilities.CARNIVORE);
//        setEdibleFoodList(Food.getFoodList(this)); // haven't implement in food
//        setFoodLevel(INIT_FOOD_LEVEL);
//    }

    /**
     * Constructor.
     * All Pterodactyl are represented by a 'P' and have 100 hit points.
     * has dinosaur capabilities fly and carnivore
     * @param inputGender the gender of Pterodactyl
     * @see Dinosaur
     * @see Actor
     * @see Species
     * @see Gender
     * @see DinosaurCapabilities
     * @see Food
     */
    public Pterodactyl(Gender inputGender){
        super(Species.P.name(), 'P', 100);
        this.addCapability(DinosaurCapabilities.FLY);
        this.addCapability(DinosaurCapabilities.CARNIVORE);
        gender = inputGender;
        setEdibleFoodList(Food.getFoodList(this)); // haven't implement in food
        setFoodLevel(50);
        setMinFoodLevel(90);
        setMaxFoodLevel(100);
        setMaxWaterLevel(100);
        setMaxUnconsciousTime(15);
        setRotTime(20);
        setRemoveCount(3);
        squares = 30;
        isFlying = true;
    }
}
