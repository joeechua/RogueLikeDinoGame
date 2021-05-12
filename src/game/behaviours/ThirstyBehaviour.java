package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkAction;
import game.actors.Dinosaur;
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
        return moveCloser(actor, map);
    }

    private Action moveCloser(Actor dino, GameMap map){
        Location here = map.locationOf(dino);
        for(Exit exits: here.getExits()){
            for(Exit exit: exits.getDestination().getExits()){
                if(exit.getDestination().getGround() instanceof Lake){
                    return new MoveActorAction(exits.getDestination(), exits.getName());
                }
            }
        }
        return null;
    }

}
