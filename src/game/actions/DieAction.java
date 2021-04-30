package game.actions;

import edu.monash.fit2099.engine.*;
import game.items.Corpse;

public class DieAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        map.removeActor(actor);
        // create and add corpse into current location
        Corpse corpse = new Corpse()
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is dead.";
    }
}
