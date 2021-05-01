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
        if(dino.getGender() == Gender.F){
            dino.addCapability(DinosaurCapabilities.PREGNANT);
            dino.addBehaviour(new PregnantBehaviour());
        }
        else{
            mate.addCapability(DinosaurCapabilities.PREGNANT);
            mate.addBehaviour(new PregnantBehaviour());
        }
        String result = menuDescription(mate);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return mate + " has impregnated " + actor;
    }
}
