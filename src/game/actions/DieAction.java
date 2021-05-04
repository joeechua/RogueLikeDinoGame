package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.BabyBrachiosaur;
import game.actors.Brachiosaur;
import game.items.Corpse;

/**
 * Die Action for Actors.
 */
public class DieAction extends Action {

    /**
     * Perform the Die action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        // remove actor from current location
        map.removeActor(actor);
        // create and add corpse into current location
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
