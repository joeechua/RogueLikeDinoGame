package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.BabyPterodactyl;
import game.actors.Dinosaur;
import game.behaviours.Behaviour;
import game.behaviours.LandingBehaviour;
import game.ground.Tree;
import game.items.Corpse;

/**
 * Special (Landing and Take Off) Action for Pterodactyl and BabyPterodactyl.
 * @author Chloe Chee Xuan Lin, Chua Jo Ee
 * @version 2.0
 * @see edu.monash.fit2099.engine.Action
 */

public class LandingTakeOffAction extends Action {

    private Location landOrTakeOffLocation;
    private boolean takeOff;

    /**
     * Constructor
     *
     * @param loc The location of landing or take off
     * @param takeOff true if take off else landing
     * @see Location
     */
    public LandingTakeOffAction(Location loc, boolean takeOff){
        landOrTakeOffLocation = loc;
        this.takeOff = takeOff;
    }

    /**
     * Perform the landing or take off action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     * @see Actor
     * @see GameMap
     * @see Dinosaur
     * @see Ground
     * @see Tree
     * @see Corpse
     * @see LandingBehaviour
     * @see Behaviour
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Ground g = landOrTakeOffLocation.getGround();
        Dinosaur dino = (Dinosaur) actor;
        if(dino instanceof BabyPterodactyl){
            dino.setDisplayChar('p');
        }
        else{
            dino.setDisplayChar('P');
        }
        if(takeOff && map.locationOf(actor).getGround() instanceof Tree){
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
            dino.setDisplayChar('ⓟ');
            return menuDescription(actor) + " takes off from a tree.";
        }
        else{
            dino.setFlying(false);
            if(g instanceof Tree){
                Tree t = (Tree) g;
                t.addDinosaur(dino);
                dino.setOnTree(true);
                return menuDescription(actor) + " lands on a tree.";
            }
            for(Item item: landOrTakeOffLocation.getItems()){
                if(item instanceof Corpse){
                    dino.setOnTree(false);
                    return menuDescription(actor) + "lands on a corpse.";
                }
            }
            dino.setOnTree(false);
            return menuDescription(actor) + " lands on the ground";
        }
    }

    /**
     * Return a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "";
    }
}
