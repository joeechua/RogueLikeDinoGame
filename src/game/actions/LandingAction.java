package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.BabyPterodactyl;
import game.actors.Dinosaur;
import game.behaviours.Behaviour;
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
        if(dino instanceof BabyPterodactyl){
            dino.setDisplayChar('p');
        }
        else{
            dino.setDisplayChar('P');
        }
        if(takeOff){
            Tree t = (Tree) g;
            if(t.hasDinosaur()){
                t.removeDinosaur();
            }
            dino.setFlying(true);
            int i = 0;
            boolean found = false;
            while(!found && i<dino.getBehaviours().size()){
                Behaviour behaviour = dino.getBehaviours().get(i);
                if(behaviour.getClass() == LandingBehaviour.class){
                    found = true;
                }
                i++;
            }
            if(i < dino.getBehaviours().size()){
                dino.getBehaviours().remove(i);
            }
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
            return menuDescription(actor) + " lands on the ground";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "";
    }
}
