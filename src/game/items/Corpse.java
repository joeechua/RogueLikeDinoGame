package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.enums.ItemCapabilities;

/**
 * Class for Corpse object
 * @see PortableItem
 */
public class Corpse extends PortableItem {
    private int rotTime;
    private final Dinosaur originDino;

    /**
     * Constructor.
     * @see edu.monash.fit2099.engine.Capabilities
     * @see Brachiosaur
     * @see Dinosaur
     * @see Actor
     */
    public Corpse(Actor actor) {
        super("corpse", '%');
        Dinosaur dino = (Dinosaur) actor;
        this.rotTime = dino.getRotTime();
        capabilities.addCapability(ItemCapabilities.EATEN);
        if(dino instanceof Brachiosaur){
            capabilities.addCapability(ItemCapabilities.BRACH);
        }
        this.originDino = dino;
    }

    /**
     * Tick function that keeps track of time and when the corpse will rot
     * @param location location of the corpse
     * @see Location
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
}
