package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Allosaur;
import game.actors.Dinosaur;
import game.actors.Pterodactyl;
import game.actors.Stegosaur;
import game.enums.DinosaurCapabilities;
import game.items.AllosaurEgg;
import game.items.BrachiosaurEgg;
import game.items.PterodactylEgg;
import game.items.StegosaurEgg;

/**
 * Lay Egg Action for Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */
public class LayEggAction extends Action {
    /**
     * Perform the LayEgg action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     * @see Dinosaur
     * @see GameMap
     * @see game.items.Egg
     * @see DinosaurCapabilities
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Location birthLocation = map.locationOf(actor);
        // create add egg to the current location and set birthLocation
        if(actor instanceof Allosaur){
            AllosaurEgg egg = new AllosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        else if(actor instanceof Stegosaur){
            StegosaurEgg egg = new StegosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        else if(actor instanceof Pterodactyl){
            PterodactylEgg egg = new PterodactylEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        else {
            BrachiosaurEgg egg = new BrachiosaurEgg();
            egg.setBirthLocation(birthLocation);
            birthLocation.addItem(egg);
        }
        // remove pregnant capability and set pregnancy turns
        dino.removeCapability(DinosaurCapabilities.PREGNANT);
        dino.setPregnancyTurns(0);
        return menuDescription(actor);
    }

    /**
     * Return a descriptive string
     *
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " lays an egg.";
    }
}
