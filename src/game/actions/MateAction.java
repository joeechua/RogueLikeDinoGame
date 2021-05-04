package game.actions;

import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.behaviours.PregnantBehaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;

/**
 * Mate Action for Actors
 */
public class MateAction extends Action{

    private Dinosaur mate;

    /**
     * Constructor
     * @param mate dinosaur target mate
     */
    public MateAction(Dinosaur mate) {
        this.mate = mate;
    }

    /**
     * Perform the Mate action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened to the actor
     * @see Dinosaur
     * @see Gender
     * @see DinosaurCapabilities
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        if(dino.getGender().equals(Gender.F) && mate.getGender().equals(Gender.M)){
            dino.addCapability(DinosaurCapabilities.PREGNANT);
            dino.addBehaviour(new PregnantBehaviour());
            return menuDescription(actor);
        }
        else if(dino.getGender().equals(Gender.M) && mate.getGender().equals(Gender.F)){
            mate.addCapability(DinosaurCapabilities.PREGNANT);
            mate.addBehaviour(new PregnantBehaviour());
            return menuDescription(actor);
        }
        return "";
    }

    /**
     * Return a descriptive string
     * @param actor The actor performing the action.
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return mate + " is able to mate with " + actor;
    }
}
