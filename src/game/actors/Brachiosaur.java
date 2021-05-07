package game.actors;

import edu.monash.fit2099.engine.*;
import game.enums.DinosaurCapabilities;
import game.enums.Food;
import game.enums.Gender;
import game.enums.Species;

/**
 * A herbivorous and long neck dinosaur (Brachiosaur).
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see Dinosaur
 */
public class Brachiosaur extends Dinosaur {

    /**
     * Constructor.
     * All Brachiosaur are represented by a 'B' and have 100 hit points.
     * has dinosaur capabilities herbivore and long neck
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public Brachiosaur() {
        super(Species.B.name(), 'B', 100);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
    }

    /**
     * Constructor.
     * All Brachiosaur are represented by a 'B' and have 100 hit points.
     * has dinosaur capabilities herbivore and long neck
     * @param inputGender the gender of brachiosaur
     * @see Dinosaur
     * @see Species
     * @see DinosaurCapabilities
     * @see Food
     */
    public Brachiosaur(Gender inputGender){
        super(Species.B.name(), 'B', 100);
        capabilities.add(DinosaurCapabilities.HERBIVORE);
        capabilities.add(DinosaurCapabilities.LONG_NECK);
        setEdibleFoodList(Food.getFoodList(this));
        gender = inputGender;
    }
}
