package game.actions;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import game.actors.Dinosaur;
import game.ground.Lake;

public class DrinkAction extends Action {
    private Location drinkLocation;

    public DrinkAction(Location location) {
        drinkLocation = location;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return actor + " is drinking from the lake.";
    }
}
