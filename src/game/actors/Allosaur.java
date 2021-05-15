package game.actors;

import edu.monash.fit2099.engine.*;
import game.behaviours.Behaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

import java.util.ArrayList;

/**
 * A carnivorous dinosaur (Allosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Dinosaur
 */

public class Allosaur extends Dinosaur {


//    /**
//     * Constructor.
//     * All Allosaurs are represented by a 'A' and have 100 hit points.
//     * has dinosaur capabilities carnivore
//     * @see Dinosaur
//     * @see Species
//     * @see DinosaurCapabilities
//     * @see Food
//     */
//    public Allosaur() {
//        super(Species.A.name(), 'A', 100);
//        // add capabilities and set EdibleFoodList
//        capabilities.add(DinosaurCapabilities.CARNIVORE);
//        setEdibleFoodList(Food.getFoodList(this));
//        // init food level of 50
//        setFoodLevel(50);
//        setMinFoodLevel(90);
//        setMaxFoodLevel(100);
//        setMaxUnconsciousTime(20);
//        setMaxWaterLevel(100);
//        setRotTime(20);
//    }

    /**
     * Constructor.
     * All Allosaurs are represented by a 'A' and have 100 hit points.
     * has dinosaur capabilities carnivore
     * @param inputGender gender of the dinosaur
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public Allosaur(Gender inputGender) {
        super(Species.A.name(), 'A', 100);
        // add capabilities
        this.addCapability(DinosaurCapabilities.CARNIVORE);
        // set EdibleFoodList and gender
        setEdibleFoodList(Food.getFoodList(this));
        gender = inputGender;
        // init food level of 50
        setFoodLevel(50);
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
