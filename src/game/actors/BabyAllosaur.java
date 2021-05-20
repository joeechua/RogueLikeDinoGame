package game.actors;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A baby carnivorous dinosaur (Baby Allosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see BabyDinosaur
 */
public class BabyAllosaur extends BabyDinosaur{

    /**
     * Constructor.
     * All Baby Allosaurs are represented by a 'a' and have 100 hit points.
     * has dinosaur capabilities carnivore
     * @param g the gender of baby allosaur
     * @see Actor
     * @see Dinosaur
     * @see Species
     * @see Gender
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyAllosaur(Gender g) {
        super("Baby " + Species.A.name(),'a',100,g);
        this.addCapability(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
        gender = g;
        // init food level of 20
        setFoodLevel(20);
        setMinFoodLevel(90);
        setMaxFoodLevel(100);
        setMaxUnconsciousTime(20);
        setMaxWaterLevel(100);
        setRotTime(20);
    }

    /**
     * Creates and returns an intrinsic weapon with damage 20.
     * @return a freshly-instantiated IntrinsicWeapon
     * @see Actor
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20,"claws");
    }
}
