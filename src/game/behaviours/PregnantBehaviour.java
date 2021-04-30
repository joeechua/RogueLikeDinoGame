package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.HatchAction;
import game.actions.LayEggAction;
import game.actors.Dinosaur;
import game.enums.DinosaurCapabilities;
import game.items.Egg;

import java.util.ArrayList;

public class PregnantBehaviour implements Behaviour{


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        if (dino.hasCapability(DinosaurCapabilities.PREGNANT)){
            return new LayEggAction();
        }
        return null;
    }

}

