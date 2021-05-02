package game.items;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;

public class Corpse extends PortableItem {
    private int rotTime;
    /***
     * Constructor.
     */
    public Corpse(Actor actor) {
        super("corpse", '%');
        Dinosaur dino = (Dinosaur) actor;
        this.rotTime = dino.getRotTime();
        capabilities.addCapability(ItemCapabilities.EATEN);
        if(dino instanceof Brachiosaur){
            capabilities.addCapability(ItemCapabilities.BRACH);
        }
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
