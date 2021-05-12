package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.behaviours.LandingBehaviour;
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
            if(t.hasDinosaur()){
                t.removeDinosaur();
            }
            dino.setFlying(true);
            dino.removeBehaviour(new LandingBehaviour());
            dino.setSquares(5);
            dino.setOnTree(false);
            return menuDescription(actor) + "takes off from a tree.";
        }
        else{
            dino.setFlying(false);
            if(g instanceof Tree){
                Tree t = (Tree) g;
                t.addDinosaur(dino);
                dino.setOnTree(true);
                return menuDescription(actor) + " lands on a tree.";
            }
            dino.setOnTree(false);
            System.out.println("hello???");
            return menuDescription(actor) + " lands on the ground";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "";
    }
}
