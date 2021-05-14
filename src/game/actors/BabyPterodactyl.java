package game.actors;

import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

public class BabyPterodactyl extends BabyDinosaur{

    /**
     * Constructor.
     * All Baby Pterodactyl are represented by a 'p' and have 100 hit points.
     * has dinosaur capabilities fly and carnivore
     * @param inputGender the gender of baby pterodactyl
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyPterodactyl(Gender inputGender){
        super("Baby " + Species.P.name(),'p',100, inputGender);
        capabilities.add(DinosaurCapabilities.FLY);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
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
