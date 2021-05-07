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
 * @version 2.0
 * @see BabyDinosaur
 */
public class BabyAllosaur extends BabyDinosaur{

    /**
     * Constructor.
     * All Baby Allosaurs are represented by a 'a' and have 100 hit points.
     * has dinosaur capabilities carnivore
     * @param g the gender of baby allosaur
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public BabyAllosaur(Gender g) {
        super("Baby " + Species.A.name(),'a',100,g);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
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
