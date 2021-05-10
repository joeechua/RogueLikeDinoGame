package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkAction;
import game.ground.Lake;

public class ThirstyBehaviour implements Behaviour{
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        for(Exit exit:loc.getExits()){
            if(exit.getDestination().getGround() instanceof Lake){
                return new DrinkAction(exit.getDestination());
            }
        }
        return null;
    }
}
