package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LandingTakeOffAction;
import game.actors.Dinosaur;
import game.ground.Tree;
import game.items.Corpse;

/**
 * Class to represent idnosaur landing behaviour
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 1.0
 * @see Behaviour
 */
public class LandingBehaviour implements Behaviour {

    //location of dinosaur
    private Location here;
    //dinosaur executing this behaviour
    private Dinosaur dino;

    /**
     * Gets action that dinosaur should perform if it wants to land
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @see Dinosaur#isFlying()
     * @see Exit#getDestination()
     * @see Tree#hasDinosaur()
     * @see Actor
     * @see GameMap#locationOf(Actor)
     * @see Location#getActor()
     * @see Location#getExits()
     * @see Location#getGround()
     * @see Location#getItems()
     * @see LandingTakeOffAction
     * @return Action type of action to be executed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        here = map.locationOf(actor);
        dino = (Dinosaur) actor;
        if(dino.isFlying()){
            for(Exit exits: here.getExits()){
                //if dino finds a tree
                if(exits.getDestination().getGround() instanceof Tree){
                    Tree t = (Tree) exits.getDestination().getGround();
                    if(!t.hasDinosaur()){
                        return new LandingTakeOffAction(exits.getDestination(),false);
                    }
                }
                //if dino finds a corpse
                else if(exits.getDestination().getItems().size() > 0 && exits.getDestination().getActor() == null){
                    for(Item it: exits.getDestination().getItems()){
                        if(it instanceof Corpse){
                            boolean found = false;
                            //check if there are any dinosaurs around it
                            for(Exit exit: here.getExits()){
                                if(exit.getDestination().getActor() instanceof Dinosaur){
                                    found = true;
                                }
                            }
                            //if no dinosaurs around it, land
                            if(!found)
                                return new LandingTakeOffAction(exits.getDestination(), false);
                        }
                    }
                }
                else{
                    return new LandingTakeOffAction(exits.getDestination(),false);
                }
            }
        }
        else{
            //go to a tree and take off from it
            for(Exit exits: here.getExits()){
                if(exits.getDestination().getGround() instanceof Tree){
                    Tree t = (Tree) exits.getDestination().getGround();
                    if(!t.hasDinosaur()){
                        return new LandingTakeOffAction(exits.getDestination(),true);
                    }
                }
            }
            //if there is a tree nearby it will move closer
            return moveCloser();
        }
        //in the case there are no trees around it will return null and it will wander or do something else
        return null;
    }

    /**
     * Allows dinosaur to move closer to a tree where it can take off
     * @see Exit#getDestination()
     * @see Exit#getName()
     * @see Location#getExits()
     * @see Location#getGround()
     * @see MoveActorAction
     * @see Tree
     * @return MoveActorAction in the direction where tree is OR null if there is no tree around
     */
    private Action moveCloser(){
        for(Exit exits: here.getExits()){
            for(Exit exit: exits.getDestination().getExits()){
                if(exits.getDestination().getGround() instanceof Tree){
                    //move closer to the tree
                    return new MoveActorAction(exits.getDestination(), exits.getName());
                }
            }
        }
        //if nothing then just return null so it will wander or eat
        return null;
    }
}
