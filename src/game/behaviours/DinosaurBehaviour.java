package game.behaviours;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.actions.DieAction;
import game.actors.Dinosaur;

import java.util.ArrayList;

public class DinosaurBehaviour implements Behaviour{


    public DinosaurBehaviour() {
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dino = (Dinosaur) actor;

        if(dino.isHungry()){
            System.out.println(dino.getName() + " is hungry!");
            HungerBehaviour hB = new HungerBehaviour();
            return hB.getAction(actor, map);
        }
        else if(dino.isPregnant()){
            PregnantBehaviour pB = new PregnantBehaviour();
            return pB.getAction(actor, map);
        }
        else{
            BreedBehaviour bB = new BreedBehaviour();
            Action a = bB.getAction(actor, map);
            if(a == null){
                WanderBehaviour wB = new WanderBehaviour();
                return wB.getAction(actor, map);
            }
            else{
                return a;
            }
        }

    }
}
