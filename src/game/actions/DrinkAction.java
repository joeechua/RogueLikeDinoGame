package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Dinosaur;
import game.ground.Lake;

/**
 * Drink Action for Actors (Dinosaurs)
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */

public class DrinkAction extends Action {
    private Location drinkLocation;

    /**
     * Constructor
     * @param location The location of lake
     * @see Location
     */
    public DrinkAction(Location location) {
        drinkLocation = location;
    }

    /**
     * Performing the Drink action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     * @see Actor
     * @see GameMap
     * @see Dinosaur
     * @see Lake
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Lake lake = (Lake) drinkLocation.getGround();
        if (dino.getDisplayChar() == 'B'|| dino.getDisplayChar() == 'b'){
            dino.incWaterLevel(80);
        }
        else {
            dino.incWaterLevel(30);
        }
        lake.decWaterSips(1);
        return menuDescription(actor);
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is drinking from the lake.";
    }
}
