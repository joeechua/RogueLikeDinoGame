package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.DrinkAction;
import game.actors.Dinosaur;
import game.ground.Lake;

/**
 * Class that represents the thirsty behaviour of a Dinosaur
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 1.0
 * @see Behaviour
 */
public class ThirstyBehaviour implements Behaviour{

    /**
     * Returns the appropriate action to be executed in order for dinosaur to drink water
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @see Location#getGround()
     * @see GameMap#locationOf(Actor)
     * @see Exit#getDestination()
     * @see Exit#getName()
     * @see Location#getExits()
     * @see Lake#gotWater()
     * @see DrinkAction
     * @return action to be executed for dinosaur to dirnk water
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location loc = map.locationOf(actor);
        for(Exit exit:loc.getExits()){
            //if around them is a lake they can drink
            if(exit.getDestination().getGround() instanceof Lake){
                Lake lake = (Lake) exit.getDestination().getGround();
                if(lake.gotWater()){
                    return new DrinkAction(exit.getDestination());
                }
            }
        }
        //or else they move closer to a lake if there is one around them
        return moveCloser(actor, map);
    }

    /**
     * Checks and determines which direction dinosaur should move to in order to drink water
     * @param dino Dinosaur that needs to drink water
     * @param map Map that Dinosaur is on
     * @see Exit#getDestination()
     * @see Exit#getName()
     * @see Location#getGround()
     * @see Lake#gotWater()
     * @see GameMap#locationOf(Actor)
     * @return MoveActorAction in the direction that it should move OR null if no lake around
     */
    private Action moveCloser(Actor dino, GameMap map){
        Location here = map.locationOf(dino);
        for(Exit exits: here.getExits()){
            for(Exit exit: exits.getDestination().getExits()){
                //if there is a lake around it and it's not empty, move
                if(exit.getDestination().getGround() instanceof Lake){
                    Lake lake = (Lake) exit.getDestination().getGround();
                    if(lake.gotWater()){
                        return new MoveActorAction(exits.getDestination(), exits.getName());
                    }
                }
            }
        }
        //no lakes around
        return null;
    }

}
