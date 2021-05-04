package game.items;

import edu.monash.fit2099.engine.Location;
import game.enums.ItemCapabilities;


/**
 * Class for a fruit object
 * @see PortableItem
 */
public class Fruit extends PortableItem {
    private int rotTime = 15;
    private boolean onTree;


    /**
     * Constructor.
     * @see edu.monash.fit2099.engine.Capabilities
     * @see ItemCapabilities
     */
    public Fruit() {
        super("Fruit", 'f');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }


    /**
     * Tick function that helps the fruit keep track of time and when it should rot
     * @param currentLocation The location of the ground on which we lie.
     * @see Location
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(rotTime == 0){
            currentLocation.removeItem(this);
        }
        else if(!onTree){
            this.rotTime--;
        }

    }

    /**
     * Allows Tree class to set the status of the fruit when fruit drops
     * @param stat status of the fruit (on tree or not)
     */
    public void setOnTree(boolean stat){
        onTree = stat;
    }

}
