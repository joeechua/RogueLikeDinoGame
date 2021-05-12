package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LandingAction;
import game.actors.Dinosaur;
import game.ground.Tree;

public class LandingBehaviour implements Behaviour {

    private Location here;
    private Dinosaur dino;
    @Override
    public Action getAction(Actor actor, GameMap map) {
        here = map.locationOf(actor);
        dino = (Dinosaur) actor;
        Action ret = null;
        if(dino.getIsFlying()){
            for(Exit exits: here.getExits()){
                if(exits.getDestination().getGround() instanceof Tree){
                    Tree t = (Tree) exits.getDestination().getGround();
                    if(!t.hasDinosaur()){
                        return new LandingAction(exits.getDestination(),false);
                    }
                }
                else{
                    ret = new LandingAction(exits.getDestination(),false);
                }
            }
        }
        else{
            for(Exit exits: here.getExits()){
                if(exits.getDestination().getGround() instanceof Tree){
                    Tree t = (Tree) exits.getDestination().getGround();
                    if(!t.hasDinosaur()){
                        return new LandingAction(exits.getDestination(),true);
                    }
                }
            }
            //if there is a tree nearby it will move closer
            return moveCloser();
        }
        //in the case there are no trees around it will return null and it will wander or do something else
        return ret;
    }

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
