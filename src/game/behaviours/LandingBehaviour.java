package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.LandingAction;
import game.actors.Dinosaur;
import game.ground.Tree;
import game.items.Corpse;

public class LandingBehaviour implements Behaviour {

    private Location here;
    private Dinosaur dino;
    @Override
    public Action getAction(Actor actor, GameMap map) {
        here = map.locationOf(actor);
        dino = (Dinosaur) actor;
        Action ret = null;
        if(dino.isFlying()){
            for(Exit exits: here.getExits()){
                //if dino lands on tree
                if(exits.getDestination().getGround() instanceof Tree){
                    Tree t = (Tree) exits.getDestination().getGround();
                    if(!t.hasDinosaur()){
                        return new LandingAction(exits.getDestination(),false);
                    }
                }
                //if dino lands on corpse
                else if(exits.getDestination().getItems().size() > 0){
                    for(Item it: exits.getDestination().getItems()){
                        if(it instanceof Corpse){
                            return new LandingAction(exits.getDestination(), false);
                        }
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
        System.out.println("Why nothing de??");
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
