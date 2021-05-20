package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Pterodactyl;
import game.actors.Stegosaur;
import game.enums.ItemCapabilities;

/**
 * Class for Corpse object
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see PortableItem
 */
public class Corpse extends PortableItem {
    private int rotTime;
    private final Dinosaur originDino;
    private int removeCount;
    private final int BRACH_CORPSE_COUNT = 10;
    private final int PTERO_CORPSE_COUNT = 3;
    private final int OTHER_CORPSE_COUNT = 5;

    /**
     * Constructor.
     * @param actor the actor who died and used to creates a corpse
     * @see edu.monash.fit2099.engine.Capabilities
     * @see Brachiosaur
     * @see Pterodactyl
     * @see Dinosaur#getRotTime()
     * @see Actor
     */
    public Corpse(Actor actor) {
        super("corpse", '%');
        Dinosaur dino = (Dinosaur) actor;
        this.rotTime = dino.getRotTime();
        capabilities.addCapability(ItemCapabilities.EATEN);
        if(dino instanceof Brachiosaur){
            capabilities.addCapability(ItemCapabilities.BRACH);
            removeCount = 10;
        }
        else if(dino instanceof Pterodactyl){
            removeCount = 3;
        }
        else {
            removeCount = 5;
        }
        this.originDino = dino;
    }

    /**
     * Tick function that keeps track of time and when the corpse will rot
     * @param location location of the corpse
     * @see Location#removeItem(Item)
     */
    //only this tick because anything in the inventory doesn't rot
    public void tick(Location location) {
        super.tick(location);
        rotTime--;
        if(rotTime ==0){
            location.removeItem(this); //this should remove the corpse from the location
        }
    }

    /**
     * Lets outside classes see which Dinosaur this corpse came from
     * @return dinosaur that this corpse came from
     * @see Dinosaur
     */
    public Dinosaur getOriginDino() {
        return originDino;
    }

    /**
     * Get the number of turns this corpse has left on the map after being eaten
     * @return
     */
    public int getRemoveCount(){
        int ret = this.removeCount;
        return ret;
    }

    /**
     * Set the number of turns this corpse has left on the map after being eaten
     * @param remC
     */
    public void setRemoveCount(int remC){
        this.removeCount = remC;
    }

}
