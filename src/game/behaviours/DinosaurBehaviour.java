package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actors.Dinosaur;

import java.util.ArrayList;

public class DinosaurBehaviour implements Behaviour{

    private ArrayList<Behaviour> bList = new ArrayList<>();
    /*
    if dino is hungry bList.add(Hungerbehaviour)
    else if dino is female and not pregantn bList.add(BreedingBehaviour)
    else if pregnant bList.add(PregnantBehaviours)
    bList.add(WanderBehaviour)
     */

    public DinosaurBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        return null;
    }
}
