package game.enums;

/**
 * A collection of possible genders for a Dinosaur
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see game.actors.Dinosaur
 */
public enum Gender {
    M("MALE"),
    F("FEMALE");

    public final String label;

    /**
     * Constructor
     * @param label name of the gender
     */
    Gender(String label){
        this.label = label;
    }

}
