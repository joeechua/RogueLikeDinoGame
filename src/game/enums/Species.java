package game.enums;

/**
 * Collection of possible Dinosaur speecies
 * @see game.actors.Dinosaur
 */
public enum Species {
    A("ALLOSAUR"),
    B("BRACHIOSAUR"),
    S("STEGOSAUR");

    public final String label;

    /**
     * Constructor
     * @param name name of the species
     */
    Species(String name){
        this.label = name;
    }

}
