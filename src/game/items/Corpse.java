package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

public class Corpse extends PortableItem {
    private int rotTime;
    /***
     * Constructor.
     *  @param rotTime time that corpse stays on the map
     */
    public Corpse(int rotTime) {
        super("corpse", '%');
        this.rotTime = rotTime;
        capabilities.addCapability(ItemCapabilities.EATEN);
    }

    //only this tick because anything in the inventory doesn't rot
    public void tick(Location location) {
        super.tick(location);

        rotTime--;
        if(rotTime ==0){
            location.removeItem(this); //this should remove the corpse from the location
        }
    }

}
