package game.behaviours;

import edu.monash.fit2099.engine.*;
import game.actions.HatchAction;
import game.actions.LayEggAction;
import game.actors.Allosaur;
import game.actors.Brachiosaur;
import game.actors.Dinosaur;
import game.actors.Stegosaur;
import game.enums.DinosaurCapabilities;
import game.items.Egg;

import java.util.ArrayList;

public class PregnantBehaviour implements Behaviour{


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;
        Action layEgg= null;
        if (dino.hasCapability(DinosaurCapabilities.PREGNANT)){
            if(dino instanceof Allosaur && dino.getTurns() >= 50){
                layEgg = new LayEggAction();
            }
            else if(dino instanceof Brachiosaur && dino.getTurns() >= 100){
                //so it is more likely to go extinct bcos higher possbility someone eats it
                layEgg = new LayEggAction();
            }
            else if(dino instanceof Stegosaur && dino.getTurns() >= 50){
                layEgg = new LayEggAction();
            }
        }
        return layEgg;
    }

}

