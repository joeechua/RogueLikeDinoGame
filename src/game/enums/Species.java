package game.enums;

/**
 * Collection of possible Dinosaur speecies
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see game.actors.Dinosaur
 */
public enum Species {
    A("ALLOSAUR"),
    B("BRACHIOSAUR"),
    S("STEGOSAUR"),
    P("PTERODACTYL");

    public final String label;

    /**
     * Constructor
     * @param name name of the species
     */
    Species(String name){
        this.label = name;
    }

}
