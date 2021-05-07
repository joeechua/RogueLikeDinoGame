package game.enums;

/**
 * Collection of activities and the eco points associated with them
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see game.actions.HarvestAction
 * @see game.items.Egg
 * @see game.actions.FeedingAction
 */
public enum Points {
    RIPE_FRUIT_PRODUCED(1),
    RIPE_FRUIT_HARVESTED(10),
    FRUIT_FED(10),
    STEGOSAUR_HATCHED(100),
    BRACHIOSAUR_HATCHED(1000),
    ALLOSAUR_HATCHED(1000);

    public final int points;

    /**
     * Constructor
     * @param points points gained when above situation happened
     */
    Points(int points){
        this.points = points;
    }

    /**
     * Allows outside classes to get restricted access to the points of each occurrence.
     * @return a int represents the points gained when above situation happened
     */
    public int getPoints() {
        return this.points;
    }
}
