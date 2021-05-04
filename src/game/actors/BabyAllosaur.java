package game.actors;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.IntrinsicWeapon;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Species;

/**
 * A baby carnivorous dinosaur (Baby Allosaur).
 * @see Actor
 * @see Dinosaur
 * @see DinosaurCapabilities
 * @see Food
 */
public class BabyAllosaur extends BabyDinosaur{
    /**
     * Constructor.
     * All Baby Allosaurs are represented by a 'a' and have 100 hit points.
     * has dinosaur capabilities carnivore
     */
    public BabyAllosaur() {
        super("Baby " + Species.A.name(),'a',100);
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
    }

    /**
     * Creates and returns an intrinsic weapon with damage 20.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(20,"claws");
    }
}
