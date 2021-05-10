package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkAction extends Action {
    Location drinkLocation;

    public DrinkAction(Location location) {
        drinkLocation = location;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is drinking.";
    }
}
