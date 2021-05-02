package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.BabyBrachiosaur;
import game.actors.Brachiosaur;
import game.items.Corpse;

public class DieAction extends Action {
    int rotTime;

    /**
     * Perform the action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        // create and add corpse into current location
        if(actor instanceof Brachiosaur || actor instanceof BabyBrachiosaur){
            rotTime = 40;
        }
        else{
            rotTime = 20;
        }
        Corpse corpse = new Corpse(actor);
        currentLocation.addItem(corpse);
        return menuDescription(actor);
    }

    /**
     * Return a descriptive string
     *
     * @param actor The actor performing the action.
     * @return string that shows on menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is dead.";
    }
}
