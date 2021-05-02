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
        // if dino is female then get pregnant
        if(dino.getGender().equals(Gender.F)){
            dino.addCapability(DinosaurCapabilities.PREGNANT);
            dino.addBehaviour(new PregnantBehaviour());
            return menuDescription(actor);
        }
        return "";
    }

    @Override
    public String menuDescription(Actor actor) {
        return mate + " has impregnated " + actor;
    }
}
