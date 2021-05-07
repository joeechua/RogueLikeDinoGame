package game.items;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.enums.ItemCapabilities;


/**
 * Class for a fruit object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see PortableItem
 */
public class Fruit extends PortableItem {
    private int rotTime = 15;
    private boolean onGround;


    /**
     * Constructor.
     * @see edu.monash.fit2099.engine.Capabilities
     * @see ItemCapabilities#EATEN
     */
    public Fruit() {
        super("Fruit", 'f');
        capabilities.addCapability(ItemCapabilities.EATEN);
    }


    /**
     * Tick function that helps the fruit keep track of time and when it should rot
     * @param currentLocation The location of the ground on which we lie.
     * @see Location#removeItem(Item)
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        if(rotTime == 0){
            currentLocation.removeItem(this);
        }
        else if(onGround){
            System.out.println("init 15 if got " + rotTime);
            this.rotTime--;
            System.out.println("dec " + rotTime);
        }

    }

    /**
     * Allows Tree class to set the status of the fruit when fruit drops to the ground
     * @param stat status of the fruit (on ground or not)
     */
    public void setOnGround(boolean stat){
        onGround = stat;
    }

}
