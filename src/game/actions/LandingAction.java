package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.ground.Tree;

public class LandingAction extends Action {

    private Location landingLoc;
    private boolean takeOff;


    public LandingAction(Location loc, boolean fly){
        landingLoc = loc;
        takeOff = fly;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Ground g = landingLoc.getGround();
        Dinosaur dino = (Dinosaur) actor;

        if(takeOff){
            Tree t = (Tree) g;
            t.loseDinosaur();
            dino.setFlying(true);
            return menuDescription(actor) + "takes off from a tree.";
        }
        else{
            dino.setFlying(false);
            if(g instanceof Tree){
                Tree t = (Tree) g;
                t.addDinosaur(dino);
                return menuDescription(actor) + " lands on a tree.";
            }
            return menuDescription(actor) + " lands on the ground";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "";
    }
}
