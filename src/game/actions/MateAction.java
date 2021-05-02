package game.actions;


import edu.monash.fit2099.engine.*;
import game.actors.Dinosaur;
import game.behaviours.PregnantBehaviour;
import game.enums.DinosaurCapabilities;
import game.enums.Gender;

public class MateAction extends Action{

    private Dinosaur mate;

    public MateAction(Dinosaur mate) {
        this.mate = mate;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return mate + " is able to mate with " + actor;
    }
}
