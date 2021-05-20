package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.Corpse;

/**
 * Die Action for Actors.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 3.0
 * @see edu.monash.fit2099.engine.Action
 */
public class DieAction extends Action {

    /**
     * Perform the Die action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see Actor
     * @see GameMap
     * @see Location
     * @see Corpse
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
