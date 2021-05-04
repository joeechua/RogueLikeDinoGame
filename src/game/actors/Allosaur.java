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

    private ArrayList<Behaviour> behaviour;

    /**
     * Constructor.
     * All Allosaurs are represented by a 'A' and have 100 hit points.
     * has dinosaur capabilities carnivore
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public Allosaur() {
        super(Species.A.name(), 'A', 100);
        // add capabilities and set EdibleFoodList
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        setEdibleFoodList(Food.getFoodList(this));
    }

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
        capabilities.add(DinosaurCapabilities.CARNIVORE);
        // set EdibleFoodList and gender
        setEdibleFoodList(Food.getFoodList(this));
        gender = inputGender;
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
