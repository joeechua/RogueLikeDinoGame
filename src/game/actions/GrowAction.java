package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.*;
import game.behaviours.Behaviour;
import game.behaviours.PregnantBehaviour;

/**
 * Grow Action for Actors.
 */

public class GrowAction extends Action {

    /**
     * Perform the Grow action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     * @see Dinosaur
     * @see BabyBrachiosaur
     * @see BabyAllosaur
     * @see BabyStegosaur
     * @see Allosaur
     * @see Brachiosaur
     * @see Stegosaur
     * @see GameMap
     * @see Location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur adultDino = null;
        // create adult dinosaur according to the species
        if(actor instanceof BabyAllosaur){
            adultDino = new Allosaur();
        }
        else if (actor instanceof BabyStegosaur){
            adultDino = new Stegosaur();
        }
        else{
            adultDino = new Brachiosaur();
        }
        // remove the baby dinosaur and add the adult dinosaur at current location
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        currentLocation.addActor(adultDino);
        return menuDescription(adultDino);
    }

    /**
     * Return a descriptive string
     *
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Baby dinosaur has grown into " + actor;
    }
}
