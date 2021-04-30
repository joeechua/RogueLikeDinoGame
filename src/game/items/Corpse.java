package game.items;

import edu.monash.fit2099.engine.Item;

public class Corpse extends Item {
    private int rotTime;
    /***
     * Constructor.
     *  @param rotTime time that corpse stays on the map
     */
    public Corpse(int rotTime) {
        super("corpse", '%', false);
        this.rotTime = rotTime;
    }

}
